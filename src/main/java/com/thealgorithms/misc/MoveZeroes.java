package com.thealgorithms.misc;

/**
 * Moves all zeroes in an array to the end while maintaining the relative
 * order of non-zero elements. The operation is performed in-place.
 *
 * <p>Example: [0, 1, 0, 3, 12] -> [1, 3, 12, 0, 0]
 *
 * <p>Time complexity: O(n), Space complexity: O(1)
 *
 * @see <a href="https://leetcode.com/problems/move-zeroes/">LeetCode #283</a>
 */
public final class MoveZeroes {
    private MoveZeroes() {
    }

    public static void moveZeroes(int[] nums) {
        int insertPos = 0;
        for (int num : nums) {
            if (num != 0) {
                nums[insertPos++] = num;
            }
        }
        while (insertPos < nums.length) {
            nums[insertPos++] = 0;
        }
    }
}
