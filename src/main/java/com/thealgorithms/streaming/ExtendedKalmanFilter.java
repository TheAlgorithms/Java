package com.thealgorithms.streaming;

import com.thealgorithms.matrix.InverseOfMatrix;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * The <b>extended Kalman filter</b>: the Kalman filter for a process that does not move in straight
 * lines.
 *
 * <p>The ordinary Kalman filter is optimal, but only for a linear model with Gaussian noise. Most
 * things worth tracking are not linear: a bearing is an arctangent of the state, a range is a square
 * root of it, an orbit is not a straight line. The extended filter keeps the whole machinery and
 * replaces the one step that needs linearity. The state is still pushed forward through the real,
 * nonlinear functions, but the <i>covariance</i> is pushed through their Jacobians, evaluated afresh
 * at the current estimate:
 *
 * <pre>
 * predict:  x &lt;- f(x)                      P &lt;- F P F' + Q,   F = df/dx at x
 * update:   y  = z - h(x)                  S  = H P H' + R,   H = dh/dx at x
 *           K  = P H' S^-1                 x &lt;- x + K y
 *                                          P &lt;- (I - K H) P (I - K H)' + K R K'
 * </pre>
 *
 * <p>The covariance update is written in Joseph form rather than as the shorter
 * {@code (I - K H) P}. The two are equal in exact arithmetic, but the short one subtracts two nearly
 * equal matrices and, after a few thousand steps of rounding, can leave a covariance that is no
 * longer symmetric or has a negative variance on its diagonal, at which point the filter quietly
 * stops being a filter. The Joseph form is a sum of two products of the form {@code A P A'}, so it
 * stays symmetric by construction and costs one extra multiplication.
 *
 * <p>What the linearisation costs is worth stating plainly: the filter is no longer optimal and no
 * longer guaranteed to converge. A Jacobian is a good description of the model only near the point
 * it was taken at, so a large error, a sharp nonlinearity or too long a step between updates can all
 * make the estimate diverge, and the covariance will not warn about it, because the covariance was
 * computed from the same linearisation. When that happens the answer is smaller steps, a better
 * initial estimate, or the unscented variant, which samples the nonlinear function instead of
 * differentiating it.
 *
 * <p>For a one dimensional linear process {@link KalmanFilter} is smaller, faster and exactly
 * equivalent; this class earns its place once the state is a vector or the model bends.
 *
 * <h2>Usage</h2>
 *
 * <pre>{@code
 * // a target moving at constant velocity, watched by a sensor that only measures range
 * ExtendedKalmanFilter filter = new ExtendedKalmanFilter(new double[] {0.0, 1.0}, identity);
 * filter.predict(new double[][] {{1.0, dt}, {0.0, 1.0}}, processNoise);
 * filter.update(new double[] {range},
 *               state -> new double[] {Math.hypot(state[0], offset)},
 *               state -> new double[][] {{state[0] / Math.hypot(state[0], offset), 0.0}},
 *               measurementNoise);
 * }</pre>
 *
 * <p>A step costs O(n^3) in the size of the state, dominated by the matrix products, plus the
 * inversion of the m by m innovation covariance, which is done by
 * {@link InverseOfMatrix#invert(double[][])} rather than by another copy of Gaussian elimination.
 * This class is not thread-safe.
 *
 * @see KalmanFilter
 * @see <a href="https://en.wikipedia.org/wiki/Extended_Kalman_filter">Extended Kalman filter</a>
 */
public final class ExtendedKalmanFilter {

    private final int stateSize;

    private double[] state;
    private double[][] covariance;
    private double[] innovation;
    private double[][] gain;
    private long predictions;
    private long corrections;

    /**
     * Creates a filter.
     *
     * @param initialState the first guess at the state, copied
     * @param initialCovariance how uncertain that guess is, a square matrix of the same size, copied
     * @throws IllegalArgumentException if the state is empty, if the covariance is not square and of
     *     the same size, or if either holds a value that is not finite
     * @throws NullPointerException if an argument is {@code null}
     */
    public ExtendedKalmanFilter(double[] initialState, double[][] initialCovariance) {
        if (initialState.length == 0) {
            throw new IllegalArgumentException("The state must not be empty");
        }
        this.stateSize = initialState.length;
        requireVector(initialState, stateSize, "initial state");
        requireMatrix(initialCovariance, stateSize, stateSize, "initial covariance");
        this.state = initialState.clone();
        this.covariance = copy(initialCovariance);
        this.innovation = new double[0];
        this.gain = new double[0][0];
    }

    /**
     * Pushes the estimate forward through a nonlinear model.
     *
     * @param transition the model itself, which maps a state to the state one step later
     * @param jacobian the derivative of that model, evaluated by the filter at the current estimate
     * @param processNoise how much the model is trusted, a square matrix of the size of the state
     * @return a copy of the predicted state
     * @throws IllegalArgumentException if the model returns the wrong shape or a value that is not
     *     finite, or if the process noise has the wrong shape
     * @throws NullPointerException if an argument is {@code null}
     */
    public double[] predict(UnaryOperator<double[]> transition, Function<double[], double[][]> jacobian, double[][] processNoise) {
        requireMatrix(processNoise, stateSize, stateSize, "process noise");

        double[][] derivative = jacobian.apply(state.clone());
        requireMatrix(derivative, stateSize, stateSize, "transition jacobian");
        double[] predicted = transition.apply(state.clone());
        requireVector(predicted, stateSize, "predicted state");

        state = predicted;
        covariance = add(multiply(multiply(derivative, covariance), transpose(derivative)), processNoise);
        predictions++;
        return state.clone();
    }

    /**
     * Pushes the estimate forward through a linear model, which is the plain Kalman prediction.
     *
     * @param transition the transition matrix, a square matrix of the size of the state
     * @param processNoise how much the model is trusted, a square matrix of the size of the state
     * @return a copy of the predicted state
     * @throws IllegalArgumentException if either matrix has the wrong shape or holds a value that is
     *     not finite
     * @throws NullPointerException if an argument is {@code null}
     */
    public double[] predict(double[][] transition, double[][] processNoise) {
        requireMatrix(transition, stateSize, stateSize, "transition");
        return predict(current -> multiply(transition, current), current -> transition, processNoise);
    }

    /**
     * Folds one nonlinear measurement into the estimate.
     *
     * @param measurement what the sensor reported
     * @param model the measurement model, which maps a state to what the sensor would report
     * @param jacobian the derivative of that model, evaluated by the filter at the current estimate
     * @param measurementNoise how much the sensor is trusted, a square matrix of the size of the
     *     measurement
     * @return a copy of the corrected state
     * @throws IllegalArgumentException if the measurement is empty, if the model returns the wrong
     *     shape or a value that is not finite, or if the noise has the wrong shape
     * @throws ArithmeticException if the innovation covariance cannot be inverted, which means the
     *     measurement carries no information the filter can use
     * @throws NullPointerException if an argument is {@code null}
     */
    public double[] update(double[] measurement, Function<double[], double[]> model, Function<double[], double[][]> jacobian, double[][] measurementNoise) {
        if (measurement.length == 0) {
            throw new IllegalArgumentException("The measurement must not be empty");
        }
        int measurementSize = measurement.length;
        requireVector(measurement, measurementSize, "measurement");
        requireMatrix(measurementNoise, measurementSize, measurementSize, "measurement noise");

        double[] expected = model.apply(state.clone());
        requireVector(expected, measurementSize, "expected measurement");
        double[][] derivative = jacobian.apply(state.clone());
        requireMatrix(derivative, measurementSize, stateSize, "measurement jacobian");

        double[] residual = new double[measurementSize];
        for (int i = 0; i < measurementSize; i++) {
            residual[i] = measurement[i] - expected[i];
        }

        double[][] transposed = transpose(derivative);
        double[][] crossCovariance = multiply(covariance, transposed);
        double[][] innovationCovariance = add(multiply(derivative, crossCovariance), measurementNoise);
        double[][] inverted = InverseOfMatrix.invert(copy(innovationCovariance));
        requireFiniteMatrix(inverted);

        gain = multiply(crossCovariance, inverted);
        for (int i = 0; i < stateSize; i++) {
            double correction = 0.0;
            for (int j = 0; j < measurementSize; j++) {
                correction += gain[i][j] * residual[j];
            }
            state[i] += correction;
        }

        // Joseph form: symmetric by construction, unlike the shorter (I - K H) P
        double[][] spread = subtract(identity(stateSize), multiply(gain, derivative));
        covariance = add(multiply(multiply(spread, covariance), transpose(spread)), multiply(multiply(gain, measurementNoise), transpose(gain)));

        innovation = residual;
        corrections++;
        return state.clone();
    }

    /**
     * Folds one linear measurement into the estimate, which is the plain Kalman correction.
     *
     * @param measurement what the sensor reported
     * @param model the measurement matrix, with one row per measurement and one column per state
     * @param measurementNoise how much the sensor is trusted, a square matrix of the size of the
     *     measurement
     * @return a copy of the corrected state
     * @throws IllegalArgumentException if a shape is wrong or a value is not finite
     * @throws ArithmeticException if the innovation covariance cannot be inverted
     * @throws NullPointerException if an argument is {@code null}
     */
    public double[] update(double[] measurement, double[][] model, double[][] measurementNoise) {
        requireMatrix(model, measurement.length, stateSize, "measurement model");
        return update(measurement, current -> multiply(model, current), current -> model, measurementNoise);
    }

    /**
     * Returns the current estimate.
     *
     * @return a copy of the state
     */
    public double[] state() {
        return state.clone();
    }

    /**
     * Returns how uncertain the current estimate is.
     *
     * @return a copy of the covariance
     */
    public double[][] covariance() {
        return copy(covariance);
    }

    /**
     * Returns the variance of one component of the state, which is what a caller usually wants out of
     * the covariance.
     *
     * @param component which component, from zero to the size of the state
     * @return the variance of that component
     * @throws IllegalArgumentException if {@code component} is outside the state
     */
    public double variance(int component) {
        if (component < 0 || component >= stateSize) {
            throw new IllegalArgumentException("The component must lie in [0, " + (stateSize - 1) + "], but was " + component);
        }
        return covariance[component][component];
    }

    /**
     * Returns the last innovation, that is how far the sensor was from what the filter expected. A
     * run of innovations that are not centred on zero means the model no longer describes the
     * process.
     *
     * @return a copy of the last residual, empty before the first update
     */
    public double[] lastInnovation() {
        return innovation.clone();
    }

    /**
     * Returns the gain the last update applied.
     *
     * @return a copy of the last Kalman gain, empty before the first update
     */
    public double[][] lastGain() {
        return copy(gain);
    }

    /**
     * Returns the size of the state.
     *
     * @return how many components the state holds
     */
    public int stateSize() {
        return stateSize;
    }

    /**
     * Returns how many predictions have been made since the last reset.
     *
     * @return the prediction count
     */
    public long predictionCount() {
        return predictions;
    }

    /**
     * Returns how many measurements have been folded in since the last reset.
     *
     * @return the update count
     */
    public long updateCount() {
        return corrections;
    }

    /**
     * Restarts the filter from a known estimate.
     *
     * @param newState the state to carry on from, copied
     * @param newCovariance how uncertain it is, copied
     * @throws IllegalArgumentException if a shape is wrong or a value is not finite
     * @throws NullPointerException if an argument is {@code null}
     */
    public void reset(double[] newState, double[][] newCovariance) {
        requireVector(newState, stateSize, "state");
        requireMatrix(newCovariance, stateSize, stateSize, "covariance");
        state = newState.clone();
        covariance = copy(newCovariance);
        innovation = new double[0];
        gain = new double[0][0];
        predictions = 0;
        corrections = 0;
    }

    @Override
    public String toString() {
        return "ExtendedKalmanFilter{state=" + Arrays.toString(state) + ", predictions=" + predictions + ", updates=" + corrections + "}";
    }

    private static void requireVector(double[] vector, int size, String name) {
        if (vector.length != size) {
            throw new IllegalArgumentException("The " + name + " must hold " + size + " values, but held " + vector.length);
        }
        for (double value : vector) {
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("The " + name + " must be finite, but held " + value);
            }
        }
    }

    private static void requireMatrix(double[][] matrix, int rows, int columns, String name) {
        if (matrix.length != rows) {
            throw new IllegalArgumentException("The " + name + " must have " + rows + " rows, but had " + matrix.length);
        }
        for (double[] row : matrix) {
            if (row.length != columns) {
                throw new IllegalArgumentException("The " + name + " must have " + columns + " columns, but a row had " + row.length);
            }
            for (double value : row) {
                if (!Double.isFinite(value)) {
                    throw new IllegalArgumentException("The " + name + " must be finite, but held " + value);
                }
            }
        }
    }

    private static void requireFiniteMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double value : row) {
                if (!Double.isFinite(value)) {
                    throw new ArithmeticException("The innovation covariance is singular, so the gain is undefined");
                }
            }
        }
    }

    private static double[][] copy(double[][] matrix) {
        double[][] copied = new double[matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
            copied[i] = matrix[i].clone();
        }
        return copied;
    }

    private static double[][] identity(int size) {
        double[][] result = new double[size][size];
        for (int i = 0; i < size; i++) {
            result[i][i] = 1.0;
        }
        return result;
    }

    private static double[][] transpose(double[][] matrix) {
        double[][] result = new double[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result[j][i] = matrix[i][j];
            }
        }
        return result;
    }

    private static double[][] multiply(double[][] left, double[][] right) {
        double[][] result = new double[left.length][right[0].length];
        for (int i = 0; i < left.length; i++) {
            for (int k = 0; k < right.length; k++) {
                double factor = left[i][k];
                for (int j = 0; j < right[0].length; j++) {
                    result[i][j] += factor * right[k][j];
                }
            }
        }
        return result;
    }

    private static double[] multiply(double[][] matrix, double[] vector) {
        double[] result = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            double sum = 0.0;
            for (int j = 0; j < vector.length; j++) {
                sum += matrix[i][j] * vector[j];
            }
            result[i] = sum;
        }
        return result;
    }

    private static double[][] add(double[][] left, double[][] right) {
        double[][] result = new double[left.length][left[0].length];
        for (int i = 0; i < left.length; i++) {
            for (int j = 0; j < left[i].length; j++) {
                result[i][j] = left[i][j] + right[i][j];
            }
        }
        return result;
    }

    private static double[][] subtract(double[][] left, double[][] right) {
        double[][] result = new double[left.length][left[0].length];
        for (int i = 0; i < left.length; i++) {
            for (int j = 0; j < left[i].length; j++) {
                result[i][j] = left[i][j] - right[i][j];
            }
        }
        return result;
    }
}
