package com.thealgorithms.slidingwindow;

/**
 * Problem: Find the maximum sum of any contiguous subarray of size K
 * Approach: Sliding Window
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class MaximumSumSubarrayOfSizeK {

    /**
     * Returns the maximum sum of any contiguous subarray of size k.
     *
     * @param arr input array
     * @param k   window size
     * @return maximum sum of subarray of size k
     * @throws IllegalArgumentException if k <= 0 or arr length < k
     */
    public static int maxSumSubarray(int[] arr, int k) {
        if (arr == null || arr.length < k || k <= 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }

        int maxSum = windowSum;

        for (int end = k; end < arr.length; end++) {
            windowSum += arr[end] - arr[end - k];
            maxSum = Math.max(maxSum, windowSum);
        }

        return maxSum;
    }

    // Example test
    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 1, 3, 2};
        int k = 3;
        System.out.println("Max sum of subarray of size " + k + " = " + maxSumSubarray(arr, k));
    }
}
