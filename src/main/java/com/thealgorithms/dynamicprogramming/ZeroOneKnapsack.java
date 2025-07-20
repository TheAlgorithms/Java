package com.thealgorithms.dynamicprogramming;

/**
 * Class to solve the 0/1 Knapsack Problem using recursion.
 *
 * The 0/1 Knapsack problem is a classic dynamic programming problem
 * where we are given weights and values of `n` items. We need to put
 * these items in a knapsack of capacity `W` to get the maximum total value
 * in the knapsack. We can either include an item or exclude it — but cannot
 * include it more than once.
 *
 * Time Complexity: O(2^n) in worst case for the pure recursive approach (due to overlapping subproblems).
 * Using memoization or bottom-up dynamic programming reduces the time complexity to O(nW).
 *
 * Example:
 * val[] = {15, 14, 10, 45, 30}
 * wt[]  = {2, 5, 1, 3, 4}
 * W = 7
 * Output: 75
 */
public class ZeroOneKnapsack {

    /**
     * Solves the 0/1 Knapsack problem using recursion.
     *
     * @param val Array of item values (must have length at least n)
     * @param wt  Array of item weights (must have length at least n)
     * @param W   Total capacity of the knapsack
     * @param n   Number of items to consider
     * @return The maximum value that can be obtained
     */
    public static int knapsack(int[] val, int[] wt, int W, int n) {
        if (val == null || wt == null || val.length != wt.length) {
            throw new IllegalArgumentException("Value and weight arrays must be non-null and of equal length.");
        }
        if (W == 0 || n == 0) {
            return 0;
        }
        if (wt[n - 1] <= W) {
            // Include the current item
            int include = val[n - 1] + knapsack(val, wt, W - wt[n - 1], n - 1);
            // Exclude the current item
            int exclude = knapsack(val, wt, W, n - 1);
            return Math.max(include, exclude);
        } else {
            // Cannot include the item, move to next
            return knapsack(val, wt, W, n - 1);
        }
    }
}
