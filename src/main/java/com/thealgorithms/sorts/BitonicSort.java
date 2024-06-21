package com.thealgorithms.sorts;

import java.util.Arrays;

/**
 * BitonicSort class implements the SortAlgorithm interface using the bitonic sort technique.
 */
public class BitonicSort implements SortAlgorithm {
    private enum Direction {
        DESCENDING,
        ASCENDING,
    }

    /**
     * Sorts the given array using the Bitonic Sort algorithm.
     *
     * @param <T> the type of elements in the array, which must implement the Comparable interface
     * @param unsorted the array to be sorted
     * @return the sorted array
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] unsorted) {
        if (unsorted == null || unsorted.length == 0) {
            return unsorted;
        }

        final int paddedSize = nextPowerOfTwo(unsorted.length);
        T[] paddedArray = Arrays.copyOf(unsorted, paddedSize);

        // Fill the padded part with a maximum value
        final T maxValue = max(unsorted);
        Arrays.fill(paddedArray, unsorted.length, paddedSize, maxValue);

        bitonicSort(paddedArray, 0, paddedSize, Direction.ASCENDING);
        return Arrays.copyOf(paddedArray, unsorted.length);
    }

    private <T extends Comparable<T>> void bitonicSort(T[] arr, int low, int cnt, Direction dir) {
        if (cnt > 1) {
            final int k = cnt / 2;

            // Sort first half in ascending order
            bitonicSort(arr, low, k, Direction.ASCENDING);

            // Sort second half in descending order
            bitonicSort(arr, low + k, cnt - k, Direction.DESCENDING);

            // Merge the whole sequence in ascending order
            bitonicMerge(arr, low, cnt, dir);
        }
    }

    /**
     * Merges the bitonic sequence in the specified direction.
     *
     * @param <T> the type of elements in the array, which must be Comparable
     * @param arr the array containing the bitonic sequence to be merged
     * @param low the starting index of the sequence to be merged
     * @param cnt the number of elements in the sequence to be merged
     * @param dir the direction of sorting: true for ascending, false for descending
     */
    private <T extends Comparable<T>> void bitonicMerge(T[] arr, int low, int cnt, Direction dir) {
        if (cnt > 1) {
            final int k = cnt / 2;

            for (int i = low; i < low + k; i++) {
                boolean condition = (dir == Direction.ASCENDING) ? arr[i].compareTo(arr[i + k]) > 0 : arr[i].compareTo(arr[i + k]) < 0;
                if (condition) {
                    SortUtils.swap(arr, i, i + k);
                }
            }

            bitonicMerge(arr, low, k, dir);
            bitonicMerge(arr, low + k, cnt - k, dir);
        }
    }

    /**
     * Finds the next power of two greater than or equal to the given number.
     *
     * @param n the number
     * @return the next power of two
     */
    private static int nextPowerOfTwo(int n) {
        int count = 0;

        // First n in the below condition is for the case where n is 0
        if ((n & (n - 1)) == 0) {
            return n;
        }

        while (n != 0) {
            n >>= 1;
            count += 1;
        }

        return 1 << count;
    }

    /**
     * Finds the maximum element in the given array.
     *
     * @param <T> the type of elements in the array, which must implement the Comparable interface
     * @param array the array to be searched
     * @return the maximum element in the array
     * @throws IllegalArgumentException if the array is null or empty
     */
    private static <T extends Comparable<T>> T max(T[] array) {
        T max = array[0];
        for (T element : array) {
            if (SortUtils.greater(element, max)) {
                max = element;
            }
        }
        return max;
    }
}
