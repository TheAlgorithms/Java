package com.thealgorithms.randomized;

/**
 * This class implements the Randomized QuickSort algorithm.
 * It selects a pivot randomly to improve performance on sorted or nearly sorted data.
 * @author Vibhu Khera
 */
public final class RandomizedQuickSort {

    private RandomizedQuickSort() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Sorts the array using the randomized quicksort algorithm.
     *
     * @param arr the array to sort
     * @param low the starting index of the array
     * @param high the ending index of the array
     */
    public static void randomizedQuickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            randomizedQuickSort(arr, low, pivotIndex - 1);
            randomizedQuickSort(arr, pivotIndex + 1, high);
        }
    }

    /**
     * Partitions the array around a pivot chosen randomly.
     *
     * @param arr the array to partition
     * @param low the starting index
     * @param high the ending index
     * @return the index of the pivot after partitioning
     */
    private static int partition(int[] arr, int low, int high) {
        int pivotIndex = low + (int) (Math.random() * (high - low + 1));
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, high); // Move pivot to end
        int storeIndex = low;
        for (int i = low; i < high; i++) {
            if (arr[i] < pivotValue) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }
        swap(arr, storeIndex, high); // Move pivot to its final place
        return storeIndex;
    }

    /**
     * Swaps two elements in the array, only if the indices are different.
     *
     * @param arr the array in which elements are to be swapped
     * @param i the first index
     * @param j the second index
     */
    private static void swap(int[] arr, int i, int j) {
        // Skip if indices are the same
        if (i == j) {
            return;
        }
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
