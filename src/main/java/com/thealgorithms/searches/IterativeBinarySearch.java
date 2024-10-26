package com.thealgorithms.searches;

import com.thealgorithms.devutils.searches.SearchAlgorithm;

/**
 * Binary search is one of the most popular algorithms This class represents
 * iterative version {@link BinarySearch} Iterative binary search is likely to
 * have lower constant factors because it doesn't involve the overhead of
 * manipulating the call stack. But in java the recursive version can be
 * optimized by the compiler to this version.
 *
 * <p>
 * Worst-case performance O(log n) Best-case performance O(1) Average
 * performance O(log n) Worst-case space complexity O(1)
 *
 * @author Gabriele La Greca : https://github.com/thegabriele97
 * @author Podshivalov Nikita (https://github.com/nikitap492)
 * @see SearchAlgorithm
 * @see BinarySearch
 */
public final class IterativeBinarySearch implements SearchAlgorithm {

    /**
     * This method implements an iterative version of binary search algorithm
     *
     * @param array a sorted array
     * @param key the key to search in array
     * @return the index of key in the array or -1 if not found
     */
    @Override
    public <T extends Comparable<T>> int find(T[] array, T key) {
        int l;
        int r;
        int k;
        int cmp;

        l = 0;
        r = array.length - 1;

        while (l <= r) {
            k = (l + r) >>> 1;
            cmp = key.compareTo(array[k]);

            if (cmp == 0) {
                return k;
            } else if (cmp < 0) {
                r = --k;
            } else {
                l = ++k;
            }
        }

        return -1;
    }
}
