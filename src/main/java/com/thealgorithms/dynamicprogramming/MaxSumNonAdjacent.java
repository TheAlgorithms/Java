package com.thealgorithms.dynamicprogramming;

import java.util.*;

/*
What would you like to Propose?
I would like to propose adding an implementation of the Maximum Sum of Non-Adjacent Elements algorithm to the dynamic programming section of the repository.

Issue details
Problem Statement:
Given an array of integers, write a function to find the maximum sum of non-adjacent elements. The elements can be chosen such that no two chosen elements are adjacent in the array.

For example:
Input: [3, 2, 5, 10, 7]
Output: 15 (The maximum sum is obtained by selecting 3, 7, and 5)

Approach:

Use dynamic programming to maintain a running maximum sum.
For each element, decide to either include it in the sum (and skip the previous element) or exclude it (and keep the sum up to the previous element).*/

// Problem Explaination: "https://medium.com/@amitrajit_bose/max-sum-of-non-adjacent-elements-a04ebc4f2602"


public class MaxSumNonAdjacent {
    // This function recursively calculates the maximum possible sum
    // by considering or not considering the current element.
    static int solveUtil(int ind, int[] arr, int[] dp) {
        // If the index is negative, there are no elements left to consider.
        if (ind < 0) return 0;
        
        // If the index is 0, there is only one element to consider, so return its value.
        if (ind == 0) return arr[ind];
        
        // If we have already calculated the result for this index, return it.
        if (dp[ind] != -1) return dp[ind];
        
        // Calculate the maximum sum by either picking the current element or not picking it.
        int pick = arr[ind] + solveUtil(ind - 2, arr, dp);
        int nonPick = solveUtil(ind - 1, arr, dp);
        
        // Store the maximum of the two options in the dp array for future reference.
        return dp[ind] = Math.max(pick, nonPick);
    }

    // This function initializes the dp array and calls the recursive solver.
    static int solve(int n, int[] arr) {
        int dp[] = new int[n];
        
        // Initialize the dp array with -1 to indicate that values are not calculated yet.
        Arrays.fill(dp, -1);
        
        // Call the recursive solver for the last index (n-1).
        return solveUtil(n - 1, arr, dp);
    }

    public static void main(String args[]) {
        Scanner sc= new Scanner(System.in);
        // Input array size with elements.
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i=0;i<n;i++){
            nums[i] = sc.nextInt();
        }
        // Call the solve function to find the maximum possible sum.
        int result = solve(n, nums);
        
        // Print the result.
        System.out.println("Maximum sum of non-adjacent elements: " + result);
    }
}

