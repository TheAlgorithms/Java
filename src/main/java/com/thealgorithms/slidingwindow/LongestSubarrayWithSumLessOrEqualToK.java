package com.thealgorithms.slidingwindow;

/**
 * The Longest Subarray with Sum Less Than or Equal to K algorithm finds the length of the longest
 * subarray with a sum that does not exceed a given value `k`.
 *
 * <p>
 * Worst-case performance O(n)
 * Best-case performance O(n)
 * Average performance O(n)
 * Worst-case space complexity O(1)
 *
 * @author  (https://github.com/Chiefpatwal)
 */
public final class LongestSubarrayWithSumLessOrEqualToK {

    // Prevent instantiation
    private LongestSubarrayWithSumLessOrEqualToK() {
    }

    /**
     * Finds the length of the longest subarray with a sum less than or equal to a given value.
     *
     * @param nums The input array of integers
     * @param k The maximum allowed sum for the subarray
     * @return The length of the longest subarray with sum <= k
     */
    public static int longestSubarrayWithSumLEK(int[] nums, int k) {
        int maxLength = 0;
        int currentSum = 0;
        int left = 0;

        // Expand the window by moving `right`
        for (int right = 0; right < nums.length; right++) {
            currentSum += nums[right];

            // Shrink the window from the left if `currentSum` exceeds `k`
            while (currentSum > k && left <= right) {
                currentSum -= nums[left];
                left++;
            }

            // Update the maximum length if the current window is valid
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
