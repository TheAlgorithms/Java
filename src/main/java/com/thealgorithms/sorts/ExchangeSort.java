package com.thealgorithms.sorts;

import static com.thealgorithms.sorts.SortUtils.*;

/**
 * Exchange Sort (Cocktail Shaker Sort) implementation.
 *
 * @author 555vedant (Vedant Kasar)
 * @see SortAlgorithm
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
        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            // Last i elements are already sorted, so we don't need to check them
            for (int j = 0; j < n - i - 1; j++) {
                // Swap if the element found is greater than the next element
                if (array[j].compareTo(array[j + 1]) > 0) {
                    // Swap array[j] and array[j+1]
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }

        return array; // Return the sorted array
    }
}
