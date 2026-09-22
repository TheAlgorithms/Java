package com.thealgorithms.streaming;

/**
 * The <b>complementary filter</b>: one estimate out of two sensors that are each wrong in a
 * different way.
 *
 * <p>The classic pair is an accelerometer and a gyroscope measuring the same tilt. The accelerometer
 * knows where down is and never drifts, but every vibration of the frame shows up in it. The
 * gyroscope is smooth and immune to vibration, but it measures a rate, so using it means integrating,
 * and the smallest bias in that rate integrates into an angle that walks away without limit. Neither
 * is usable alone; their errors live in different parts of the spectrum, which is exactly the
 * situation this filter is for.
 *
 * <pre>
 * value &lt;- a * (value + rate * dt) + (1 - a) * reference
 * </pre>
 *
 * <p>Read as a pair of filters that add up to one, it is a high pass on the integrated rate and a low
 * pass on the absolute reading: the drift of the first is cut off below the corner frequency and the
 * noise of the second above it. The two transfer functions sum to unity at every frequency, so the
 * true signal passes through untouched whatever {@code a} is. That is where the name comes from, and
 * it is also why the filter cannot introduce a lag of its own the way a plain low pass on the
 * accelerometer would.
 *
 * <p>The single parameter is best thought of as a time constant rather than as a number near one:
 *
 * <pre>
 * tau = a * dt / (1 - a)
 * </pre>
 *
 * <p>Below {@code tau} the answer comes from the gyroscope, above it from the accelerometer. That
 * also fixes the price of the trade exactly: a gyroscope with a constant bias {@code b} leaves a
 * steady state error of {@code tau * b} and no more, where plain integration would have grown without
 * limit. Use {@link #ofTimeConstant(double, double)} to set it that way round.
 *
 * <p>Against {@link KalmanFilter}: the Kalman filter is the right answer when the noise of both
 * sensors is known and worth modelling, and it will beat this one when it is. The complementary
 * filter needs no covariance, no model of the process, two multiplications per sample and one number
 * of state, and it degrades gracefully when the noise is not what anybody assumed. That is why it is
 * what actually runs on small flight controllers.
 *
 * <h2>Usage</h2>
 *
 * <pre>{@code
 * ComplementaryFilter tilt = ComplementaryFilter.ofTimeConstant(0.5, 0.01);
 * for (Reading reading : imu) {
 *     double angle = tilt.accept(reading.gyroscopeRate(), reading.accelerometerAngle(), reading.dt());
 * }
 * }</pre>
 *
 * <p>Each sample costs O(1) time and the filter keeps one number of state. This class is not
 * thread-safe.
 *
 * @see KalmanFilter
 * @see <a href="https://en.wikipedia.org/wiki/Complementary_filter">Complementary filter</a>
 */
public final class ComplementaryFilter {

    /** Weight given to the integrated rate when none is chosen, the usual setting for an IMU. */
    public static final double DEFAULT_COEFFICIENT = 0.98;

    private final double coefficient;

    private double value;
    private long count;

    /**
     * Creates a filter that leans on the rate with the customary weight of {@code 0.98}.
     */
    public ComplementaryFilter() {
        this(DEFAULT_COEFFICIENT);
    }

    /**
     * Creates a filter.
     *
     * @param coefficient how much of the estimate comes from the integrated rate, in {@code (0, 1)};
     *     closer to one trusts the rate for longer, closer to zero follows the reference more quickly
     * @throws IllegalArgumentException if {@code coefficient} is outside {@code (0, 1)}
     */
    public ComplementaryFilter(double coefficient) {
        if (!(coefficient > 0.0) || !(coefficient < 1.0)) {
            throw new IllegalArgumentException("The coefficient must lie in (0, 1), but was " + coefficient);
        }
        this.coefficient = coefficient;
    }

    /**
     * Creates a filter from the time constant that separates the two sensors, which is usually the
     * quantity that is actually known: {@code a = tau / (tau + dt)}.
     *
     * @param timeConstant how long the rate is trusted before the reference takes over, strictly positive
     * @param samplingInterval the interval between samples, strictly positive and in the same unit
     * @return a new filter
     * @throws IllegalArgumentException if either argument is not finite and strictly positive
     */
    public static ComplementaryFilter ofTimeConstant(double timeConstant, double samplingInterval) {
        requirePositive(timeConstant, "time constant");
        requirePositive(samplingInterval, "sampling interval");
        return new ComplementaryFilter(timeConstant / (timeConstant + samplingInterval));
    }

