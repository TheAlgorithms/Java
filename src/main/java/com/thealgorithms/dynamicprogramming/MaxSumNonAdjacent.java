package com.thealgorithms.dynamicprogramming;

/*
What would you like to Propose?
I would like to propose adding an implementation of the Maximum Sum of Non-Adjacent Elements
algorithm to the dynamic programming section of the repository. Issue details Problem Statement:
Given an array of integers, write a function to find the maximum sum of non-adjacent elements. The
elements can be chosen such that no two chosen elements are adjacent in the array. For example:
Input: [3, 2, 5, 10, 7]
Output: 15 (The maximum sum is obtained by selecting 3, 7, and 5)
Approach:
Use dynamic programming to maintain a running maximum sum.
For each element, decide to either include it in the sum (and skip the previous element) or exclude
it (and keep the sum up to the previous element).*/
// Problem explanation:
// "https://medium.com/@amitrajit_bose/max-sum-of-non-adjacent-elements-a04ebc4f2602"

public class MaxSumNonAdjacent {
    static int solveUtil(int index, int[] arr, int[] dp) {
        if (index < 0) {
            return 0;
        }

        if (index == 0) {
            return arr[index];
        }

        if (dp[index] != -1) {
            return dp[index];
        }

        int pick = arr[index] + solveUtil(index - 2, arr, dp);
        int nonPick = solveUtil(index - 1, arr, dp);

        return dp[index] = Math.max(pick, nonPick);
    }

    static int solve(int n, int[] arr) {
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) dp[i] = -1;

        return solveUtil(n - 1, arr, dp);
    }
}
