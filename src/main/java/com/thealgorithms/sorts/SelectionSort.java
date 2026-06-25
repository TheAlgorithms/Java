package com.thealgorithms.sorts;

/**
 * Implementation of the Selection Sort algorithm.
 *
 * @see SortAlgorithm
 */
public class SelectionSort implements SortAlgorithm {
    /**
     * Generic Selection Sort algorithm.
     *
     * Time Complexity:
     * - Best case: O(n^2)
     * - Average case: O(n^2)
     * - Worst case: O(n^2)
     *
     * Space Complexity: O(1) – in-place sorting.
     *
     * @param array the array to be sorted
     * @param <T> the type of elements in the array
     * @return the sorted array
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            final int minIndex = findIndexOfMin(array, i);
            SortUtils.swap(array, i, minIndex);
        }
        return array;
    }

    /**
     * Finds the index of the minimum element in the array starting from a given index.
     *
     * @param array the array to search
     * @param startIndex the index to start searching from
     * @param <T> the type of elements in the array
     * @return the index of the minimum element
     */
    private static <T extends Comparable<T>> int findIndexOfMin(T[] array, final int startIndex) {
        int minIndex = startIndex;
        for (int i = startIndex + 1; i < array.length; i++) {
            if (SortUtils.less(array[i], array[minIndex])) {
                minIndex = i;
            }
        }
        return minIndex;
    }
}