package com.thealgorithms.dynamicprogramming;

/**
 * @author Afrizal Fikri (https://github.com/icalF)
 */
public final class LongestIncreasingSubsequence {
    private LongestIncreasingSubsequence() {
    }

    private static int upperBound(int[] ar, int l, int r, int key) {
        while (l < r - 1) {
            int m = (l + r) >>> 1;
            if (ar[m] >= key) {
                r = m;
            } else {
                l = m;
            }
        }

        return r;
    }

    public static int lis(int[] array) {
        int len = array.length;
        if (len == 0) {
            return 0;
        }

        int[] tail = new int[len];

        // always points empty slot in tail
        int length = 1;

        tail[0] = array[0];
        for (int i = 1; i < len; i++) {
            // new smallest value
            if (array[i] < tail[0]) {
                tail[0] = array[i];
            } // array[i] extends largest subsequence
            else if (array[i] > tail[length - 1]) {
                tail[length++] = array[i];
            } // array[i] will become end candidate of an existing subsequence or
            // Throw away larger elements in all LIS, to make room for upcoming grater elements than
            // array[i]
            // (and also, array[i] would have already appeared in one of LIS, identify the location
            // and replace it)
            else {
                tail[upperBound(tail, -1, length - 1, array[i])] = array[i];
            }
        }

        return length;
    }

    /**
     * @author Alon Firestein (https://github.com/alonfirestein)
     */
    // A function for finding the length of the LIS algorithm in O(nlogn) complexity.
    public static int findLISLen(int[] a) {
        final int size = a.length;
        if (size == 0) {
            return 0;
        }
        int[] arr = new int[size];
        arr[0] = a[0];
        int lis = 1;
        for (int i = 1; i < size; i++) {
            int index = binarySearchBetween(arr, lis - 1, a[i]);
            arr[index] = a[i];
            if (index == lis) {
                lis++;
            }
        }
        return lis;
    }

    // O(logn)

    private static int binarySearchBetween(int[] t, int end, int key) {
        int left = 0;
        int right = end;
        if (key < t[0]) {
            return 0;
        }
        if (key > t[end]) {
            return end + 1;
        }
        while (left < right - 1) {
            final int middle = (left + right) >>> 1;
            if (t[middle] < key) {
                left = middle;
            } else {
                right = middle;
            }
        }
        return right;
    }
}
