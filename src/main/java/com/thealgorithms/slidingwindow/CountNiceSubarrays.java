package com.thealgorithms.slidingwindow;

/**
 * Counts the number of "nice subarrays".
 * A nice subarray is a contiguous subarray that contains exactly k odd numbers.
 *
 * This implementation uses the sliding window technique.
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

        // Final answer: total number of nice subarrays
        int result = 0;

        /*
         * memo[i] stores how many valid starting positions exist
         * when the left pointer is at index i.
         *
         * This avoids recomputing the same values again.
         */
        int[] memo = new int[n];

        // Right pointer moves forward to expand the window
        for (int right = 0; right < n; right++) {

            // If current element is odd, increment odd count
            if ((nums[right] & 1) == 1) {
                oddCount++;
            }

            /*
             * If oddCount exceeds k, shrink the window from the left
             * until oddCount becomes valid again.
             */
            if (oddCount > k) {
                left += memo[left];
                oddCount--;
            }

            /*
             * When the window contains exactly k odd numbers,
             * count all possible valid subarrays starting at `left`.
             */
            if (oddCount == k) {

                /*
                 * If this left index hasn't been processed before,
                 * count how many consecutive even numbers follow it.
                 */
                if (memo[left] == 0) {
                    int count = 0;
                    int temp = left;

                    // Count consecutive even numbers
                    while ((nums[temp] & 1) == 0) {
                        count++;
                        temp++;
                    }

                    /*
                     * Number of valid subarrays starting at `left`
                     * is (count of even numbers + 1)
                     */
                    memo[left] = count + 1;
                }

                // Add number of valid subarrays for this left position
                result += memo[left];
            }
        }
        return result;
    }
}
