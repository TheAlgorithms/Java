package com.thealgorithms.sorts;

import java.util.Arrays;

/**
 * This class provides an implementation of the radix sort algorithm.
 * It sorts an array of integers in increasing order.
 */
public final class RadixSort {
    private RadixSort() {
    }

    /**
     * Sorts an array of integers using the radix sort algorithm.
     *
     * @param array the array to be sorted
     * @return the sorted array
     */
    public static int[] sort(int[] array) {
        if (array.length == 0) {
            return array;
        }

        final int max = Arrays.stream(array).max().getAsInt();
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(array, exp);
        }

        return array;
    }

    /**
     * A utility method to perform counting sort of array[] according to the digit represented by exp.
     *
     * @param array the array to be sorted
     * @param exp   the exponent representing the current digit position
     */
    private static void countingSortByDigit(int[] array, int exp) {
        int[] output = new int[array.length];
        int[] count = new int[10];

        for (int i = 0; i < array.length; i++) {
            count[(array[i] / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = array.length - 1; i >= 0; i--) {
            int digit = (array[i] / exp) % 10;
            output[count[digit] - 1] = array[i];
            count[digit]--;
        }

        System.arraycopy(output, 0, array, 0, array.length);
    }
}
// Written by James Mc Dermott(theycallmemac)
