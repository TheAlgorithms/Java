package com.thealgorithms.sorts;

import static com.thealgorithms.sorts.SortUtils.*;

/**
 * Exchange Sort (Cocktail Shaker Sort) implementation.
 *
 * @author 555vedant(Vedant Kasar)
 * @see SortAlgorithm
 */
class ExchangeSort implements SortAlgorithm {

    /**
     * Implements generic exchange sort (Cocktail Shaker Sort) algorithm.
     *
     * @param array the array to be sorted.
     * @param <T>   the type of elements in the array.
     * @return the sorted array.
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            boolean swapped = false;

            // Traverse from left to right
            for (int i = left; i < right; ++i) {
                if (greater(array[i], array[i + 1])) {
                    swap(array, i, i + 1);
                    swapped = true;
                }
            }

            // If no swap occurred, the array is already sorted
            if (!swapped) {
                break;
            }

            // Move the right boundary one position to the left
            --right;

            // Traverse from right to left
            for (int i = right; i > left; --i) {
                if (greater(array[i - 1], array[i])) {
                    swap(array, i - 1, i);
                    swapped = true;
                }
            }

            // If no swap occurred, the array is already sorted
            if (!swapped) {
                break;
            }

            // Move the left boundary one position to the right
            ++left;
        }

        return array;
    }
}
