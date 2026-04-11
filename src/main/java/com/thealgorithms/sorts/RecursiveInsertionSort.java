package com.thealgorithms.sorts;

/**
 * Recursive Insertion Sort algorithm.
 *
 * This is a recursive implementation of the standard Insertion Sort algorithm.
 * Instead of iterating through the array, it sorts the first n-1 elements recursively
 * and then inserts the nth element into its correct position.
 *
 * Concept:
 * - Divide the problem into smaller subproblems by sorting first n-1 elements.
 * - Insert the last element into the sorted portion.
 *
 * Time Complexity:
 * - Best case: O(n) – array is already sorted
 * - Average case: O(n^2)
 * - Worst case: O(n^2) – array is reverse sorted
 *
 * Space Complexity:
 * - O(n) – due to recursion stack
 *
 * Note:
 * - This implementation is mainly useful for understanding recursion.
 * - Iterative insertion sort is preferred in production due to lower space overhead.
 *
 * @see SortAlgorithm
 */
public class RecursiveInsertionSort implements SortAlgorithm {

    /**
     * Sorts the given array using recursive insertion sort.
     *
     * @param array The array to be sorted
     * @param <T>   The type of elements in the array, which must be comparable
     * @return The sorted array
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }

        recursiveSort(array, array.length);
        return array;
    }

    /**
     * Recursively sorts the first n elements of the array.
     *
     * @param array The array to be sorted
     * @param n     The number of elements to sort
     * @param <T>   The type of elements in the array
     */
    private <T extends Comparable<T>> void recursiveSort(T[] array, int n) {

        // Base case: single element is already sorted
        if (n <= 1) {
            return;
        }

        // Recursively sort first n-1 elements
        recursiveSort(array, n - 1);

        // Insert the nth element into the sorted subarray
        insert(array, n);
    }

    /**
     * Inserts the nth element into its correct position in the sorted subarray [0...n-2].
     *
     * @param array The array containing sorted subarray and one unsorted element
     * @param n     The size of the subarray to consider
     * @param <T>   The type of elements in the array
     */
    private <T extends Comparable<T>> void insert(T[] array, int n) {
        final T key = array[n - 1];
        int j = n - 2;

        // Shift elements greater than key to one position ahead
        while (j >= 0 && SortUtils.less(key, array[j])) {
            array[j + 1] = array[j];
            j--;
        }

        // Place key at correct position
        array[j + 1] = key;
    }
}
