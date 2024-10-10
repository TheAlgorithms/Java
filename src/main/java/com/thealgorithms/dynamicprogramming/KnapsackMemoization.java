package com.thealgorithms.dynamicprogramming;

import java.util.Arrays;

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
        for (int[] table : dpTable) {
            Arrays.fill(table, -1);
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
        } else {
            // case 1. include the item, if it is less than the capacity
            final int includeCurrentItem = profits[numOfItems - 1] + solveKnapsackRecursive(capacity - weights[numOfItems - 1], weights, profits, numOfItems - 1, dpTable);

            // case 2. exclude the item if it is more than the capacity
            final int excludeCurrentItem = solveKnapsackRecursive(capacity, weights, profits, numOfItems - 1, dpTable);

            // Store the value of function call stack in table and return
            dpTable[numOfItems][capacity] = Math.max(includeCurrentItem, excludeCurrentItem);
        }
        return dpTable[numOfItems][capacity];
    }
}
