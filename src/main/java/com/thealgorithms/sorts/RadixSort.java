package com.thealgorithms.sorts;

import com.thealgorithms.maths.NumberOfDigits;
import java.util.Arrays;

/**
 * This class provides an implementation of the radix sort algorithm.
 * It sorts an array of integers in increasing order.
 */
public final class RadixSort {
    private static final int BASE = 10;

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

        int[] negatives = Arrays.stream(array).filter(x -> x < 0).map(x -> - x).toArray();
        int[] positives = Arrays.stream(array).filter(x -> x >= 0).toArray();

        if (negatives.length > 0) {
            radixSort(negatives);
            reverseAndNegate(negatives);
        }

        if (positives.length > 0) {
            radixSort(positives);
        }

        int[] sortedArray = new int[array.length];
        mergeNegativeAndPositiveArrays(negatives, sortedArray, positives);

        return sortedArray;
    }

    private static void mergeNegativeAndPositiveArrays(int[] negatives, int[] sortedArray, int[] positives) {
        System.arraycopy(negatives, 0, sortedArray, 0, negatives.length);
        System.arraycopy(positives, 0, sortedArray, negatives.length, positives.length);
    }

    private static void radixSort(int[] array) {
        final int max = Arrays.stream(array).max().getAsInt();
        for (int i = 0, exp = 1; i < NumberOfDigits.numberOfDigits(max); i++, exp *= BASE) {
            countingSortByDigit(array, exp);
        }
    }

    private static void reverseAndNegate(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = -array[array.length - 1 - i];
            array[array.length - 1 - i] = -temp;
        }
        if (array.length % 2 != 0) {
            array[array.length / 2] = -array[array.length / 2];
        }
    }

    /**
     * A utility method to perform counting sort of array[] according to the digit represented by exp.
     *
     * @param array the array to be sorted
     * @param exp   the exponent representing the current digit position
     */
    private static void countingSortByDigit(int[] array, int exp) {
        int[] output = new int[array.length];
        int[] count = new int[BASE];

        countDigits(array, exp, count);
        accumulateCounts(count);
        buildOutput(array, exp, output, count);
        copyOutput(array, output);
    }

    private static void countDigits(int[] array, int exp, int[] count) {
        for (int i = 0; i < array.length; i++) {
            count[getDigit(array[i], exp)]++;
        }
    }

    private static int getDigit(int number, int position) {
        return (number / position) % BASE;
    }

    private static void accumulateCounts(int[] count) {
        for (int i = 1; i < BASE; i++) {
            count[i] += count[i - 1];
        }
    }

    private static void buildOutput(int[] array, int exp, int[] output, int[] count) {
        for (int i = array.length - 1; i >= 0; i--) {
            int digit = getDigit(array[i], exp);
            output[count[digit] - 1] = array[i];
            count[digit]--;
        }
    }

    private static void copyOutput(int[] array, int[] output) {
        System.arraycopy(output, 0, array, 0, array.length);
    }
}
