
package com.thealgorithms.searches;

import com.thealgorithms.devutils.searches.SearchAlgorithm;

/**
 * Linear Search is a simple searching algorithm that checks
 * each element of the array sequentially until the target
 * value is found or the array ends.
 *
 * It works for both sorted and unsorted arrays.
 *
 * <p>How it works step-by-step:
 * <ol>
 *   <li>Start from the first element of the array.</li>
 *   <li>Compare the current element with the target value.</li>
 *   <li>If they match, return the current index.</li>
 *   <li>If they don't match, move to the next element.</li>
 *   <li>Repeat until the element is found or the array ends.</li>
 *   <li>If not found, return -1.</li>
 * </ol>
 *
 * <p>Example:
 * <pre>
 *   Input array: [5, 3, 8, 1, 9]
 *   Target: 8
 *
 *   Step 1: Compare 5 with 8 → no match, move on
 *   Step 2: Compare 3 with 8 → no match, move on
 *   Step 3: Compare 8 with 8 → match found at index 2!
 *
 *   Output: 2
 *
 *   If target = 7:
 *   Output: -1 (not found)
 * </pre>
 * Time Complexity:
 *  - Best case: O(1) - target is the first element
 *  - Average case: O(n) - target is somewhere in the middle
 *  - Worst case: O(n) - target is last or not present
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
     * Generic Linear search method that searches for a value
     * in the given array by checking each element one by one.
     *
     * @param array List to be searched (can be unsorted)
     * @param value Key being searched for
     * @return Location of the key, -1 if array is null or empty, or key not found
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
