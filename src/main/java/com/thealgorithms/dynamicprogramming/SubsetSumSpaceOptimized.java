package com.thealgorithms.dynamicprogramming;
/*
The Sum of Subset problem determines whether a subset of elements from a
given array sums up to a specific target value.
*/
public final class SubsetSumSpaceOptimized {
    private SubsetSumSpaceOptimized() {
    }
    /**
     * This method checks whether the subset of an array
     * contains a given sum or not. This is an space
     * optimized solution using 1D boolean array
     * Time Complexity: O(n * sum), Space complexity: O(sum)
     *
     * @param arr An array containing integers
     * @param sum The target sum of the subset
     * @return True or False
     */
    public static boolean isSubsetSum(int[] arr, int sum) {
        int n = arr.length;
        // Declare the boolean array with size sum + 1
        boolean[] dp = new boolean[sum + 1];

        // Initialize the first element as true
        dp[0] = true;

        // Find the subset sum using 1D array
        for (int i = 0; i < n; i++) {
            for (int j = sum; j >= arr[i]; j--) {
                dp[j] = dp[j] || dp[j - arr[i]];
            }
        }
        return dp[sum];
    }
}
