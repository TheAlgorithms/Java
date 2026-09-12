package com.thealgorithms.streaming;

import java.util.Arrays;

/**
 * The <b>P-square (P²) algorithm</b> of Jain and Chlamtac: it estimates one quantile of a stream
 * using five numbers and no memory of the samples themselves.
 *
 * <p>Computing an exact quantile requires storing everything seen so far, which is impossible for an
 * unbounded stream. P² instead keeps five <i>markers</i> that track the minimum, the quantile
 * {@code p/2}, the quantile {@code p} itself, the quantile {@code (1 + p)/2} and the maximum. Each
 * marker has a height (its current value) and a position (how many samples are known to be below
 * it). Every new sample shifts the positions of the markers it falls below, and each marker is then
 * nudged back towards the position it is supposed to occupy. The nudge follows a piecewise
 * parabolic prediction fitted through the three neighbouring markers, falling back to a linear one
 * whenever the parabola would break the ordering of the heights.
 *
 * <p>Accuracy is typically a fraction of a percent in rank for stationary streams and improves as
 * more samples arrive. The markers adapt continuously, so a drifting distribution is tracked rather
 * than averaged away, but no accuracy bound is guaranteed for adversarial inputs.
 *
 * <table border="1">
 *   <caption>Cost</caption>
 *   <tr><th>Operation</th><th>Complexity</th></tr>
 *   <tr><td>{@link #add(double)}</td><td>O(1)</td></tr>
 *   <tr><td>{@link #quantile()}</td><td>O(1)</td></tr>
 *   <tr><td>memory</td><td>O(1) - five markers, independent of the stream length</td></tr>
 * </table>
 *
 * <h2>Usage</h2>
 *
 * <pre>{@code
 * P2QuantileEstimator p99 = new P2QuantileEstimator(0.99);
 * for (double latency : latencies) {
 *     p99.add(latency);
 * }
 * double tail = p99.quantile();
 * }</pre>
 *
 * <p>The first five samples are stored verbatim, so the estimate is exact until the sixth one
 * arrives. This class is not thread-safe.
 *
 * @see <a href="https://www.cse.wustl.edu/~jain/papers/ftp/psqr.pdf">R. Jain, I. Chlamtac, The P-square algorithm (1985)</a>
 */
public final class P2QuantileEstimator {

    private static final int MARKERS = 5;

    private final double probability;

    /** Marker heights, kept in non-decreasing order; also the raw buffer for the first five samples. */
    private final double[] heights = new double[MARKERS];

    /** One-based rank of each marker, i.e. how many samples are known to sit at or below it. */
    private final int[] positions = new int[MARKERS];

    /** Position each marker should ideally occupy. */
    private final double[] desiredPositions = new double[MARKERS];

    /** How much {@link #desiredPositions} grows per sample. */
    private final double[] positionIncrements = new double[MARKERS];

    private long count;

    /**
     * Creates an estimator for a single quantile.
     *
     * @param probability the quantile to track, strictly between 0 and 1, e.g. {@code 0.5} for the median
     * @throws IllegalArgumentException if {@code probability} is outside {@code (0, 1)}
     */
    public P2QuantileEstimator(double probability) {
        if (!(probability > 0.0) || !(probability < 1.0)) {
            throw new IllegalArgumentException("The quantile probability must lie strictly between 0 and 1, but was " + probability);
        }
        this.probability = probability;
    }

