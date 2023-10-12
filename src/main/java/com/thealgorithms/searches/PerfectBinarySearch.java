package com.thealgorithms.searches;

import com.thealgorithms.devutils.searches.SearchAlgorithm;

/**
 * Binary search is one of the most popular algorithms The algorithm finds the
 * position of a target value within a sorted array
 *
 * <p>
 * Worst-case performance O(log n) Best-case performance O(1) Average
 * performance O(log n) Worst-case space complexity O(1)
 *
 * @author D Sunil (https://github.com/sunilnitdgp)
 * @see SearchAlgorithm
 */

public class PerfectBinarySearch<T> implements SearchAlgorithm {

    /**
     * @param array is an array where the element should be found
     * @param key is an element which should be found
     * @param <T> is any comparable type
     * @return index of the element
     */
    @Override
    public <T extends Comparable<T>> int find(T[] array, T key) {
        return search(array, key, 0, array.length - 1);
    }

    /**
     * This method implements the Generic Binary Search iteratively.
     *
     * @param array The array to make the binary search
     * @param key   The number you are looking for
     * @return the location of the key, or -1 if not found
     */
    private static <T extends Comparable<T>> int search(T[] array, T key, int left, int right) {
        while (left <= right) {
            int median = (left + right) >>> 1;
            int comp = key.compareTo(array[median]);

            if (comp == 0) {
                return median; // Key found
            }

            if (comp < 0) {
                right = median - 1; // Adjust the right bound
            } else {
                left = median + 1; // Adjust the left bound
            }
        }
        return -1; // Key not found
    }
}
