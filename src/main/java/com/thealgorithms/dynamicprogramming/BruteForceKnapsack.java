package com.thealgorithms.dynamicprogramming;

/**
 * A naive recursive implementation of the 0-1 Knapsack problem.
 *
 * <p>The 0-1 Knapsack problem is a classic optimization problem where you are
 * given a set of items, each with a weight and a value, and a knapsack with a
 * fixed capacity. The goal is to determine the maximum value that can be
 * obtained by selecting a subset of the items such that the total weight does
 * not exceed the knapsack's capacity. Each item can either be included (1) or
 * excluded (0), hence the name "0-1" Knapsack.</p>
 *
 * <p>This class provides a brute-force recursive approach to solving the
 * problem. It evaluates all possible combinations of items to find the optimal
 * solution, but this approach has exponential time complexity and is not
 * suitable for large input sizes.</p>
 *
 * <p><b>Time Complexity:</b> O(2^n), where n is the number of items.</p>
 *
 * <p><b>Space Complexity:</b> O(n), due to the recursive function call stack.</p>
 */
public final class BruteForceKnapsack {
    private BruteForceKnapsack() {
    }

    /**
     * Solves the 0-1 Knapsack problem using a recursive brute-force approach.
     *
     * @param w   the total capacity of the knapsack
     * @param wt  an array where wt[i] represents the weight of the i-th item
     * @param val an array where val[i] represents the value of the i-th item
     * @param n   the number of items available for selection
     * @return    the maximum value that can be obtained with the given capacity
     *
     * <p>The function uses recursion to explore all possible subsets of items.
     * For each item, it has two choices: either include it in the knapsack
     * (if it fits) or exclude it. It returns the maximum value obtainable
     * through these two choices.</p>
     *
     * <p><b>Base Cases:</b>
     * <ul>
     * <li>If no items are left (n == 0), the maximum value is 0.</li>
     * <li>If the knapsack's remaining capacity is 0 (w == 0), no more items can
     * be included, and the value is 0.</li>
     * </ul></p>
     *
     * <p><b>Recursive Steps:</b>
     * <ul>
     * <li>If the weight of the n-th item exceeds the current capacity, it is
     * excluded from the solution, and the function proceeds with the remaining
     * items.</li>
     * <li>Otherwise, the function considers two possibilities: include the n-th
     * item or exclude it, and returns the maximum value of these two scenarios.</li>
     * </ul></p>
     */
    static int knapSack(int w, int[] wt, int[] val, int n) {
        if (n == 0 || w == 0) {
            return 0;
        }

        if (wt[n - 1] > w) {
            return knapSack(w, wt, val, n - 1);
        } else {
            return Math.max(knapSack(w, wt, val, n - 1), val[n - 1] + knapSack(w - wt[n - 1], wt, val, n - 1));
        }
    }
}
