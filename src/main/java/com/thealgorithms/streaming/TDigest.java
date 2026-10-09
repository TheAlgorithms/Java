package com.thealgorithms.streaming;

import java.util.Arrays;

/**
 * A <b>t-digest</b>: a small, mergeable sketch that answers <em>any</em> quantile of a stream, with
 * much better accuracy at the tails than in the middle.
 *
 * <p>The sketch is a list of centroids, each holding a mean and a weight, kept sorted by mean.
 * Together they approximate the distribution of everything ever added. What makes a t-digest more
 * than a histogram is the rule that decides how big a centroid is allowed to be: instead of a fixed
 * bucket width, the size limit is expressed through a <i>scale function</i>
 *
 * <pre>
 * k(q) = compression / (2 * pi) * asin(2q - 1)
 * </pre>
 *
 * <p>and a centroid may absorb points only while it spans at most one unit of {@code k}. Because
 * {@code asin} is steep near {@code q = 0} and {@code q = 1}, centroids near the tails are forced to
 * stay tiny - often a single point - while centroids near the median are allowed to grow large.
 * That is exactly the trade one wants in practice: the rank error is of the order of
 * {@code 1 / compression} around the median and shrinks as {@code q} approaches 0 or 1, so
 * {@code p99.9} comes out nearly exact. On smooth data the interpolation between centroids brings
 * the error down by another order of magnitude, and the whole sketch still fits in a few kilobytes.
 *
 * <p>Insertions are buffered and folded into the centroid list in batches, which keeps the amortised
 * cost per sample low. The minimum and the maximum are tracked separately and reported exactly.
 *
 * <table border="1">
 *   <caption>Cost, with {@code d} the compression parameter</caption>
 *   <tr><th>Operation</th><th>Complexity</th></tr>
 *   <tr><td>{@link #add(double)}</td><td>O(log d) amortised</td></tr>
 *   <tr><td>{@link #quantile(double)}, {@link #cdf(double)}</td><td>O(d)</td></tr>
 *   <tr><td>{@link #merge(TDigest)}</td><td>O(d log d)</td></tr>
 *   <tr><td>memory</td><td>O(d), independent of the stream length</td></tr>
 * </table>
 *
 * <h2>Usage</h2>
 *
 * <pre>{@code
 * TDigest digest = new TDigest(100);
 * for (double latency : latencies) {
 *     digest.add(latency);
 * }
 * digest.quantile(0.5);   // median
 * digest.quantile(0.999); // deep tail, the case t-digest is built for
 * digest.cdf(250.0);      // share of samples below 250
 *
 * // Sketches computed on different shards combine the way partial sums do.
 * shardA.merge(shardB);
 * }</pre>
 *
 * <p>Query methods fold the pending insertion buffer in before answering, so they mutate internal
 * state; they are not safe to call concurrently. This class is not thread-safe.
 *
 * @see P2QuantileEstimator for a constant-memory estimator of one fixed quantile
 * @see <a href="https://arxiv.org/abs/1902.04023">T. Dunning, O. Ertl, Computing extremely accurate quantiles using t-digests</a>
 */
public final class TDigest {

    /** Below roughly this value the scale function stops leaving room for a useful number of centroids. */
    private static final double MIN_COMPRESSION = 10.0;

    private static final double TWO_PI = 2.0 * Math.PI;

    /** Buffered points per centroid slot; larger buffers mean fewer, bigger merge passes. */
    private static final int BUFFER_FACTOR = 5;

    /**
     * How many centroid slots to reserve per unit of compression. Repeated merges make centroids
     * atomic, so they pack less tightly than the scale function alone would suggest: in practice the
     * sketch settles around {@code 0.6 * compression} centroids rather than the ideal
     * {@code compression / 2}. Twice the compression leaves ample headroom.
     */
    private static final int CENTROID_SLOTS_PER_UNIT = 2;

    /** Below this length the parallel sort switches to insertion sort. */
    private static final int INSERTION_SORT_THRESHOLD = 16;

    private final double compression;

    private final double[] centroidMeans;
    private final double[] centroidWeights;
    private int centroidCount;

    private final double[] bufferMeans;
    private final double[] bufferWeights;
    private int bufferCount;

    private final double[] scratchMeans;
    private final double[] scratchWeights;

    private double totalWeight;
    private double min = Double.POSITIVE_INFINITY;
    private double max = Double.NEGATIVE_INFINITY;

    /**
     * Creates an empty digest with a compression of 100, a good default for most streams.
     */
    public TDigest() {
        this(100.0);
    }

