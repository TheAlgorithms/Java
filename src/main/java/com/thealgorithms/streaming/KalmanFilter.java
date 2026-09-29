package com.thealgorithms.streaming;

/**
 * A scalar (one dimensional) <b>Kalman filter</b>: the optimal way to track a quantity that drifts
 * slowly while every measurement of it is noisy.
 *
 * <p>The filter carries two numbers: the current estimate {@code x} and how much it distrusts that
 * estimate, the error variance {@code p}. Each step has two halves.
 *
 * <pre>
 * predict:  x &lt;- x + u            p &lt;- p + q
 * update:   k &lt;- p / (p + r)      x &lt;- x + k * (z - x)      p &lt;- (1 - k) * p
 * </pre>
 *
 * <p>where {@code q} is the process noise (how much the tracked quantity is expected to wander
 * between two steps), {@code r} the measurement noise, {@code z} the measurement and {@code k} the
 * Kalman gain. The gain is the whole story: it is the share of the measurement that gets believed.
 * When the filter is unsure ({@code p} large) or the sensor is good ({@code r} small), {@code k}
 * approaches 1 and the filter follows the sensor; in the opposite case it clings to its own
 * prediction. Nothing tunes this by hand, the variances do it.
 *
 * <h2>Sensor fusion</h2>
 *
 * <p>Fusing several sensors is not a separate algorithm: it is what happens when the same estimate
 * is corrected once per sensor, each with its own noise level. A cheap sensor with a large {@code r}
 * nudges the estimate a little, a precise one pulls it a lot, and the result is exactly the
 * inverse-variance weighted combination that {@link #fuse(double[], double[])} computes in closed
 * form:
 *
 * <pre>{@code
 * KalmanFilter filter = new KalmanFilter(startingHeight, 1.0, 0.01, 1.0);
 * for (int t = 0; t < steps; t++) {
 *     filter.predict();
 *     filter.update(barometer[t], barometerVariance);
 *     filter.update(gps[t], gpsVariance);          // second sensor, same estimate
 *     double height = filter.estimate();
 * }
 * }</pre>
 *
 * <p>Both steps run in O(1) time and memory. This class is not thread-safe.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Kalman_filter">Kalman filter</a>
 */
public final class KalmanFilter {

    private final double processNoise;
    private final double measurementNoise;

    private double estimate;
    private double errorCovariance;
    private double lastGain;

    private final double initialEstimate;
    private final double initialErrorCovariance;

    /**
     * Creates a filter.
     *
     * @param initialEstimate the starting guess for the tracked quantity
     * @param initialErrorCovariance how uncertain that guess is; a large value makes the filter trust
     *     the first measurements almost completely
     * @param processNoise variance added on every {@link #predict()}, i.e. how fast the quantity is
     *     expected to change on its own
     * @param measurementNoise default variance of a measurement, used by {@link #update(double)}
     * @throws IllegalArgumentException if any argument is not finite, or if a variance is negative,
     *     or if {@code measurementNoise} is zero
     */
    public KalmanFilter(double initialEstimate, double initialErrorCovariance, double processNoise, double measurementNoise) {
        requireFinite(initialEstimate, "initialEstimate");
        requireNonNegativeVariance(initialErrorCovariance, "initialErrorCovariance");
        requireNonNegativeVariance(processNoise, "processNoise");
        requirePositiveVariance(measurementNoise, "measurementNoise");

        this.initialEstimate = initialEstimate;
        this.initialErrorCovariance = initialErrorCovariance;
        this.processNoise = processNoise;
        this.measurementNoise = measurementNoise;
        reset();
    }

    /**
     * Advances the model by one step without any control input, growing the uncertainty by the
     * process noise.
     *
     * @return the predicted estimate, unchanged in value for this constant model
     */
    public double predict() {
        return predict(0.0);
    }

    /**
     * Advances the model by one step, shifting the estimate by a known control input.
     *
     * @param controlInput the change the estimate is expected to undergo, e.g. velocity times the
     *     time step when tracking a position
     * @return the predicted estimate
     * @throws IllegalArgumentException if {@code controlInput} is not finite
     */
    public double predict(double controlInput) {
        requireFinite(controlInput, "controlInput");
        estimate += controlInput;
        errorCovariance += processNoise;
        return estimate;
    }

    /**
     * Corrects the estimate with a measurement taken by the default sensor.
     *
     * @param measurement the observed value
     * @return the corrected estimate
     * @throws IllegalArgumentException if {@code measurement} is not finite
     */
    public double update(double measurement) {
        return update(measurement, measurementNoise);
    }

