package dynamicprogramming;
/**
 * Implementation of the 0/1 Knapsack Problem using Dynamic Programming.
 *
 * Given weights and values of n items, put these items in a knapsack of capacity W
 * to get the maximum total value in the knapsack.
 */
public class Knapsack {

    /**
     * Returns the maximum value that can be put in a knapsack of capacity W.
     *
     * @param W    capacity of the knapsack
     * @param wt   array of weights of the items
     * @param val  array of values of the items
     * @param n    number of items
     * @return maximum value achievable with given capacity
     */
    public static int knapSack(int W, int[] wt, int[] val, int n) {
        int[][] dp = new int[n + 1][W + 1];

        // Build table dp[][] in bottom-up manner
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0; // Base case: no items or zero capacity
                } else if (wt[i - 1] <= w) {
                    // Include the item or exclude it, choose max value
                    dp[i][w] = Math.max(val[i - 1] + dp[i - 1][w - wt[i - 1]],
                                        dp[i - 1][w]);
                } else {
                    // Item can't be included because it weighs more than capacity
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[n][W];
    }

    // Example usage and simple test case
    public static void main(String[] args) {
        int[] values = { 60, 100, 120 };
        int[] weights = { 10, 20, 30 };
        int capacity = 50;
        int n = values.length;

        int maxValue = knapSack(capacity, weights, values, n);
        System.out.println("Maximum value in Knapsack = " + maxValue);
        // Expected output: 220
    }
}