    /**
     * Creates an empty digest.
     *
     * @param compression the size/accuracy trade-off; around {@code 0.6 * compression} centroids end
     *     up being kept, so larger values cost more memory and give smaller quantile errors
     * @throws IllegalArgumentException if {@code compression} is smaller than 10 or not finite
     */
    public TDigest(double compression) {
        if (!(compression >= MIN_COMPRESSION) || !Double.isFinite(compression)) {
            throw new IllegalArgumentException("The compression must be finite and at least " + MIN_COMPRESSION + ", but was " + compression);
        }
        this.compression = compression;

        int scale = (int) Math.ceil(compression) + 2;
        int centroidCapacity = CENTROID_SLOTS_PER_UNIT * scale;
        this.centroidMeans = new double[centroidCapacity];
        this.centroidWeights = new double[centroidCapacity];

        int bufferCapacity = BUFFER_FACTOR * scale;
        this.bufferMeans = new double[bufferCapacity];
        this.bufferWeights = new double[bufferCapacity];

        this.scratchMeans = new double[centroidCapacity + bufferCapacity];
        this.scratchWeights = new double[centroidCapacity + bufferCapacity];
    }

    /**
     * Adds one sample of unit weight.
     *
     * @param value the sample to add
     * @throws IllegalArgumentException if {@code value} is NaN or infinite
     */
    public void add(double value) {
        add(value, 1.0);
    }

