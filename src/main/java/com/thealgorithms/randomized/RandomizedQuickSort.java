package com.thealgorithms.randomized;

public class RandomizedQuickSort {

    public static void randomizedQuickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            randomizedQuickSort(arr, low, pivotIndex - 1);
            randomizedQuickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivotIndex = low + (int) (Math.random() * (high - low + 1));
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, high); // Move pivot to end
        int storeIndex = low;
        for (int i = low; i < high; i++) {
            if (arr[i] < pivotValue) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }
        swap(arr, storeIndex, high); // Move pivot to its final place
        return storeIndex;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
}
