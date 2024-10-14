package com.thealgorithms.divideandconquer;

/**
 * A utility class for counting the number of inversions in an array.
 * <p>
 * An inversion is a pair (i, j) such that i < j and arr[i] > arr[j].
 * This class implements a divide-and-conquer approach, similar to merge sort,
 * to count the number of inversions efficiently.
 * <p>
 * Time Complexity: O(n log n)
 * Space Complexity: O(n) (due to temporary arrays during merge step)
 *
 * <p>Applications:
 * - Used in algorithms related to sorting and permutation analysis.
 * - Helps in determining how far an array is from being sorted.
 * - Applicable in bioinformatics and signal processing.
 *
 * <p>This class cannot be instantiated, as it is intended to provide
 * only static utility methods.
 *
 * @author Hardvan
 */
public final class CountingInversions {
    private CountingInversions() {
    }

    /**
     * Counts the number of inversions in the given array.
     *
     * @param arr The input array of integers.
     * @return The total number of inversions in the array.
     */
    public static int countInversions(int[] arr) {
        return mergeSortAndCount(arr, 0, arr.length - 1);
    }

    /**
     * Recursively divides the array into two halves, sorts them, and counts
     * the number of inversions. Uses a modified merge sort approach.
     *
     * @param arr  The input array.
     * @param left The starting index of the current segment.
     * @param right The ending index of the current segment.
     * @return The number of inversions within the segment [left, right].
     */
    private static int mergeSortAndCount(int[] arr, int left, int right) {
        if (left >= right) {
            return 0;
        }

        int mid = left + (right - left) / 2;
        int inversions = 0;

        inversions += mergeSortAndCount(arr, left, mid);
        inversions += mergeSortAndCount(arr, mid + 1, right);
        inversions += mergeAndCount(arr, left, mid, right);
        return inversions;
    }

    /**
     * Merges two sorted subarrays and counts the cross-inversions between them.
     * A cross-inversion occurs when an element from the right subarray is
     * smaller than an element from the left subarray.
     *
     * @param arr The input array.
     * @param left The starting index of the first subarray.
     * @param mid The ending index of the first subarray and midpoint of the segment.
     * @param right The ending index of the second subarray.
     * @return The number of cross-inversions between the two subarrays.
     */
    private static int mergeAndCount(int[] arr, int left, int mid, int right) {
        int[] leftArr = new int[mid - left + 1];
        int[] rightArr = new int[right - mid];

        System.arraycopy(arr, left, leftArr, 0, mid - left + 1);
        System.arraycopy(arr, mid + 1, rightArr, 0, right - mid);

        int i = 0;
        int j = 0;
        int k = left;
        int inversions = 0;

        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
                inversions += mid + 1 - left - i;
            }
        }

        while (i < leftArr.length) {
            arr[k++] = leftArr[i++];
        }
        while (j < rightArr.length) {
            arr[k++] = rightArr[j++];
        }

        return inversions;
    }
}
