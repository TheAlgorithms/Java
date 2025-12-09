package com.thealgorithms.devutils;

import java.util.Arrays;

/**
 * Computes the maximum alternating sum based on the squared values of the input array.
 *
 * Steps:
 * 1. Convert nums[i] to nums[i]^2
 * 2. Sort the squared array
 * 3. Select the largest (n + 1) / 2 elements
 * 4. Use formula: result = 2 * sum(selected) - totalSum
 *
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 */
public class MaxAlternatingSum {

    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squared = new long[n];

        for (int i = 0; i < n; i++) {
            squared[i] = (long) nums[i] * nums[i];
        }

        Arrays.sort(squared);

        int k = (n + 1) / 2;

        long total = 0;
        for (long v : squared) {
            total += v;
        }

        long maxHalf = 0;
        for (int i = n - 1; i >= n - k; i--) {
            maxHalf += squared[i];
        }

        return 2 * maxHalf - total;
    }
}
