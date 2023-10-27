package com.thealgorithms.sorts;

import static com.thealgorithms.sorts.SortUtils.*;

/**
 * QuickSort algorithm implementation.
 *
 * @author Varun Upadhyay (https://github.com/varunu28)
 * @author Podshivalov Nikita (https://github.com/nikitap492)
 * @see SortAlgorithm
 */
class QuickSort implements SortAlgorithm {

    /**
     * This method implements the Generic Quick Sort
     *
     * @param array The array to be sorted (Sorts the array in increasing order)
     * @param <T>   The type of elements in the array
     * @return The sorted array
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        doSort(array, 0, array.length - 1);
        return array;
    }

    /**
     * The sorting process
     *
     * @param array The array to be sorted
     * @param left  The first index of the array
     * @param right The last index of the array
     */
    private static <T extends Comparable<T>> void doSort(T[] array, int left, int right) {
        if (left < right) {
            int pivot = partition(array, left, right);
            doSort(array, left, pivot - 1);
            doSort(array, pivot + 1, right);
        }
    }

    /**
     * This method partitions the array for QuickSort
     *
     * @param array The array to be sorted
     * @param left  The first index of the array
     * @param right The last index of the array
     * @return The partition index of the array
     */
    private static <T extends Comparable<T>> int partition(T[] array, int left, int right) {
        int mid = left + (right - left) / 2; // Calculate mid without overflow
        T pivot = array[mid];
        int i = left;
        int j = right;

        while (i <= j) {
            while (less(array[i], pivot)) {
                i++;
            }
            while (less(pivot, array[j])) {
                j--;
            }
            if (i <= j) {
                swap(array, i, j);
                i++;
                j--;
            }
        }
        return i;
    }
}
