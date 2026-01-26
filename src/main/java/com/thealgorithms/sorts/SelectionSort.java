package com.thealgorithms.sorts;

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
     * @see SortAlgorithm
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {

        for (int i = 0; i < array.length - 1; i++) {
            final int minIndex = findIndexOfMin(array, i);
            SortUtils.swap(array, i, minIndex);
        }
        return array;
    }

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
