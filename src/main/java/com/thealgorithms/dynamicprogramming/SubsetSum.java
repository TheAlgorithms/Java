package com.thealgorithms.dynamicprogramming;

public final class SubsetSum {
    private SubsetSum() {
    }

    /**
     * Test if a set of integers contains a subset that sums to a given integer.
     *
     * @param arr the array containing integers.
     * @param sum the target sum of the subset.
     * @return {@code true} if a subset exists that sums to the given value,
     *         otherwise {@code false}.
     */
    public static boolean subsetSum(int[] arr, int sum) {
        int n = arr.length;

        // Initialize a single array to store the possible sums
        boolean[] isSum = new boolean[sum + 1];

        // Mark isSum[0] = true since a sum of 0 is always possible with 0 elements
        isSum[0] = true;

        // Iterate through each Element in the array
        for (int i = 0; i < n; i++) {
            // Traverse the isSum array backwards to prevent overwriting values
            for (int j = sum; j >= arr[i]; j--) {
                isSum[j] = isSum[j] || isSum[j - arr[i]];
            }
        }
        return isSum[sum];
    }
}
