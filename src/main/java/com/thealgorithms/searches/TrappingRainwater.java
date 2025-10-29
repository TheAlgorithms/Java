package com.thealgorithms.searches;

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
public class TrappingRainwater {

    /**
     * Calculates the total trapped rainwater.
     *
     * @param height an array representing elevation map
     * @return total units of water trapped
     */
    public static int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int trappedWater = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    trappedWater += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    trappedWater += rightMax - height[right];
                }
                right--;
            }
        }

        return trappedWater;
    }

    // Example test
    public static void main(String[] args) {
        int[] height = {4, 2, 0, 3, 2, 5};
        System.out.println("Total trapped water: " + trap(height)); // Output: 9
    }
}
