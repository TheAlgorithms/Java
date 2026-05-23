package com.thealgorithms.searches;

import com.thealgorithms.devutils.searches.SearchAlgorithm;

/**
 * Binary Search Algorithm Implementation.
 *
 * <p>Binary search is an efficient algorithm for finding a target element in a sorted array. It
 * repeatedly divides the search range in half and compares the target value with the middle
 * element.
 *
 * <p>The algorithm works as follows:
 * <ol>
 * <li>Start with the entire sorted array.</li>
 * <li>Find the middle element of the current search range.</li>
 * <li>If the middle element is equal to the target, return its index.</li>
 * <li>If the target is smaller than the middle element, continue searching the left half.</li>
 * <li>If the target is greater than the middle element, continue searching the right half.</li>
 * <li>If the search range becomes empty, return {@code -1}.</li>
 * </ol>
 *
 * <p>Example:
 * <pre>
 * array = [1, 3, 5, 7, 9, 11]
 * key = 7
 * result = 3
 * </pre>
 *
 * <p>Time Complexity:
 * <ul>
 * <li>Best case: O(1), when the key is found at the middle index.</li>
 * <li>Average case: O(log n), because the search range is divided in half each time.</li>
 * <li>Worst case: O(log n), when the key is not present or is found after repeated halving.</li>
 * </ul>
 *
 * <p>Space Complexity: O(log n), due to the recursive call stack.
 *
 * <p>Note: The input array must be sorted in ascending order.
 *
 * @author Varun Upadhyay (https://github.com/varunu28)
 * @author Podshivalov Nikita (https://github.com/nikitap492)
 * @see SearchAlgorithm
 * @see IterativeBinarySearch
 */
class BinarySearch implements SearchAlgorithm {

    /**
     * Searches for the given key in a sorted array.
     *
     * @param <T> the type of elements in the array; must implement {@link Comparable}
     * @param array the sorted array to search in
     * @param key the element to search for
     * @return the index of the key if found, or {@code -1} if the key is not found
     */
    @Override
    public <T extends Comparable<T>> int find(T[] array, T key) {

        return search(array, key, 0, array.length - 1);
    }

    /**
     * Recursively searches for the key between the given left and right indexes.
     *
     * @param <T> the type of elements in the array; must implement {@link Comparable}
     * @param array the sorted array to search in
     * @param key the element to search for
     * @param left the left boundary of the current search range
     * @param right the right boundary of the current search range
     * @return the index of the key if found, or {@code -1} if the key is not found
     */
    private <T extends Comparable<T>> int search(T[] array, T key, int left, int right) {
        if (right < left) {
            return -1;
        }

        int median = (left + right) >>> 1;
        int comp = key.compareTo(array[median]);
        if (comp == 0) {
            return median;
        }
        else if (comp < 0) {
            return search(array, key, left, median - 1);
        }
        else {
            return search(array, key, median + 1, right);
        }
    }
}
