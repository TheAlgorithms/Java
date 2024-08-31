package com.thealgorithms.sorts;

import com.thealgorithms.maths.NumberOfDigits;
import java.util.Arrays;

/**
 * This class provides an implementation of the radix sort algorithm.
 * It sorts an array of nonnegative integers in increasing order.
 */
public final class RadixSort {
    private static final int BASE = 10;

    private RadixSort() {
    }

    /**
     * Sorts an array of nonnegative integers using the radix sort algorithm.
     *
     * @param array the array to be sorted
     * @return the sorted array
     * @throws IllegalArgumentException if any negative integers are found
     */
    public static int[] sort(int[] array) {
        if (array.length == 0) {
            return array;
        }

        checkForNegativeInput(array);
        radixSort(array);
        return array;
    }

    /**
     * Checks if the array contains any negative integers.
     *
     * @param array the array to be checked
     * @throws IllegalArgumentException if any negative integers are found
     */
    private static void checkForNegativeInput(int[] array) {
        for (int number : array) {
            if (number < 0) {
                throw new IllegalArgumentException("Array contains non-positive integers.");
            }
        }
    }

    private static void radixSort(int[] array) {
        final int max = Arrays.stream(array).max().getAsInt();
        for (int i = 0, exp = 1; i < NumberOfDigits.numberOfDigits(max); i++, exp *= BASE) {
            countingSortByDigit(array, exp);
        }
    }

    /**
     * A utility method to perform counting sort of array[] according to the digit represented by exp.
     *
     * @param array the array to be sorted
     * @param exp   the exponent representing the current digit position
     */
    private static void countingSortByDigit(int[] array, int exp) {
        int[] count = countDigits(array, exp);
        accumulateCounts(count);
        int[] output = buildOutput(array, exp, count);
        copyOutput(array, output);
    }

    private static int[] countDigits(int[] array, int exp) {
        int[] count = new int[BASE];
        for (int i = 0; i < array.length; i++) {
            count[getDigit(array[i], exp)]++;
        }
        return count;
    }

    private static int getDigit(int number, int position) {
        return (number / position) % BASE;
    }

    private static void accumulateCounts(int[] count) {
        for (int i = 1; i < BASE; i++) {
            count[i] += count[i - 1];
        }
    }

    private static int[] buildOutput(int[] array, int exp, int[] count) {
        int[] output = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            int digit = getDigit(array[i], exp);
            output[count[digit] - 1] = array[i];
            count[digit]--;
        }
        return output;
    }

    private static void copyOutput(int[] array, int[] output) {
        System.arraycopy(output, 0, array, 0, array.length);
    }
}
