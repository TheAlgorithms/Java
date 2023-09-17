package com.thealgorithms.dynamicprogramming;

/**
 * Author Manan Solanki - https://github.com/Manan-09
 * Stone game - Dynamic programming
 * Alice and Bob play a game with piles of stones. There are  number of piles arranged in a row,
 * and each pile has a positive integer number of stones[i].
 * <p>
 * The objective of the game is to end with the most stones.The total number of stones across all the piles is odd,
 * so there are no ties.
 * <p>
 * Alice and Bob take turns, with Alice starting first. Each turn,
 * a player takes the entire pile of stones either from the beginning or from the end of the row.
 * This continues until there are no more piles left, at which point the person with the most stones wins.
 * <p>
 * Assuming Alice and Bob play optimally, return if Alice can win the game or not.
 *
 * Time complexity - O(n^2)
 * Space complexity - O(n^2)
 */
public class StoneGame {

    private StoneGame(){};

    public static boolean stoneGame(int[] stones) {
        int n = stones.length;

        // Create a 2D DP array to store the maximum total value difference between Alex and Lee
        int[][] dp = new int[n][n];

        // Initialize the DP array with the values of the stones themselves
        for (int i = 0; i < n; i++) {
            dp[i][i] = stones[i];
        }

        // Build the DP array bottom-up
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;

                // Calculate the maximum difference if Alex chooses the first stone
                int chooseFirst = stones[i] - dp[i + 1][j];

                // Calculate the maximum difference if Alex chooses the last stone
                int chooseLast = stones[j] - dp[i][j - 1];

                // Alex will choose the option that maximizes the difference
                dp[i][j] = Math.max(chooseFirst, chooseLast);
            }
        }

        return dp[0][n - 1] > 0;
    }
}
