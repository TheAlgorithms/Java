package com.thealgorithms.searches;

import com.thealgorithms.devutils.searches.SearchAlgorithm;

/**
 * The UpperBound method is used to return an index pointing to the first
 * element in the range [first, last) which has a value greater than val, or the
 * last index if no such element exists i.e. the index of the next smallest
 * number just greater than that number. If there are multiple values that are
 * equal to val it returns the index of the first such value.
 *
 * <p>
 * This is an extension of BinarySearch.
 *
 * <p>
 * Worst-case performance O(log n) Best-case performance O(1) Average
 * performance O(log n) Worst-case space complexity O(1)
 *
 * @author Pratik Padalia (https://github.com/15pratik)
 * @see SearchAlgorithm
 * @see BinarySearch
 */
class UpperBound implements SearchAlgorithm {

    /**
     * @param array is an array where the UpperBound value is to be found
     * @param key is an element for which the UpperBound is to be found
     * @param <T> is any comparable type
     * @return index of the UpperBound element
     */
    @Override
    public <T extends Comparable<T>> int find(T[] array, T key) {
        return search(array, key, 0, array.length - 1);
    }

    /**
     * This method implements the Generic Binary Search
     *
     * @param array The array to make the binary search
     * @param key The number you are looking for
     * @param left The lower bound
     * @param right The upper bound
     * @return the location of the key
     */
    private <T extends Comparable<T>> int search(T[] array, T key, int left, int right) {
        if (right <= left) {
            return left;
        }

        // find median
        int median = (left + right) >>> 1;
        int comp = key.compareTo(array[median]);

        if (comp < 0) {
            // key is smaller, median position can be a possible solution
            return search(array, key, left, median);
        } else {
            // key we are looking is greater, so we must look on the right of median position
            return search(array, key, median + 1, right);
        }
    }
}