    /**
     * Incorporates one sample.
     *
     * @param value the sample to add
     * @throws IllegalArgumentException if {@code value} is NaN or infinite
     */
    public void add(double value) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Samples must be finite, but was " + value);
        }
        if (count < MARKERS) {
            collectInitialSample(value);
            return;
        }

        int cell = locate(value);
        for (int i = cell + 1; i < MARKERS; i++) {
            positions[i]++;
        }
        for (int i = 0; i < MARKERS; i++) {
            desiredPositions[i] += positionIncrements[i];
        }
        for (int i = 1; i < MARKERS - 1; i++) {
            adjustMarker(i);
        }
        count++;
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
     * Returns the current estimate of the tracked quantile.
     *
     * @return the estimated quantile, exact while fewer than five samples have been seen
     * @throws IllegalStateException if no sample has been added yet
     */
    public double quantile() {
        requireNonEmpty();
        if (count < MARKERS) {
            return exactQuantileOfInitialSamples();
        }
        return heights[2];
    }

    /**
     * Returns the smallest sample seen so far, which P² tracks exactly.
     *
     * @return the running minimum
     * @throws IllegalStateException if no sample has been added yet
     */
    public double min() {
        requireNonEmpty();
        return count < MARKERS ? Arrays.stream(heights, 0, (int) count).min().orElse(Double.NaN) : heights[0];
    }

    /**
     * Returns the largest sample seen so far, which P² tracks exactly.
     *
     * @return the running maximum
     * @throws IllegalStateException if no sample has been added yet
     */
    public double max() {
        requireNonEmpty();
        return count < MARKERS ? Arrays.stream(heights, 0, (int) count).max().orElse(Double.NaN) : heights[MARKERS - 1];
    }

    /**
     * Returns the quantile this estimator was configured for.
     *
     * @return the probability given at construction time
     */
    public double probability() {
        return probability;
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
     * @return {@code true} if the estimator holds no samples
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Forgets every sample.
     */
    public void reset() {
        count = 0;
        Arrays.fill(heights, 0.0);
        Arrays.fill(positions, 0);
        Arrays.fill(desiredPositions, 0.0);
        Arrays.fill(positionIncrements, 0.0);
    }

    @Override
    public String toString() {
        return "P2QuantileEstimator{p=" + probability + ", count=" + count + ", quantile=" + (count == 0 ? Double.NaN : quantile()) + '}';
    }

    /**
     * Stores one of the first five samples and, on the fifth, lays out the markers.
     */
    private void collectInitialSample(double value) {
        heights[(int) count] = value;
        count++;
        if (count < MARKERS) {
            return;
        }
        Arrays.sort(heights);
        // Marker positions are one-based, exactly as in the paper. Only differences of positions are
        // ever used, so the origin is arithmetically irrelevant - but the desired positions are grown
        // by repeated addition, and starting them one lower would round differently and flip the
        // strict comparisons below on the samples where a desired position lands on a whole number.
        for (int i = 0; i < MARKERS; i++) {
            positions[i] = i + 1;
        }
        desiredPositions[0] = 1.0;
        desiredPositions[1] = 1.0 + 2.0 * probability;
        desiredPositions[2] = 1.0 + 4.0 * probability;
        desiredPositions[3] = 3.0 + 2.0 * probability;
        desiredPositions[4] = 5.0;
        positionIncrements[0] = 0.0;
        positionIncrements[1] = probability / 2.0;
        positionIncrements[2] = probability;
        positionIncrements[3] = (1.0 + probability) / 2.0;
        positionIncrements[4] = 1.0;
    }

    /**
     * Finds the cell the sample falls into, stretching the outer markers when it falls outside the
     * range seen so far.
     *
     * @param value the incoming sample
     * @return the index of the marker immediately below the sample
     */
    private int locate(double value) {
        if (value < heights[0]) {
            heights[0] = value;
            return 0;
        }
        for (int i = 1; i < MARKERS - 1; i++) {
            if (value < heights[i]) {
                return i - 1;
            }
        }
        if (value > heights[MARKERS - 1]) {
            heights[MARKERS - 1] = value;
        }
        return MARKERS - 2;
    }

    /**
     * Moves one inner marker by a single position if it has drifted too far from where it should be.
     */
    private void adjustMarker(int i) {
        double drift = desiredPositions[i] - positions[i];
        boolean shiftRight = drift >= 1.0 && positions[i + 1] - positions[i] > 1;
        boolean shiftLeft = drift <= -1.0 && positions[i - 1] - positions[i] < -1;
        if (!shiftRight && !shiftLeft) {
            return;
        }

        int direction = shiftRight ? 1 : -1;
        double candidate = parabolicPrediction(i, direction);
        heights[i] = heights[i - 1] < candidate && candidate < heights[i + 1] ? candidate : linearPrediction(i, direction);
        positions[i] += direction;
    }

    /**
     * Piecewise parabolic prediction: fits a parabola through markers {@code i - 1}, {@code i} and
     * {@code i + 1} and evaluates it one position away from the current one.
     */
    private double parabolicPrediction(int i, int direction) {
        double left = positions[i] - positions[i - 1];
        double right = positions[i + 1] - positions[i];
        // Grouped exactly as in the paper. The result then decides, by a strict comparison against the
        // neighbouring heights, whether the parabola is used at all, so regrouping the arithmetic would
        // flip that decision on knife-edge inputs and make this estimator drift away from the reference.
        return heights[i] + direction / (left + right) * ((left + direction) * (heights[i + 1] - heights[i]) / right + (right - direction) * (heights[i] - heights[i - 1]) / left);
    }

    /**
     * Linear fallback used whenever the parabolic prediction would violate the ordering of the
     * marker heights.
     */
    private double linearPrediction(int i, int direction) {
        int neighbour = i + direction;
        return heights[i] + direction * (heights[neighbour] - heights[i]) / (positions[neighbour] - positions[i]);
    }

    /**
     * Exact quantile of the fewer than five samples buffered so far, by linear interpolation between
     * the two order statistics surrounding the requested rank.
     */
    private double exactQuantileOfInitialSamples() {
        double[] sorted = Arrays.copyOf(heights, (int) count);
        Arrays.sort(sorted);
        if (sorted.length == 1) {
            return sorted[0];
        }
        double rank = probability * (sorted.length - 1);
        int lower = (int) Math.floor(rank);
        int upper = Math.min(lower + 1, sorted.length - 1);
        return sorted[lower] + (rank - lower) * (sorted[upper] - sorted[lower]);
    }

    private void requireNonEmpty() {
        if (count == 0) {
            throw new IllegalStateException("The estimator has not seen any sample yet");
        }
    }
}
