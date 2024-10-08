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

        // Intialize Two Arrays to store current and prev states
        boolean[] isSumCurr = new boolean[sum + 1];
        boolean[] isSumPrev = new boolean[sum + 1];

        // Mark prev[0] = true as it is true to make sum = 0 using 0 elements
        isSumPrev[0] = true;

        // Fill the subset sum matrix
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                if (arr[i - 1] <= j) {
                    isSumCurr[j] = isSumPrev[j] || isSumPrev[j - arr[i - 1]];
                } else {
                    isSumCurr[j] = isSumPrev[j];
                }
            }
            isSumPrev = isSumCurr.clone();
        }

        return isSumPrev[sum];
    }
}
