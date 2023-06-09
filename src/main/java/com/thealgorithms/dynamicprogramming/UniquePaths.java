/**
 * Author : Siddhant Swarup Mallick
 * Github : https://github.com/siddhant2002
 */

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * The robot can only move either down or right at any point in time.
 * The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram
 * below). How many possible unique paths are there?
 */

/** Program description - To find the number of unique paths possible */

package com.thealgorithms.dynamicprogramming;

import java.util.*;

public class UniquePaths {

    public static boolean uniquePaths(int m, int n, int ans) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] += dp[j - 1];
            }
        }
        return dp[n - 1] == ans;
        // return true if predicted answer matches with expected answer
    }

    // The above method runs in O(n) time
    public static boolean uniquePaths2(int m, int n, int ans) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1] == ans;
        // return true if predicted answer matches with expected answer
    }
    // The above mthod takes O(m*n) time
}
/**
 * OUTPUT :
 * Input - m = 3, n = 7
 * Output: it returns either true if expected answer matches with the predicted answer else it
 * returns false 1st approach Time Complexity : O(n) Auxiliary Space Complexity : O(n) Input - m =
 * 3, n = 7 Output: it returns either true if expected answer matches with the predicted answer else
 * it returns false 2nd approach Time Complexity : O(m*n) Auxiliary Space Complexity : O(m*n)
 */
