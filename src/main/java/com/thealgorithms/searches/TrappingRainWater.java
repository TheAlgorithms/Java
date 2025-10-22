package com.thealgorithms.searches;

/**
 * Trapping Rain Water
 *
 * Given n non-negative integers representing an elevation map where the width of
 * each bar is 1, compute how much water it can trap after raining.
 *
 * Approach: Two-pointer optimized O(n) time and O(1) extra space.
 */
public final class TrappingRainWater {

    private TrappingRainWater() {}

    /**
     * Compute trapped rain water amount for the given heights array.
     *
     * @param height array of non-negative integers
     * @return total units of trapped water
     * @throws IllegalArgumentException if height is null
     */
    public static int trap(final int[] height) {
        if (height == null) {
            throw new IllegalArgumentException("height array must not be null");
        }

        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int trapped = 0;

        while (left <= right) {
            if (height[left] <= height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    trapped += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    trapped += rightMax - height[right];
                }
                right--;
            }
        }

        return trapped;
    }
}
