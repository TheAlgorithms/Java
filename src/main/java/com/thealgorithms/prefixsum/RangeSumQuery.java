package com.thealgorithms.prefixsum;

/**
 * Implements an algorithm to efficiently compute the sum of elements
 * between any two indices in an integer array using the Prefix Sum technique.
 *
 * <p>
 * Given an array nums, this algorithm precomputes the prefix sum array
 * to allow O(1) sum queries for any range [left, right].
 * </p>
 *
 * <p>
 * Let prefixSum[i] be the sum of elements from index 0 to i-1.
 * The sum of elements from left to right is:
 *
 * <pre>
 * prefixSum[right + 1] - prefixSum[left]
 * </pre>
 * </p>
 *
 * <p>
 * <strong>Time Complexity:</strong> O(N) for preprocessing, O(1) per query<br>
 * <strong>Space Complexity:</strong> O(N)
 * </p>
 *
 * @author Ruturaj Jadhav, <a href="https://github.com/ruturajjadhav07">ruturajjadhav07</a>
 */
public final class RangeSumQuery {

    private RangeSumQuery() {
        // Utility class; prevent instantiation
    }

    /**
     * Computes the prefix sum array for efficient range queries.
     *
     * @param nums The input integer array.
     * @return Prefix sum array where prefixSum[i+1] = sum of nums[0..i].
     * @throws IllegalArgumentException if nums is null.
     */
    public static int[] buildPrefixSum(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }

        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        return prefixSum;
    }

    /**
     * Returns the sum of elements from index left to right (inclusive)
     * using the provided prefix sum array.
     *
     * @param prefixSum The prefix sum array computed using buildPrefixSum.
     * @param left      The start index (inclusive).
     * @param right     The end index (inclusive).
     * @return The sum of elements in the range [left, right].
     * @throws IllegalArgumentException if indices are invalid.
     */
    public static int sumRange(int[] prefixSum, int left, int right) {
        if (prefixSum == null) {
            throw new IllegalArgumentException("Prefix sum array cannot be null");
        }
        if (left < 0 || right >= prefixSum.length - 1 || left > right) {
            throw new IllegalArgumentException("Invalid range indices");
        }
        return prefixSum[right + 1] - prefixSum[left];
    }
}
