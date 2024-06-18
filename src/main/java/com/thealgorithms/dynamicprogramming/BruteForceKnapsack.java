package com.thealgorithms.dynamicprogramming;

/* A Naive recursive implementation
of 0-1 Knapsack problem */
public final class BruteForceKnapsack {
    private BruteForceKnapsack() {
    }
    // Returns the maximum value that
    // can be put in a knapsack of
    // capacity W
    static int knapSack(int w, int[] wt, int[] val, int n) {
        // Base Case
        if (n == 0 || w == 0) {
            return 0;
        }

        // If weight of the nth item is
        // more than Knapsack capacity W,
        // then this item cannot be included
        // in the optimal solution
        if (wt[n - 1] > w) {
            return knapSack(w, wt, val, n - 1);
        } // Return the maximum of two cases:
        // (1) nth item included
        // (2) not included
        else {
            return Math.max(val[n - 1] + knapSack(w - wt[n - 1], wt, val, n - 1), knapSack(w, wt, val, n - 1));
        }
    }

    // Driver code
    public static void main(String[] args) {
        int[] val = new int[] {60, 100, 120};
        int[] wt = new int[] {10, 20, 30};
        int w = 50;
        int n = val.length;
        System.out.println(knapSack(w, wt, val, n));
    }
}