    /**
     * Feeds one pair of readings taken one unit of time after the previous one.
     *
     * @param rate the reading of the drifting sensor, a derivative of the estimated quantity
     * @param reference the reading of the noisy but drift free sensor, in the unit of the estimate
     * @return the updated estimate
     * @throws IllegalArgumentException if a reading is NaN or infinite
     */
    public double accept(double rate, double reference) {
        return accept(rate, reference, 1.0);
    }

    /**
     * Feeds one pair of readings.
     *
     * @param rate the reading of the drifting sensor, a derivative of the estimated quantity
     * @param reference the reading of the noisy but drift free sensor, in the unit of the estimate
     * @param elapsed time since the previous pair, strictly positive
     * @return the updated estimate; the very first pair is answered with the reference alone, because
     *     there is nothing yet to integrate from
     * @throws IllegalArgumentException if a reading is NaN or infinite, or if {@code elapsed} is not
     *     finite and strictly positive
     */
    public double accept(double rate, double reference, double elapsed) {
        requireFinite(rate, "rate");
        requireFinite(reference, "reference");
        requirePositive(elapsed, "elapsed time");

        if (count == 0) {
            value = reference;
        } else {
            value = coefficient * (value + rate * elapsed) + (1.0 - coefficient) * reference;
        }
        count++;
        return value;
    }

    /**
     * Runs the filter over a whole pair of recordings sampled at unit intervals.
     *
     * @param rates the readings of the drifting sensor
     * @param references the readings of the drift free sensor, as many as there are rates
     * @return a new array of the same length holding the estimate after every sample
     * @throws IllegalArgumentException if the two recordings differ in length or hold a reading that
     *     is NaN or infinite
     * @throws NullPointerException if a recording is {@code null}
     */
    public double[] scan(double[] rates, double[] references) {
        if (rates.length != references.length) {
            throw new IllegalArgumentException("Every rate needs a reference, but there were " + rates.length + " and " + references.length);
        }
        double[] estimates = new double[rates.length];
        for (int i = 0; i < rates.length; i++) {
            estimates[i] = accept(rates[i], references[i]);
        }
        return estimates;
    }

    /**
     * Returns the current estimate.
     *
     * @return the estimate after the last pair of readings, {@code 0} before the first one
     */
    public double value() {
        return value;
    }

    /**
     * Returns the weight given to the integrated rate.
     *
     * @return the coefficient given at construction time
     */
    public double coefficient() {
        return coefficient;
    }

    /**
     * Returns the time constant the filter works out to at a given sampling interval, that is
     * {@code a * dt / (1 - a)}: the horizon below which the rate decides the answer and above which
     * the reference does.
     *
     * @param samplingInterval the interval between samples, strictly positive
     * @return the time constant, in the unit of the interval
     * @throws IllegalArgumentException if {@code samplingInterval} is not finite and strictly positive
     */
    public double timeConstant(double samplingInterval) {
        requirePositive(samplingInterval, "sampling interval");
        return coefficient * samplingInterval / (1.0 - coefficient);
    }

    /**
     * Returns how many pairs of readings have been filtered since the last reset.
     *
     * @return the sample count
     */
    public long count() {
        return count;
    }

    /**
     * Forgets everything seen so far, so that the next reference seeds the estimate again.
     */
    public void reset() {
        value = 0.0;
        count = 0;
    }

    /**
     * Restarts the filter from a known estimate, which is what to do after the process has been moved
     * by something the sensors could not see.
     *
     * @param estimate the value to carry on from
     * @throws IllegalArgumentException if {@code estimate} is NaN or infinite
     */
    public void reset(double estimate) {
        requireFinite(estimate, "estimate");
        value = estimate;
        count = 1;
    }

    @Override
    public String toString() {
        return "ComplementaryFilter{coefficient=" + coefficient + ", value=" + value + ", samples=" + count + "}";
    }

    private static void requireFinite(double value, String name) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("The " + name + " must be finite, but was " + value);
        }
    }

    private static void requirePositive(double value, String name) {
        if (!(value > 0.0) || !Double.isFinite(value)) {
            throw new IllegalArgumentException("The " + name + " must be finite and strictly positive, but was " + value);
        }
    }
}
