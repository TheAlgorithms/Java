package com.thealgorithms.streaming;

/**
 * <b>ADWIN</b>, adaptive windowing, after Bifet and Gavalda: an average over a window whose length is
 * not a parameter but a result.
 *
 * <p>Every windowed estimator forces the same bad choice. A long window is accurate while nothing
 * changes and hopelessly slow once something does; a short one reacts immediately and is noisy the
 * rest of the time. ADWIN refuses the choice: it keeps a window of recent values and, after every
 * sample, looks for a way to split it into an old part and a recent part whose means are too far
 * apart to be explained by chance. When it finds one, the old part is dropped. The window therefore
 * grows on its own while the stream is stationary and collapses as soon as the stream moves, and the
 * length it settles at is an estimate of how long the current regime has been running.
 *
 * <p>"Too far apart" is a variance sensitive Hoeffding bound. For a cut into sub-windows of
 * {@code n0} and {@code n1} elements, with {@code v} the variance of the whole window:
 *
 * <pre>
 * m       = 1 / (n0 - minLength + 1) + 1 / (n1 - minLength + 1)
 * d       = ln( 2 * ln(width) / delta )
 * epsilon = sqrt(2 * m * v * d) + 2/3 * d * m
 * cut when |mean0 - mean1| &gt; epsilon
 * </pre>
 *
 * <p>The {@code delta} parameter is a confidence level: the probability of cutting a window that
 * never changed is bounded by it, which is the guarantee that makes the window length trustworthy.
 * Smaller values make the detector more conservative and slower.
 *
 * <p>Keeping every sample would cost O(n) memory, so the window is stored as an exponential
 * histogram: buckets of 1, 2, 4, 8 ... elements, at most {@code MAX_BUCKETS} of each size, each
 * holding the sum and the variance of the elements it covers. That is O(log n) buckets for a window
 * of n elements, and cuts are only tried at bucket boundaries, which is what keeps a sample O(log n)
 * instead of O(n) while costing only a bounded loss of resolution.
 *
 * <h2>Usage</h2>
 *
 * <pre>{@code
 * Adwin window = new Adwin(0.002);
 * for (double sample : stream) {
 *     if (window.accept(sample)) {
 *         alert(window.estimate(), window.width());
 *     }
 * }
 * }</pre>
 *
 * <p>This class is not thread-safe.
 *
 * @see CusumDetector
 * @see <a href="https://en.wikipedia.org/wiki/Concept_drift">Concept drift</a>, the problem ADWIN was written for; the algorithm is due to A. Bifet and R. Gavalda, Learning from Time-Changing Data with Adaptive Windowing, SDM 2007
 */
public final class Adwin {

    /** Confidence level used when none is given. */
    public static final double DEFAULT_DELTA = 0.002;

    /** How many buckets of the same size the histogram holds before merging the two oldest. */
    public static final int MAX_BUCKETS = 5;

    private static final int MIN_SUBWINDOW = 5;
    private static final long MIN_WIDTH_FOR_DETECTION = 2L * MIN_SUBWINDOW;

    private final double delta;

    private Row newest;
    private Row oldest;

    private long width;
    private double total;
    private double variance;
    private int bucketCount;
    private long count;
    private long changeCount;

    /**
     * Creates a window with the customary confidence level of {@code 0.002}.
     */
    public Adwin() {
        this(DEFAULT_DELTA);
    }

    /**
     * Creates a window.
     *
     * @param delta the confidence level, in {@code (0, 1)}; smaller values cut less eagerly
     * @throws IllegalArgumentException if {@code delta} is outside {@code (0, 1)}
     */
    public Adwin(double delta) {
        if (!(delta > 0.0) || !(delta < 1.0)) {
            throw new IllegalArgumentException("The delta must lie in (0, 1), but was " + delta);
        }
        this.delta = delta;
        start();
    }

