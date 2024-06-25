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
        // Base case: if the array is null, empty, or index has reached the end of the array, return
        if (array == null || array.length == 0) {
            return;
        }
        if (index == array.length - 1) {
            return;
        }

        // Find the minimum element in the remaining unsorted array
        int minIndex = findMinIndex(array, index, index + 1);

        // Swap the found minimum element with the element at the current index
        if (minIndex != index) {
            SortUtils.swap(array, index, minIndex);
        }

        // Recursively call selection sort for the remaining array
        recursiveSelectionSort(array, index + 1);
    }

    /**
     * Recursively finds the index of the minimum element in the array starting from a given index.
     *
     * @param array            the array to search
     * @param currentMinIndex  the index of the current minimum element
     * @param currentIndex     the current index being checked
     * @param <T>              the type of elements in the array (must be Comparable)
     * @return the index of the minimum element in the array
     */
    private static <T extends Comparable<T>> int findMinIndex(T[] array, int currentMinIndex, int currentIndex) {
        // Base case: if the currentIndex has reached the end of the array, return currentMinIndex
        if (currentIndex == array.length) {
            return currentMinIndex;
        }

        // Update currentMinIndex if the element at currentIndex is smaller
        if (array[currentIndex].compareTo(array[currentMinIndex]) < 0) {
            currentMinIndex = currentIndex;
        }

        // Recursively find the minimum element in the rest of the array
        return findMinIndex(array, currentMinIndex, currentIndex + 1);
    }
}
