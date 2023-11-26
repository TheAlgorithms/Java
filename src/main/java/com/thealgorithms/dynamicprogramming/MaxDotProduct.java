package com.thealgorithms.dynamicprogramming;

import java.util.*;

/**
 * Java program for solving the Max Dot Product of Two Subsequences problem.
 * Author: Your Name
 * File: MaxDotProduct.java
 * Comments: This program calculates the maximum dot product of two subsequences.
 */
public class MaxDotProduct {
    /**
     * Recursively solve the problem to find the maximum dot product of two subsequences.
     *
     * @param i     Index in the first array.
     * @param j     Index in the second array.
     * @param nums1 The first array of integers.
     * @param nums2 The second array of integers.
     * @param n     Length of nums1.
     * @param m     Length of nums2.
     * @param dp    Memoization table.
     * @return Maximum dot product of two subsequences.
     */
    public int solve(int i, int j, int[] nums1, int[] nums2, int n, int m, int[][] dp) {
        if (i == n || j == m) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int ans = Integer.MIN_VALUE;
        int take = Integer.MIN_VALUE;
        take = nums1[i] * nums2[j] + solve(i + 1, j + 1, nums1, nums2, n, m, dp);

        int nottake2 = solve(i + 1, j, nums1, nums2, n, m, dp);
        int nottake1 = solve(i, j + 1, nums1, nums2, n, m, dp);
        ans = Math.max(take, Math.max(nottake1, nottake2));
        dp[i][j] = ans;
        return ans;
    }

    /**
     * Calculate the maximum dot product of two subsequences.
     *
     * @param nums1 The first array of integers.
     * @param nums2 The second array of integers.
     * @return Maximum dot product of two subsequences.
     */
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int[][] dp = new int[n][m];
        for (int[] sub : dp) {
            Arrays.fill(sub, -1);
        }
        return solve(0, 0, nums1, nums2, n, m, dp);
    }

}
