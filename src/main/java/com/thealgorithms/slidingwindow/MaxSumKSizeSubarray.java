package com.thealgorithms.slidingwindow;

/**
 * The Sliding Window algorithm is used to find the maximum sum of a subarray
 * of a fixed size k within a given array.
 *
 * <p>
 * Worst-case performance O(n)
 * Best-case performance O(n)
 * Average performance O(n)
 * Worst-case space complexity O(1)
 *
 * @author Your Name (https://github.com/Chiefpatwal)
 */
public final class MaxSumKSizeSubarray {

    // Prevent instantiation
    private MaxSumKSizeSubarray() {
    }

    /**
     * This method finds the maximum sum of a subarray of a given size k.
     *
     * @param arr is the input array where the maximum sum needs to be found
     * @param k   is the size of the subarray
     * @return the maximum sum of the subarray of size k
     */
    public static int maxSumKSizeSubarray(int[] arr, int k) {
        if (arr.length < k) {
            return -1; // Edge case: not enough elements
        }

        int maxSum;
        int windowSum = 0;

        // Calculate the sum of the first window
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }
        maxSum = windowSum;

        // Slide the window across the array
        for (int i = k; i < arr.length; i++) {
            windowSum += arr[i] - arr[i - k];
            maxSum = Math.max(maxSum, windowSum);
        }

        return maxSum;
    }
}
