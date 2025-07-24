package com.thealgorithms.dynamicprogramming;

/**
 * The {@code KnapsackZeroOne} provides Recursive solution for the 0/1 Knapsack
 * problem. Solves by exploring all combinations of items using recursion. No
 * memoization or dynamic programming optimizations are applied.
 *
 * Time Complexity: O(2^n) — explores all subsets.
 * Space Complexity: O(n) — due to recursive call stack.
 *
 * Problem Reference: https://en.wikipedia.org/wiki/Knapsack_problem
 */
public final class KnapsackZeroOne {

    private KnapsackZeroOne() {
        // Prevent instantiation
    }

    /**
     * Solves the 0/1 Knapsack problem using recursion.
     *
     * @param values   the array containing values of the items
     * @param weights  the array containing weights of the items
     * @param capacity the total capacity of the knapsack
     * @param n        the number of items
     * @return the maximum total value achievable within the given weight limit
     * @throws IllegalArgumentException if input arrays are null, empty, or
     *     lengths mismatch
     */
    public static int compute(final int[] values, final int[] weights, final int capacity, final int n) {
        if (values == null || weights == null) {
            throw new IllegalArgumentException("Input arrays cannot be null.");
        }
        if (values.length != weights.length) {
            throw new IllegalArgumentException("Value and weight arrays must be of the same length.");
        }
        if (capacity < 0 || n < 0) {
            throw new IllegalArgumentException("Invalid input: arrays must be non-empty and capacity/n "
                + "non-negative.");
        }
        if (n == 0 || capacity == 0 || values.length == 0) {
            return 0;
        }

        if (weights[n - 1] <= capacity) {
            final int include = values[n - 1] + compute(values, weights, capacity - weights[n - 1], n - 1);
            final int exclude = compute(values, weights, capacity, n - 1);
            return Math.max(include, exclude);
        } else {
            return compute(values, weights, capacity, n - 1);
        }
    }
}
