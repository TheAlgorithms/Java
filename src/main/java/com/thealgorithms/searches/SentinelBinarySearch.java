package com.thealgorithms.searches;

/**
 * Sentinel Binary Search algorithm implementation.
 *
 * Sentinel Binary Search is a variation of Binary Search that reduces
 * the number of comparisons by placing a sentinel value at the end
 * of the array, eliminating the need to check array bounds on every step.
 *
 * Worst case: O(log n)
 * Best case: O(1)
 *
 * <a href="https://en.wikipedia.org/wiki/Linear_search#With_a_sentinel">Wiki</a>
 */
public class SentinelBinarySearch {

    /**
     * Finds the index of a target value in a sorted array.
     *
     * @param arr the sorted array to search
     * @param target the value to find
     * @return the index of target if found, otherwise -1
     */
    public int find(int[] arr, int target) {
        int n = arr.length;

        if (n == 0) {
            return -1;
        }

        int last = arr[n - 1];
        arr[n - 1] = target;

        int i = 0;
        while (arr[i] != target) {
            i++;
        }

        arr[n - 1] = last;

        if (i < n - 1 || last == target) {
            return i;
        }

        return -1;
    }
}
