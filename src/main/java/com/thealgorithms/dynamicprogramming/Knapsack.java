package com.thealgorithms.dynamicprogramming;

import java.util.Arrays;

/**
 * 0/1 Knapsack Problem - Dynamic Programming solution.
 *
 * This algorithm solves the classic optimization problem where we have n items,
 * each with a weight and a value. The goal is to maximize the total value
 * without exceeding the knapsack's weight capacity.
 *
 * Time Complexity: O(n * W)
 * Space Complexity: O(W)
 *
 * Example:
 * values = {60, 100, 120}
 * weights = {10, 20, 30}
 * W = 50
 * Output: 220
 *
 * @author Arpita
 * @see <a href="https://en.wikipedia.org/wiki/Knapsack_problem">Knapsack Problem</a>
 */
public final class Knapsack {

    private Knapsack() { }

    /**
     * Validates the input to ensure correct constraints.
     */
    private static void throwIfInvalidInput(final int weightCapacity, final int[] weights, final int[] values) {
        if (weightCapacity < 0) {
            throw new IllegalArgumentException("Weight capacity should not be negative.");
        }
        if (weights == null || values == null || weights.length != values.length) {
            throw new IllegalArgumentException("Weights and values must be non-null and of the same length.");
        }
        if (Arrays.stream(weights).anyMatch(w -> w <= 0)) {
            throw new IllegalArgumentException("Weights must be positive.");
        }
    }

    /**
     * Solves the 0/1 Knapsack problem using Dynamic Programming (bottom-up approach).
     *
     * @param weightCapacity The maximum weight capacity of the knapsack.
     * @param weights        The array of item weights.
     * @param values         The array of item values.
     * @return The maximum total value achievable without exceeding capacity.
     */
    public static int knapSack(final int weightCapacity, final int[] weights, final int[] values) {
        throwIfInvalidInput(weightCapacity, weights, values);

        int[] dp = new int[weightCapacity + 1];

        // Fill dp[] array iteratively
        for (int i = 0; i < values.length; i++) {
            for (int w = weightCapacity; w >= weights[i]; w--) {
                dp[w] = Math.max(dp[w], dp[w - weights[i]] + values[i]);
            }
        }

        return dp[weightCapacity];
    }

    /**
     * Example main method for demonstration.
     */
    public static void main(String[] args) {
        int[] values = {60, 100, 120};
        int[] weights = {10, 20, 30};
        int weightCapacity = 50;

        int maxValue = knapSack(weightCapacity, weights, values);
        System.out.println("Maximum value = " + maxValue); // Output : 220
    }
}
