package com.thealgorithms.recursion;

public class LongestIncreasingSubsequence {

    public static int solve(int[] nums, int prev, int idx) {
        // base case
        if (idx == nums.length) {
            return 0;
        }

        // process
        int include = 0;
        if (prev == -1 || nums[idx] > nums[prev]) {
            include = 1 + solve(nums, idx, idx + 1);
        }
        int exclude = solve(nums, prev, idx + 1);
        return Math.max(include, exclude);
    }

    public int lengthOfLIS(int[] nums) {
        int prev = -1;
        return solve(nums, prev, 0);
    }

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
        System.out.println("Length of LIS: " + lis.lengthOfLIS(nums));
    }
}
