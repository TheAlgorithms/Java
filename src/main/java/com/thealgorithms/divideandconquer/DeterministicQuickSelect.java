package com.thealgorithms.divideandconquer;

import java.util.Arrays;

/**
 * QuickSelect algorithm using the Median of Medians method.
 *
 * <p>This algorithm finds the kth smallest element in an unsorted array in
 * O(n) worst-case time complexity.
 *
 * <p>Steps:
 * <ol>
 *   <li>Divide the array into groups of five elements each.</li>
 *   <li>Find the median of each group.</li>
 *   <li>Recursively find the median of these medians, which becomes the pivot.</li>
 *   <li>Partition the array around this pivot.</li>
 *   <li>Recurse into the part that contains the kth smallest element.</li>
 * </ol>
 *
 * <p>Reference:
 * <a href="https://en.wikipedia.org/wiki/Median_of_medians">
 * Median of Medians Algorithm</a>
 */
public final class QuickSelectMedianOfMedians {

    private QuickSelectMedianOfMedians() {
        // Utility class; prevent instantiation
    }

    /**
     * Returns the kth smallest element in the given array using the
     * deterministic Median of Medians approach.
     *
     * @param arr the input array
     * @param k index (0-based) of the kth smallest element to find
     * @return the kth smallest element
     * @throws IllegalArgumentException if input is invalid
     */
    public static int quickSelect(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k < 0 || k >= arr.length) {
            throw new IllegalArgumentException("Invalid input");
        }
        return select(arr, 0, arr.length - 1, k);
    }

    private static int select(int[] arr, int left, int right, int k) {
        if (left == right) {
            return arr[left];
        }

        int pivotIndex = getPivotIndex(arr, left, right);
        int pivotValue = arr[pivotIndex];
        int partitionIndex = partition(arr, left, right, pivotValue);

        if (k == partitionIndex) {
            return arr[k];
        } else if (k < partitionIndex) {
            return select(arr, left, partitionIndex - 1, k);
        } else {
            return select(arr, partitionIndex + 1, right, k);
        }
    }

    private static int getPivotIndex(int[] arr, int left, int right) {
        int n = right - left + 1;
        if (n < 5) {
            Arrays.sort(arr, left, right + 1);
            return left + n / 2;
        }

        int numMedians = (int) Math.ceil(n / 5.0);
        int[] medians = new int[numMedians];

        for (int i = 0; i < numMedians; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(subLeft + 4, right);
            Arrays.sort(arr, subLeft, subRight + 1);
            medians[i] = arr[subLeft + (subRight - subLeft) / 2];
        }

        int medianOfMedians = quickSelect(medians, numMedians / 2);
        for (int i = left; i <= right; i++) {
            if (arr[i] == medianOfMedians) {
                return i;
            }
        }
        return left; // fallback
    }

    private static int partition(int[] arr, int left, int right, int pivotValue) {
        int i = left;
        for (int j = left; j <= right; j++) {
            if (arr[j] < pivotValue) {
                swap(arr, i, j);
                i++;
            }
        }

        int pivotIndex = i;
        for (int j = i; j <= right; j++) {
            if (arr[j] == pivotValue) {
                swap(arr, j, pivotIndex);
                break;
            }
        }
        return pivotIndex;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
