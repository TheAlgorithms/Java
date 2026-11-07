package com.thealgorithms.misc;

/**
 * Finds the maximum amount of water a container can store given an array of
 * heights. Uses a two-pointer approach starting from both ends.
 *
 * <p>Example: height = [1,8,6,2,5,4,8,3,7] -> 49
 *
 * <p>Time complexity: O(n), Space complexity: O(1)
 *
 * @see <a href="https://leetcode.com/problems/container-with-most-water/">LeetCode #11</a>
 */
public final class ContainerWithMostWater {
    private ContainerWithMostWater() {
    }

    public static int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxWater = 0;
        while (left < right) {
            int water = Math.min(height[left], height[right]) * (right - left);
            maxWater = Math.max(maxWater, water);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxWater;
    }
}
