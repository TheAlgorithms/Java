package com.thealgorithms.sorts;

import java.util.Arrays;

/**
 * Implementation of Merge Sort without using extra space for merging.
 * This implementation performs in-place merging to sort the array of integers.
 */
public final class MergeSortNoExtraSpace {
    private MergeSortNoExtraSpace() {
    }

    /**
     * Sorts the array using in-place merge sort algorithm.
     *
     * @param array the array to be sorted
     * @return the sorted array
     * @throws IllegalArgumentException If the array contains negative numbers.
     */
    public static int[] sort(int[] array) {
        if (array.length == 0) {
            return array;
        }
        if (Arrays.stream(array).anyMatch(s -> s < 0)) {
            throw new IllegalArgumentException("Implementation cannot sort negative numbers.");
        }

        final int maxElement = Arrays.stream(array).max().getAsInt() + 1;
        mergeSort(array, 0, array.length - 1, maxElement);
        return array;
    }

    /**
     * Recursively divides the array into two halves, sorts and merges them.
     *
     * @param array  the array to be sorted
     * @param start  the starting index of the array
     * @param end    the ending index of the array
     * @param maxElement the value greater than any element in the array, used for encoding
     */
    public static void mergeSort(int[] array, int start, int end, int maxElement) {
        if (start < end) {
            final int middle = (start + end) >>> 1;
            mergeSort(array, start, middle, maxElement);
            mergeSort(array, middle + 1, end, maxElement);
            merge(array, start, middle, end, maxElement);
        }
    }

    /**
     * Merges two sorted subarrays [start...middle] and [middle+1...end] in place.
     *
     * @param array  the array containing the subarrays to be merged
     * @param start  the starting index of the first subarray
     * @param middle    the ending index of the first subarray and starting index of the second subarray
     * @param end    the ending index of the second subarray
     * @param maxElement the value greater than any element in the array, used for encoding
     */
    private static void merge(int[] array, int start, int middle, int end, int maxElement) {
        int i = start;
        int j = middle + 1;
        int k = start;
        while (i <= middle && j <= end) {
            if (array[i] % maxElement <= array[j] % maxElement) {
                array[k] = array[k] + (array[i] % maxElement) * maxElement;
                k++;
                i++;
            } else {
                array[k] = array[k] + (array[j] % maxElement) * maxElement;
                k++;
                j++;
            }
        }
        while (i <= middle) {
            array[k] = array[k] + (array[i] % maxElement) * maxElement;
            k++;
            i++;
        }
        while (j <= end) {
            array[k] = array[k] + (array[j] % maxElement) * maxElement;
            k++;
            j++;
        }
        for (i = start; i <= end; i++) {
            array[i] = array[i] / maxElement;
        }
    }
}