    /**
     * Corrects the estimate with a measurement whose noise differs from the default one. Calling this
     * several times per step, once per sensor, is the whole of sensor fusion.
     *
     * @param measurement the observed value
     * @param noise variance of this particular measurement, strictly positive
     * @return the corrected estimate
     * @throws IllegalArgumentException if {@code measurement} is not finite or {@code noise} is not strictly positive
     */
    public double update(double measurement, double noise) {
        requireFinite(measurement, "measurement");
        requirePositiveVariance(noise, "noise");

        double innovationVariance = errorCovariance + noise;
        lastGain = errorCovariance / innovationVariance;
        estimate += lastGain * (measurement - estimate);
        // Algebraically this is (1 - gain) * p, but computing 1 - gain cancels away most of the
        // significant digits whenever the gain is close to one, as it is on the first measurements.
        errorCovariance = errorCovariance * noise / innovationVariance;
        return estimate;
    }

    /**
     * Runs one full cycle: predict, then correct with the given measurement.
     *
     * @param measurement the observed value
     * @return the filtered estimate
     * @throws IllegalArgumentException if {@code measurement} is not finite
     */
    public double filter(double measurement) {
        predict();
        return update(measurement);
    }

    /**
     * Filters a whole signal offline, one cycle per sample.
     *
     * @param measurements the noisy signal
     * @return a new array holding the filtered signal, of the same length
     * @throws IllegalArgumentException if any measurement is not finite
     * @throws NullPointerException if {@code measurements} is {@code null}
     */
    public double[] filter(double[] measurements) {
        double[] filtered = new double[measurements.length];
        for (int i = 0; i < measurements.length; i++) {
            filtered[i] = filter(measurements[i]);
        }
        return filtered;
    }

    /**
     * Combines simultaneous readings of the same quantity taken by independent sensors, weighting
     * each by the inverse of its variance. This is the closed form of what repeated
     * {@link #update(double, double)} calls achieve within one step.
     *
     * @param measurements one reading per sensor
     * @param variances the noise variance of each sensor, strictly positive, same length as {@code measurements}
     * @return the fused reading together with its variance, which is never larger than the variance of
     *     the best single sensor
     * @throws IllegalArgumentException if the arrays are empty, differ in length, hold a non-finite
     *     measurement or a non-positive variance
     * @throws NullPointerException if either array is {@code null}
     */
    public static Estimate fuse(double[] measurements, double[] variances) {
        if (measurements.length != variances.length) {
            throw new IllegalArgumentException("There must be exactly one variance per measurement, but got " + measurements.length + " and " + variances.length);
        }
        if (measurements.length == 0) {
            throw new IllegalArgumentException("At least one measurement is required");
        }

        double weightSum = 0.0;
        double weightedSum = 0.0;
        for (int i = 0; i < measurements.length; i++) {
            requireFinite(measurements[i], "measurement");
            requirePositiveVariance(variances[i], "variance");
            double weight = 1.0 / variances[i];
            weightSum += weight;
            weightedSum += weight * measurements[i];
        }
        return new Estimate(weightedSum / weightSum, 1.0 / weightSum);
    }

    /**
     * Returns the current estimate of the tracked quantity.
     *
     * @return the state estimate
     */
    public double estimate() {
        return estimate;
    }

    /**
     * Returns the variance of the current estimate; it shrinks with every update and grows with every
     * prediction.
     *
     * @return the error covariance
     */
    public double errorCovariance() {
        return errorCovariance;
    }

    /**
     * Returns the Kalman gain used by the most recent update, a number in {@code [0, 1)} telling how
     * much of that measurement was believed.
     *
     * @return the last gain, {@code 0} if no update has happened yet
     */
    public double lastGain() {
        return lastGain;
    }

    /**
     * Returns the process noise variance.
     *
     * @return the value given at construction time
     */
    public double processNoise() {
        return processNoise;
    }

    /**
     * Returns the default measurement noise variance.
     *
     * @return the value given at construction time
     */
    public double measurementNoise() {
        return measurementNoise;
    }

    /**
     * Restores the state the filter had right after construction.
     */
    public void reset() {
        estimate = initialEstimate;
        errorCovariance = initialErrorCovariance;
        lastGain = 0.0;
    }

    @Override
    public String toString() {
        return "KalmanFilter{estimate=" + estimate + ", errorCovariance=" + errorCovariance + ", lastGain=" + lastGain + '}';
    }

    private static void requireFinite(double value, String name) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("The " + name + " must be finite, but was " + value);
        }
    }

    private static void requireNonNegativeVariance(double value, String name) {
        if (!(value >= 0.0) || !Double.isFinite(value)) {
            throw new IllegalArgumentException("The " + name + " must be finite and non-negative, but was " + value);
        }
    }

    private static void requirePositiveVariance(double value, String name) {
        if (!(value > 0.0) || !Double.isFinite(value)) {
            throw new IllegalArgumentException("The " + name + " must be finite and strictly positive, but was " + value);
        }
    }

    /**
     * A value paired with the variance that describes how much it can be trusted.
     *
     * @param value the estimated quantity
     * @param variance the variance of that estimate
     */
    public record Estimate(double value, double variance) {
    }
}
