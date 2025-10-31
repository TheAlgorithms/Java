package com.thealgorithms.stacks;
/**
 * Trapping Rainwater Problem
 * Given an array of non-negative integers representing the height of bars,
 * compute how much water it can trap after raining.
 *
 * Example:
 * Input: [4,2,0,3,2,5]
 * Output: 9
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * Reference: https://en.wikipedia.org/wiki/Trapping_rain_water
 */
public final class TrappingRainwater {

    private TrappingRainwater() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int result = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    result += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    result += rightMax - height[right];
                }
                right--;
            }
        }
        return result;
    }
}
