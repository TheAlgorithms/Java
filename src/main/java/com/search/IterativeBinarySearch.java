package com.search;

/**
 * Binary search is an algorithm which finds the position of a target value within a sorted array
 * <p>
 * Worst-case performance	O(log n)
 * Best-case performance	O(1)
 * Average performance	O(log n)
 * Worst-case space complexity	O(1)
 */
public final class IterativeBinarySearch {

    /**
     * @param array a sorted array
     * @param key   the key to search in array
     * @return the index of key in the array or -1 if not found
     */
    public static <T extends Comparable<T>> int find(T[] array, T key) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int middle = (left + right) / 2;
            int compareTo = key.compareTo(array[middle]);

            if (compareTo == 0) {
                return middle;
            } else if (compareTo < 0) {
                right = --middle;
            } else {
                left = ++middle;
            }
        }

        return -1;
    }

}
