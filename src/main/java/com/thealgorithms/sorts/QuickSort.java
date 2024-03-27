package com.thealgorithms.sorts;

import static com.thealgorithms.sorts.SortUtils.*;

/**
 * Implementation of the QuickSort algorithm.
 * 
 * @author Manish Raj (https://github.com/manishraj27)
 * @see SortAlgorithm
 */
class QuickSort implements SortAlgorithm {

    /**
     * Sorts the input array using QuickSort algorithm.
     *
     * @param array The array to be sorted. Sorts the array in increasing order.
     * @return The sorted array.
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        quickSort(array, 0, array.length - 1);
        return array;
    }

    /**
     * Recursive method to perform QuickSort on the input array.
     *
     * @param array The array to be sorted.
     * @param left  The leftmost index of the subarray.
     * @param right The rightmost index of the subarray.
     */
    private static <T extends Comparable<T>> void quickSort(T[] array, int left, int right) {
        if (left < right) {
            int pivotIndex = partitionWithRandomPivot(array, left, right);
            quickSort(array, left, pivotIndex - 1);
            quickSort(array, pivotIndex, right);
        }
    }

    /**
     * Randomly selects a pivot element and partitions the array around it.
     *
     * @param array The array to be partitioned.
     * @param left  The leftmost index of the subarray.
     * @param right The rightmost index of the subarray.
     * @return The index of the pivot element after partitioning.
     */
    private static <T extends Comparable<T>> int partitionWithRandomPivot(T[] array, int left, int right) {
        int randomIndex = left + (int) (Math.random() * (right - left + 1));
        swap(array, randomIndex, right);
        return partition(array, left, right);
    }

    /**
     * Partitions the array around a pivot element.
     *
     * @param array The array to be partitioned.
     * @param left  The leftmost index of the subarray.
     * @param right The rightmost index of the subarray.
     * @return The index of the pivot element after partitioning.
     */
    private static <T extends Comparable<T>> int partition(T[] array, int left, int right) {
        int mid = (left + right) >>> 1;
        T pivot = array[mid];

        while (left <= right) {
            while (less(array[left], pivot)) {
                left++;
            }
            while (less(pivot, array[right])) {
                right--;
            }
            if (left <= right) {
                swap(array, left, right);
                left++;
                right--;
            }
        }
        return left;
    }
}
