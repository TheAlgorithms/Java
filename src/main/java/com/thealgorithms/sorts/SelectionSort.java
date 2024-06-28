package com.thealgorithms.sorts;

/**
 * This class provides an implementation of the selection sort algorithm.
 * It sorts an array of elements in increasing order using an iterative approach.
 */
public class SelectionSort implements SortAlgorithm {

    /**
     * Sorts an array of comparable elements in increasing order using the selection sort algorithm.
     *
     * @param array the array to be sorted
     * @param <T> the class of array elements
     * @return the sorted array
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        if (array.length == 0) {
            return array;
        }

        // One by one move the boundary of the unsorted subarray
        for (int i = 0; i < array.length - 1; i++) {
            // Find the minimum element in the unsorted array
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j].compareTo(array[minIndex]) < 0) {
                    minIndex = j;
                }
            }

            // Swap the found minimum element with the first element
            if (minIndex != i) {
                SortUtils.swap(array, i, minIndex);
            }
        }
        return array;
    }
}