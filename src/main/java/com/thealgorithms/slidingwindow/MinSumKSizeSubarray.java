package com.thealgorithms.slidingwindow;
/**
 * The Sliding Window algorithm is used to find the minimum sum of a subarray
 * of a fixed size k within a given array.
 *
 * <p>
 * Worst-case performance O(n)
 * Best-case performance O(n)
 * Average performance O(n)
 * Worst-case space complexity O(1)
 *
 * This class provides a static method to find the minimum sum of a subarray
 * with a specified length k.
 *
 * @author Rashi Dashore (https://github.com/rashi07dashore)
 */
public final class MinSumKSizeSubarray {

    // Prevent instantiation
    private MinSumKSizeSubarray() {
    }

    /**
     * This method finds the minimum sum of a subarray of a given size k.
     *
     * @param arr is the input array where the minimum sum needs to be found
     * @param k   is the size of the subarray
     * @return the minimum sum of the subarray of size k
     */
    public static int minSumKSizeSubarray(int[] arr, int k) {
        if (arr.length < k) {
            return -1; // Edge case: not enough elements
        }

        int minSum;
        int windowSum = 0;

        // Calculate the sum of the first window
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }
        minSum = windowSum;

        // Slide the window across the array
        for (int i = k; i < arr.length; i++) {
            windowSum += arr[i] - arr[i - k];
            minSum = Math.min(minSum, windowSum);
        }
        return minSum;
    }
}
