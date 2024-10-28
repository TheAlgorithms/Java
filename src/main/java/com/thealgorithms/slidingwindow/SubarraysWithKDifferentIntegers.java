package com.thealgorithms.slidingwindow;

/**
 * The Subarrays with K Different Integers algorithm counts the number of subarrays
 * that contain exactly k distinct integers.
 *
 * <p>
 * Worst-case performance O(n)
 * Best-case performance O(n)
 * Average performance O(n)
 * Worst-case space complexity O(k)
 *
 * @author https://github.com/Chiefpatwal
 */
public final class SubarraysWithKDifferentIntegers {

    // Prevent instantiation
    private SubarraysWithKDifferentIntegers() {
    }

    /**
     * This method counts the number of subarrays with exactly k different integers.
     *
     * @param arr is the input array
     * @param k   is the number of distinct integers
     * @return the count of subarrays with exactly k distinct integers
     */
    public static int subarraysWithKDistinct(int[] arr, int k) {
        return atMostKDistinct(arr, k) - atMostKDistinct(arr, k - 1);
    }

    // Helper method to count subarrays with at most k distinct integers
    private static int atMostKDistinct(int[] arr, int k) {
        if (k <= 0) {
            return 0;
        }

        int count = 0; // To store the count of valid subarrays
        int left = 0; // Left index of the sliding window
        int[] frequency = new int[arr.length + 1]; // Frequency array to count distinct integers

        for (int right = 0; right < arr.length; right++) {
            if (frequency[arr[right]] == 0) {
                k--; // New distinct integer added
            }
            frequency[arr[right]]++; // Increment the frequency of the current element

            while (k < 0) { // More than k distinct integers
                frequency[arr[left]]--; // Remove the leftmost element from the window
                if (frequency[arr[left]] == 0) {
                    k++; // Distinct integer count reduced
                }
                left++; // Move the left index to the right
            }
            count += right - left + 1; // Count the number of valid subarrays ending at 'right'
        }

        return count; // Return the total count
    }
}
