package com.thealgorithms.dynamicprogramming;

public final class SubsetSum {
    private SubsetSum() {
    }

    /**
     * Test if a set of integers contains a subset that sums to a given integer.
     *
     * @param arr the array containing integers.
     * @param sum the target sum of the subset.
     * @return {@code true} if a subset exists that sums to the given value, otherwise {@code false}.
     */
    public static boolean subsetSum(int[] arr, int sum) {
        int n = arr.length;
        boolean[][] isSum = new boolean[n + 1][sum + 1];

        // Initialize the first column to true since a sum of 0 can always be achieved with an empty subset.
        for (int i = 0; i <= n; i++) {
            isSum[i][0] = true;
        }

        // Fill the subset sum matrix
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (arr[i - 1] <= j) {
                    isSum[i][j] = isSum[i - 1][j] || isSum[i - 1][j - arr[i - 1]];
                } else {
                    isSum[i][j] = isSum[i - 1][j];
                }
            }
        }

        return isSum[n][sum];
    }
}
