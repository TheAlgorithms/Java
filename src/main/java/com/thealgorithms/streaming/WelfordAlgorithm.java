package com.thealgorithms.streaming;

/**
 * Online (single pass) mean and variance using <b>Welford's algorithm</b>.
 *
 * <p>The textbook formula {@code Var = (sum(x^2) - n * mean^2) / (n - 1)} is fast but numerically
 * treacherous: {@code sum(x^2)} and {@code n * mean^2} may be huge and nearly equal, so their
 * difference loses most of its significant digits and can even come out negative. Welford's
 * recurrence never forms those large intermediate values. It keeps only the running mean and the sum
 * of squared deviations from that running mean, {@code M2}:
 *
 * <pre>
 * n     &lt;- n + 1
 * delta &lt;- x - mean
 * mean  &lt;- mean + delta / n
 * M2    &lt;- M2 + delta * (x - mean)   // note: the second factor uses the *updated* mean
 * </pre>
 *
 * <p>Both {@link #add(double)} and {@link #remove(double)} run in O(1) time and the accumulator
 * occupies O(1) memory no matter how many samples pass through it.
 *
 * <h2>Sliding windows and map-reduce</h2>
 *
 * <ul>
 *   <li>{@link #remove(double)} runs the recurrence backwards, which turns the accumulator into the
 *       statistics of a sliding window: feed the incoming sample to {@code add} and the sample that
 *       just left the window to {@code remove}. Removal is the one operation that can degrade
 *       accuracy over a very long run, since the value being removed no longer matches the mean it
 *       was added to; recreate the accumulator periodically if that matters.</li>
 *   <li>{@link #merge(WelfordAlgorithm, WelfordAlgorithm)} implements Chan's parallel update, so
 *       partial results computed on different shards can be combined exactly.</li>
 * </ul>
 *
 * <h2>Usage</h2>
 *
 * <pre>{@code
 * WelfordAlgorithm stats = new WelfordAlgorithm();
 * stats.add(2.0);
 * stats.add(4.0);
 * stats.add(4.0);
 * stats.mean();                        // 3.3333...
 * stats.populationStandardDeviation();  // 0.9428...
 * }</pre>
 *
 * <p>This class is not thread-safe.
 *
 * @see ExponentialMovingAverage
 * @see <a href="https://en.wikipedia.org/wiki/Algorithms_for_calculating_variance">Algorithms for calculating variance</a>
 */
public final class WelfordAlgorithm {

    private long count;
    private double mean;
    private double sumOfSquaredDeviations;

    /**
     * Creates an empty accumulator.
     */
    public WelfordAlgorithm() {
        clear();
    }

    /**
     * Incorporates one sample.
     *
     * @param value the sample to add
     * @throws IllegalArgumentException if {@code value} is NaN or infinite
     */
    public void add(double value) {
        requireFinite(value);
        count++;
        double delta = value - mean;
        mean += delta / count;
        sumOfSquaredDeviations += delta * (value - mean);
    }

    /**
     * Incorporates every given sample, in order.
     *
     * @param values the samples to add
     * @throws IllegalArgumentException if any value is NaN or infinite
     * @throws NullPointerException if {@code values} is {@code null}
     */
    public void addAll(double... values) {
        for (double value : values) {
            add(value);
        }
    }

    /**
     * Removes a previously added sample, reversing {@link #add(double)}. This is what makes the
     * accumulator usable for a sliding window.
     *
     * @param value the sample to remove; it must genuinely have been added before
     * @throws IllegalStateException if the accumulator is empty
     * @throws IllegalArgumentException if {@code value} is NaN or infinite
     */
    public void remove(double value) {
        requireFinite(value);
        if (count == 0) {
            throw new IllegalStateException("Cannot remove a sample from an empty accumulator");
        }
        if (count == 1) {
            clear();
            return;
        }
        double previousMean = mean;
        mean = (count * mean - value) / (count - 1);
        sumOfSquaredDeviations -= (value - previousMean) * (value - mean);
        count--;
        if (sumOfSquaredDeviations < 0.0) {
            sumOfSquaredDeviations = 0.0;
        }
    }

    /**
     * Combines two independently accumulated summaries using Chan's parallel variance update.
     *
     * @param left summary of the first batch of samples
     * @param right summary of the second batch of samples
     * @return a new summary describing the concatenation of both batches
     * @throws NullPointerException if either argument is {@code null}
     */
    public static WelfordAlgorithm merge(WelfordAlgorithm left, WelfordAlgorithm right) {
        WelfordAlgorithm merged = new WelfordAlgorithm();
        merged.count = left.count + right.count;
        if (merged.count == 0) {
            return merged;
        }
        double delta = right.mean - left.mean;
        merged.mean = left.mean + delta * right.count / merged.count;
        merged.sumOfSquaredDeviations = left.sumOfSquaredDeviations + right.sumOfSquaredDeviations + delta * delta * left.count * right.count / merged.count;
        return merged;
    }

    /**
     * Returns the number of samples seen so far.
     *
     * @return the sample count
     */
    public long count() {
        return count;
    }

    /**
     * Tells whether any sample has been added.
     *
     * @return {@code true} if no sample is currently accounted for
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Returns the arithmetic mean of the samples.
     *
     * @return the mean, or {@link Double#NaN} if no sample has been added
     */
    public double mean() {
        return count == 0 ? Double.NaN : mean;
    }

    /**
     * Returns the sum of the samples, reconstructed from the mean.
     *
     * @return {@code count * mean}, or {@code 0} if no sample has been added
     */
    public double sum() {
        return count == 0 ? 0.0 : mean * count;
    }

    /**
     * Returns the sum of squared deviations from the mean, {@code M2}.
     *
     * @return the sum of squared deviations, {@code 0} for an empty accumulator
     */
    public double sumOfSquaredDeviations() {
        return sumOfSquaredDeviations;
    }

    /**
     * Returns the unbiased sample variance, normalised by {@code count - 1}.
     *
     * @return the sample variance, or {@link Double#NaN} if fewer than two samples were added
     */
    public double sampleVariance() {
        return count < 2 ? Double.NaN : sumOfSquaredDeviations / (count - 1);
    }

    /**
     * Returns the population variance, normalised by {@code count}.
     *
     * @return the population variance, or {@link Double#NaN} if no sample has been added
     */
    public double populationVariance() {
        return count == 0 ? Double.NaN : sumOfSquaredDeviations / count;
    }

    /**
     * Returns the square root of {@link #sampleVariance()}.
     *
     * @return the sample standard deviation, or {@link Double#NaN} if fewer than two samples were added
     */
    public double sampleStandardDeviation() {
        return Math.sqrt(sampleVariance());
    }

    /**
     * Returns the square root of {@link #populationVariance()}.
     *
     * @return the population standard deviation, or {@link Double#NaN} if no sample has been added
     */
    public double populationStandardDeviation() {
        return Math.sqrt(populationVariance());
    }

    /**
     * Returns the standard error of the mean, {@code sampleStandardDeviation / sqrt(count)}.
     *
     * @return the standard error, or {@link Double#NaN} if fewer than two samples were added
     */
    public double standardError() {
        return sampleStandardDeviation() / Math.sqrt(count);
    }

    /**
     * Forgets every sample.
     */
    public void clear() {
        count = 0;
        mean = 0.0;
        sumOfSquaredDeviations = 0.0;
    }

    @Override
    public String toString() {
        return "WelfordAlgorithm{count=" + count + ", mean=" + mean() + ", sampleStandardDeviation=" + sampleStandardDeviation() + '}';
    }

    private static void requireFinite(double value) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Samples must be finite, but was " + value);
        }
    }
}
