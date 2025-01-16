package com.thealgorithms.sorts;

import static com.thealgorithms.sorts.SortUtils.less;

/**
 * Generic implementation of Dark Sort algorithm.
 * Dark Sort is a conceptual sorting algorithm combining divide-and-conquer
 * and randomized selection principles.
 *
 * @see SortAlgorithm
 */
class DarkSort implements SortAlgorithm {

    /**
     * Sorts the array using the Dark Sort algorithm.
     *
     * @param unsorted the array to be sorted
     * @param <T> Comparable class
     * @return sorted array
     */
    @Override

    public <T extends Comparable<T>> T[] sort(T[] unsorted) {
        if (unsorted == null || unsorted.length <= 1) {
            return unsorted;
        }
        doDarkSort(unsorted, 0, unsorted.length - 1);
        return unsorted;
    }

    /**
     * Recursive function that implements Dark Sort.
     *
     * @param arr the array to be sorted
     * @param left the starting index of the array
     * @param right the ending index of the array
     */
    private <T extends Comparable<T>> void doDarkSort(T[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        // Divide the array into two parts using a random pivot
        int pivotIndex = partition(arr, left, right);

        // Recursively sort both halves
        doDarkSort(arr, left, pivotIndex - 1);
        doDarkSort(arr, pivotIndex + 1, right);
    }

    /**
     * Partitions the array into two halves around a pivot element.
     *
     * @param arr the array to partition
     * @param left the starting index
     * @param right the ending index
     * @return the index of the pivot element
     */
    private <T extends Comparable<T>> int partition(T[] arr, int left, int right) {
        T pivot = arr[right]; // Choosing the last element as pivot
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (less(arr[j], pivot)) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, right);
        return i + 1;
    }

    /**
     * Swaps two elements in the array.
     *
     * @param arr the array
     * @param i the index of the first element
     * @param j the index of the second element
     */
    private <T extends Comparable<T>> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
