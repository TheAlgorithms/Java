package com.thealgorithms.slidingwindow;

/**
 * The Sliding Window algorithm is used to find the length of the smallest
 * contiguous subarray whose sum is greater than or equal to a given target.
 *
 * <p>Time Complexity: O(n)
 * <br>Space Complexity: O(1)
 *
 * <p>This class provides a static method to find the minimum length subarray
 * that satisfies the given sum condition.
 *
 * <p>Example:
 * <pre>
 * int[] nums = {2, 3, 1, 2, 4, 3};
 * int target = 7;
 * int result = SmallestSubarrayWithSum.findMinSubArrayLen(target, nums);
 * System.out.println(result); // Output: 2 (subarray [4,3])
 * </pre>
 *
 * @author Nihal
 */
public final class SmallestSubarrayWithSum {

    // Prevent instantiation
    private SmallestSubarrayWithSum() {
    }

    /**
     * Finds the minimal length of a contiguous subarray of which the sum ≥ target.
     *
     * @param target the target sum
     * @param nums the array of positive integers
     * @return the length of the smallest subarray with sum ≥ target,
     *         or 0 if no such subarray exists
     */
    public static int findMinSubArrayLen(int target, int[] nums) {
        int windowStart = 0;
        int windowSum = 0;
        int minLength = Integer.MAX_VALUE;

        for (int windowEnd = 0; windowEnd < nums.length; windowEnd++) {
            windowSum += nums[windowEnd];

            while (windowSum >= target) {
                minLength = Math.min(minLength, windowEnd - windowStart + 1);
                windowSum -= nums[windowStart];
                windowStart++;
            }
        }

        return (minLength == Integer.MAX_VALUE) ? 0 : minLength;
    }
}
