package com.thealgorithms.searches;

import com.thealgorithms.devutils.searches.SearchAlgorithm;

/**
 * Binary search is one of the most popular algorithms.
 * This class represents the iterative version of {@link BinarySearch}.
 *
 * <p>Iterative binary search avoids recursion overhead and uses constant space.
 *
 * <p>Performance:
 * <ul>
 *   <li>Best-case: O(1)</li>
 *   <li>Average-case: O(log n)</li>
 *   <li>Worst-case: O(log n)</li>
 *   <li>Space complexity: O(1)</li>
 * </ul>
 *
 * @author Gabriele La Greca
 * @author Podshivalov Nikita
 * @see SearchAlgorithm
 * @see BinarySearch
 */
public final class IterativeBinarySearch implements SearchAlgorithm {

    /**
     * Performs iterative binary search on a sorted array.
     *
     * @param array the sorted array
     * @param key the element to search
     * @param <T> type of elements (must be Comparable)
     * @return index of the key if found, otherwise -1
     */
    @Override
    public <T extends Comparable<T>> int find(T[] array, T key) {
        if (array == null || array.length == 0) {
            return -1;
        }

        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = (left + right) >>> 1;
            int cmp = key.compareTo(array[mid]);

            if (cmp == 0) {
                return mid;
            } else if (cmp < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }
}
