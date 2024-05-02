package com.thealgorithms.sorts;

public class BinaryInsertionSort {

    // Binary Insertion Sort method
    public int[] binaryInsertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int low = 0;
            int high = i - 1;

            while (low <= high) {
                final int mid = (low + high) >>> 1;
                if (temp < array[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            for (int j = i; j >= low + 1; j--) {
                array[j] = array[j - 1];
            }

            array[low] = temp;
        }
        return array;
    }
}
