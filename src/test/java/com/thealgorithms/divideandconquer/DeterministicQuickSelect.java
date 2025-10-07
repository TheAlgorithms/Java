package com.thealgorithms.divideandconquer;

/**
 * Deterministic QuickSelect (Median of Medians) algorithm.
 * <p>
 * Finds the kth smallest element in an unsorted array in O(n) worst-case time
 * complexity using the Median of Medians method to select a well-balanced pivot.
 * <p>
 * Reference: https://en.wikipedia.org/wiki/Median_of_medians
 */
public final class DeterministicQuickSelect {

    private DeterministicQuickSelect() {
        // Private constructor to prevent instantiation
    }

    /**
     * Returns the kth smallest element in the array.
     *
     * @param arr The input array
     * @param k   The order statistic (1-based). k=1 returns the smallest element.
     * @return The kth smallest element in the array
     */
    public static int selectKthSmallest(int[] arr, int k) {
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

    private static int medianOfMedians(int[] arr, int left, int right) {
        int n = right - left + 1;
        if (n <= 5) {
            insertionSort(arr, left, right);
            return left + n / 2;
        }

        int numMedians = (int) Math.ceil((double) n / 5);
        int[] medians = new int[numMedians];

        for (int i = 0; i < numMedians; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(subLeft + 4, right);
            insertionSort(arr, subLeft, subRight);
            medians[i] = arr[subLeft + (subRight - subLeft) / 2];
        }

        int medianValue = quickSelect(medians, 0, medians.length - 1, medians.length / 2);
        for (int i = left; i <= right; i++) {
            if (arr[i] == medianValue) {
                return i; // Return the index of the median in original array
            }
        }
        throw new IllegalStateException("Median value not found in the array");
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
        swap(arr, storeIndex, right);
        return storeIndex;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}
