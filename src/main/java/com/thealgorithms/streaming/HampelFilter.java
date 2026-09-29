package com.thealgorithms.streaming;

/**
 * The <b>Hampel filter</b>, also known as the Hampel identifier: a decision rule that replaces the
 * samples of a signal that look like outliers and leaves every other sample untouched.
 *
 * <p>A {@link MedianFilter} rewrites every sample, which throws away detail even where the signal
 * was perfectly clean. The Hampel filter is the conservative version. For each incoming sample it
 * looks at the window that ends at that sample and computes two robust statistics:
 *
 * <ul>
 *   <li>the <b>median</b> of the window, a robust estimate of where the signal is;</li>
 *   <li>the <b>median absolute deviation</b> {@code MAD = median(|x_i - median|)}, a robust estimate
 *       of how much the signal normally moves.</li>
 * </ul>
 *
 * <p>The MAD is rescaled by {@code 1.4826}, the factor that makes it match the standard deviation
 * for normally distributed data, and the sample is declared an outlier when
 *
 * <pre>
 * |x - median| &gt; threshold * 1.4826 * MAD
 * </pre>
 *
 * <p>Outliers are reported and replaced by the window median; everything else passes through
 * unchanged. Both statistics are robust, so the spike being tested cannot inflate the very
 * yardstick it is measured against - which is exactly what happens if one uses a mean and a standard
 * deviation instead.
 *
 * <p>A threshold of 3 is the usual starting point, but how eager the filter turns out to be depends
 * just as much on the window, because the MAD of a handful of samples is itself a noisy estimate of
 * the spread. Measured on clean Gaussian noise with a threshold of 3, the filter flags about 7% of
 * the samples with a window of 5, 3% with a window of 11 and 0.5% with a window of 101, slowly
 * approaching the 0.3% one would get from a perfect estimate of sigma. Widen the window, or raise
 * the threshold, when too many good samples are being rewritten.
 *
 * <h2>Usage</h2>
 *
 * <pre>{@code
 * HampelFilter filter = new HampelFilter(7, 3.0);
 * for (double sample : sensorReadings) {
 *     double clean = filter.accept(sample);
 *     if (filter.lastWasOutlier()) {
 *         log.warn("spike of {} replaced by {}", sample, clean);
 *     }
 * }
 * }</pre>
 *
 * <p>The window is causal and includes the sample being judged, so the filter introduces no delay,
 * and the raw sample - not its replacement - is what enters the window, which keeps the statistics
 * honest. Before the window has filled up the statistics are computed over the samples seen so far.
 * If more than half of the window holds one and the same value the MAD is zero, and any sample that
 * differs from the median at all is then flagged; that is the textbook behaviour of the identifier.
 *
 * <p>Each sample costs O(w) time for a window of {@code w} samples and nothing is allocated after
 * construction. This class is not thread-safe.
 *
 * @see MedianFilter
 * @see <a href="https://en.wikipedia.org/wiki/Median_absolute_deviation">Median absolute deviation</a>
 */
public final class HampelFilter {

    /** Makes the MAD of normally distributed data an unbiased estimator of its standard deviation. */
    public static final double GAUSSIAN_MAD_SCALE = 1.4826;

    /** Threshold, in robust standard deviations, used when none is given. */
    public static final double DEFAULT_THRESHOLD = 3.0;

    private final MedianFilter window;
    private final double threshold;
    private final double[] sortedWindow;
    private final double[] deviations;

    private boolean lastWasOutlier;
    private long outlierCount;

    /**
     * Creates a filter with the customary threshold of three robust standard deviations.
     *
     * @param windowSize how many recent samples the statistics are computed over; odd sizes are the usual choice
     * @throws IllegalArgumentException if {@code windowSize} is not positive
     */
    public HampelFilter(int windowSize) {
        this(windowSize, DEFAULT_THRESHOLD);
    }

    /**
     * Creates a filter.
     *
     * @param windowSize how many recent samples the statistics are computed over
     * @param threshold how many robust standard deviations a sample may deviate before it counts as an
     *     outlier; must be non-negative
     * @throws IllegalArgumentException if {@code windowSize} is not positive or {@code threshold} is negative or not finite
     */
    public HampelFilter(int windowSize, double threshold) {
        if (!(threshold >= 0.0) || !Double.isFinite(threshold)) {
            throw new IllegalArgumentException("The threshold must be finite and non-negative, but was " + threshold);
        }
        this.window = new MedianFilter(windowSize);
        this.threshold = threshold;
        this.sortedWindow = new double[windowSize];
        this.deviations = new double[windowSize];
    }

