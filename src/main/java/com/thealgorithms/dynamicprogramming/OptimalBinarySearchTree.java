package com.thealgorithms.dynamicprogramming;

/**
 * To calculate the cost of an optimal binary search tree
 * 
 * The OptimalBinarySearchTree class provides methods to calculate the cost of an optimal binary search tree.
 * This implementation utilizes dynamic programming and memoization for efficient computation.
 * 
 * The algorithm aims to find the optimal cost of a binary search tree given the keys and their corresponding frequencies.
 * It employs a recursive approach combined with memoization to avoid redundant calculations.
 * 
 * The OptimalBinarySearchTree class includes methods to compute the sum of frequencies, calculate the optimal cost using memoization,
 * and find the optimal binary search tree's cost.
 * 
 * @author Yash Jain (https://github.com/Yashjain1602)
 */

import java.util.*;
public class OptimalBinarySearchTree {

    static final int MAX = 1000;
    static int cost[][] = new int[MAX][MAX];

    // Helper function to calculate the sum of frequencies from index i to j
    static int Sum(int freq[], int i, int j) {
        int s = 0;
        for (int k = i; k <= j; k++) s += freq[k];
        return s;
    }

    // Recursive function to find the optimal cost of a BST using memoization
    static int optCostMemoized(int freq[], int i, int j) {
        if (i < 0 || j < 0) return 0;
        if (cost[i][j] != 0) return cost[i][j];
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
    
    // public static void main(String[] args)
    // {
    //     int keys[] = { 10, 12, 20 };
    //     int freq[] = { 34, 8, 50 };
    //     int n = keys.length;
 
    //     // cost[i][j] = Optimal cost of binary search
    //     // tree that can be formed from keys[i] to keys[j].
    //     // cost[0][n-1] will store the resultant cost
    //     for (int i = 0; i < n; i++)
    //         Arrays.fill(cost[i], 0);
 
    //     // For a single key, cost is equal to
    //     // frequency of the key
    //     for (int i = 0; i < n; i++)
    //         cost[i][i] = freq[i];
 
    //     System.out.println(
    //         "Cost of Optimal BST is "
    //         + optimalSearchTree(keys, freq, n));
    // }
