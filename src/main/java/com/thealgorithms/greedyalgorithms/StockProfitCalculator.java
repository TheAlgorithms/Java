package com.thealgorithms.greedyalgorithms;

/**
 * The StockProfitCalculator class provides a method to calculate the maximum profit
 * that can be made from a single buy and sell of one share of stock.
 * The approach uses a greedy algorithm to efficiently determine the maximum profit.
 *
 * @author Hardvan
 */
public final class StockProfitCalculator {
    private StockProfitCalculator() {
    }

    /**
     * Calculates the maximum profit from a list of stock prices.
     *
     * @param prices an array of integers representing the stock prices on different days
     * @return the maximum profit that can be achieved from a single buy and sell
     * transaction, or 0 if no profit can be made
     */
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int minPrice = prices[0];
        int maxProfit = 0;
        for (int price : prices) {
            minPrice = Math.min(price, minPrice);
            maxProfit = Math.max(price - minPrice, maxProfit);
        }
        return maxProfit;
    }
}
