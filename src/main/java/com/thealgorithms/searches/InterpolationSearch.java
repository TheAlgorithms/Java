package com.thealgorithms.searches;

/**
 * InterpolationSearch is an algorithm that searches for a target value within a sorted array
 * by estimating the position based on the values at the corners of the current search range.
 *
 * <p>
 * The performance of this algorithm can vary:
 * - Worst-case performance: O(n)
 * - Best-case performance: O(1)
 * - Average performance: O(log(log(n))) if the elements are uniformly distributed; otherwise O(n)
 * - Worst-case space complexity: O(1)
 * </p>
 *
 * <p>
 * This search algorithm requires the input array to be sorted.
 * </p>
 *
 * @author Podshivalov Nikita (https://github.com/nikitap492)
 */
class InterpolationSearch {

    /**
     * Finds the index of the specified key in a sorted array using interpolation search.
     *
     * @param array The sorted array to search.
     * @param key The value to search for.
     * @return The index of the key if found, otherwise -1.
     */
    public int find(int[] array, int key) {
        // Find indexes of two corners
        int start = 0;
        int end = (array.length - 1);

        // Since array is sorted, an element present
        // in array must be in range defined by corner
        while (start <= end && key >= array[start] && key <= array[end]) {
            // Probing the position with keeping
            // uniform distribution in mind.
            int pos = start + (((end - start) / (array[end] - array[start])) * (key - array[start]));

            // Condition of target found
            if (array[pos] == key) {
                return pos;
            }

            // If key is larger, key is in upper part
            if (array[pos] < key) {
                start = pos + 1;
            } // If key is smaller, x is in lower part
            else {
                end = pos - 1;
            }
        }
        return -1;
    }
}
