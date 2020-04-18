package com.search;

/**
 * A ternary search algorithm is a technique in computer science for finding the minimum or maximum of a unimodal function
 * The algorithm determines either that the minimum or maximum cannot be in the first third of the domain
 * or that it cannot be in the last third of the domain, then repeats on the remaining third.
 * <p>
 * Worst-case performance	Θ(log3(N))
 * Best-case performance	O(1)
 * Average performance	Θ(log3(N))
 * Worst-case space complexity	O(1)
 */
public final class TernarySearch {

    /**
     * @param arr   The **Sorted** array in which we will search the element.
     * @param value The value that we want to search for.
     * @return The index of the element if found.
     * Else returns -1.
     */
    public static <T extends Comparable<T>> int find(T[] arr, T value) {
        return search(arr, value, 0, arr.length - 1);
    }

    /**
     * @param arr   The **Sorted** array in which we will search the element.
     * @param key   The value that we want to search for.
     * @param start The starting index from which we will start Searching.
     * @param end   The ending index till which we will Search.
     * @return Returns the index of the Element if found.
     * Else returns -1.
     */
    private static <T extends Comparable<T>> int search(T[] arr, T key, int start, int end) {
        if (start > end) {
            return -1;
        }

        int mid1 = start + (end - start) / 3;
        int mid2 = start + 2 * (end - start) / 3;

        if (key.compareTo(arr[mid1]) == 0) {
            return mid1;
        } else if (key.compareTo(arr[mid2]) == 0) {
            return mid2;
        }
        else if (key.compareTo(arr[mid1]) < 0) {
            return search(arr, key, start, --mid1);
        }
        else if (key.compareTo(arr[mid2]) > 0) {
            return search(arr, key, ++mid2, end);
        }
        else {
            return search(arr, key, mid1, mid2);
        }
    }

}
