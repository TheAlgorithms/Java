package com.thealgorithms.arrays;

/**
 * This class provides a method to find the maximum element in an array.
 */
public class MaximumElement {

    /**
     * Finds the maximum value in the given array.
     *
     * @param arr the input array
     * @return the maximum element in the array
     */
    public static int findMax(int[] arr) {
        int max = Integer.MIN_VALUE;

        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 3, 9, 2};
        System.out.println("Maximum element: " + findMax(arr));
    }
}
