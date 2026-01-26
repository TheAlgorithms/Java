package com.thealgorithms.searches;

import com.thealgorithms.devutils.searches.SearchAlgorithm;

/**
 * Searches for a key in a sorted array that has been rotated at an unknown pivot.
 *
 * <p>
 * Example:
 * {@code [8, 9, 10, 1, 2, 3, 4, 5, 6, 7]}
 *
 * <p>
 * This is a modified binary search. When the array contains no duplicates, the
 * time complexity is {@code O(log n)}. With duplicates, the algorithm still
 * works but may degrade to {@code O(n)} in the worst case.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Search_in_rotated_sorted_array">Search in rotated sorted array</a>
 * @see SearchAlgorithm
 */
public final class RotatedBinarySearch implements SearchAlgorithm {

    @Override
    public <T extends Comparable<T>> int find(T[] array, T key) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int middle = (left + right) >>> 1;
            int cmp = key.compareTo(array[middle]);
            if (cmp == 0) {
                return middle;
            }

            // Handle duplicates: if we cannot determine which side is sorted.
            if (array[left].compareTo(array[middle]) == 0 && array[middle].compareTo(array[right]) == 0) {
                left++;
                right--;
                continue;
            }

            // Left half is sorted.
            if (array[left].compareTo(array[middle]) <= 0) {
                if (array[left].compareTo(key) <= 0 && key.compareTo(array[middle]) < 0) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            } else {
                // Right half is sorted.
                if (array[middle].compareTo(key) < 0 && key.compareTo(array[right]) <= 0) {
                    left = middle + 1;
                } else {
                    right = middle - 1;
                }
            }
        }

        return -1;
    }
}
