package com.thealgorithms.datastructures.heaps;

import java.util.Arrays;

/*
 * Heapsort Implementation
 * Wikipedia: https://en.wikipedia.org/wiki/Heapsort
 *
 * Author: William Nian
 *
 * */
public class HeapSort {

    public HeapSort() {
    }

    public static void heapSort(int testArr[]) {
        for (int i = testArr.length / 2 - 1; i >= 0; i--) {
            fixHeap(testArr, i, testArr.length);
        }
        for (int j = testArr.length - 1; j > 0; j--) {
            int temp = testArr[0];
            testArr[0] = testArr[j];
            testArr[j] = temp;
            fixHeap(testArr, 0, j);
        }
        System.out.println(Arrays.toString(testArr));
    }

    public static void fixHeap(int testArr[], int i, int length) {
        int temp = testArr[i];
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            if (k + 1 < length && testArr[k + 1] > testArr[k]) {
                k++;
            }
            if (testArr[k] > temp) {
                testArr[i] = testArr[k];
                i = k;
            } else {
                break;
            }
        }
        testArr[i] = temp;
    }

    public static void main(String[] args) {
        int[] testArr = {1, 5, 4, 7, 9};
        heapSort(testArr);
    }
}
