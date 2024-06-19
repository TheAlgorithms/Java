package com.thealgorithms.sorts;

/**
 * BinaryInsertionSort class implements the SortAlgorithm interface using the binary insertion sort technique.
 * Binary Insertion Sort improves upon the simple insertion sort by using binary search to find the appropriate
 * location to insert the new element, reducing the number of comparisons in the insertion step.
 */
public class BinaryInsertionSort implements SortAlgorithm {

    /**
     * Sorts the given array using the Binary Insertion Sort algorithm.
     *
     * @param <T> the type of elements in the array, which must implement the Comparable interface
     * @param array the array to be sorted
     * @return the sorted array
     */
    public <T extends Comparable<T>> T[] sort(T[] array) {
        for (int i = 1; i < array.length; i++) {
            final T temp = array[i];
            int low = 0;
            int high = i - 1;

            while (low <= high) {
                final int mid = (low + high) >>> 1;
                if (temp.compareTo(array[mid]) < 0) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            for (int j = i; j >= low + 1; j--) {
                array[j] = array[j - 1];
            }

            array[low] = temp;
        }
        return array;
    }
}
