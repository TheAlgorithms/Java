package com.thealgorithms.searches;

import com.thealgorithms.devutils.searches.SearchAlgorithm;
import java.util.Arrays;

/**
 * ExponentialSearch is an algorithm that efficiently finds the position of a target
 * value within a sorted array. It works by expanding the range to find the bounds
 * where the target might exist and then using binary search within that range.
 *
 * <p>
 * Worst-case time complexity: O(log n)
 * Best-case time complexity: O(1) when the element is found at the first position.
 * Average time complexity: O(log n)
 * Worst-case space complexity: O(1)
 * </p>
 *
 * <p>
 * Note: This algorithm requires that the input array be sorted.
 * </p>
 */
class ExponentialSearch implements SearchAlgorithm {

    /**
     * Finds the index of the specified key in a sorted array using exponential search.
     *
     * @param array The sorted array to search.
     * @param key The element to search for.
     * @param <T> The type of the elements in the array, which must be comparable.
     * @return The index of the key if found, otherwise -1.
     */
    @Override
    public <T extends Comparable<T>> int find(T[] array, T key) {
        if (array.length == 0) {
            return -1;
        }
        if (array[0].equals(key)) {
            return 0;
        }
        if (array[array.length - 1].equals(key)) {
            return array.length - 1;
        }

        int range = 1;
        while (range < array.length && array[range].compareTo(key) < 0) {
            range = range * 2;
        }

        return Arrays.binarySearch(array, range / 2, Math.min(range, array.length), key);
    }
}
