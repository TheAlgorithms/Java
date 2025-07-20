package com.thealgorithms.dynamicprogramming;

/**
 * The {@code ZeroOneKnapsackTab} class provides a method to solve the 0-1 Knapsack problem
 * using dynamic programming (tabulation approach).
 *
 * <p>0-1 Knapsack Problem -
 * Given weights and values of n items, and a maximum weight W,
 * determine the maximum total value of items that can be included in the knapsack
 * such that their total weight does not exceed W. Each item can be picked only once.
 *
 * Problem Link: https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/
 */
public final class ZeroOneKnapsackTab {

    private ZeroOneKnapsackTab() {
        // prevent instantiation
    }

    /**
     * Solves the 0-1 Knapsack problem using the bottom-up tabulation technique.
     *
     * @param val the values of the items
     * @param wt the weights of the items
     * @param W the total capacity of the knapsack
     * @param n the number of items
     * @return the maximum value that can be put in the knapsack
     */
    public static int compute(int[] val, int[] wt, int W, int n) {
        int[][] dp = new int[n + 1][W + 1];

        for (int i = 1; i <= n; i++) {
            int value = val[i - 1];
            int weight = wt[i - 1];

            for (int w = 1; w <= W; w++) {
                if (weight <= w) {
                    int include = value + dp[i - 1][w - weight];
                    int exclude = dp[i - 1][w];
                    dp[i][w] = Math.max(include, exclude);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[n][W];
    }
}
