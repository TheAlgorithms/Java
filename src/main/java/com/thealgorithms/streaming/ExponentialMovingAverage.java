package com.thealgorithms.streaming;

/**
 * Exponentially weighted moving average (EWMA) and, alongside it, the exponentially weighted
 * variance of the same stream.
 *
 * <p>A plain moving average has to remember the whole window. An exponentially weighted one does
 * not: every sample simply decays, so a single number carries the entire history.
 *
 * <pre>
 * mean     &lt;- mean + alpha * (x - mean)
 * variance &lt;- (1 - alpha) * (variance + alpha * (x - mean_before)^2)
 * </pre>
 *
 * <p>The smoothing factor {@code alpha} in {@code (0, 1]} sets how fast the past is forgotten:
 * {@code alpha == 1} keeps only the latest sample, while a small {@code alpha} produces a smooth but
 * sluggish estimate. It is usually easier to specify the responsiveness in terms of a window, which
 * the factory methods do for you:
 *
 * <table border="1">
 *   <caption>Ways to choose alpha</caption>
 *   <tr><th>Factory</th><th>alpha</th><th>Meaning</th></tr>
 *   <tr><td>{@link #ofAlpha(double)}</td><td>as given</td><td>direct control</td></tr>
 *   <tr><td>{@link #ofSpan(double)}</td><td>{@code 2 / (span + 1)}</td><td>comparable to a simple moving average of {@code span} samples</td></tr>
 *   <tr><td>{@link #ofHalfLife(double)}</td><td>{@code 1 - exp(-ln2 / halfLife)}</td><td>a sample loses half of its weight after {@code halfLife} steps</td></tr>
 * </table>
 *
 * <p>The average is seeded with the first sample, which avoids the warm-up bias that a zero seed
 * would introduce. Pass an explicit seed to {@link #ofAlpha(double, double)} when the resting level
 * of the signal is known in advance, as a control chart does.
 *
 * <p>Both the update and every query run in O(1) time and O(1) memory. This class is not
 * thread-safe.
 *
 * @see EwmaChangeDetector
 * @see <a href="https://en.wikipedia.org/wiki/Moving_average#Exponential_moving_average">Exponential moving average</a>
 */
public final class ExponentialMovingAverage {

    private final double alpha;
    private final double seed;
    private final boolean seeded;

    private double mean;
    private double variance;
    private long count;
    private boolean initialized;

    private ExponentialMovingAverage(double alpha, double seed, boolean seeded) {
        if (!(alpha > 0.0) || alpha > 1.0) {
            throw new IllegalArgumentException("The smoothing factor alpha must lie in (0, 1], but was " + alpha);
        }
        if (seeded && !Double.isFinite(seed)) {
            throw new IllegalArgumentException("The seed must be finite, but was " + seed);
        }
        this.alpha = alpha;
        this.seed = seed;
        this.seeded = seeded;
        reset();
    }

    /**
     * Creates an average that is seeded with its first sample.
     *
     * @param alpha smoothing factor in {@code (0, 1]}
     * @return a new average
     * @throws IllegalArgumentException if {@code alpha} is outside {@code (0, 1]}
     */
    public static ExponentialMovingAverage ofAlpha(double alpha) {
        return new ExponentialMovingAverage(alpha, 0.0, false);
    }

    /**
     * Creates an average that starts from a known level instead of waiting for the first sample.
     *
     * @param alpha smoothing factor in {@code (0, 1]}
     * @param seed the initial value of the average
     * @return a new average
     * @throws IllegalArgumentException if {@code alpha} is outside {@code (0, 1]} or {@code seed} is not finite
     */
    public static ExponentialMovingAverage ofAlpha(double alpha, double seed) {
        return new ExponentialMovingAverage(alpha, seed, true);
    }

