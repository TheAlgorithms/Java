package com.thealgorithms.slidingwindow;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Maximum Sliding Window Algorithm
 *
 * This algorithm finds the maximum element in each sliding window of size k
 * in a given array of integers. It uses a deque (double-ended queue) to
 * efficiently keep track of potential maximum values in the current window.
 *
 * Time Complexity: O(n), where n is the number of elements in the input array
 * Space Complexity: O(k), where k is the size of the sliding window
 */

public class MaximumSlidingWindow {

    /**
     * Finds the maximum values in each sliding window of size k.
     *
     * @param nums The input array of integers
     * @param windowSize The size of the sliding window
     * @return An array of integers representing the maximums in each window
     */
    public int[] maxSlidingWindow(int[] nums, int windowSize) {
        if (nums == null || nums.length == 0 || windowSize <= 0 || windowSize > nums.length) {
            return new int[0]; // Handle edge cases
        }

        int[] result = new int[nums.length - windowSize + 1];
        Deque<Integer> deque = new ArrayDeque<>();

        for (int currentIndex = 0; currentIndex < nums.length; currentIndex++) {

            // Remove the first element if it's outside the current window
            if (!deque.isEmpty() && deque.peekFirst() == currentIndex - windowSize) {
                deque.pollFirst();
            }

            // Remove all elements smaller than the current element from the end
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[currentIndex]) {
                deque.pollLast();
            }

            // Add the current element's index to the deque
            deque.offerLast(currentIndex);

            // If we have processed at least k elements, add to result
            if (currentIndex >= windowSize - 1) {
                result[currentIndex - windowSize + 1] = nums[deque.peekFirst()];
            }
        }
        return result;
    }
}
