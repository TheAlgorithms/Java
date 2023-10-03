package com.thealgorithms.dynamicprogramming;

/**
 * The problem is to find the maximum profit that can be obtained by buying 
 * and selling a stock represented by an array of prices. 
 * The stock can only be bought and sold once, and the selling can 
 * only happen after the buying.
 */

public class StockBuyAndSell {
    // Driver code
    public static void main(String[] args) {
        int[] arr = { 100, 180, 260, 310, 40, 535, 695 };
        int maxProfit = maxProfit(arr);
        System.out.println("Maximum profit that can be obtained is: " + maxProfit);
    }

    /**
     * This method returns the maximum profit that can be obtained by buying
     * and selling a stock.
     *
     * Time Complexity: O(n)
     * Space Complexity: 0(1)
     *
     * @param arr input array of stock prices on different days of size n
     * @return maximum profit that can be obtained by buying and selling a stock
     */
    public static int maxProfit(int[] arr) {
        int min = arr[0];
        int maxProfit = 0;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                // update min
                min = arr[i];
            } else {
                // update maxProfit
                maxProfit = Math.max(maxProfit, arr[i] - min);
            }
        }

        return maxProfit;
    }
}
