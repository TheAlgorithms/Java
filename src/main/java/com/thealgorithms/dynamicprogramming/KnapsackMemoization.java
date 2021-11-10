package com.thealgorithms.dynamicprogramming;

import java.util.Arrays;

/**
 * Recursive Solution for 0-1 knapsack with memoization
 */
public class KnapsackMemoization {

    private static int[][] t;

    // Returns the maximum value that can
    // be put in a knapsack of capacity W
    public static int knapsack(int[] wt, int[] value, int W, int n) {
        if (t[n][W] != -1) {
            return t[n][W];
        }
        if (n == 0 || W == 0) {
            return 0;
        }
        if (wt[n - 1] <= W) {
            t[n - 1][W - wt[n - 1]] = knapsack(wt, value, W - wt[n - 1], n - 1);
            // Include item in the bag. In that case add the value of the item and call for the remaining items
            int tmp1 = value[n - 1] + t[n - 1][W - wt[n - 1]];
            // Don't include the nth item in the bag anl call for remaining item without reducing the weight
            int tmp2 = knapsack(wt, value, W, n - 1);
            t[n - 1][W] = tmp2;
            // include the larger one
            int tmp = tmp1 > tmp2 ? tmp1 : tmp2;
            t[n][W] = tmp;
            return tmp;
            // If Weight for the item is more than the desired weight then don't include it
            // Call for rest of the n-1 items
        } else if (wt[n - 1] > W) {
            t[n][W] = knapsack(wt, value, W, n - 1);
            return t[n][W];
        }
        return -1;
    }

    // Driver code
    public static void main(String args[]) {
        int[] wt = {1, 3, 4, 5};
        int[] value = {1, 4, 5, 7};
        int W = 10;
        t = new int[wt.length + 1][W + 1];
        Arrays.stream(t).forEach(a -> Arrays.fill(a, -1));
        int res = knapsack(wt, value, W, wt.length);
        System.out.println("Maximum knapsack value " + res);
    }
}
