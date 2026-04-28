package com.thealgorithms.searches;

import com.thealgorithms.devutils.searches.SearchAlgorithm;

/**
 * Linear Search is a simple searching algorithm that checks each element 
 * of the array sequentially until the target value is found or the array ends.
 *
 * <p>It works for both sorted and unsorted arrays.</p>
 *
 * <p>Time Complexity:
 * <ul>
 * <li>Best case: O(1) - Target is at the first index.</li>
 * <li>Average case: O(n) - Target is in the middle.</li>
 * <li>Worst case: O(n) - Target is at the end or missing.</li>
 * </ul>
 * </p>
 *
 * <p>Space Complexity: O(1)</p>
 *
 * @author Varun Upadhyay
 * @author Podshivalov Nikita
 * @author yawarali1
 * @see BinarySearch
 * @see SearchAlgorithm
 */
public class LinearSearch implements SearchAlgorithm {

    /**
     * Generic Linear search method.
     *
     * @param array List to be searched.
     * @param value Key being searched for.
     * @return Location of the key, -1 if array is null or empty, or key not found.
     */
    @Override
    public <T extends Comparable<T>> int find(T[] array, T value) {

        if (array == null || array.length == 0 || value == null) {
            return -1;
        }

        for (int i = 0; i < array.length; i++) {
            T currentElement = array[i];
            if (currentElement != null && currentElement.compareTo(value) == 0) {
                return i;
            }
        }

        return -1;
    }
}