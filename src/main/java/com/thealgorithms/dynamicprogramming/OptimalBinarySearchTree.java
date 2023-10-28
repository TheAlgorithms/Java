package com.thealgorithms.dynamicprogramming;

/**
 * To calculate the cost of an optimal binary search tree
 * @author Yash Jain (https://github.com/Yashjain1602)
 */

import java.util.*;

public class OptimalBinarySearchTree {
    static final int MAX = 1000;
    static int cost[][] = new int[MAX][MAX];

    // Helper function to calculate the sum of frequencies from index i to j
    static int Sum(int freq[], int i, int j) {
        int s = 0;
        for (int k = i; k <= j; k++)
            s += freq[k];
        return s;
    }

    // Recursive function to find the optimal cost of a BST using memoization
    static int optCostMemoized(int freq[], int i, int j) {
        if (i < 0 || j < 0)
            return 0;
        if (cost[i][j] != 0)
            return cost[i][j];
        int fsum = Sum(freq, i, j);
        int Min = Integer.MAX_VALUE;
        for (int r = i; r <= j; r++) {
            int c = optCostMemoized(freq, i, r - 1) + optCostMemoized(freq, r + 1, j) + fsum;
            if (c < Min) {
                Min = c;
                cost[i][j] = c;
            }
        }
        return cost[i][j];
    }

    // Main function to calculate the minimum cost of a BST
    static int optimalSearchTree(int keys[], int freq[], int n) {
        return optCostMemoized(freq, 0, n - 1);
    }
}
