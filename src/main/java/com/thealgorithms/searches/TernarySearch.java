package com.thealgorithms.searches;

import com.thealgorithms.devutils.searches.SearchAlgorithm;

/**
 * A ternary search algorithm is a divide-and-conquer algorithm that searches for
 * a key in a sorted array by dividing the search domain into three parts.
 *
 * <p>
 * Worst-case performance: Θ(log3(N))<br>
 * Best-case performance: O(1)<br>
 * Average performance: Θ(log3(N))<br>
 * Worst-case space complexity: O(log3(N))
 *
 * @author Podshivalov Nikita (https://github.com/nikitap492)
 * @see SearchAlgorithm
 * @see IterativeBinarySearch
 */
public final class TernarySearch implements SearchAlgorithm {

    /**
     * Searches for a value in a sorted array using ternary search.
     *
     * @param arr The sorted array in which we will search for the element.
     * @param value The value that we want to search for.
     * @return The index of the element if found. Returns -1 if the array is null or empty, if value is null, or if not found.
     */
    @Override
    public <T extends Comparable<T>> int find(T[] arr, T value) {
        if (arr == null || arr.length == 0 || value == null) {
            return -1;
        }
        return ternarySearch(arr, value, 0, arr.length - 1);
    }

    /**
     * Recursively searches for the specified key within the range [start, end].
     *
     * @param arr The sorted array in which we will search the element.
     * @param key The value that we want to search for.
     * @param start The starting index from which we will start searching.
     * @param end The ending index till which we will search.
     * @return Returns the index of the element if found. Else returns -1.
     */
    private <T extends Comparable<T>> int ternarySearch(T[] arr, T key, int start, int end) {
        if (start > end) {
            return -1;
        }
        /* First boundary: add 1/3 of length to start */
        int mid1 = start + (end - start) / 3;
        /* Second boundary: add 2/3 of length to start */
        int mid2 = start + 2 * (end - start) / 3;

        int cmp1 = key.compareTo(arr[mid1]);
        if (cmp1 == 0) {
            return mid1;
        }

        int cmp2 = key.compareTo(arr[mid2]);
        if (cmp2 == 0) {
            return mid2;
        }

        if (cmp1 < 0) {
            return ternarySearch(arr, key, start, mid1 - 1);
        } else if (cmp2 > 0) {
            return ternarySearch(arr, key, mid2 + 1, end);
        } else {
            return ternarySearch(arr, key, mid1 + 1, mid2 - 1);
        }
    }
}
