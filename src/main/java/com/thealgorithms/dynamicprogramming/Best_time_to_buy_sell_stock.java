/**
 * Java program for solving the Best Time to
 * Buy and Sell Stock problem with transaction fees.
 * Author: Yash Kesharwani
 * File: BestTimeToBuySellStock.java
 * Comments: This program calculates the maximum profit
 * achievable when buying and selling stocks with fees.
 */
public class Solution {
    /**
     * Recursively solve the problem to find the maximum profit.
     *
     * @param prices The array of stock prices for each day.
     * @param index The current day index.
     * @param fee The transaction fee.
     * @param buy Buy or sell flag (1 for buy, 0 for sell).
     * @param dp Memoization table.
     * @return Maximum profit achievable.
     */
    public int solve(int[] prices, int index, int fee, int buy, int[][] dp) {
        if (index >= prices.length) {
            return 0;
        }
        if (dp[index][buy] != -1) {
            return dp[index][buy];
        }

        int profit = 0;
        if (buy == 1) {
            profit = Math.max(-prices[index] + solve(prices, index + 1, fee, 0, dp), solve(prices, index + 1, fee, 1, dp));
        } else {
            profit = Math.max((prices[index] + solve(prices, index + 1, fee, 1, dp)) - fee, solve(prices, index + 1, fee, 0, dp);
        }

        dp[index][buy] = profit;
        return profit;
    }

    /**
     * Calculate the maximum profit achievable when buying and selling stocks with a fee.
     *
     * @param prices The array of stock prices for each day.
     * @param fee The transaction fee.
     * @return Maximum profit achievable.
     */
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int[] sub : dp) {
            Arrays.fill(sub, -1);
        }

        return solve(prices, 0, fee, 1, dp);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the list of prices (space-separated): ");
        String input = scanner.nextLine();
        String[] priceStrings = input.split(" ");
        int[] prices = new int[priceStrings.length];
        for (int i = 0; i < priceStrings.length; i++) {
            prices[i] = Integer.parseInt(priceStrings[i]);
        }

        System.out.print("Enter the transaction fee: ");
        int fee = scanner.nextInt();

        Solution solution = new Solution();
        int maxProfit = solution.maxProfit(prices, fee);

        System.out.println("Maximum profit: " + maxProfit);
    }
}
