package com.thealgorithms.dynamicprogramming;

import java.util.*;

// Problem explanation:
// "https://medium.com/@amitrajit_bose/max-sum-of-non-adjacent-elements-a04ebc4f2602"

public class MaxSumNonAdjacent {
    // This function recursively calculates the maximum possible sum
    // by considering or not considering the current element.
    static int solveUtil(int ind, int[] arr, int[] dp) {
        // If the index is negative, there are no elements left to consider.
        if (ind < 0) {
            return 0;
        }

        // If the index is 0, there is only one element to consider, so return its
        // value.
        if (ind == 0) {
            return arr[ind];
        }

        // If we have already calculated the result for this index, return it.
        if (dp[ind] != -1) {
            return dp[ind];
        }

        // Calculate the maximum sum by either picking the current element or not
        // picking it.
        int pick = arr[ind] + solveUtil(ind - 2, arr, dp);
        int nonPick = solveUtil(ind - 1, arr, dp);

        // Store the maximum of the two options in the dp array for future reference.
        return dp[ind] = Math.max(pick, nonPick);
    }

    // This function initializes the dp array and calls the recursive solver.
    static int solve(int n, int[] arr) {
        int[] dp = new int[n];

        // Initialize the dp array with -1 to indicate that values are not calculated
        // yet.
        Arrays.fill(dp, -1);

        // Call the recursive solver for the last index (n-1).
        return solveUtil(n - 1, arr, dp);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input array size and elements.
        System.out.print("Enter the size of the array: ");
        int n = sc.nextInt();
        int[] nums = new int[n];
        System.out.print("Enter the elements of the array: ");
        for (int i = 0; i < n; i++) { nums[i] = sc.nextInt(); }

        // Call the solve function to find the maximum possible sum.
        int result = solve(n, nums);

        // Print the result.
        System.out.println("Maximum sum of non-adjacent elements: " + result);

        sc.close(); // Close the scanner to prevent resource leakage.
    }
}
