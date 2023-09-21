package com.thealgorithms.dynamicprogramming;

import java.util.Arrays;

/*
Partition a set into two subsets such that the difference of subset sums is minimum

Input:  arr[] = {1, 6, 11, 5}
Output: 1
Explanation:
Subset1 = {1, 5, 6}, sum of Subset1 = 12
Subset2 = {11}, sum of Subset2 = 11

Input:  arr[] = {36, 7, 46, 40}
Output: 23
Explanation:
Subset1 = {7, 46} ;  sum of Subset1 = 53
Subset2 = {36, 40} ; sum of Subset2  = 76
 */
public class MinimumSumPartition {

    private MinimumSumPartition() {
    }

    public static int minimumSumPartition(final int[] array) {
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
