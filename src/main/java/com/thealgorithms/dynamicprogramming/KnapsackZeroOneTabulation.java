package com.thealgorithms.dynamicprogramming;

/**
 * Tabulation (Bottom-Up) Solution for 0-1 Knapsack Problem.
 * This method uses dynamic programming to build up a solution iteratively,
 * filling a 2-D array where each entry dp[i][w] represents the maximum value
 * achievable with the first i items and a knapsack capacity of w.
 *
 * The tabulation approach is efficient because it avoids redundant calculations
 * by solving all subproblems in advance and storing their results, ensuring
 * each subproblem is solved only once. This is a key technique in dynamic programming,
 * making it possible to solve problems that would otherwise be infeasible due to
 * exponential time complexity in naive recursive solutions.
 *
 * Time Complexity: O(n * W), where n is the number of items and W is the knapsack capacity.
 * Space Complexity: O(n * W) for the DP table.
 *
 * For more information, see:
 * https://en.wikipedia.org/wiki/Knapsack_problem#Dynamic_programming
 */
public final class KnapsackZeroOneTabulation {

    private KnapsackZeroOneTabulation() {
        // Prevent instantiation
    }

    /**
     * Solves the 0-1 Knapsack problem using the bottom-up tabulation technique.
     * @param values the values of the items
     * @param weights the weights of the items
     * @param capacity the total capacity of the knapsack
     * @param itemCount the number of items
     * @return the maximum value that can be put in the knapsack
     * @throws IllegalArgumentException if input arrays are null, of different lengths,or if capacity or itemCount is invalid
     */
    public static int compute(final int[] values, final int[] weights, final int capacity, final int itemCount) {
        if (values == null || weights == null) {
            throw new IllegalArgumentException("Values and weights arrays must not be null.");
        }
        if (values.length != weights.length) {
            throw new IllegalArgumentException("Values and weights arrays must be non-null and of same length.");
        }
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity must not be negative.");
        }
        if (itemCount < 0 || itemCount > values.length) {
            throw new IllegalArgumentException("Item count must be between 0 and the length of the values array.");
        }

        final int[][] dp = new int[itemCount + 1][capacity + 1];

        for (int i = 1; i <= itemCount; i++) {
            final int currentValue = values[i - 1];
            final int currentWeight = weights[i - 1];

            for (int w = 1; w <= capacity; w++) {
                if (currentWeight <= w) {
                    final int includeItem = currentValue + dp[i - 1][w - currentWeight];
                    final int excludeItem = dp[i - 1][w];
                    dp[i][w] = Math.max(includeItem, excludeItem);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[itemCount][capacity];
    }
}
