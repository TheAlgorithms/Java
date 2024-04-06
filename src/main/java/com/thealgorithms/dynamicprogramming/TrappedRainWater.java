package com.thealgorithms.dynamicprogramming;

/**
 * Utility class for calculating the amount of trapped rainwater between blocks.
 */
public final class TrappedRainWater {
    private TrappedRainWater() {
    }

    /**
     * Calculates the amount of trapped rainwater between blocks given their heights.
     *
     * @param height An array representing the heights of blocks.
     * @return The total amount of trapped rainwater.
     */
    public static int trap(final int[] height) {
        if (height == null || height.length == 0) return 0;

        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int trappedWater = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax)
                    leftMax = height[left];
                else
                    trappedWater += leftMax - height[left];
                left++;
            } else {
                if (height[right] >= rightMax)
                    rightMax = height[right];
                else
                    trappedWater += rightMax - height[right];
                right--;
            }
        }
        return trappedWater;
    }
}
