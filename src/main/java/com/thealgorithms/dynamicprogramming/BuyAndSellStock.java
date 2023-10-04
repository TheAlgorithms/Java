package com.thealgorithms.dynamicprogramming;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Bharath Subu (https://github.com/BharathSubu)
 */

//

/*
 * You are given an integer array prices where prices[i] is the price of a given
 * stock on the ith day.
 * On each day, you may decide to buy and/or sell the stock. You can only hold
 * at most one share of the stock at any time. However, you can buy it then
 * immediately sell it on the same day.
 * Find and return the maximum profit you can achieve.
 */

public class BuyAndSellStock {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int prices[] = new int[n];
        for (int i = 0; i < n; i++)
            prices[i] = sc.nextInt();
        // memoization
        System.out.println(buyAndSellStockMemo(prices));
        // Tabulation
        System.out.println(buyAndSellStockMemo(prices));
        sc.close();
    }

    // Memoization solution
    // Time Complexity :- O(N^2)
    // Space Complexity :- O(N^2) + O(N)
    public static int buyAndSellStockMemo(int[] prices) {
        int n = prices.length;
        int dp[][] = new int[n][2];
        Arrays.stream(dp).forEach(a -> Arrays.fill(a, -1));
        return buyAndSellStockMemoHelper(0, 0, n, dp, prices);
    }

    // i -> ith day
    // j -> If 0 means we have no stock at hand else we have bought a stock already
    private static int buyAndSellStockMemoHelper(int i, int j, int n, int[][] dp, int[] prices) {
        // base condition
        if (i == n - 1) {
            if (j == 1)
                return prices[i];
            else
                return 0;
        }
        if (dp[i][j] != -1)
            return dp[i][j];

        // If j==0 means we have not bought any stock so we either buy on ith day which
        // will be
        // subtracted from the profit we make by selling it on some other day or do
        // nothing
        // Else we have bought it so we either sell on ith day or do nothing

        if (j == 0) {
            int case1 = buyAndSellStockMemoHelper(i + 1, 1, n, dp, prices) - prices[i];
            int case2 = buyAndSellStockMemoHelper(i + 1, 0, n, dp, prices);
            dp[i][j] = Math.max(case2, case1);
        } else {
            int case1 = prices[i] + buyAndSellStockMemoHelper(i + 1, 0, n, dp, prices);
            int case2 = buyAndSellStockMemoHelper(i + 1, 1, n, dp, prices);
            dp[i][j] = Math.max(case2, case1);
        }
        return dp[i][j];
    }

    // tabulation Solution
    // Time Complexity :- O(N^2)
    // Space Complexity :- O(N^2)
    static long buyAndSellStockTabulation(int[] prices) {
        int n = prices.length;
        int dp[][] = new int[n + 1][2];
        for (int row[] : dp)
            Arrays.fill(row, -1);

        // base condition
        dp[n][0] = dp[n][1] = 0;

        int profit = 0;

        for (int ind = n - 1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                if (buy == 0) {
                    // We can buy the stock
                    profit = Math.max(0 + dp[ind + 1][0], -prices[ind] + dp[ind + 1][1]);
                }

                if (buy == 1) {
                    // We can sell the stock
                    profit = Math.max(0 + dp[ind + 1][1], prices[ind] + dp[ind + 1][0]);
                }
                dp[ind][buy] = profit;
            }
        }
        return dp[0][0];
    }
}
