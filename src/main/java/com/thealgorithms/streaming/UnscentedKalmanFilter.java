package com.thealgorithms.streaming;

import com.thealgorithms.matrix.InverseOfMatrix;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * The <b>unscented Kalman filter</b>: a Kalman filter for a nonlinear model that samples the model
 * instead of differentiating it.
 *
 * <p>The extended filter pushes the covariance through a Jacobian, which is a straight line drawn at
 * one point. That needs the model to be differentiable, needs somebody to write the derivative, and
 * throws away everything the model does beyond first order. The unscented filter takes the opposite
 * route. It picks a small, deterministic set of <i>sigma points</i> whose mean and covariance are
 * exactly those of the current estimate, sends every one of them through the real model, and reads
 * the new mean and covariance off where they land:
 *
 * <pre>
 * lambda = alpha^2 (n + kappa) - n
 * X_0 = x,   X_i = x + [sqrt((n + lambda) P)]_i,   X_{i+n} = x - [sqrt((n + lambda) P)]_i
 * Wm_0 = lambda / (n + lambda),   Wc_0 = Wm_0 + 1 - alpha^2 + beta,   W_i = 1 / (2 (n + lambda))
 * </pre>
 *
 * <p>The square root is the Cholesky factor, so a step costs {@code 2n + 1} evaluations of the model
 * and one factorisation, about what the extended filter costs, with no Jacobian anywhere. The payoff
 * is accuracy: the transformed mean and covariance are right to second order for any model, where
 * the extended filter is right to first. For {@code x^2} with {@code x} normally distributed, the
 * extended filter predicts a mean of {@code mu^2} and misses the {@code sigma^2} that the spread
 * itself contributes; the unscented one gets the mean exactly, and with the defaults it gets the
 * variance {@code 4 mu^2 sigma^2 + 2 sigma^4} exactly as well. For a linear model it reduces to the
 * ordinary Kalman filter, step for step.
 *
 * <p>The three parameters are left at {@code alpha = 1}, {@code beta = 2}, {@code kappa = 0}. The
 * value {@code alpha = 1e-3} often quoted from the literature puts the sigma points almost on top of
 * the mean and compensates with a central weight of about minus a million, which is exact on paper
 * and a loss of six digits in floating point. {@code beta = 2} is the optimal choice for a Gaussian
 * prior and is what makes the variance of the quadratic above come out exact.
 *
 * <p>After an update the covariance {@code P - K S K'} is symmetrised explicitly. The subtraction is
 * symmetric in exact arithmetic but not after rounding, and a covariance that has drifted out of
 * symmetry eventually fails the Cholesky factorisation of the next step. When that factorisation does
 * fail, because the covariance has stopped being positive definite, the step is refused with an
 * {@link ArithmeticException} rather than carried on with a square root that does not exist.
 *
 * <h2>Usage</h2>
 *
 * <pre>{@code
 * UnscentedKalmanFilter filter = new UnscentedKalmanFilter(new double[] {0.0, 1.0}, identity);
 * filter.predict(state -> new double[] {state[0] + dt * state[1], state[1]}, processNoise);
 * filter.update(new double[] {range}, state -> new double[] {Math.hypot(state[0], offset)}, measurementNoise);
 * }</pre>
 *
 * <p>A step costs O(n^3) for the factorisation plus {@code 2n + 1} calls of the model. The inversion
 * of the innovation covariance reuses {@link InverseOfMatrix#invert(double[][])}. This class is not
 * thread-safe.
 *
 * @see ExtendedKalmanFilter
 * @see KalmanFilter
 * @see <a href="https://en.wikipedia.org/wiki/Kalman_filter#Unscented_Kalman_filter">Unscented Kalman filter</a>
 */
public final class UnscentedKalmanFilter {

    /** Spread of the sigma points used when none is given. */
    public static final double DEFAULT_ALPHA = 1.0;

    /** Prior knowledge of the distribution used when none is given, optimal for a Gaussian. */
    public static final double DEFAULT_BETA = 2.0;

    /** Secondary scaling used when none is given. */
    public static final double DEFAULT_KAPPA = 0.0;

    private final int stateSize;
    private final double alpha;
    private final double beta;
    private final double kappa;
    private final double lambda;
    private final double[] meanWeights;
    private final double[] covarianceWeights;

    private double[] state;
    private double[][] covariance;
    private double[] innovation;
    private long predictions;
    private long corrections;

    /**
     * Creates a filter with the default sigma point parameters.
     *
     * @param initialState the first guess at the state, copied
     * @param initialCovariance how uncertain that guess is, copied
     * @throws IllegalArgumentException if the state is empty, if the covariance is not square and of
     *     the same size, or if either holds a value that is not finite
     * @throws NullPointerException if an argument is {@code null}
     */
    public UnscentedKalmanFilter(double[] initialState, double[][] initialCovariance) {
        this(initialState, initialCovariance, DEFAULT_ALPHA, DEFAULT_BETA, DEFAULT_KAPPA);
    }

    /**
     * Creates a filter.
     *
     * @param initialState the first guess at the state, copied
     * @param initialCovariance how uncertain that guess is, copied
     * @param alpha how far the sigma points spread from the mean, strictly positive
     * @param beta what is known about the shape of the distribution, not negative; two is optimal for
     *     a Gaussian
     * @param kappa a secondary scaling, chosen so that {@code n + kappa} is strictly positive
     * @throws IllegalArgumentException if a shape is wrong, a value is not finite, or the parameters do
     *     not leave {@code n + lambda} strictly positive
     * @throws NullPointerException if an argument is {@code null}
     */
    public UnscentedKalmanFilter(double[] initialState, double[][] initialCovariance, double alpha, double beta, double kappa) {
        if (initialState.length == 0) {
            throw new IllegalArgumentException("The state must not be empty");
        }
        if (!(alpha > 0.0) || !Double.isFinite(alpha)) {
            throw new IllegalArgumentException("The alpha must be finite and strictly positive, but was " + alpha);
        }
        if (!(beta >= 0.0) || !Double.isFinite(beta)) {
            throw new IllegalArgumentException("The beta must be finite and not negative, but was " + beta);
        }
        if (!Double.isFinite(kappa)) {
            throw new IllegalArgumentException("The kappa must be finite, but was " + kappa);
        }

        this.stateSize = initialState.length;
        requireVector(initialState, stateSize, "initial state");
        requireMatrix(initialCovariance, stateSize, stateSize, "initial covariance");

        this.alpha = alpha;
        this.beta = beta;
        this.kappa = kappa;
        this.lambda = alpha * alpha * (stateSize + kappa) - stateSize;
        double scale = stateSize + lambda;
        if (!(scale > 0.0)) {
            throw new IllegalArgumentException("The parameters leave n + lambda at " + scale + ", but it must be strictly positive");
        }

        int points = 2 * stateSize + 1;
        this.meanWeights = new double[points];
        this.covarianceWeights = new double[points];
        meanWeights[0] = lambda / scale;
        covarianceWeights[0] = meanWeights[0] + 1.0 - alpha * alpha + beta;
        for (int i = 1; i < points; i++) {
            meanWeights[i] = 1.0 / (2.0 * scale);
            covarianceWeights[i] = meanWeights[i];
        }

        this.state = initialState.clone();
        this.covariance = copy(initialCovariance);
        this.innovation = new double[0];
    }

    /**
     * Pushes the estimate forward through the model.
     *
     * @param transition the model, which maps a state to the state one step later
     * @param processNoise how much the model is trusted, a square matrix of the size of the state
     * @return a copy of the predicted state
     * @throws IllegalArgumentException if the model returns the wrong shape or a value that is not
     *     finite, or if the process noise has the wrong shape
     * @throws ArithmeticException if the covariance is no longer positive definite
     * @throws NullPointerException if an argument is {@code null}
     */
    public double[] predict(UnaryOperator<double[]> transition, double[][] processNoise) {
        requireMatrix(processNoise, stateSize, stateSize, "process noise");

        double[][] points = sigmaPoints();
        double[][] propagated = new double[points.length][];
        for (int i = 0; i < points.length; i++) {
            propagated[i] = transition.apply(points[i]);
            requireVector(propagated[i], stateSize, "propagated sigma point");
        }

        double[] mean = weightedMean(propagated);
        double[][] spread = add(weightedCovariance(propagated, mean, propagated, mean), processNoise);

        state = mean;
        covariance = symmetrised(spread);
        predictions++;
        return state.clone();
    }

    /**
     * Folds one measurement into the estimate.
     *
     * @param measurement what the sensor reported
     * @param model the measurement model, which maps a state to what the sensor would report
     * @param measurementNoise how much the sensor is trusted, a square matrix of the size of the
     *     measurement
     * @return a copy of the corrected state
     * @throws IllegalArgumentException if the measurement is empty, if the model returns the wrong
     *     shape or a value that is not finite, or if the noise has the wrong shape
     * @throws ArithmeticException if the covariance is no longer positive definite, or if the
     *     innovation covariance cannot be inverted
     * @throws NullPointerException if an argument is {@code null}
     */
    public double[] update(double[] measurement, Function<double[], double[]> model, double[][] measurementNoise) {
        if (measurement.length == 0) {
            throw new IllegalArgumentException("The measurement must not be empty");
        }
        int measurementSize = measurement.length;
        requireVector(measurement, measurementSize, "measurement");
        requireMatrix(measurementNoise, measurementSize, measurementSize, "measurement noise");

        double[][] points = sigmaPoints();
        double[][] observed = new double[points.length][];
        for (int i = 0; i < points.length; i++) {
            observed[i] = model.apply(points[i].clone());
            requireVector(observed[i], measurementSize, "expected measurement");
        }

        double[] expected = weightedMean(observed);
        double[][] innovationCovariance = add(weightedCovariance(observed, expected, observed, expected), measurementNoise);
        double[][] crossCovariance = weightedCovariance(points, state, observed, expected);

        double[][] inverted = InverseOfMatrix.invert(copy(innovationCovariance));
        requireFiniteMatrix(inverted, "The innovation covariance is singular, so the gain is undefined");
        double[][] gain = multiply(crossCovariance, inverted);

        double[] residual = new double[measurementSize];
        for (int i = 0; i < measurementSize; i++) {
            residual[i] = measurement[i] - expected[i];
        }
        for (int i = 0; i < stateSize; i++) {
            double correction = 0.0;
            for (int j = 0; j < measurementSize; j++) {
                correction += gain[i][j] * residual[j];
            }
            state[i] += correction;
        }

        double[][] reduction = multiply(multiply(gain, innovationCovariance), transpose(gain));
        covariance = symmetrised(subtract(covariance, reduction));
        innovation = residual;
        corrections++;
        return state.clone();
    }

    /**
     * Returns the sigma points of the current estimate, whose weighted mean and covariance are the
     * estimate and its covariance.
     *
     * @return {@code 2n + 1} points, the mean first, each a fresh array
     * @throws ArithmeticException if the covariance is no longer positive definite
     */
    public double[][] sigmaPoints() {
        double[][] root = cholesky(scaled(covariance, stateSize + lambda));
        double[][] points = new double[2 * stateSize + 1][];
        points[0] = state.clone();
        for (int column = 0; column < stateSize; column++) {
            double[] plus = state.clone();
            double[] minus = state.clone();
            for (int row = 0; row < stateSize; row++) {
                plus[row] += root[row][column];
                minus[row] -= root[row][column];
            }
            points[column + 1] = plus;
            points[column + 1 + stateSize] = minus;
        }
        return points;
    }

    /**
     * Returns the weights the sigma points carry when the mean is formed.
     *
     * @return a copy of the mean weights, which sum to one
     */
    public double[] meanWeights() {
        return meanWeights.clone();
    }

    /**
     * Returns the weights the sigma points carry when the covariance is formed.
     *
     * @return a copy of the covariance weights
     */
    public double[] covarianceWeights() {
        return covarianceWeights.clone();
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
     * Returns the variance of one component of the state.
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
     * Returns the last innovation, how far the sensor was from what the filter expected.
     *
     * @return a copy of the last residual, empty before the first update
     */
    public double[] lastInnovation() {
        return innovation.clone();
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
     * Returns the spread parameter.
     *
     * @return alpha
     */
    public double alpha() {
        return alpha;
    }

    /**
     * Returns the distribution parameter.
     *
     * @return beta
     */
    public double beta() {
        return beta;
    }

    /**
     * Returns the secondary scaling parameter.
     *
     * @return kappa
     */
    public double kappa() {
        return kappa;
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
        predictions = 0;
        corrections = 0;
    }

    @Override
    public String toString() {
        return "UnscentedKalmanFilter{state=" + Arrays.toString(state) + ", alpha=" + alpha + ", beta=" + beta + ", kappa=" + kappa + ", predictions=" + predictions + ", updates=" + corrections + "}";
    }

    private double[] weightedMean(double[][] points) {
        double[] mean = new double[points[0].length];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < mean.length; j++) {
                mean[j] += meanWeights[i] * points[i][j];
            }
        }
        return mean;
    }

    private double[][] weightedCovariance(double[][] first, double[] firstMean, double[][] second, double[] secondMean) {
        double[][] result = new double[firstMean.length][secondMean.length];
        for (int i = 0; i < first.length; i++) {
            for (int row = 0; row < firstMean.length; row++) {
                double left = covarianceWeights[i] * (first[i][row] - firstMean[row]);
                for (int column = 0; column < secondMean.length; column++) {
                    result[row][column] += left * (second[i][column] - secondMean[column]);
                }
            }
        }
        return result;
    }

    /**
     * Returns the lower triangular Cholesky factor {@code L} of a symmetric positive definite matrix,
     * with {@code L L' = matrix}.
     *
     * @param matrix the matrix to factorise
     * @return its Cholesky factor
     * @throws ArithmeticException if the matrix is not positive definite
     */
    private static double[][] cholesky(double[][] matrix) {
        int size = matrix.length;
        double[][] factor = new double[size][size];
        for (int row = 0; row < size; row++) {
            for (int column = 0; column <= row; column++) {
                double sum = matrix[row][column];
                for (int k = 0; k < column; k++) {
                    sum -= factor[row][k] * factor[column][k];
                }
                if (row == column) {
                    if (!(sum > 0.0)) {
                        throw new ArithmeticException("The covariance is not positive definite, so it has no square root to spread the sigma points with");
                    }
                    factor[row][row] = Math.sqrt(sum);
                } else {
                    factor[row][column] = sum / factor[column][column];
                }
            }
        }
        return factor;
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

    private static void requireFiniteMatrix(double[][] matrix, String message) {
        for (double[] row : matrix) {
            for (double value : row) {
                if (!Double.isFinite(value)) {
                    throw new ArithmeticException(message);
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

    private static double[][] scaled(double[][] matrix, double factor) {
        double[][] result = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result[i][j] = factor * matrix[i][j];
            }
        }
        return result;
    }

    private static double[][] symmetrised(double[][] matrix) {
        double[][] result = new double[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                result[i][j] = 0.5 * (matrix[i][j] + matrix[j][i]);
            }
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