    /**
     * Creates an average whose responsiveness matches a simple moving average of {@code span}
     * samples, that is {@code alpha = 2 / (span + 1)}.
     *
     * @param span the equivalent window length, greater than or equal to one
     * @return a new average
     * @throws IllegalArgumentException if {@code span} is smaller than one or not finite
     */
    public static ExponentialMovingAverage ofSpan(double span) {
        if (!(span >= 1.0) || !Double.isFinite(span)) {
            throw new IllegalArgumentException("The span must be finite and at least 1, but was " + span);
        }
        return ofAlpha(2.0 / (span + 1.0));
    }

    /**
     * Creates an average in which a sample loses half of its weight after {@code halfLife} updates,
     * that is {@code alpha = 1 - exp(-ln2 / halfLife)}.
     *
     * @param halfLife number of updates after which a weight is halved, strictly positive
     * @return a new average
     * @throws IllegalArgumentException if {@code halfLife} is not strictly positive or not finite
     */
    public static ExponentialMovingAverage ofHalfLife(double halfLife) {
        if (!(halfLife > 0.0) || !Double.isFinite(halfLife)) {
            throw new IllegalArgumentException("The half-life must be finite and positive, but was " + halfLife);
        }
        return ofAlpha(-Math.expm1(-Math.log(2.0) / halfLife));
    }

    /**
     * Incorporates one sample.
     *
     * @param value the sample to add
     * @return the updated average
     * @throws IllegalArgumentException if {@code value} is NaN or infinite
     */
    public double add(double value) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Samples must be finite, but was " + value);
        }
        count++;
        if (!initialized) {
            initialized = true;
            mean = value;
            variance = 0.0;
            return mean;
        }
        double deviation = value - mean;
        mean += alpha * deviation;
        variance = (1.0 - alpha) * (variance + alpha * deviation * deviation);
        return mean;
    }

    /**
     * Incorporates every given sample, in order.
     *
     * @param values the samples to add
     * @return the average after the last sample
     * @throws IllegalArgumentException if any value is NaN or infinite
     * @throws IllegalStateException if {@code values} is empty and the average is not initialized yet
     * @throws NullPointerException if {@code values} is {@code null}
     */
    public double addAll(double... values) {
        for (double value : values) {
            add(value);
        }
        return value();
    }

    /**
     * Returns the current average.
     *
     * @return the exponentially weighted mean
     * @throws IllegalStateException if the average has neither a seed nor a sample yet
     */
    public double value() {
        if (!initialized) {
            throw new IllegalStateException("The average has not seen any sample yet");
        }
        return mean;
    }

    /**
     * Returns the exponentially weighted variance of the stream, the natural companion of
     * {@link #value()} when the spread matters as much as the level.
     *
     * @return the weighted variance, {@code 0} until the second sample arrives
     * @throws IllegalStateException if the average has neither a seed nor a sample yet
     */
    public double variance() {
        if (!initialized) {
            throw new IllegalStateException("The average has not seen any sample yet");
        }
        return variance;
    }

    /**
     * Returns the square root of {@link #variance()}.
     *
     * @return the weighted standard deviation
     * @throws IllegalStateException if the average has neither a seed nor a sample yet
     */
    public double standardDeviation() {
        return Math.sqrt(variance());
    }

    /**
     * Returns the smoothing factor in use.
     *
     * @return alpha
     */
    public double alpha() {
        return alpha;
    }

    /**
     * Returns the number of samples added since the last reset.
     *
     * @return the sample count, which excludes the seed
     */
    public long count() {
        return count;
    }

    /**
     * Tells whether {@link #value()} may be queried.
     *
     * @return {@code true} once a seed or at least one sample is available
     */
    public boolean isInitialized() {
        return initialized;
    }

    /**
     * Restores the state the average had right after construction.
     */
    public void reset() {
        count = 0;
        variance = 0.0;
        mean = seeded ? seed : 0.0;
        initialized = seeded;
    }

    @Override
    public String toString() {
        return "ExponentialMovingAverage{alpha=" + alpha + ", value=" + (initialized ? mean : Double.NaN) + ", count=" + count + '}';
    }
}
