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
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i].compareTo(array[j]) > 0) {
                    SortUtils.swap(array, i, j);
                }
            }
        }
        return array;
    }
}
