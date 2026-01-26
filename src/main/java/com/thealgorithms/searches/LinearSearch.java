package com.thealgorithms.searches;

import com.thealgorithms.devutils.searches.SearchAlgorithm;
/**
 * Linear Search is a simple searching algorithm that checks
 * each element of the array sequentially until the target
 * value is found or the array ends.
 *
 * It works for both sorted and unsorted arrays.
 *
 * Time Complexity:
 *  - Best case: O(1)
 *  - Average case: O(n)
 *  - Worst case: O(n)
 *
 * Space Complexity: O(1)
 *
 * @author Varun Upadhyay
 * @author Podshivalov Nikita
 * @see BinarySearch
 * @see SearchAlgorithm
 */

public class LinearSearch implements SearchAlgorithm {

    /**
     * Generic Linear search method
     *
     * @param array List to be searched
     * @param value Key being searched for
     * @return Location of the key
     */
    @Override
    public <T extends Comparable<T>> int find(T[] array, T value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].compareTo(value) == 0) {
                return i;
            }
        }
        return -1;
    }
}
