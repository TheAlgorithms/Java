package com.thealgorithms.streaming;

import java.util.Arrays;

/**
 * A streaming <b>median filter</b>: it reports the median of the last {@code windowSize} samples of
 * a signal.
 *
 * <p>The median filter is the standard first line of defence against impulsive noise. A single
 * corrupted sample - a dropped bit, a spike on an analogue line, a sensor glitch - drags a moving
 * average with it, but it cannot drag a median: as long as fewer than half of the samples in the
 * window are corrupted, the output stays on the true signal. Unlike a linear low-pass filter, a
 * median filter also preserves sharp edges instead of smearing them.
 *
 * <p>Two arrays of {@code windowSize} doubles are kept: the samples in arrival order, so the oldest
 * one can be evicted, and the same samples in ascending order, so the median is the middle element.
 * Insertion and removal in the sorted copy are binary search plus a memory move, which makes an
 * update O(w) in the worst case but with a very small constant, and nothing is allocated after
 * construction.
 *
 * <h2>Usage</h2>
 *
 * <pre>{@code
 * MedianFilter filter = new MedianFilter(5);
 * for (double sample : signal) {
 *     double clean = filter.accept(sample);
 * }
 * }</pre>
 *
 * <p>The filter is causal: the value it reports for a sample is the median of that sample and the
 * {@code windowSize - 1} preceding ones, so the output lags the input by roughly half a window.
 * Before the window is full the median is taken over however many samples have arrived. With an even
 * window size the median is the average of the two middle samples.
 *
 * <p>This class is not thread-safe.
 *
 * @see HampelFilter for a variant that only replaces samples identified as outliers
 * @see <a href="https://en.wikipedia.org/wiki/Median_filter">Median filter</a>
 */
public final class MedianFilter {

    private final double[] arrivalOrder;
    private final double[] ascending;
    private final int windowSize;

    private int size;
    private int oldest;

    /**
     * Creates an empty filter.
     *
     * @param windowSize how many recent samples the median is taken over; odd sizes are the usual choice
     * @throws IllegalArgumentException if {@code windowSize} is not positive
     */
    public MedianFilter(int windowSize) {
        if (windowSize <= 0) {
            throw new IllegalArgumentException("The window size must be positive, but was " + windowSize);
        }
        this.windowSize = windowSize;
        this.arrivalOrder = new double[windowSize];
        this.ascending = new double[windowSize];
    }

    /**
     * Feeds one sample into the filter.
     *
     * @param value the incoming sample
     * @return the median of the window that now ends at this sample
     * @throws IllegalArgumentException if {@code value} is NaN or infinite
     */
    public double accept(double value) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Samples must be finite, but was " + value);
        }
        if (size == windowSize) {
            removeFromSorted(arrivalOrder[oldest]);
            size--;
        }
        insertIntoSorted(value);
        arrivalOrder[oldest] = value;
        oldest = (oldest + 1) % windowSize;
        size++;
        return median();
    }

    /**
     * Filters a whole signal, one sample at a time, starting from the current state.
     *
     * @param signal the samples to filter
     * @return a new array of the same length holding the running medians
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
     * Returns the median of the samples currently in the window.
     *
     * @return the median, averaging the two middle samples when the window holds an even number of them
     * @throws IllegalStateException if no sample has been accepted yet
     */
    public double median() {
        if (size == 0) {
            throw new IllegalStateException("The window is empty");
        }
        int middle = size / 2;
        if (size % 2 != 0) {
            return ascending[middle];
        }
        return 0.5 * (ascending[middle - 1] + ascending[middle]);
    }

    /**
     * Returns the smallest sample currently in the window.
     *
     * @return the minimum of the window
     * @throws IllegalStateException if no sample has been accepted yet
     */
    public double min() {
        requireNonEmpty();
        return ascending[0];
    }

    /**
     * Returns the largest sample currently in the window.
     *
     * @return the maximum of the window
     * @throws IllegalStateException if no sample has been accepted yet
     */
    public double max() {
        requireNonEmpty();
        return ascending[size - 1];
    }

    /**
     * Returns the configured window length.
     *
     * @return the window size given at construction time
     */
    public int windowSize() {
        return windowSize;
    }

    /**
     * Returns how many samples the window currently holds.
     *
     * @return the current fill level, at most {@link #windowSize()}
     */
    public int size() {
        return size;
    }

    /**
     * Tells whether the window has warmed up.
     *
     * @return {@code true} once the window holds {@link #windowSize()} samples
     */
    public boolean isFull() {
        return size == windowSize;
    }

    /**
     * Tells whether the window holds no samples.
     *
     * @return {@code true} if nothing has been accepted since the last reset
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the window contents in ascending order.
     *
     * @return a new array holding the sorted window
     */
    public double[] sortedWindow() {
        return Arrays.copyOf(ascending, size);
    }

    /**
     * Empties the window.
     */
    public void reset() {
        size = 0;
        oldest = 0;
    }

    @Override
    public String toString() {
        return "MedianFilter{windowSize=" + windowSize + ", size=" + size + ", median=" + (size == 0 ? Double.NaN : median()) + '}';
    }

    /**
     * Copies the sorted window into a caller supplied buffer, which lets tight loops avoid allocating.
     *
     * @param destination buffer of at least {@link #windowSize()} elements
     * @return the number of values written
     */
    int copySortedWindow(double[] destination) {
        System.arraycopy(ascending, 0, destination, 0, size);
        return size;
    }

    private void insertIntoSorted(double value) {
        int position = Arrays.binarySearch(ascending, 0, size, value);
        if (position < 0) {
            position = -(position + 1);
        }
        System.arraycopy(ascending, position, ascending, position + 1, size - position);
        ascending[position] = value;
    }

    private void removeFromSorted(double value) {
        int position = Arrays.binarySearch(ascending, 0, size, value);
        System.arraycopy(ascending, position + 1, ascending, position, size - position - 1);
    }

    private void requireNonEmpty() {
        if (size == 0) {
            throw new IllegalStateException("The window is empty");
        }
    }
}
