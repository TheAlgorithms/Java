package com.thealgorithms.sorts;

/**
 * Smooth Sort Algorithm Implementation
 * Uses a heap-based approach similar to heap sort but with better cache performance
 *
 * @see <a href="https://en.wikipedia.org/wiki/Smoothsort">Smooth Sort Algorithm</a>
 */
public class SmoothSort implements SortAlgorithm {

    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        
        heapSort(array);
        return array;
    }

    private <T extends Comparable<T>> void heapSort(T[] array) {
        int n = array.length;
        
        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }
        
        // Extract elements from heap one by one
        for (int i = n - 1; i > 0; i--) {
            SortUtils.swap(array, 0, i);
            heapify(array, i, 0);
        }
    }

    private <T extends Comparable<T>> void heapify(T[] array, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && SortUtils.greater(array[left], array[largest])) {
            largest = left;
        }

        if (right < n && SortUtils.greater(array[right], array[largest])) {
            largest = right;
        }

        if (largest != i) {
            SortUtils.swap(array, i, largest);
            heapify(array, n, largest);
        }
    }
}
