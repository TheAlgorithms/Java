package com.thealgorithms.sorts;

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
        // One by one move the boundary of the unsorted subarray
        for (int i = 0; i < array.length - 1; i++) {

            // Swap the remaining minimum element with the current element
            SortUtils.swap(array, i, findIndexOfMin(array, i));
        }
        return array;
    }

    private static <T extends Comparable<T>> int findIndexOfMin(T[] array, final int start) {
        int minIndex = start;
        for (int i = start + 1; i < array.length; i++) {
            if (array[i].compareTo(array[minIndex]) < 0) {
                minIndex = i;
            }
        }
        return minIndex;
    }
}
