package com.thealgorithms.dynamicprogramming;

import java.util.HashMap;

@SuppressWarnings({"rawtypes", "unchecked"})
final class LongestArithmeticSubsequence {
    private LongestArithmeticSubsequence() {
    }

    /**
     * Returns the length of the longest arithmetic subsequence in the given array.
     *
     * A sequence seq is arithmetic if seq[i + 1] - seq[i] are all the same value
     * (for 0 <= i < seq.length - 1).
     *
     * @param nums the input array of integers
     * @return the length of the longest arithmetic subsequence
     */
    public static int getLongestArithmeticSubsequenceLength(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }

        if (nums.length <= 1) {
            return nums.length;
        }

        HashMap<Integer, Integer>[] dp = new HashMap[nums.length];
        int maxLength = 2;

        // fill the dp array
        for (int i = 0; i < nums.length; i++) {
            dp[i] = new HashMap<>();
            for (int j = 0; j < i; j++) {
                final int diff = nums[i] - nums[j];
                dp[i].put(diff, dp[j].getOrDefault(diff, 1) + 1);
                maxLength = Math.max(maxLength, dp[i].get(diff));
            }
        }

        return maxLength;
    }
}
