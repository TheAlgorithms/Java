package com.thealgorithms.dynamicprogramming;

/**
 * Recursive Solution for 0-1 knapsack with memoization
 * This method is basically an extension to the recursive approach so that we
 * can overcome the problem of calculating redundant cases and thus increased
 * complexity. We can solve this problem by simply creating a 2-D array that can
 * store a particular state (n, w) if we get it the first time.
 */
public class KnapsackMemoization {

    int knapSack(int W, int wt[], int val[], int N) {

        // Declare the table dynamically
        int dp[][] = new int[N + 1][W + 1];

        // Loop to initially filled the
        // table with -1
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < W + 1; j++) {
                dp[i][j] = -1;
            }
        }

        return knapSackRec(W, wt, val, N, dp);
    }

    // Returns the value of maximum profit using Recursive approach
    int knapSackRec(int W, int wt[],
            int val[], int n,
            int[][] dp) {

        // Base condition
        if (n == 0 || W == 0) {
            return 0;
        }

        if (dp[n][W] != -1) {
            return dp[n][W];
        }

        if (wt[n - 1] > W) {
            // Store the value of function call stack in table
            dp[n][W] = knapSackRec(W, wt, val, n - 1, dp);
            return dp[n][W];
        } else {
            // Return value of table after storing
            return dp[n][W] = Math.max((val[n - 1] + knapSackRec(W - wt[n - 1], wt, val, n - 1, dp)),
                    knapSackRec(W, wt, val, n - 1, dp));
        }
    }
}
