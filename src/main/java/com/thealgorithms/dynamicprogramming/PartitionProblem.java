/**
 * @author Md Asif Joardar
 *
 * @Description: The partition problem is a classic problem in computer science
 * that asks whether a given set can be partitioned into two subsets such that
 * the sum of elements in each subset is the same.
 *
 * @Example:
 * Consider nums = {1, 2, 3}
 * We can split the array "nums" into two partitions, where each having a sum of 3.
 * nums1 = {1, 2}
 * nums2 = {3}
 *
 * The time complexity of the solution is O(n × sum) and requires O(n × sum) space
 */

package com.thealgorithms.dynamicprogramming;

import java.util.Arrays;

public class PartitionProblem {
    // return true if there is exists a subarray into nums[] with the given sum
    public static boolean subsetSum(int[] nums, int sum)
    {
        int n = nums.length;

        // dp[i][j] stores true if subset with sum "j" can be attained
        // using items up to first "i" items
        boolean[][] dp = new boolean[n + 1][sum + 1];

        // initialize the first row of the array to true, as it is always possible to form a sum of 0 using an empty subset
        dp[0][0] = true;

        // iterate through each element in the array and each possible sum up to the target sum
        for (int i = 1; i <= n; i++)
        {
            // consider all sum from 1 to sum
            for (int j = 1; j <= sum; j++)
            {
                // if the current element is greater than the current sum, copy the value from the row above
                if (nums[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                }
                // otherwise, set the value of the current cell to true if either the value above or to the left is true
                else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }

        // return value in the last cell of the array indicates whether a partition exists for the given set
        return dp[n][sum];
    }

    public static boolean partition(int[] nums)
    {
        // calculate the sum of all the elements in the array
        int sum = Arrays.stream(nums).sum();

        // will return true if the sum is even and the array can be divided into two subarrays with equal sum
        return (sum & 1) == 0 && subsetSum(nums, sum/2);
    }

    public static void main(String[] args)
    {
        int[] nums = { 1, 2, 3, 6 }; // True

        if (partition(nums)) {
            System.out.println("True");
        }
        else {
            System.out.println("False");
        }
    }
}
