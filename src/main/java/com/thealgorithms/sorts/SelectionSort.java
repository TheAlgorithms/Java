package com.thealgorithms.sorts;

public class SelectionSort implements SortAlgorithm {

    /**
     * Generic selection sort algorithm in increasing order.
     *
     * @param arr the array to be sorted.
     * @param <T> the class of array.
     * @return sorted array.
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[minIndex].compareTo(arr[j]) > 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                T temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
        return arr;
    }

    /**
     * Driver Code
     */
    public static void main(String[] args) {
        Integer[] arr = { 4, 23, 6, 78, 1, 54, 231, 9, 12 };
        SelectionSort selectionSort = new SelectionSort();
        Integer[] sorted = selectionSort.sort(arr);
        for (int i = 0; i < sorted.length - 1; ++i) {
            assert sorted[i] <= sorted[i + 1];
        }

        String[] strings = { "c", "a", "e", "b", "d" };
        String[] sortedStrings = selectionSort.sort(strings);
        for (int i = 0; i < sortedStrings.length - 1; ++i) {
            assert strings[i].compareTo(strings[i + 1]) <= 0;
        }
    }
}
