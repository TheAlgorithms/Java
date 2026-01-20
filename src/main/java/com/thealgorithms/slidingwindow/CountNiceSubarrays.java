package com.thealgorithms.slidingwindow;

/**
 * Counts the number of "nice subarrays".
 * A nice subarray is a contiguous subarray that contains exactly k odd numbers.
 *
 * Approach:
 * This implementation uses a sliding window technique.
 * The window expands using a right pointer and shrinks from the left when the
 * number of odd elements exceeds k. Even numbers do not affect the odd count
 * and can be freely included in the window.
 *
 * Example:
 * Input: nums = [1, 1, 2, 1, 1], k = 3
 * Output: 2
 *
 * Explanation:
 * The subarrays with exactly 3 odd numbers are:
 * [1, 1, 2, 1]
 * [1, 2, 1, 1]
 *
 * Reference:
 * https://leetcode.com/problems/count-number-of-nice-subarrays/
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public final class CountNiceSubarrays {

    // Private constructor to prevent instantiation
    private CountNiceSubarrays() {
    }

    /**
     * Returns the count of subarrays containing exactly k odd numbers.
     *
     * @param nums input array of integers
     * @param k    number of odd elements required in the subarray
     * @return number of nice subarrays
     */
    public static int countNiceSubarrays(int[] nums, int k) {

        int n = nums.length;

        // Left pointer of the sliding window
        int left = 0;

        // Tracks number of odd elements in the current window
        int oddCount = 0;

        // Stores the total number of nice subarrays
        int result = 0;

        // memo[i] stores how many valid subarrays can start at index i
        int[] memo = new int[n];

        // Right pointer expands the window
        for (int right = 0; right < n; right++) {

            // Increment odd count if current number is odd
            if ((nums[right] & 1) == 1) {
                oddCount++;
            }

            // Shrink the window if odd count exceeds k
            if (oddCount > k) {
                left += memo[left];
                oddCount--;
            }

            // When exactly k odd numbers are present
            if (oddCount == k) {

                // Calculate number of valid subarrays starting at left (only once)
                if (memo[left] == 0) {
                    int count = 0;
                    int temp = left;

                    // Count consecutive even numbers
                    while ((nums[temp] & 1) == 0) {
                        count++;
                        temp++;
                    }

                    // Valid subarrays = consecutive evens + 1
                    memo[left] = count + 1;
                }

                // Add valid subarrays count for current window
                result += memo[left];
            }
        }
        return result;
    }
}
