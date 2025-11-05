package com.thealgorithms.searches;

import com.thealgorithms.devutils.searches.SearchAlgorithm;

/**
 * Sentinel Linear Search is a variation of linear search that eliminates the
 * need to check the array bounds in each iteration by placing the search key
 * at the end of the array as a sentinel value.
 *
 * <p>
 * The algorithm works by:
 * 1. Storing the last element of the array
 * 2. Placing the search key at the last position (sentinel)
 * 3. Searching from the beginning without bound checking
 * 4. If found before the last position, return the index
 * 5. If found at the last position, check if it was originally there
 *
 * <p>
 * Time Complexity:
 * - Best case: O(1) - when the element is at the first position
 * - Average case: O(n) - when the element is in the middle
 * - Worst case: O(n) - when the element is not present
 *
 * <p>
 * Space Complexity: O(1) - only uses constant extra space
 *
 * <p>
 * Advantages over regular linear search:
 * - Reduces the number of comparisons by eliminating bound checking
 * - Slightly more efficient in practice due to fewer conditional checks
 *
 * @author TheAlgorithms Contributors
 * @see LinearSearch
 * @see SearchAlgorithm
 */
public class SentinelLinearSearch implements SearchAlgorithm {
    /**
     * Performs sentinel linear search on the given array.
     *
     * @param array the array to search in
     * @param key the element to search for
     * @param <T> the type of elements in the array, must be Comparable
     * @return the index of the first occurrence of the key, or -1 if not found
     * @throws IllegalArgumentException if the array is null
     */
    @Override
    public <T extends Comparable<T>> int find(T[] array, T key) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }

        if (array.length == 0) {
            return -1;
        }

        if (key == null) {
            return findNull(array);
        }

        // Store the last element
        T lastElement = array[array.length - 1];

        // Place the sentinel (search key) at the end
        array[array.length - 1] = key;

        int i = 0;
        // Search without bound checking since sentinel guarantees we'll find the key
        while (array[i].compareTo(key) != 0) {
            i++;
        }

        // Restore the original last element
        array[array.length - 1] = lastElement;

        // Check if we found the key before the sentinel position
        // or if the original last element was the key we were looking for
        if (i < array.length - 1 || (lastElement != null && lastElement.compareTo(key) == 0)) {
            return i;
        }

        return -1; // Key not found
    }

    /**
     * Helper method to find null values in the array.
     *
     * @param array the array to search in
     * @param <T> the type of elements in the array
     * @return the index of the first null element, or -1 if not found
     */
    private <T extends Comparable<T>> int findNull(T[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                return i;
            }
        }
        return -1;
    }
}
