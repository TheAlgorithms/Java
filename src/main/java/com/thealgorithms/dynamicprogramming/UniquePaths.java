/**
 * Author: Siddhant Swarup Mallick
 * Github: https://github.com/siddhant2002
 * <p>
 * Problem Description:
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * The robot can only move either down or right at any point in time.
 * The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * How many possible unique paths are there?
 * <p>
 * Program Description:
 * This program calculates the number of unique paths possible for a robot to reach the bottom-right corner
 * of an m x n grid using dynamic programming.
 */

package com.thealgorithms.dynamicprogramming;

import java.util.Arrays;

public final class UniquePaths {

    private UniquePaths() {
    }

    /**
     * Calculates the number of unique paths using a 1D dynamic programming array.
     * Time complexity O(n*m)
     * Space complexity O(min(n,m))
     *
     * @param m The number of rows in the grid.
     * @param n The number of columns in the grid.
     * @return The number of unique paths.
     */
    public static int uniquePaths(final int m, final int n) {
        if (m > n) {
            return uniquePaths(n, m); // Recursive call to handle n > m cases
        }
        int[] dp = new int[n]; // Create a 1D array to store unique paths for each column
        Arrays.fill(dp, 1); // Initialize all values to 1 (one way to reach each cell)
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] = Math.addExact(dp[j], dp[j - 1]); // Update the number of unique paths for each cell
            }
        }
        return dp[n - 1]; // The result is stored in the last column of the array
    }

    /**
     * Calculates the number of unique paths using a 2D dynamic programming array.
     * Time complexity O(n*m)
     * Space complexity O(n*m)
     *
     * @param m The number of rows in the grid.
     * @param n The number of columns in the grid.
     * @return The number of unique paths.
     */
    public static int uniquePaths2(final int m, final int n) {
        int[][] dp = new int[m][n]; // Create a 2D array to store unique paths for each cell
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1; // Initialize the first column to 1 (one way to reach each cell)
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1; // Initialize the first row to 1 (one way to reach each cell)
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.addExact(dp[i - 1][j], dp[i][j - 1]); // Update the number of unique paths for each cell
            }
        }
        return dp[m - 1][n - 1]; // The result is stored in the bottom-right cell of the array
    }
}
