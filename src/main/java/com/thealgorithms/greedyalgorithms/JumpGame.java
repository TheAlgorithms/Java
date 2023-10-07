package com.thealgorithms.greedyalgorithms;

// problem link : https://leetcode.com/problems/jump-game/

public class JumpGame {
    // A method to check if you can jump to the end of an array
    public boolean canJump(int[] nums) {
        int goal = nums.length - 1; // Initialize the goal as the last index of the array

        // Loop through the array from right to left
        for (int i = nums.length - 2; i >= 0; i--) {
            // If the current position plus the maximum jump from that position
            // is greater than or equal to the goal, update the goal to the current position
            if (i + nums[i] >= goal) {
                goal = i;
            }
        }

        // If the goal is at the beginning (index 0), it means we can jump to the end
        if (goal == 0) {
            return true;
        } else {
            // If the goal is not at the beginning, we cannot reach the end
            return false;
        }
    }
}
