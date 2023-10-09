package com.thealgorithms.dynamicprogramming;

/**
 * Recursive Solution for 0-1 knapsack with memoization
 * This method is basically an extension to the recursive approach so that we
 * can overcome the problem of calculating redundant cases and thus increased
 * complexity. We can solve this problem by simply creating a 2-D array that can
 * store a particular state (n, w) if we get it the first time.
 */
public class KnapsackMemoization {

    int knapSack(int capacity, int[] weights, int[] profits, int numOfItems) {

        // Declare the table dynamically
        int[][] dpTable = new int[numOfItems + 1][capacity + 1];

        // Loop to initially fill the table with -1
        for (int i = 0; i < numOfItems + 1; i++) {
            for (int j = 0; j < capacity + 1; j++) {
                dpTable[i][j] = -1;
            }
        }

        return solveKnapsackRecursive(capacity, weights, profits, numOfItems, dpTable);
    }

    // Returns the value of maximum profit using recursive approach
    int solveKnapsackRecursive(int capacity, int[] weights, int[] profits, int numOfItems, int[][] dpTable) {
        // Base condition
        if (numOfItems == 0 || capacity == 0) {
            return 0;
        }

        if (dpTable[numOfItems][capacity] != -1) {
            return dpTable[numOfItems][capacity];
        }

        if (weights[numOfItems - 1] > capacity) {
            // Store the value of function call stack in table
            dpTable[numOfItems][capacity] = solveKnapsackRecursive(capacity, weights, profits, numOfItems - 1, dpTable);
            return dpTable[numOfItems][capacity];
        } else {
            // Return value of table after storing
            return dpTable[numOfItems][capacity] = Math.max((profits[numOfItems - 1] + solveKnapsackRecursive(capacity - weights[numOfItems - 1], weights, profits, numOfItems - 1, dpTable)), solveKnapsackRecursive(capacity, weights, profits, numOfItems - 1, dpTable));
        }
    }
}
