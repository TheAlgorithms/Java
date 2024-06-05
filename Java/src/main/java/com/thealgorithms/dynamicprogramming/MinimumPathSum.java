package com.thealgorithms.dynamicprogramming;

/*
Given the following grid with length m and width n:
\---\---\---\ (n)
\ 1 \ 3 \ 1 \
\---\---\---\
\ 1 \ 5 \ 1 \
\---\---\---\
\ 4 \ 2 \ 1 \
\---\---\---\
(m)
Find the path where its sum is the smallest.

The Time Complexity of your algorithm should be smaller than or equal to O(mn).
The Space Complexity of your algorithm should be smaller than or equal to O(n).
You can only move from the top left corner to the down right corner.
You can only move one step down or right.

EXAMPLE:
INPUT: grid = [[1,3,1],[1,5,1],[4,2,1]]
OUTPUT: 7
EXPLANATIONS: 1 + 3 + 1 + 1 + 1 = 7

For more information see https://www.geeksforgeeks.org/maximum-path-sum-matrix/
 */
public final class MinimumPathSum {

    private MinimumPathSum() {
    }

    public static int minimumPathSum(final int[][] grid) {
        int numRows = grid.length;
        int numCols = grid[0].length;

        if (numCols == 0) {
            return 0;
        }

        int[] dp = new int[numCols];

        // Initialize the first element of the dp array
        dp[0] = grid[0][0];

        // Calculate the minimum path sums for the first row
        for (int col = 1; col < numCols; col++) {
            dp[col] = dp[col - 1] + grid[0][col];
        }

        // Calculate the minimum path sums for the remaining rows
        for (int row = 1; row < numRows; row++) {
            // Update the minimum path sum for the first column
            dp[0] += grid[row][0];

            for (int col = 1; col < numCols; col++) {
                // Choose the minimum path sum from the left or above
                dp[col] = Math.min(dp[col - 1], dp[col]) + grid[row][col];
            }
        }

        // Return the minimum path sum for the last cell in the grid
        return dp[numCols - 1];
    }
}
