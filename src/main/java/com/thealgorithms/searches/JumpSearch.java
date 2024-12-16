package com.thealgorithms.searches;

import com.thealgorithms.devutils.searches.SearchAlgorithm;

/**
 * An implementation of the Jump Search algorithm.
 *
 * <p>
 * Jump Search is an algorithm for searching sorted arrays. It works by dividing the array
 * into blocks of a fixed size (the block size is typically the square root of the array length)
 * and jumping ahead by this block size to find a range where the target element may be located.
 * Once the range is found, a linear search is performed within that block.
 *
 * <p>
 * The Jump Search algorithm is particularly effective for large sorted arrays where the cost of
 * performing a linear search on the entire array would be prohibitive.
 *
 * <p>
 * Worst-case performance: O(√N)<br>
 * Best-case performance: O(1)<br>
 * Average performance: O(√N)<br>
 * Worst-case space complexity: O(1)
 *
 * <p>
 * This class implements the {@link SearchAlgorithm} interface, providing a generic search method
 * for any comparable type.
 */
public class JumpSearch implements SearchAlgorithm {

    /**
     * Jump Search algorithm implementation.
     *
     * @param array the sorted array containing elements
     * @param key   the element to be searched
     * @return the index of {@code key} if found, otherwise -1
     */
    @Override
    public <T extends Comparable<T>> int find(T[] array, T key) {
        int length = array.length;
        int blockSize = (int) Math.sqrt(length);

        int limit = blockSize;
        // Jumping ahead to find the block where the key may be located
        while (limit < length && key.compareTo(array[Math.min(limit, length - 1)]) > 0) {
            limit += blockSize;
        }

        // Handle the case where limit exceeds array length
        limit = Math.min(limit, length);

        // Perform linear search within the identified block
        for (int i = limit - blockSize; i <= limit && i < length; i++) {
            if (i < 0) { // Skip negative indices
                continue;
            }
            if (array[i].equals(key)) {
                return i;
            }
        }
        return -1;
    }
}