    /**
     * Feeds one sample into the window.
     *
     * @param value the incoming sample
     * @return {@code true} if the window was cut, that is if the stream changed
     * @throws IllegalArgumentException if {@code value} is NaN or infinite
     */
    public boolean accept(double value) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Samples must be finite, but was " + value);
        }
        count++;
        insert(value);
        return detectChange();
    }

    /**
     * Runs the window over a whole signal.
     *
     * @param signal the samples to inspect
     * @return a new array of the same length saying for every sample whether it cut the window
     * @throws IllegalArgumentException if any sample is NaN or infinite
     * @throws NullPointerException if {@code signal} is {@code null}
     */
    public boolean[] scan(double[] signal) {
        boolean[] cuts = new boolean[signal.length];
        for (int i = 0; i < signal.length; i++) {
            cuts[i] = accept(signal[i]);
        }
        return cuts;
    }

    /**
     * Returns the current estimate of the level of the stream.
     *
     * @return the mean of the window, {@code 0} while the window is empty
     */
    public double estimate() {
        return width == 0 ? 0.0 : total / width;
    }

    /**
     * Returns the length of the window, which is how many recent samples the estimate rests on.
     *
     * @return the window width
     */
    public long width() {
        return width;
    }

    /**
     * Returns the variance of the samples inside the window.
     *
     * @return the window variance, {@code 0} while the window holds fewer than two samples
     */
    public double variance() {
        return width < 2 ? 0.0 : variance / width;
    }

    /**
     * Returns how many buckets the histogram holds, which grows like the logarithm of the width.
     *
     * @return the bucket count
     */
    public int bucketCount() {
        return bucketCount;
    }

    /**
     * Returns how many samples have been inspected since the last reset.
     *
     * @return the sample count
     */
    public long count() {
        return count;
    }

    /**
     * Returns how many times the window has been cut since the last reset.
     *
     * @return the number of detected changes
     */
    public long changeCount() {
        return changeCount;
    }

    /**
     * Returns the configured confidence level.
     *
     * @return the delta given at construction time
     */
    public double delta() {
        return delta;
    }

    /**
     * Empties the window.
     */
    public void reset() {
        start();
        count = 0;
        changeCount = 0;
    }

    @Override
    public String toString() {
        return "Adwin{width=" + width + ", estimate=" + estimate() + ", buckets=" + bucketCount + ", changes=" + changeCount + "}";
    }

    private void start() {
        newest = new Row(0);
        oldest = newest;
        width = 0;
        total = 0.0;
        variance = 0.0;
        bucketCount = 0;
    }

    private void insert(double value) {
        width++;
        newest.add(value, 0.0);
        bucketCount++;
        if (width > 1) {
            double deviation = value - total / (width - 1);
            variance += (width - 1) * deviation * deviation / width;
        }
        total += value;
        compress();
    }

    /**
     * Merges the two oldest buckets of every row that has run out of room into one bucket of the next
     * row, which is what keeps the number of buckets logarithmic in the width.
     */
    private void compress() {
        Row row = newest;
        while (row != null && row.size > MAX_BUCKETS) {
            if (row.older == null) {
                row.older = new Row(row.level + 1);
                row.older.newer = row;
                oldest = row.older;
            }
            long size = 1L << row.level;
            double firstMean = row.totals[0] / size;
            double secondMean = row.totals[1] / size;
            double merged = size * size * (firstMean - secondMean) * (firstMean - secondMean) / (size + size);
            row.older.add(row.totals[0] + row.totals[1], row.variances[0] + row.variances[1] + merged);
            row.removeOldest(2);
            bucketCount--;
            row = row.older;
        }
    }

    /**
     * Tries every cut the histogram allows, from the oldest boundary inwards, and drops the oldest
     * bucket whenever a cut is significant. Repeats until no cut is left.
     *
     * @return whether anything was dropped
     */
    private boolean detectChange() {
        boolean changed = false;
        boolean searching = true;

        while (searching && width >= MIN_WIDTH_FOR_DETECTION) {
            searching = false;
            long oldWidth = 0;
            double oldTotal = 0.0;

        outer:
            for (Row row = oldest; row != null; row = row.newer) {
                for (int bucket = 0; bucket < row.size; bucket++) {
                    if (row.newer == null && bucket == row.size - 1) {
                        break outer;
                    }
                    oldWidth += 1L << row.level;
                    oldTotal += row.totals[bucket];
                    long recentWidth = width - oldWidth;
                    double recentTotal = total - oldTotal;
                    if (recentWidth < MIN_SUBWINDOW) {
                        break outer;
                    }
                    if (oldWidth >= MIN_SUBWINDOW && isSignificant(oldWidth, recentWidth, oldTotal / oldWidth - recentTotal / recentWidth)) {
                        changed = true;
                        searching = true;
                        changeCount++;
                        dropOldestBucket();
                        break outer;
                    }
                }
            }
        }
        return changed;
    }

    private boolean isSignificant(long oldWidth, long recentWidth, double difference) {
        double harmonic = 1.0 / (oldWidth - MIN_SUBWINDOW + 1) + 1.0 / (recentWidth - MIN_SUBWINDOW + 1);
        double confidence = Math.log(2.0 * Math.log(width) / delta);
        double windowVariance = variance / width;
        double epsilon = Math.sqrt(2.0 * harmonic * windowVariance * confidence) + 2.0 / 3.0 * confidence * harmonic;
        return Math.abs(difference) > epsilon;
    }

    private void dropOldestBucket() {
        Row row = oldest;
        long size = 1L << row.level;
        double bucketTotal = row.totals[0];

        width -= size;
        total -= bucketTotal;
        if (width > 0) {
            double bucketMean = bucketTotal / size;
            double difference = bucketMean - total / width;
            variance -= row.variances[0] + size * width * difference * difference / (size + width);
        } else {
            variance = 0.0;
        }
        if (variance < 0.0) {
            variance = 0.0;
        }

        row.removeOldest(1);
        bucketCount--;
        if (row.size == 0 && row.newer != null) {
            oldest = row.newer;
            oldest.older = null;
        }
    }

    /**
     * One row of the exponential histogram: up to {@code MAX_BUCKETS + 1} buckets that each cover
     * {@code 2^level} samples, the oldest at index zero.
     */
    private static final class Row {

        private final int level;
        private final double[] totals = new double[MAX_BUCKETS + 1];
        private final double[] variances = new double[MAX_BUCKETS + 1];
        private int size;
        private Row older;
        private Row newer;

        Row(int level) {
            this.level = level;
        }

        void add(double total, double variance) {
            totals[size] = total;
            variances[size] = variance;
            size++;
        }

        void removeOldest(int buckets) {
            for (int i = buckets; i < size; i++) {
                totals[i - buckets] = totals[i];
                variances[i - buckets] = variances[i];
            }
            size -= buckets;
        }
    }
}
