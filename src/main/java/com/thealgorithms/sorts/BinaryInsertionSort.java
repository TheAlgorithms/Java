package com.thealgorithms.sorts;

public class BinaryInsertionSort implements SortAlgorithm {

    // Binary Insertion Sort method
    public <T extends Comparable<T>> T[] sort(T[] array) {
        for (int i = 1; i < array.length; i++) {
            T temp = array[i];
            int low = 0;
            int high = i - 1;

            while (low <= high) {
                final int mid = (low + high) >>> 1;
                if (temp.compareTo(array[mid]) < 0 ) {
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
