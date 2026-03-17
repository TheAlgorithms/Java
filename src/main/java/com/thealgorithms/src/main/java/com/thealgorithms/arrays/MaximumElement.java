package com.thealgorithms.arrays;

public class MaximumElement {
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
