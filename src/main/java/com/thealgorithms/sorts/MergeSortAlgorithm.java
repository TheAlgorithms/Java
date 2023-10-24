package com.thealgorithms.sorts;

/**
 * Merge Sort Algorithm Implementation
 *
 * Wiki:https://en.wikipedia.org/wiki/Merge_sort
 */
public class MergeSortAlgorithm {
    public static void mergeSort(int[] arr) {
        if (arr.length > 1) {
            int mid = arr.length / 2;
            int[] leftArray = new int[mid];
            int[] rightArray = new int[arr.length - mid];

            System.arraycopy(arr, 0, leftArray, 0, mid);
            System.arraycopy(arr, mid, rightArray, 0, arr.length - mid);

            mergeSort(leftArray);
            mergeSort(rightArray);

            int i = 0, j = 0, k = 0;

            while (i < leftArray.length && j < rightArray.length) {
                if (leftArray[i] < rightArray[j]) {
                    arr[k++] = leftArray[i++];
                } else {
                    arr[k++] = rightArray[j++];
                }
            }

            while (i < leftArray.length) {
                arr[k++] = leftArray[i++];
            }

            while (j < rightArray.length) {
                arr[k++] = rightArray[j++];
            }
        }
    }
}

class mergeSortInernTest{
    public static void main(String[] args) {
        int[] arr = {9, 3, 1, 5, 13, 12};
        MergeSortAlgorithm.mergeSort(arr);

        System.out.println("Sorted array");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}

