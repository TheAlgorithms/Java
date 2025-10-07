package com.thealgorithms.divideandconquer;

/**
 * Deterministic QuickSelect (Median of Medians) algorithm.
 *
 * Finds the kth smallest element in an unsorted array in O(n) worst-case time.
 *
 * Reference: https://en.wikipedia.org/wiki/Median_of_medians
 */
public final class DeterministicQuickSelect {

    private DeterministicQuickSelect() {
    }

    public static int select(int[] arr, int k) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }
        if (k < 1 || k > arr.length) {
            throw new IllegalArgumentException("k is out of bounds");
        }
        return quickSelect(arr, 0, arr.length - 1, k - 1);
    }

    private static int quickSelect(int[] arr, int left, int right, int k) {
        if (left == right) {
            return arr[left];
        }

        int pivotIndex = medianOfMedians(arr, left, right);
        pivotIndex = partition(arr, left, right, pivotIndex);

        if (k == pivotIndex) {
            return arr[k];
        } else if (k < pivotIndex) {
            return quickSelect(arr, left, pivotIndex - 1, k);
        } else {
            return quickSelect(arr, pivotIndex + 1, right, k);
        }
    }

    private static int partition(int[] arr, int left, int right, int pivotIndex) {
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (arr[i] < pivotValue) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }
        swap(arr, right, storeIndex);
        return storeIndex;
    }

    private static int medianOfMedians(int[] arr, int left, int right) {
        int n = right - left + 1;
        if (n <= 5) {
            return partition5(arr, left, right);
        }

        int numMedians = (int) Math.ceil((double) n / 5);
        int[] medians = new int[numMedians];

        for (int i = 0; i < numMedians; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(subLeft + 4, right);
            medians[i] = arr[partition5(arr, subLeft, subRight)];
        }

        return quickSelect(medians, 0, medians.length - 1, medians.length / 2);
    }

    private static int partition5(int[] arr, int left, int right) {
        java.util.Arrays.sort(arr, left, right + 1);
        return (left + right) / 2;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
