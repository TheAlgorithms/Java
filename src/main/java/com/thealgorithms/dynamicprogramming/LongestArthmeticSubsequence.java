package com.thealgorithms.dynamicprogramming;

import java.util.HashMap;

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
        // If the array is empty or has only one element, return its length.
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return 1;
        }

        int n = nums.length;
        HashMap<Integer, Integer>[] dp = new HashMap[n];
        int maxLength = 2;

        // Initialize dp array
        for (int i = 0; i < n; i++) {
            dp[i] = new HashMap<>();
            for (int j = 0; j < i; j++) {
                int diff = nums[i] - nums[j];
                dp[i].put(diff, dp[j].getOrDefault(diff, 1) + 1);
                maxLength = Math.max(maxLength, dp[i].get(diff));
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        int[] nums = {3, 6, 9, 12, 15};
        int length = getLongestArithmeticSubsequenceLength(nums);

        // Print the result
        System.out.println("Array: ");
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
        System.out.println("Length of the Longest Arithmetic Subsequence: " + length);
    }
}
