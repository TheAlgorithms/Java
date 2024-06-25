package com.thealgorithms.sorts;

/**
 * Class that implements the Selection Sort algorithm using recursion.
 */
public class SelectionSortRecursive implements SortAlgorithm {

    /**
     * Sorts an array using recursive selection sort.
     *
     * @param array the array to be sorted
     * @param <T>   the type of elements in the array (must be Comparable)
     * @return the sorted array
     */
    public <T extends Comparable<T>> T[] sort(T[] array) {
        recursiveSelectionSort(array, 0);
        return array;
    }

    /**
     * Recursively sorts the array using selection sort.
     *
     * @param array the array to be sorted
     * @param index the current index to start sorting from
     * @param <T>   the type of elements in the array (must be Comparable)
     */
    private static <T extends Comparable<T>> void recursiveSelectionSort(T[] array, int index) {
        if (array == null || array.length == 0) {
            return;
        }
        if (index == array.length - 1) {
            return;
        }

        // Find the minimum element in the remaining unsorted array
        int minIndex = findMinIndex(array, index);

        // Swap the found minimum element with the element at the current index
        if (minIndex != index) {
            SortUtils.swap(array, index, minIndex);
        }

        // Recursively call selection sort for the remaining array
        recursiveSelectionSort(array, index + 1);
    }

    /**
     * Finds the index of the minimum element in the array starting from the given index.
     *
     * @param array the array to search
     * @param start the starting index for the search
     * @param <T>   the type of elements in the array
     * @return the index of the minimum element
     */
    private static <T extends Comparable<T>> int findMinIndex(T[] array, int start) {
        int currentMinIndex = start;

        for (int currentIndex = start + 1; currentIndex < array.length; currentIndex++) {
            if (array[currentIndex].compareTo(array[currentMinIndex]) < 0) {
                currentMinIndex = currentIndex;
            }
        }

        return currentMinIndex;
    }
}
