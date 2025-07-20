package com.thealgorithms.dynamicprogramming;

/**
 * The {@code ZeroOneKnapsack} class provides a method to solve the classic 0/1 Knapsack problem.
 * It returns the maximum value that can be obtained by selecting items within the weight limit.
 *
 * Problem Description: Given weights and values of n items, put these items in a knapsack of capacity W
 * such that the total value is maximized. You cannot break an item, either pick the complete item or don't pick it.
 *
 * https://en.wikipedia.org/wiki/Knapsack_problem
 */
public final class ZeroOneKnapsack {
    private ZeroOneKnapsack() {
    }

    /**
     * Solves the 0/1 Knapsack problem using recursion.
     *
     * @param values the array containing values of the items
     * @param weights the array containing weights of the items
     * @param capacity the total capacity of the knapsack
     * @param n the number of items
     * @return the maximum total value achievable within the given weight limit
     */
    public static int KnapsackCompute(int[] values, int[] weights, int capacity, int n) {
        if (n == 0 || capacity == 0) {
            return 0;
        }

        if (weights[n - 1] <= capacity) {
            int include = values[n - 1] + KnapsackCompute(values, weights, capacity - weights[n - 1], n - 1);
            int exclude = KnapsackCompute(values, weights, capacity, n - 1);
            return Math.max(include, exclude);
        } else {
            return KnapsackCompute(values, weights, capacity, n - 1);
        }
    }
}
