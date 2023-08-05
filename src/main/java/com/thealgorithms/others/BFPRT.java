package com.thealgorithms.others;

import java.util.Arrays;

/**
 * BFPRT algorithm.
 */
public class BFPRT {

    public static int[] getMinKNumsByBFPRT(int[] arr, int k) {
        if (k < 1 || k > arr.length) {
            return null;
        }
        int minKth = getMinKthByBFPRT(arr, k);
        int[] res = new int[k];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < minKth) {
                res[index++] = arr[i];
            }
        }
        for (; index != res.length; index++) {
            res[index] = minKth;
        }
        return res;
    }

    public static int getMinKthByBFPRT(int[] arr, int k) {
        int[] copyArr = copyArray(arr);
        return bfprt(copyArr, 0, copyArr.length - 1, k - 1);
    }

    public static int[] copyArray(int[] arr) {
        int[] copyArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            copyArr[i] = arr[i];
        }
        return copyArr;
    }

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
     * wikipedia: https://en.wikipedia.org/wiki/Median_of_medians .
     *
     * @param arr an array.
     * @param begin begin num.
     * @param end end num.
     * @return median of medians.
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

    public static void swap(int[] arr, int i, int j) {
        int swap = arr[i];
        arr[i] = arr[j];
        arr[j] = swap;
    }

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
        int[] pivotRange = new int[2];
        pivotRange[0] = small + 1;
        pivotRange[1] = big - 1;
        return pivotRange;
    }

    public static int getMedian(int[] arr, int begin, int end) {
        insertionSort(arr, begin, end);
        int sum = begin + end;
        int mid = sum / 2 + (sum % 2);
        return arr[mid];
    }

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

    public static void main(String[] args) {
        int[] arr = {
            11,
            9,
            1,
            3,
            9,
            2,
            2,
            5,
            6,
            5,
            3,
            5,
            9,
            7,
            2,
            5,
            5,
            1,
            9,
        };
        int[] minK = getMinKNumsByBFPRT(arr, 5);
        System.out.println(Arrays.toString(minK));
    }
}
