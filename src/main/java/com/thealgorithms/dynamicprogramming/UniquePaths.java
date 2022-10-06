/** Author : Siddhant Swarup Mallick
 * Github : https://github.com/siddhant2002
 */

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * The robot can only move either down or right at any point in time.
 * The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * How many possible unique paths are there?
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
        int dp[][] = new int[m][n];
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
    // The above method takes O(m*n) time

    /*
     * we can also solve this problem in o(m+n) time and o(1) space, here is how
     * we have to take m-1 right moves and n-1 down moves to reach 
     * to the bottom right corner, a path is unique arrangement of these moves.
     * suppose m=3 and n=3, now you will move 2 times right and 2 times down, possible paths are
     * (R,R,D,D),(R,D,R,D),(D,R,D,R),(D,D,R,R),(R,D,D,R),(D,R,R,D)
     * R means Right and D means DOWN.
     * we can use combinatorics to find the number of unique paths, the number of arrangements
     * to place A identical things and B identical things, will be ((A+B)!)/(A!*B!)
     */
    public static boolean uniquePaths3(int m, int n, int expectedAns) {
        int downMoves=m-1, rightMoves=n-1, ans=1;
        for(int i=1;i<=downMoves+rightMoves;i++){
            ans*=i;
        }
        for(int i=1;i<=downMoves;i++){
            ans/=i;
        }
        for(int i=1;i<=rightMoves;i++){
            ans/=i;
        }
        return (ans==expectedAns);
    }

}
/**
 * OUTPUT :
 * Input - m = 3, n = 7
 * Output: it returns either true if expected answer matches with the predicted answer else it returns false
 * 1st approach Time Complexity : O(n)
 * Auxiliary Space Complexity : O(n)
 * Input - m = 3, n = 7
 * Output: it returns either true if expected answer matches with the predicted answer else it returns false
 * 2nd approach Time Complexity : O(m*n)
 * Auxiliary Space Complexity : O(m*n)
 */
