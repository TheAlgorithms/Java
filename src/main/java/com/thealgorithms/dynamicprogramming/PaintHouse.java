package com.thealgorithms.dynamicprogramming;
/**
 *
 * Author: Janmesh Singh
 * Github: https://github.com/janmeshjs

 * Problem Statement: You are given a row of houses, each painted with one of k different colors.
 * The cost of painting the i-th house with color j is represented by costs[i][j].
 * You need to paint all the houses such that no two adjacent houses have the same color.
 * Find the minimum total cost to achieve this.
 *
 */

public class PaintHouse {

    public static int paintCost(int[][] costs) {

        if (costs == null || costs.length == 0) {
            return 0;
        }
        int n = costs.length;
        int k = costs[0].length;

        int[][] dp = new int[n][k];

        // Initialize the first row of dp with the costs of painting the first house
        for (int j = 0; j < k; j++) {
            dp[0][j] = costs[0][j];
        }

        // Iterate through the houses starting from the 2nd house
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < k; j++) {
                // Calculate the minimum cost for painting the current house with color j
                dp[i][j] = costs[i][j] + minCostOfPreviousHouses(dp, i - 1, j);
            }
        }

        // Find the minimum cost among the last row of dp
        int minCost = Integer.MAX_VALUE;
        for (int j = 0; j < k; j++) {
            minCost = Math.min(minCost, dp[n - 1][j]);
        }

        return minCost;
    }
    private static int minCostOfPreviousHouses(int[][] dp, int i, int j) {
        int minCost = Integer.MAX_VALUE;
        for (int color = 0; color < dp[i].length; color++) {
            if (color != j) {
                minCost = Math.min(minCost, dp[i][color]);
            }
        }
        return minCost;
    }
}
