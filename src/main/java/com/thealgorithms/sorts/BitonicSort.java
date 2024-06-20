package com.thealgorithms.sorts;

import java.util.Arrays;

/**
 * BitonicSort class implements the SortAlgorithm interface using the bitonic sort technique.
 */
public class BitonicSort implements SortAlgorithm {

    /**
     * Sorts the given array using the Bitonic Sort algorithm.
     *
     * @param <T> the type of elements in the array, which must implement the Comparable interface
     * @param arr the array to be sorted
     * @return the sorted array
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }

        int n = arr.length;
        int paddedSize = nextPowerOfTwo(n);
        T[] paddedArray = Arrays.copyOf(arr, paddedSize);

        // Fill the padded part with a maximum value
        T maxValue = findMax(arr);
        Arrays.fill(paddedArray, n, paddedSize, maxValue);

        bitonicSort(paddedArray, 0, paddedSize, true);

        return Arrays.copyOf(paddedArray, n);
    }

    private <T extends Comparable<T>> void bitonicSort(T[] arr, int low, int cnt, boolean dir) {
        if (cnt > 1) {
            int k = cnt / 2;

            // Sort first half in ascending order
            bitonicSort(arr, low, k, true);

            // Sort second half in descending order
            bitonicSort(arr, low + k, cnt - k, false);

            // Merge the whole sequence in ascending order
            bitonicMerge(arr, low, cnt, dir);
        }
    }

    private <T extends Comparable<T>> void bitonicMerge(T[] arr, int low, int cnt, boolean dir) {
        if (cnt > 1) {
            int k = cnt / 2;

            for (int i = low; i < low + k; i++) {
                if (dir == (arr[i].compareTo(arr[i + k]) > 0)) {
                    SortUtils.swap(arr, i, i + k);
                }
            }

            bitonicMerge(arr, low, k, dir);
            bitonicMerge(arr, low + k, cnt - k, dir);
        }
    }

    /**
     * Finds the maximum element in the given array.
     *
     * @param <T> the type of elements in the array, which must implement the Comparable interface
     * @param array the array to be searched
     * @return the maximum element in the array
     * @throws IllegalArgumentException if the array is null or empty
     */
    public static <T extends Comparable<T>> T findMax(T[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty");
        }

        T max = array[0];
        for (T element : array) {
            if (element.compareTo(max) > 0) {
                max = element;
            }
        }
        return max;
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
}
