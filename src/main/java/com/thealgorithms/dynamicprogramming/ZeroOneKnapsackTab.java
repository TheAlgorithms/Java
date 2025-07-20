package com.thealgorithms.dynamicprogramming;

/**
 * Dynamic Programming approach for solving 0-1 Knapsack problem using tabulation.
 * 
 * Problem Statement:
 * Given weights and values of n items, put these items in a knapsack of capacity W 
 * to get the maximum total value in the knapsack. You cannot break an item.
 *
 * Time Complexity: O(n * W)
 * Space Complexity: O(n * W)
 */
public class ZeroOneKnapsackTab {

    /**
     * Solves the 0-1 Knapsack problem using a tabulation approach (bottom-up DP).
     *
     * @param val The values of the items
     * @param wt The weights of the items
     * @param W The total capacity of the knapsack
     * @param n The number of items
     * @return The maximum value that can be put in a knapsack of capacity W
     */
    public static int knapsackTab(int[] val, int[] wt, int W, int n) {
        int[][] dp = new int[n + 1][W + 1];

        for (int i = 1; i <= n; i++) {
            int v = val[i - 1]; // value of current item
            int w = wt[i - 1];  // weight of current item
            for (int j = 1; j <= W; j++) {
                if (w <= j) {
                    int include = v + dp[i - 1][j - w];
                    int exclude = dp[i - 1][j];
                    dp[i][j] = Math.max(include, exclude);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][W];
    }
}
