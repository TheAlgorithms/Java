package com.thealgorithms.dynamicprogramming;

/**
 * The WineProblem class provides a solution to the wine selling problem.
 * Given a collection of N wines with different prices, the objective is to maximize profit by selling
 * one wine each year, considering the constraint that only the leftmost or rightmost wine can be sold
 * at any given time.
 *
 * The price of the ith wine is pi, and the selling price increases by a factor of the year in which
 * it is sold. This class implements three approaches to solve the problem:
 *
 * 1. **Recursion**: A straightforward recursive method that computes the maximum profit.
 *    - Time Complexity: O(2^N)
 *    - Space Complexity: O(N) due to recursive calls.
 *
 * 2. **Top-Down Dynamic Programming (Memoization)**: This approach caches the results of subproblems
 *    to avoid redundant computations.
 *    - Time Complexity: O(N^2)
 *    - Space Complexity: O(N^2) for the storage of results and O(N) for recursion stack.
 *
 * 3. **Bottom-Up Dynamic Programming (Tabulation)**: This method builds a table iteratively to
 *    compute the maximum profit for all possible subproblems.
 *    - Time Complexity: O(N^2)
 *    - Space Complexity: O(N^2) for the table.
 */
public final class WineProblem {
    private WineProblem() {
    }

    /**
     * Calculate maximum profit using recursion.
     *
     * @param arr Array of wine prices.
     * @param si  Start index of the wine to consider.
     * @param ei  End index of the wine to consider.
     * @return Maximum profit obtainable by selling the wines.
     */
    public static int wpRecursion(int[] arr, int si, int ei) {
        int n = arr.length;
        int year = (n - (ei - si + 1)) + 1;
        if (si == ei) {
            return arr[si] * year;
        }

        int start = wpRecursion(arr, si + 1, ei) + arr[si] * year;
        int end = wpRecursion(arr, si, ei - 1) + arr[ei] * year;

        return Math.max(start, end);
    }

    /**
     * Calculate maximum profit using top-down dynamic programming with memoization.
     *
     * @param arr  Array of wine prices.
     * @param si   Start index of the wine to consider.
     * @param ei   End index of the wine to consider.
     * @param strg 2D array to store results of subproblems.
     * @return Maximum profit obtainable by selling the wines.
     */
    public static int wptd(int[] arr, int si, int ei, int[][] strg) {
        int n = arr.length;
        int year = (n - (ei - si + 1)) + 1;
        if (si == ei) {
            return arr[si] * year;
        }

        if (strg[si][ei] != 0) {
            return strg[si][ei];
        }
        int start = wptd(arr, si + 1, ei, strg) + arr[si] * year;
        int end = wptd(arr, si, ei - 1, strg) + arr[ei] * year;

        int ans = Math.max(start, end);
        strg[si][ei] = ans;

        return ans;
    }

    /**
     * Calculate maximum profit using bottom-up dynamic programming with tabulation.
     *
     * @param arr Array of wine prices.
     * @throws IllegalArgumentException if the input array is null or empty.
     * @return Maximum profit obtainable by selling the wines.
     */
    public static int wpbu(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Input array cannot be null or empty.");
        }
        int n = arr.length;
        int[][] strg = new int[n][n];

        for (int slide = 0; slide <= n - 1; slide++) {
            for (int si = 0; si <= n - slide - 1; si++) {
                int ei = si + slide;
                int year = (n - (ei - si + 1)) + 1;
                if (si == ei) {
                    strg[si][ei] = arr[si] * year;
                } else {
                    int start = strg[si + 1][ei] + arr[si] * year;
                    int end = strg[si][ei - 1] + arr[ei] * year;

                    strg[si][ei] = Math.max(start, end);
                }
            }
        }
        return strg[0][n - 1];
    }
}
