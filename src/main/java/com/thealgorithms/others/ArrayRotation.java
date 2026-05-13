package com.thealgorithms.others;

/**
 * Array Rotation Utility
 *
 * Supports:
 * 1. Left Rotation
 * 2. Right Rotation
 *
 * Approach:
 * Reversal Algorithm
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class ArrayRotation {

    private ArrayRotation() {
    }

    /**
     * Rotates the array to the right by k positions.
     *
     * @param nums the input array
     * @param k number of rotations
     */
    public static void rotateRight(int[] nums, int k) {

        int n = nums.length;

        if (n == 0) {
            return;
        }

        k = k % n;

        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    /**
     * Rotates the array to the left by k positions.
     *
     * @param nums the input array
     * @param k number of rotations
     */
    public static void rotateLeft(int[] nums, int k) {

        int n = nums.length;

        if (n == 0) {
            return;
        }

        k = k % n;

        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
        reverse(nums, 0, n - 1);
    }

    /**
     * Reverses elements between start and end indices.
     *
     * @param nums the input array
     * @param start starting index
     * @param end ending index
     */
    private static void reverse(int[] nums, int start, int end) {

        while (start < end) {

            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;

            start++;
            end--;
        }
    }
  }