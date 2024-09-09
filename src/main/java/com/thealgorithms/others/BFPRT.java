package com.thealgorithms.others;

/**
 * The BFPRT (Median of Medians) algorithm implementation.
 * It provides a way to find the k-th smallest element in an unsorted array
 * with an optimal worst-case time complexity of O(n).
 * This algorithm is used to find the k smallest numbers in an array.
 */
public final class BFPRT {
    private BFPRT() {
    }

    /**
     * Returns the k smallest elements from the array using the BFPRT algorithm.
     *
     * @param arr the input array
     * @param k the number of smallest elements to return
     * @return an array containing the k smallest elements, or null if k is invalid
     */
    public static int[] getMinKNumsByBFPRT(int[] arr, int k) {
        if (k < 1 || k > arr.length) {
            return null;
        }
        int minKth = getMinKthByBFPRT(arr, k);
        int[] res = new int[k];
        int index = 0;
        for (int value : arr) {
            if (value < minKth) {
                res[index++] = value;
            }
        }
        for (; index != res.length; index++) {
            res[index] = minKth;
        }
        return res;
    }

    /**
     * Returns the k-th smallest element from the array using the BFPRT algorithm.
     *
     * @param arr the input array
     * @param k the rank of the smallest element to find
     * @return the k-th smallest element
     */
    public static int getMinKthByBFPRT(int[] arr, int k) {
        int[] copyArr = copyArray(arr);
        return bfprt(copyArr, 0, copyArr.length - 1, k - 1);
    }

    /**
     * Creates a copy of the input array.
     *
     * @param arr the input array
     * @return a copy of the array
     */
    public static int[] copyArray(int[] arr) {
        int[] copyArr = new int[arr.length];
        System.arraycopy(arr, 0, copyArr, 0, arr.length);
        return copyArr;
    }

    /**
     * BFPRT recursive method to find the k-th smallest element.
     *
     * @param arr the input array
     * @param begin the starting index
     * @param end the ending index
     * @param i the index of the desired smallest element
     * @return the k-th smallest element
     */
    public static int bfprt(int[] arr, int begin, int end, int i) {
        if (begin == end) {
            return arr[begin];
        }
        int pivot = medianOfMedians(arr, begin, end);
        int[] pivotRange = partition(arr, begin, end, pivot);
        if (i >= pivotRange[0] && i <= pivotRange[1]) {
            return arr[i];
        } else if (i < pivotRange[0]) {
            return bfprt(arr, begin, pivotRange[0] - 1, i);
        } else {
            return bfprt(arr, pivotRange[1] + 1, end, i);
        }
    }

    /**
     * Finds the median of medians as the pivot element.
     *
     * @param arr the input array
     * @param begin the starting index
     * @param end the ending index
     * @return the median of medians
     */
    public static int medianOfMedians(int[] arr, int begin, int end) {
        int num = end - begin + 1;
        int offset = num % 5 == 0 ? 0 : 1;
        int[] mArr = new int[num / 5 + offset];
        for (int i = 0; i < mArr.length; i++) {
            mArr[i] = getMedian(arr, begin + i * 5, Math.min(end, begin + i * 5 + 4));
        }
        return bfprt(mArr, 0, mArr.length - 1, mArr.length / 2);
    }

    /**
     * Partitions the array around a pivot.
     *
     * @param arr the input array
     * @param begin the starting index
     * @param end the ending index
     * @param num the pivot element
     * @return the range where the pivot is located
     */
    public static int[] partition(int[] arr, int begin, int end, int num) {
        int small = begin - 1;
        int cur = begin;
        int big = end + 1;
        while (cur != big) {
            if (arr[cur] < num) {
                swap(arr, ++small, cur++);
            } else if (arr[cur] > num) {
                swap(arr, --big, cur);
            } else {
                cur++;
            }
        }
        return new int[] {small + 1, big - 1};
    }

    /**
     * Finds the median of the elements between the specified range.
     *
     * @param arr the input array
     * @param begin the starting index
     * @param end the ending index
     * @return the median of the specified range
     */
    public static int getMedian(int[] arr, int begin, int end) {
        insertionSort(arr, begin, end);
        int sum = begin + end;
        int mid = sum / 2 + (sum % 2);
        return arr[mid];
    }

    /**
     * Sorts a portion of the array using insertion sort.
     *
     * @param arr the input array
     * @param begin the starting index
     * @param end the ending index
     */
    public static void insertionSort(int[] arr, int begin, int end) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = begin + 1; i != end + 1; i++) {
            for (int j = i; j != begin; j--) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                } else {
                    break;
                }
            }
        }
    }

    /**
     * Swaps two elements in an array.
     *
     * @param arr the input array
     * @param i the index of the first element
     * @param j the index of the second element
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