    /**
     * Adds a sample that stands for several observations of the same value.
     *
     * @param value the sample to add
     * @param weight how many observations the sample represents, strictly positive
     * @throws IllegalArgumentException if {@code value} is not finite or {@code weight} is not strictly positive
     */
    public void add(double value, double weight) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Samples must be finite, but was " + value);
        }
        if (!(weight > 0.0) || !Double.isFinite(weight)) {
            throw new IllegalArgumentException("The weight must be finite and positive, but was " + weight);
        }
        if (bufferCount == bufferMeans.length) {
            flushBuffer();
        }
        bufferMeans[bufferCount] = value;
        bufferWeights[bufferCount] = weight;
        bufferCount++;
        min = Math.min(min, value);
        max = Math.max(max, value);
    }

    /**
     * Adds every given sample with unit weight.
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
     * Folds another digest into this one. The result approximates the union of the two streams, which
     * is what makes t-digests usable as a map-reduce style aggregate.
     *
     * @param other the digest to absorb; it is left unchanged apart from its pending buffer being folded in
     * @throws NullPointerException if {@code other} is {@code null}
     */
    public void merge(TDigest other) {
        other.flushBuffer();
        for (int i = 0; i < other.centroidCount; i++) {
            add(other.centroidMeans[i], other.centroidWeights[i]);
        }
        if (!other.isEmpty()) {
            min = Math.min(min, other.min);
            max = Math.max(max, other.max);
        }
    }

    /**
     * Estimates the value below which the given fraction of the stream lies.
     *
     * @param q the requested quantile, between 0 and 1 inclusive
     * @return the estimated quantile; exactly {@link #min()} for {@code q == 0} and {@link #max()} for {@code q == 1}
     * @throws IllegalArgumentException if {@code q} is outside {@code [0, 1]} or is NaN
     * @throws IllegalStateException if no sample has been added yet
     */
    public double quantile(double q) {
        if (!(q >= 0.0) || !(q <= 1.0)) {
            throw new IllegalArgumentException("The quantile probability must lie in [0, 1], but was " + q);
        }
        flushBuffer();
        requireNonEmpty();
        if (centroidCount == 1) {
            return centroidMeans[0];
        }

        double index = q * totalWeight;
        if (index <= 0.0) {
            return min;
        }
        if (index >= totalWeight) {
            return max;
        }

        // The centroids are treated as knots of a piecewise linear quantile function, each sitting at
        // the centre of the weight it carries, with min and max closing the two ends.
        double previousValue = min;
        double previousIndex = 0.0;
        double currentIndex = 0.0;
        for (int i = 0; i < centroidCount; i++) {
            currentIndex += i == 0 ? centroidWeights[0] / 2.0 : (centroidWeights[i - 1] + centroidWeights[i]) / 2.0;
            if (index < currentIndex) {
                return interpolate(previousIndex, previousValue, currentIndex, centroidMeans[i], index);
            }
            previousValue = centroidMeans[i];
            previousIndex = currentIndex;
        }
        return interpolate(previousIndex, previousValue, totalWeight, max, index);
    }

    /**
     * Estimates the fraction of the stream that is smaller than the given value, i.e. the empirical
     * cumulative distribution function.
     *
     * @param value the value to look up
     * @return a number in {@code [0, 1]}
     * @throws IllegalArgumentException if {@code value} is NaN
     * @throws IllegalStateException if no sample has been added yet
     */
    public double cdf(double value) {
        if (Double.isNaN(value)) {
            throw new IllegalArgumentException("The value must not be NaN");
        }
        flushBuffer();
        requireNonEmpty();
        if (value < min) {
            return 0.0;
        }
        if (value > max) {
            return 1.0;
        }
        if (max <= min) {
            return 0.5;
        }

        double previousValue = min;
        double previousIndex = 0.0;
        double currentIndex = 0.0;
        for (int i = 0; i < centroidCount; i++) {
            currentIndex += i == 0 ? centroidWeights[0] / 2.0 : (centroidWeights[i - 1] + centroidWeights[i]) / 2.0;
            if (value < centroidMeans[i]) {
                return interpolate(previousValue, previousIndex, centroidMeans[i], currentIndex, value) / totalWeight;
            }
            previousValue = centroidMeans[i];
            previousIndex = currentIndex;
        }
        return interpolate(previousValue, previousIndex, max, totalWeight, value) / totalWeight;
    }

    /**
     * Returns the smallest sample ever added, tracked exactly.
     *
     * @return the minimum
     * @throws IllegalStateException if no sample has been added yet
     */
    public double min() {
        requireAnySample();
        return min;
    }

    /**
     * Returns the largest sample ever added, tracked exactly.
     *
     * @return the maximum
     * @throws IllegalStateException if no sample has been added yet
     */
    public double max() {
        requireAnySample();
        return max;
    }

    /**
     * Returns the total weight of everything added, which for unweighted input is the number of
     * samples.
     *
     * @return the accumulated weight
     */
    public double totalWeight() {
        double pending = 0.0;
        for (int i = 0; i < bufferCount; i++) {
            pending += bufferWeights[i];
        }
        return totalWeight + pending;
    }

    /**
     * Returns how many centroids the sketch currently holds, after folding in pending insertions.
     *
     * @return the number of centroids, bounded by roughly {@code compression / 2}
     */
    public int centroidCount() {
        flushBuffer();
        return centroidCount;
    }

    /**
     * Returns the compression parameter.
     *
     * @return the value given at construction time
     */
    public double compression() {
        return compression;
    }

    /**
     * Tells whether the digest holds no samples.
     *
     * @return {@code true} if nothing has been added
     */
    public boolean isEmpty() {
        return centroidCount == 0 && bufferCount == 0;
    }

    /**
     * Forgets every sample.
     */
    public void reset() {
        centroidCount = 0;
        bufferCount = 0;
        totalWeight = 0.0;
        min = Double.POSITIVE_INFINITY;
        max = Double.NEGATIVE_INFINITY;
    }

    @Override
    public String toString() {
        return "TDigest{compression=" + compression + ", weight=" + totalWeight() + ", centroids=" + (isEmpty() ? 0 : centroidCount()) + '}';
    }

    /**
     * Folds every buffered point into the centroid list.
     *
     * <p>Buffer and centroids are concatenated, sorted by mean and then swept once from the smallest
     * mean upwards. The sweep keeps filling the current output centroid while doing so would not push
     * it past the weight limit given by the scale function at the quantile the centroid starts at;
     * otherwise the centroid is closed and a new one is opened.
     */
    private void flushBuffer() {
        if (bufferCount == 0) {
            return;
        }

        int size = centroidCount + bufferCount;
        System.arraycopy(centroidMeans, 0, scratchMeans, 0, centroidCount);
        System.arraycopy(centroidWeights, 0, scratchWeights, 0, centroidCount);
        System.arraycopy(bufferMeans, 0, scratchMeans, centroidCount, bufferCount);
        System.arraycopy(bufferWeights, 0, scratchWeights, centroidCount, bufferCount);
        bufferCount = 0;
        sortByMean(scratchMeans, scratchWeights, 0, size - 1);

        double weight = 0.0;
        for (int i = 0; i < size; i++) {
            weight += scratchWeights[i];
        }
        totalWeight = weight;

        int last = 0;
        centroidMeans[0] = scratchMeans[0];
        centroidWeights[0] = scratchWeights[0];
        double weightOfClosedCentroids = 0.0;
        double weightLimit = totalWeight * quantileLimit(0.0);

        for (int i = 1; i < size; i++) {
            double projected = centroidWeights[last] + scratchWeights[i];
            boolean roomLeft = weightOfClosedCentroids + projected <= weightLimit;
            if (roomLeft || last == centroidMeans.length - 1) {
                centroidWeights[last] = projected;
                centroidMeans[last] += (scratchMeans[i] - centroidMeans[last]) * scratchWeights[i] / projected;
            } else {
                weightOfClosedCentroids += centroidWeights[last];
                weightLimit = totalWeight * quantileLimit(weightOfClosedCentroids / totalWeight);
                last++;
                centroidMeans[last] = scratchMeans[i];
                centroidWeights[last] = scratchWeights[i];
            }
        }
        centroidCount = last + 1;
    }

    /**
     * Returns the quantile at which the centroid starting at quantile {@code q} must be closed, that
     * is {@code kInverse(k(q) + 1)} for the scale function {@code k}.
     */
    private double quantileLimit(double q) {
        // Rounding in the running weight sum could push the ratio a hair outside [0, 1], which would
        // turn asin into NaN and silently disable the size limit.
        double bounded = Math.min(1.0, Math.max(0.0, q));
        double k = compression / TWO_PI * Math.asin(2.0 * bounded - 1.0) + 1.0;
        double angle = k * TWO_PI / compression;
        if (angle >= Math.PI / 2.0) {
            return 1.0;
        }
        if (angle <= -Math.PI / 2.0) {
            return 0.0;
        }
        return (Math.sin(angle) + 1.0) / 2.0;
    }

    private void requireNonEmpty() {
        if (centroidCount == 0) {
            throw new IllegalStateException("The digest has not seen any sample yet");
        }
    }

    private void requireAnySample() {
        if (isEmpty()) {
            throw new IllegalStateException("The digest has not seen any sample yet");
        }
    }

    /**
     * Linear interpolation between two knots, degenerating to the right knot when they coincide.
     */
    private static double interpolate(double x0, double y0, double x1, double y1, double x) {
        if (x1 <= x0) {
            return y1;
        }
        return y0 + (x - x0) / (x1 - x0) * (y1 - y0);
    }

    /**
     * Sorts two parallel arrays by the values of the first one, using quicksort with a
     * median-of-three pivot and an insertion sort for short ranges.
     */
    private static void sortByMean(double[] means, double[] weights, int from, int to) {
        int low = from;
        int high = to;
        while (low < high) {
            if (high - low < INSERTION_SORT_THRESHOLD) {
                insertionSort(means, weights, low, high);
                return;
            }
            int pivotIndex = partition(means, weights, low, high);
            // Recurse into the smaller half and loop on the larger one to keep the stack shallow.
            if (pivotIndex - low < high - pivotIndex) {
                sortByMean(means, weights, low, pivotIndex - 1);
                low = pivotIndex + 1;
            } else {
                sortByMean(means, weights, pivotIndex + 1, high);
                high = pivotIndex - 1;
            }
        }
    }

    private static int partition(double[] means, double[] weights, int low, int high) {
        int middle = low + (high - low) / 2;
        if (means[middle] < means[low]) {
            swap(means, weights, low, middle);
        }
        if (means[high] < means[low]) {
            swap(means, weights, low, high);
        }
        if (means[high] < means[middle]) {
            swap(means, weights, middle, high);
        }
        swap(means, weights, middle, high - 1);
        double pivot = means[high - 1];

        int store = low;
        for (int i = low; i < high - 1; i++) {
            if (means[i] < pivot) {
                swap(means, weights, store, i);
                store++;
            }
        }
        swap(means, weights, store, high - 1);
        return store;
    }

    private static void insertionSort(double[] means, double[] weights, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            double mean = means[i];
            double weight = weights[i];
            int j = i - 1;
            while (j >= low && means[j] > mean) {
                means[j + 1] = means[j];
                weights[j + 1] = weights[j];
                j--;
            }
            means[j + 1] = mean;
            weights[j + 1] = weight;
        }
    }

    private static void swap(double[] means, double[] weights, int i, int j) {
        double mean = means[i];
        means[i] = means[j];
        means[j] = mean;
        double weight = weights[i];
        weights[i] = weights[j];
        weights[j] = weight;
    }

    /**
     * Returns the centroid means, smallest first. Exposed for tests and for inspecting the shape of
     * the sketch; the returned array is a copy.
     *
     * @return a copy of the centroid means
     */
    double[] centroidMeansSnapshot() {
        flushBuffer();
        return Arrays.copyOf(centroidMeans, centroidCount);
    }

    /**
     * Returns the centroid weights, aligned with {@link #centroidMeansSnapshot()}.
     *
     * @return a copy of the centroid weights
     */
    double[] centroidWeightsSnapshot() {
        flushBuffer();
        return Arrays.copyOf(centroidWeights, centroidCount);
    }
}
