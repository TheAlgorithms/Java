package com.thealgorithms.dynamicprogramming;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * The robot can only move either down or right at any point in time.
 * The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * How many possible unique paths are there?
 */

public class UniquePaths {
    public static void main(String[] args) {
        int m = 3;
        int n = 7;
        System.out.println(uniquePaths2(3,7)); // result 28
        System.out.println(uniquePaths2(3,2)); // result 3
        System.out.println(uniquePaths2(3,3)); // result 6

        System.out.println(uniquePaths(3,7)); // result 28
        System.out.println(uniquePaths(3,2)); // result 3
        System.out.println(uniquePaths(3,3)); // result 6
    }

    // O(n) space, dp
    public static int uniquePaths(int m, int n) {
        int []dp = new int[n];
        for (int j=0; j<n; j++)
            dp[j] = 1;
        for (int i=1; i<m; i++)
            for (int j=1; j<n; j++)
                dp[j] += dp[j-1];
        return dp[n-1];
    }

    // O(n*m) space, dp
    public static int uniquePaths2(int m, int n) {
        int dp[][] = new int[m][n];
        for (int i=0; i<m; i++)
            dp[i][0] = 1;
        for (int j=0; j<n; j++)
            dp[0][j] = 1;
        for (int i=1; i<m; i++)
            for (int j=1; j<n; j++)
                dp[i][j]=dp[i-1][j]+dp[i][j-1];
        return dp[m-1][n-1];
    }
}
