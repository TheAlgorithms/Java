package com.thealgorithms.others;

import java.util.HashMap;
import java.util.Map;

/**
 * Algorithm to find the maximum sum of a subarray of size K with all distinct
 * elements.
 *
 * This implementation uses a sliding window approach with a hash map to
 * efficiently
 * track element frequencies within the current window. The algorithm maintains
 * a window
 * of size K and slides it across the array, ensuring all elements in the window
 * are distinct.
 *
 * Time Complexity: O(n) where n is the length of the input array
 * Space Complexity: O(k) for storing elements in the hash map
 *
 * @see <a href="https://en.wikipedia.org/wiki/Streaming_algorithm">Streaming
 *      Algorithm</a>
 * @see <a href="https://en.wikipedia.org/wiki/Sliding_window_protocol">Sliding
 *      Window</a>
 * @author Swarga-codes (https://github.com/Swarga-codes)
 */
public final class MaximumSumOfDistinctSubarraysWithLengthK {
    private MaximumSumOfDistinctSubarraysWithLengthK() {
    }

    /**
     * Finds the maximum sum of a subarray of size K consisting of distinct
     * elements.
     *
     * The algorithm uses a sliding window technique with a frequency map to track
     * the count of each element in the current window. A window is valid only if
     * all K elements are distinct (frequency map size equals K).
     *
     * @param k    The size of the subarray. Must be non-negative.
     * @param nums The array from which subarrays will be considered.
     * @return The maximum sum of any distinct-element subarray of size K.
     *         Returns 0 if no such subarray exists or if k is 0 or negative.
     * @throws IllegalArgumentException if k is negative
     */
    public static long maximumSubarraySum(int k, int... nums) {
        if (k <= 0 || nums == null || nums.length < k) {
            return 0;
        }

        long maxSum = 0;
        long currentSum = 0;
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        // Initialize the first window of size k
        for (int i = 0; i < k; i++) {
            currentSum += nums[i];
            frequencyMap.put(nums[i], frequencyMap.getOrDefault(nums[i], 0) + 1);
        }

        // Check if the first window has all distinct elements
        if (frequencyMap.size() == k) {
            maxSum = currentSum;
        }

        // Slide the window across the array
        for (int i = k; i < nums.length; i++) {
            // Remove the leftmost element from the window
            int leftElement = nums[i - k];
            currentSum -= leftElement;
            int leftFrequency = frequencyMap.get(leftElement);
            if (leftFrequency == 1) {
                frequencyMap.remove(leftElement);
            } else {
                frequencyMap.put(leftElement, leftFrequency - 1);
            }

            // Add the new rightmost element to the window
            int rightElement = nums[i];
            currentSum += rightElement;
            frequencyMap.put(rightElement, frequencyMap.getOrDefault(rightElement, 0) + 1);

            // If all elements in the window are distinct, update maxSum if needed
            if (frequencyMap.size() == k && currentSum > maxSum) {
                maxSum = currentSum;
            }
        }

        return maxSum;
    }
}
