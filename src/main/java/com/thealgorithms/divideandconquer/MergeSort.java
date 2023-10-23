package com.thealgorithms.sorts;

import java.util.Arrays;

/**
 * Merge Sort Algorithm Implementation
 */

public class MergeSort {

    public static int[] merge(int[] leftHalf, int[] rightHalf) {
        int[] sortedArray = new int[leftHalf.length + rightHalf.length];

        int pointer1 = 0; // pointer to current index for left Half
        int pointer2 = 0; // pointer to current index for the right Half
        int index = 0; // pointer to current index for the sorted array Half

        while (pointer1 < leftHalf.length && pointer2 < rightHalf.length) {
            if (leftHalf[pointer1] < rightHalf[pointer2]) {
                sortedArray[index] = leftHalf[pointer1];
                pointer1 += 1;
            } else {
                sortedArray[index] = rightHalf[pointer2];
                pointer2 += 1;
            }
            index += 1;
        }

        while (pointer1 < leftHalf.length) {
            sortedArray[index] = leftHalf[pointer1];
            pointer1 += 1;
            index += 1;
        }

        while (pointer2 < rightHalf.length) {
            sortedArray[index] = rightHalf[pointer2];
            pointer2 += 1;
            index += 1;
        }

        return sortedArray;
    }

    public static int[] mergeSort(int[] array) {
        if (array.length <= 1) {
            return array;
        }
        int middle = 0 + (array.length - 0) / 2;

        int[] leftHalf = Arrays.copyOfRange(array, 0, middle);
        int[] rightHalf = Arrays.copyOfRange(array, middle, array.length);

        return merge(mergeSort(leftHalf), mergeSort(rightHalf));
    }

    public static void main(String[] args) {
        int[] array = {-2, 3, -10, 11, 99, 100000, 100, -200};
        System.out.println("Unsorted array: " + Arrays.toString(array));
        array = mergeSort(array);
        System.out.println("Sorted array: " + Arrays.toString(array));
    }
}
