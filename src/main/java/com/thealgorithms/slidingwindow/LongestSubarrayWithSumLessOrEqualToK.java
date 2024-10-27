package com.thealgorithms.slidingwindow;

/**
 * The Longest Subarray with Sum Less Than or Equal to k algorithm finds the length
 * of the longest subarray whose sum is less than or equal to a given value k.
 *
 * <p>
 * Worst-case performance O(n)
 * Best-case performance O(n)
 * Average performance O(n)
 * Worst-case space complexity O(1)
 *
 * @author https://github.com/Chiefpatwal
 */
public final class LongestSubarrayWithSumLessOrEqualToK {

    // Prevent instantiation
    private LongestSubarrayWithSumLessOrEqualToK() {
    }

    /**
     * This method finds the length of the longest subarray with a sum less than or equal to k.
     *
     * @param arr is the input array
     * @param k   is the maximum sum allowed
     * @return the length of the longest subarray with sum less than or equal to k
     */
    public static int longestSubarrayWithSumLEK(int[] arr, int k) {
        int maxLength = 0; // To store the maximum length found
        int currentSum = 0; // To store the current sum of the window
        int left = 0; // Left index of the sliding window

        for (int right = 0; right < arr.length; right++) {
            currentSum += arr[right]; // Expand the window to the right

            // Shrink the window from the left if the current sum exceeds k
            while (currentSum > k && left <= right) {
                currentSum -= arr[left]; // Remove the leftmost element
                left++; // Move the left index to the right
            }

            // Update maxLength if the current window is valid
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength; // Return the maximum length found
    }
}
