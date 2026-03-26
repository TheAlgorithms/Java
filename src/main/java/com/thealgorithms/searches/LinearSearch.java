package com.thealgorithms.searches;

import com.thealgorithms.devutils.searches.SearchAlgorithm;

/**
 * LinearSearch - searches for a value in an array by checking each element one by one.
 *
 * I come from C and this is basically the same as a simple for loop with an if inside.
 * The idea is straightforward: start from index 0, compare each element to the target,
 * return the index if found, or -1 if we reach the end without finding anything.
 *
 * How it works step by step:
 * 1. We start at the first element (index 0)
 * 2. We compare it with the value we are looking for
 * 3. If it matches, we return its index
 * 4. If not, we move to the next element
 * 5. We repeat until we find it or we go through the whole array
 * 6. If nothing matched, we return -1 to indicate "not found"
 *
 * Example:
 *   array = [3, 7, 1, 9, 4], target = 9
 *   - index 0: 3 != 9, continue
 *   - index 1: 7 != 9, continue
 *   - index 2: 1 != 9, continue
 *   - index 3: 9 == 9, return 3
 *
 *   array = [3, 7, 1, 9, 4], target = 5
 *   - nothing matched -> return -1
 *
 * Time complexity: O(n) because in the worst case we check every element.
 * Space complexity: O(1), we do not use any extra memory.
 *
 * Note: unlike binary search, this works on unsorted arrays too.
 *
 * @author Varun Upadhyay
 * @author Podshivalov Nikita
 * @see BinarySearch
 * @see SearchAlgorithm
 */
public class LinearSearch implements SearchAlgorithm {

    /**
     * Goes through the array and returns the index of the target value.
     * Returns -1 if the value is not in the array.
     *
     * @param array the array we search in
     * @param value the value we are looking for
     * @return index of value if found, -1 otherwise
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
