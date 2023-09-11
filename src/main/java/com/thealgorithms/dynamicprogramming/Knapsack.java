package com.thealgorithms.dynamicprogramming;

/**
 * A Dynamic Programming based solution for the 0-1 Knapsack problem.
 * This class provides a method, `knapSack`, that calculates the maximum value that can be
 * obtained from a given set of items with weights and values, while not exceeding a
 * given weight capacity.
 *
 * @see <a href="https://en.wikipedia.org/?title=0-1_Knapsack_problem">0-1 Knapsack Problem </a>
 */
public final class Knapsack {

    /**
     * Solves the 0-1 Knapsack problem using Dynamic Programming.
     *
     * @param weightCapacity The maximum weight capacity of the knapsack.
     * @param weights        An array of item weights.
     * @param values         An array of item values.
     * @return The maximum value that can be obtained without exceeding the weight capacity.
     * @throws IllegalArgumentException If the input arrays are null or have different lengths.
     */
    public static int knapSack(final int weightCapacity, final int[] weights, final int[] values) throws IllegalArgumentException {
        if (weights == null || values == null || weights.length != values.length) {
            throw new IllegalArgumentException("Input arrays must not be null and must have the same length.");
        }

        // DP table to store the state of the maximum possible return for a given weight capacity.
        int[] dp = new int[weightCapacity + 1];

        for (int i = 0; i < values.length; i++) {
            for (int w = weightCapacity; w > 0; w--) {
                if (weights[i] <= w) {
                    dp[w] = Math.max(dp[w], dp[w - weights[i]] + values[i]);
                }
            }
        }

        return dp[weightCapacity];
    }
}
