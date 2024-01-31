package com.thealgorithms.sorts;

/**
 * ExchangeSort is an implementation of the Exchange Sort algorithm.
 *
 * <p>
 * Exchange sort works by comparing each element with all subsequent elements,
 * swapping where needed, to ensure the correct placement of each element
 * in the final sorted order. It iteratively performs this process for each
 * element in the array. While it lacks the advantage of bubble sort in
 * detecting sorted lists in one pass, it can be more efficient than bubble sort
 * due to a constant factor (one less pass over the data to be sorted; half as
 * many total comparisons) in worst-case scenarios.
 * </p>
 *
 * <p>
 * Reference: https://en.wikipedia.org/wiki/Sorting_algorithm#Exchange_sort
 * </p>
 *
 * @author 555vedant (Vedant Kasar)
 */
class ExchangeSort implements SortAlgorithm {

    /**
     * Implementation of Exchange Sort Algorithm
     *
     * @param array the array to be sorted.
     * @param <T>   the type of elements in the array.
     * @return the sorted array.
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        final int n = array.length;

        for (int i = 0; i < n - 1; i++) {
<<<<<<< HEAD
            for (int j = i + 1; j < n; j++) {
                if (array[i].compareTo(array[j]) > 0) {
                    // Use a generic swap method
                    swap(array, i, j);
=======
            // Last i elements are already sorted, so we don't need to check them
            for (int j = 0; j < n - i - 1; j++) {
                // Swap if the element found is greater than the next element
                if (array[j].compareTo(array[j + 1]) > 0) {
                    // Swap array[j] and array[j+1]
                    final T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
>>>>>>> af748c4d5cb12e0e6f64ed1e8b9ff557533e86b7
                }
            }
        }

        return array;
    }

    /**
     * Generic swap method to swap elements at given indices in an array.
     *
     * @param array the array in which elements are to be swapped.
     * @param i     the index of the first element.
     * @param j     the index of the second element.
     * @param <T>   the type of elements in the array.
     */
    private <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
