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
 * Note: This implementation handles negative integers as it
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
        final var stats = Arrays.stream(array).summaryStatistics();
        final int min = stats.getMin();
        int[] count = computeHistogram(array, min, stats.getMax() - min + 1);
        toCumulative(count);
        return reconstructSorted(count, min, array);
    }

    private static int[] computeHistogram(final int[] array, final int shift, final int spread) {
        int[] res = new int[spread];
        for (final var value : array) {
            res[value - shift]++;
        }
        return res;
    }

    private static void toCumulative(int[] count) {
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
    }

    private static int[] reconstructSorted(final int[] cumulativeCount, final int shift, final int[] array) {
        int[] res = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            res[cumulativeCount[array[i] - shift] - 1] = array[i];
            cumulativeCount[array[i] - shift]--;
        }
        return res;
    }
}
