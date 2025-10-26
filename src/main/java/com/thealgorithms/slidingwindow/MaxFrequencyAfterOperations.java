package com.thealgorithms.slidingwindow;

import java.util.Arrays;

/**
 * Implementation of the algorithm to compute the maximum possible frequency of
 * any element
 * in an integer array after performing a fixed number of operations.
 *
 * <p>
 * In each operation, you can:
 * <ul>
 * <li>Select an index that hasn't been used in previous operations.</li>
 * <li>Add an integer in the range [-k, k] to the element at that index.</li>
 * </ul>
 *
 * <p>
 * The goal is to maximize the frequency of the most common element after all
 * operations.
 * </p>
 *
 * <p>
 * <b>Example:</b>
 * </p>
 * 
 * <pre>
 * Input: nums = [1,4,5], k = 1, numOperations = 2
 * Output: 2
 * Explanation:
 * - Add 0 to nums[1] → [1,4,5]
 * - Add -1 to nums[2] → [1,4,4]
 * The maximum frequency is 2.
 * </pre>
 *
 * <p>
 * <b>Time Complexity:</b> O(n log n)
 * <br>
 * Because the array is sorted initially using {@link Arrays#sort}, which takes
 * O(n log n),
 * and the sliding window traversal over the sorted array runs in O(n) time.
 * </p>
 *
 * <p>
 * <b>Space Complexity:</b> O(1)
 * <br>
 * The algorithm modifies and scans the array in place without using any
 * additional data structures proportional to the input size.
 * </p>
 */
public final class MaxFrequencyAfterOperations {

    private MaxFrequencyAfterOperations() {
        // Utility class; prevent instantiation
    }

    /**
     * Computes the maximum possible frequency of any element in {@code nums}
     * after performing {@code numOperations} modifications where each modification
     * allows adding an integer in [-k, k] to a previously unused index.
     *
     * @param nums          the input array of integers
     * @param k             the maximum absolute value that can be added or
     *                      subtracted per operation
     * @param numOperations the number of operations allowed
     * @return the maximum achievable frequency after operations
     * @throws NullPointerException if {@code nums} is {@code null}
     *
     *                              <p>
     *                              <b>Time Complexity:</b> O(n log n)
     *                              </p>
     *                              <p>
     *                              <b>Space Complexity:</b> O(1)
     *                              </p>
     */
    public static int maxFrequency(int[] nums, int k, int numOperations) {
        if (nums == null) {
            throw new NullPointerException("Input array cannot be null.");
        }

        // Sort the array for efficient sliding window traversal
        Arrays.sort(nums);
        int n = nums.length;

        // Sliding window pointers and counters
        int l1 = 0, r1 = 0, cnt1 = 0;
        int l2 = 0, cnt2 = 0;
        int sameCnt = 0, prev = Integer.MIN_VALUE;
        int maxF = 0;

        for (int x : nums) {
            if (x == prev)
                sameCnt++;
            else {
                prev = x;
                sameCnt = 1;
            }

            // Window for elements within [x - k, x]
            while (nums[l1] < x - k) {
                cnt1--;
                l1++;
            }
            while (r1 < n && nums[r1] <= x + k) {
                cnt1++;
                r1++;
            }

            // Option 1: Adjust within ±k
            maxF = Math.max(maxF, sameCnt + Math.min(cnt1 - sameCnt, numOperations));

            // Option 2: Adjust within ±2k
            cnt2++;
            while (nums[l2] < x - 2L * k) {
                cnt2--;
                l2++;
            }

            maxF = Math.max(maxF, Math.min(cnt2, numOperations));
        }

        return maxF;
    }
}
