/**
 * This class contains a method to solve the Sherlock and Cost problem using dynamic programming.
 * The problem is to find the maximum possible sum of absolute differences between adjacent elements in an array.
 *
 * @author gowtham sankar gunasekaran
 */

package com.thealgorithms.dynamicprogramming;

import java.util.List;

public final class SherLockAndCost {
    private SherLockAndCost() {
    }

    /**
     * This method takes a list of integers as input and returns the maximum possible sum of absolute differences between adjacent elements in the array.
     *
     * @param list the input list of integers
     * @return the maximum possible sum of absolute differences between adjacent elements in the array
     * <p></p>
     * Approach:
     * I can use dynamic programming to solve this problem efficiently. Let’s break down the approach:
     * <p>
     * 1.) Initialize two arrays: dp[i][0] and dp[i][1]. These arrays will store the maximum sum of absolute differences for the first i elements of the input array.
     * 2.) Iterate through the input array from left to right:
     * Update dp[i][0] and dp[i][1] based on the previous values and the current element.
     * 3.) The final answer is the maximum value between dp[N-1][0] and dp[N-1][1].
     * 4.) The time complexity of this method is O(N), where N is the length of the input list.
     */
    public static int sherlockAndCostProblem(List<Integer> list) {

        if (list == null || list.isEmpty()) {
            return 0;
        }

        int N = list.size();
        int[][] dp = new int[N][2];
        dp[0][0] = 0;
        dp[0][1] = 0;

        for (int i = 1; i < N; i++) {
            int curr = list.get(i);
            int prev = list.get(i - 1);

            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prev - 1);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + curr - 1);
        }

        return Math.max(dp[N - 1][0], dp[N - 1][1]);
    }
}