    /**
     * Feeds one sample into the filter.
     *
     * @param value the incoming sample
     * @return the sample itself, or the window median if the sample was judged to be an outlier
     * @throws IllegalArgumentException if {@code value} is NaN or infinite
     */
    public double accept(double value) {
        double median = window.accept(value);
        double scaledDeviation = threshold * GAUSSIAN_MAD_SCALE * medianAbsoluteDeviation();
        lastWasOutlier = Math.abs(value - median) > scaledDeviation;
        if (lastWasOutlier) {
            outlierCount++;
            return median;
        }
        return value;
    }

    /**
     * Filters a whole signal, one sample at a time, starting from the current state.
     *
     * @param signal the samples to filter
     * @return a new array of the same length in which flagged samples are replaced by the local median
     * @throws IllegalArgumentException if any sample is NaN or infinite
     * @throws NullPointerException if {@code signal} is {@code null}
     */
    public double[] filter(double[] signal) {
        double[] filtered = new double[signal.length];
        for (int i = 0; i < signal.length; i++) {
            filtered[i] = accept(signal[i]);
        }
        return filtered;
    }

    /**
     * Runs the filter over a signal and reports which samples were flagged, without altering them.
     *
     * @param signal the samples to inspect
     * @return a new array of the same length, {@code true} where the sample was judged an outlier
     * @throws IllegalArgumentException if any sample is NaN or infinite
     * @throws NullPointerException if {@code signal} is {@code null}
     */
    public boolean[] detectOutliers(double[] signal) {
        boolean[] flags = new boolean[signal.length];
        for (int i = 0; i < signal.length; i++) {
            accept(signal[i]);
            flags[i] = lastWasOutlier;
        }
        return flags;
    }

    /**
     * Returns the median absolute deviation of the current window, the robust counterpart of the
     * standard deviation.
     *
     * @return the MAD, {@code 0} when the window holds a single sample
     * @throws IllegalStateException if no sample has been accepted yet
     */
    public double medianAbsoluteDeviation() {
        int size = window.copySortedWindow(sortedWindow);
        if (size == 0) {
            throw new IllegalStateException("The window is empty");
        }
        double median = window.median();

        // The window is sorted, so the absolute deviations form two already sorted runs that meet at
        // the median: descending to its left, ascending to its right. Merging them is linear.
        int split = size;
        for (int i = 0; i < size; i++) {
            if (sortedWindow[i] > median) {
                split = i;
                break;
            }
        }
        int left = split - 1;
        int right = split;
        for (int i = 0; i < size; i++) {
            boolean takeLeft = right >= size || (left >= 0 && median - sortedWindow[left] <= sortedWindow[right] - median);
            if (takeLeft) {
                deviations[i] = median - sortedWindow[left];
                left--;
            } else {
                deviations[i] = sortedWindow[right] - median;
                right++;
            }
        }

        int middle = size / 2;
        return size % 2 != 0 ? deviations[middle] : 0.5 * (deviations[middle - 1] + deviations[middle]);
    }

    /**
     * Returns the median of the current window.
     *
     * @return the window median
     * @throws IllegalStateException if no sample has been accepted yet
     */
    public double median() {
        return window.median();
    }

    /**
     * Tells whether the most recently accepted sample was flagged.
     *
     * @return {@code true} if the last sample was replaced by the median
     */
    public boolean lastWasOutlier() {
        return lastWasOutlier;
    }

    /**
     * Returns how many samples have been flagged since the last reset.
     *
     * @return the number of detected outliers
     */
    public long outlierCount() {
        return outlierCount;
    }

    /**
     * Returns the configured window length.
     *
     * @return the window size given at construction time
     */
    public int windowSize() {
        return window.windowSize();
    }

    /**
     * Returns the configured threshold.
     *
     * @return the threshold in robust standard deviations
     */
    public double threshold() {
        return threshold;
    }

    /**
     * Returns how many samples the window currently holds.
     *
     * @return the current fill level, at most {@link #windowSize()}
     */
    public int size() {
        return window.size();
    }

    /**
     * Empties the window and forgets the outlier count.
     */
    public void reset() {
        window.reset();
        lastWasOutlier = false;
        outlierCount = 0;
    }

    @Override
    public String toString() {
        return "HampelFilter{windowSize=" + windowSize() + ", threshold=" + threshold + ", outliers=" + outlierCount + '}';
    }
}
