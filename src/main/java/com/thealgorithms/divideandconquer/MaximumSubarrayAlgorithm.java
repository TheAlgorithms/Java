package com.thealgorithms.divideandconquer;

public class MaximumSubarrayAlgorithm {

    /**
     * Finds the maximum subarray crossing the middle index.
     *
     * @param A the array to search for the maximum subarray
     * @param low the lower index of the subarray to search
     * @param mid the middle index of the subarray to search
     * @param high the upper index of the subarray to search
     * @return an array containing the indices and sum of the maximum subarray crossing the middle index
     */
    public static int[] findMaxCrossingSubarray(int[] A, int low, int mid, int high) {
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        int maxLeft = 0;

        for (int i = mid; i >= low; i--) {
            sum += A[i];
            if (sum > leftSum) {
                leftSum = sum;
                maxLeft = i;
            }
        }

        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        int maxRight = 0;

        for (int j = mid + 1; j <= high; j++) {
            sum += A[j];
            if (sum > rightSum) {
                rightSum = sum;
                maxRight = j;
            }
        }

        return new int[]{maxLeft, maxRight, leftSum + rightSum};
    }

    /**
     * Finds the maximum subarray within a given range of indices.
     *
     * @param A the array to search for the maximum subarray
     * @param low the lower index of the subarray to search
     * @param high the upper index of the subarray to search
     * @return an array containing the indices and sum of the maximum subarray within the given range
     */
    public static int[] findMaximumSubarray(int[] A, int low, int high) {
        if (high == low) {
            return new int[]{low, high, A[low]};
        } else {
            int mid = (low + high) / 2;
            int[] leftSubarray = findMaximumSubarray(A, low, mid);
            int[] rightSubarray = findMaximumSubarray(A, mid + 1, high);
            int[] crossSubarray = findMaxCrossingSubarray(A, low, mid, high);

            if (leftSubarray[2] >= rightSubarray[2] && leftSubarray[2] >= crossSubarray[2]) {
                return leftSubarray;
            } else if (rightSubarray[2] >= leftSubarray[2] && rightSubarray[2] >= crossSubarray[2]) {
                return rightSubarray;
            } else {
                return crossSubarray;
            }
        }
    }
}
