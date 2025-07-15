package com.thealgorithms.dynamicprogramming;

/**
 * Utility class for solving the Subset Sum problem using a space-optimized dynamic programming approach.
 *
 * <p>This algorithm determines whether any subset of a given array sums up to a specific target value.</p>
 *
 * <p><b>Time Complexity:</b> O(n * sum)</p>
 * <p><b>Space Complexity:</b> O(sum)</p>
 */
public final class SubsetSumSpaceOptimized {
    private SubsetSumSpaceOptimized() {
    }

    /**
     * Determines whether there exists a subset of the given array that adds up to the specified sum.
     * This method uses a space-optimized dynamic programming approach with a 1D boolean array.
     *
     * @param nums The array of non-negative integers
     * @param targetSum The desired subset sum
     * @return {@code true} if such a subset exists, {@code false} otherwise
     */
    public static boolean isSubsetSum(int[] nums, int targetSum) {
        if (targetSum < 0) {
            return false; // Subset sum can't be negative
        }

        boolean[] dp = new boolean[targetSum + 1];
        dp[0] = true; // Empty subset always sums to 0

        for (int number : nums) {
            for (int j = targetSum; j >= number; j--) {
                dp[j] = dp[j] || dp[j - number];
            }
        }

        return dp[targetSum];
    }
}
