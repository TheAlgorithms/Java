package com.thealgorithms.sorts;

import java.util.Arrays;

/**
 * A standard implementation of the Counting Sort algorithm for integer arrays.
 * This implementation has a time complexity of O(n + k), where n is the number
 * of elements in the input array and k is the range of the input.
 * It works only with integer arrays.
 *
 * The space complexity is O(k), where k is the range of the input integers.
 *
 * Note: This implementation does not handle negative integers as it
 * calculates the range based on the minimum and maximum values of the array.
 *
 */
public final class CountingSort {
    private CountingSort() {
    }

    /**
     * Sorts an array of integers using the Counting Sort algorithm.
     *
     * @param array the array to be sorted
     * @return the sorted array
     */
    public static int[] sort(int[] array) {
        if (array.length == 0) {
            return array;
        }

        int max = Arrays.stream(array).max().orElse(Integer.MIN_VALUE);
        int min = Arrays.stream(array).min().orElse(Integer.MAX_VALUE);
        int range = max - min + 1;

        int[] count = new int[range];
        int[] output = new int[array.length];

        for (int value : array) {
            count[value - min]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        for (int i = array.length - 1; i >= 0; i--) {
            output[count[array[i] - min] - 1] = array[i];
            count[array[i] - min]--;
        }

        return output;
    }
}
