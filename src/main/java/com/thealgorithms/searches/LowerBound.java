package com.thealgorithms.searches;

import com.thealgorithms.devutils.searches.SearchAlgorithm;

/**
 * The LowerBound method is used to return an index pointing to the first
 * element in the range [first, last) which has a value not less than val, i.e.
 * the index of the next smallest number just greater than or equal to that
 * number. If there are multiple values that are equal to val it returns the
 * index of the first such value.
 *
 * <p>
 * This is an extension of BinarySearch.
 *
 * <p>
 * Worst-case performance O(log n) Best-case performance O(log n) Average
 * performance O(log n) Worst-case space complexity O(1)
 *
 * @author Pratik Padalia (https://github.com/15pratik)
 * @see SearchAlgorithm
 * @see BinarySearch
 */
class LowerBound implements SearchAlgorithm {

    /**
     * @param array is an array where the LowerBound value is to be found
     * @param key is an element for which the LowerBound is to be found
     * @param <T> is any comparable type
     * @return index of the first element not less than key, or 0 if array is null/empty or key is null
     */
    @Override
    public <T extends Comparable<T>> int find(T[] array, T key) {
        if (array == null || array.length == 0 || key == null) {
            return 0;
        }
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

        if (comp <= 0) {
            // key is less than or equal to array[median], median position can be a possible solution
            return search(array, key, left, median);
        } else {
            // key we are looking is greater, so we must look on the right of median position
            return search(array, key, median + 1, right);
        }
    }
}
