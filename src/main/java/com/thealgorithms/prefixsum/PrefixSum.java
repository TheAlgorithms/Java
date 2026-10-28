package com.thealgorithms.prefixsum;

/**
 * A class that implements the Prefix Sum algorithm.
 *
 * <p>Prefix Sum is a technique used to preprocess an array such that
 * range sum queries can be answered in O(1) time.
 * The preprocessing step takes O(N) time.
 *
 * <p>This implementation uses a long array for the prefix sums to prevent
 * integer overflow when the sum of elements exceeds Integer.MAX_VALUE.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Prefix_sum">Prefix Sum (Wikipedia)</a>
 * @author Chahat Sandhu, <a href="https://github.com/singhc7">singhc7</a>
 */
public class PrefixSum {

    private final long[] prefixSums;

    /**
     * Constructor to preprocess the input array.
     *
     * @param array The input integer array.
     * @throws IllegalArgumentException if the array is null.
     */
    public PrefixSum(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }
        this.prefixSums = new long[array.length + 1];
        this.prefixSums[0] = 0;

        for (int i = 0; i < array.length; i++) {
            // Automatically promotes int to long during addition
            this.prefixSums[i + 1] = this.prefixSums[i] + array[i];
        }
    }

    /**
     * Calculates the sum of elements in the range [left, right].
     * Indices are 0-based.
     *
     * @param left  The starting index (inclusive).
     * @param right The ending index (inclusive).
     * @return The sum of elements from index left to right as a long.
     * @throws IndexOutOfBoundsException if indices are out of valid range.
     */
    public long sumRange(int left, int right) {
        if (left < 0 || right >= prefixSums.length - 1 || left > right) {
            throw new IndexOutOfBoundsException("Invalid range indices");
        }
        return prefixSums[right + 1] - prefixSums[left];
    }
}
