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
        if (array.length == 0) {
            return array;
        }
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
    private static <T extends Comparable<T>> void recursiveSelectionSort(T[] array, final int index) {
        if (index == array.length - 1) {
            return;
        }

        SortUtils.swap(array, index, findMinIndex(array, index));

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
    private static <T extends Comparable<T>> int findMinIndex(T[] array, final int start) {
        // Base case: if start is the last index, return start
        if (start == array.length - 1) {
            return start;
        }

        // Recursive call to find the minimum index in the rest of the array
        final int minIndexInRest = findMinIndex(array, start + 1);

        // Return the index of the smaller element between array[start] and the minimum element in the rest of the array
        return array[start].compareTo(array[minIndexInRest]) < 0 ? start : minIndexInRest;
    }
}
