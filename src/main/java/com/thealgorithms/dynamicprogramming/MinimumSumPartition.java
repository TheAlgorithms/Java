package com.thealgorithms.dynamicprogramming;

import java.util.Arrays;

/*
Given an array of non-negative integers , partition the array in two subset that
difference in sum of elements for both subset minimum.
Return the minimum difference in sum of these subsets you can achieve.

Input:  array[] = {1, 6, 11, 4}
Output: 0
Explanation:
Subset1 = {1, 4, 6}, sum of Subset1 = 11
Subset2 = {11}, sum of Subset2 = 11

Input:  array[] = {36, 7, 46, 40}
Output: 23
Explanation:
Subset1 = {7, 46} ;  sum of Subset1 = 53
Subset2 = {36, 40} ; sum of Subset2  = 76
 */
public final class MinimumSumPartition {
    private MinimumSumPartition() {
    }

    private static void throwIfInvalidInput(final int[] array) {
        if (Arrays.stream(array).anyMatch(a -> a < 0)) {
            throw new IllegalArgumentException("Input array should not contain negative number(s).");
        }
    }

    public static int minimumSumPartition(final int[] array) {
        throwIfInvalidInput(array);
        int sum = Arrays.stream(array).sum();
        boolean[] dp = new boolean[sum / 2 + 1];
        dp[0] = true; // Base case , don't select any element from array

        // Find the closest sum of subset array that we can achieve which is closest to half of sum of full array
        int closestPartitionSum = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = sum / 2; j > 0; j--) {
                if (array[i] <= j) {
                    dp[j] = dp[j] || dp[j - array[i]];
                }
                if (dp[j]) {
                    closestPartitionSum = Math.max(closestPartitionSum, j);
                }
            }
        }
        /*
        Difference in sum = Big partition sum  - Small partition sum
                          = ( Total sum - Small partition sum) - Small partition sum
         */
        return sum - (2 * closestPartitionSum);
    }
}
