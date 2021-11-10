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

All numbers given are positive.
The Time Complexity of your algorithm should be smaller than or equal to O(mn).
The Space Complexity of your algorithm should be smaller than or equal to O(mn).
You can only move from the top left corner to the down right corner.
You can only move one step down or right.

EXAMPLE:
INPUT: grid = [[1,3,1],[1,5,1],[4,2,1]]
OUTPUT: 7
EXPLANATIONS: 1 + 3 + 1 + 1 + 1 = 7

For more information see https://www.geeksforgeeks.org/maximum-path-sum-matrix/
 */
public class MinimumPathSum {

    public void testRegular() {
        int[][] grid = {
            {1, 3, 1},
            {1, 5, 1},
            {4, 2, 1}
        };
        System.out.println(minimumPathSum(grid));
    }

    public void testLessColumns() {
        int[][] grid = {
            {1, 2},
            {5, 6},
            {1, 1}
        };
        System.out.println(minimumPathSum(grid));
    }

    public void testLessRows() {
        int[][] grid = {
            {2, 3, 3},
            {7, 2, 1}
        };
        System.out.println(minimumPathSum(grid));
    }

    public void testOneRowOneColumn() {
        int[][] grid = {{2}};
        System.out.println(minimumPathSum(grid));
    }

    public static int minimumPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (n == 0) {
            return 0;
        }
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 0; i < n - 1; i++) {
            dp[0][i + 1] = dp[0][i] + grid[0][i + 1];
        }
        for (int i = 0; i < m - 1; i++) {
            dp[i + 1][0] = dp[i][0] + grid[i + 1][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }
}
