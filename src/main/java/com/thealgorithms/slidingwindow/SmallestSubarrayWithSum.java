package com.thealgorithms.slidingwindow;

/**
 * Finds the length of the smallest contiguous subarray
 * whose sum is greater than or equal to a given target.
 *
 * <p>Example:
 * arr = [2, 3, 1, 2, 4, 3], target = 7
 * Smallest subarray = [4, 3], length = 2
 *
 * <p>Time complexity: O(n)
 * Space complexity: O(1)
 */
public final class SmallestSubarrayWithSum {

    // Prevent instantiation
    private SmallestSubarrayWithSum() {
    }

    /**
     * Returns the minimal length of a contiguous subarray of which
     * the sum ≥ target. If no such subarray exists, returns 0.
     *
     * @param arr the input array
     * @param target the target sum
     * @return the minimal subarray length, or 0 if not found
     */
    public static int smallestSubarrayLen(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int left = 0;
        int sum = 0;
        int minLen = Integer.MAX_VALUE;

        for (int right = 0; right < arr.length; right++) {
            sum += arr[right];
            while (sum >= target) {
                minLen = Math.min(minLen, right - left + 1);
                sum -= arr[left++];
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
