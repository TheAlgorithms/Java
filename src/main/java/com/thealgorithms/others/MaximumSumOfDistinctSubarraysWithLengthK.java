package com.thealgorithms.others;

import java.util.HashSet;
import java.util.Set;

/**
 * References: https://en.wikipedia.org/wiki/Streaming_algorithm
 *
 * This model involves computing the maximum sum of subarrays of a fixed size \( K \) from a stream of integers.
 * As the stream progresses, elements from the end of the window are removed, and new elements from the stream are added.
 *
 * @author Swarga-codes (https://github.com/Swarga-codes)
 */
public final class MaximumSumOfDistinctSubarraysWithLengthK {
    private MaximumSumOfDistinctSubarraysWithLengthK() {
    }

    /**
     * Finds the maximum sum of a subarray of size K consisting of distinct elements.
     *
     * @param k The size of the subarray.
     * @param nums The array from which subarrays will be considered.
     *
     * @return The maximum sum of any distinct-element subarray of size K. If no such subarray exists, returns 0.
     */
    public static long maximumSubarraySum(int k, int... nums) {
        if (nums.length < k) {
            return 0;
        }
        long masSum = 0; // Variable to store the maximum sum of distinct subarrays
        long currentSum = 0; // Variable to store the sum of the current subarray
        Set<Integer> currentSet = new HashSet<>(); // Set to track distinct elements in the current subarray

        // Initialize the first window
        for (int i = 0; i < k; i++) {
            currentSum += nums[i];
            currentSet.add(nums[i]);
        }
        // If the first window contains distinct elements, update maxSum
        if (currentSet.size() == k) {
            masSum = currentSum;
        }
        // Slide the window across the array
        for (int i = 1; i < nums.length - k + 1; i++) {
            // Update the sum by removing the element that is sliding out and adding the new element
            currentSum = currentSum - nums[i - 1];
            currentSum = currentSum + nums[i + k - 1];
            int j = i;
            boolean flag = false; // flag value which says that the subarray contains distinct elements
            while (j < i + k && currentSet.size() < k) {
                if (nums[i - 1] == nums[j]) {
                    flag = true;
                    break;
                } else {
                    j++;
                }
            }
            if (!flag) {
                currentSet.remove(nums[i - 1]);
            }
            currentSet.add(nums[i + k - 1]);
            // If the current window has distinct elements, compare and possibly update maxSum
            if (currentSet.size() == k && masSum < currentSum) {
                masSum = currentSum;
            }
        }
        return masSum; // the final maximum sum
    }
}
