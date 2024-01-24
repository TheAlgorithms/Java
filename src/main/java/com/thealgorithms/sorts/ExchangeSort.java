package com.thealgorithms.sorts;

import java.util.Arrays;

public class ExchangeSort {

    public static void exchangeSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap arr[j] and arr[j + 1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {12, 45, 23, 67, 10};

        System.out.println("Original Array: " + Arrays.toString(arr));

        // Sorting using ExchangeSort
        exchangeSort(arr);

        System.out.println("Sorted Array: " + Arrays.toString(arr));
    }
}


