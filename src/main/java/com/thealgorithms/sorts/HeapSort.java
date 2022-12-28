package com.thealgorithms.sorts;

/**
 * Heap Sort Algorithm Implementation
 *
 * @see <a href="https://en.wikipedia.org/wiki/Heapsort">Heap Sort Algorithm</a>
 */
public class HeapSort implements SortAlgorithm {

    /**
     * For simplicity, we are considering the heap root index as 1 instead of 0.
     * It simplifies future calculations. Because of that we are decreasing the
     * provided indexes by 1 in {@link #swap(Object[], int, int)} and
     * {@link #less(Comparable[], int, int)} functions.
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] unsorted) {
        int n = unsorted.length;
        heapify(unsorted, n);
        while (n > 1) {
            swap(unsorted, 1, n--);
            siftDown(unsorted, 1, n);
        }
        return unsorted;
    }

    private static <T extends Comparable<T>> void heapify(T[] unsorted, int n) {
        for (int k = n / 2; k >= 1; k--) {
            siftDown(unsorted, k, n);
        }
    }

    private static <T extends Comparable<T>> void siftDown(T[] unsorted, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(unsorted, j, j + 1)) {
                j++;
            }
            if (!less(unsorted, k, j)) {
                break;
            }
            swap(unsorted, k, j);
            k = j;
        }
    }

    private static <T> void swap(T[] array, int idx, int idy) {
        T swap = array[idx - 1];
        array[idx - 1] = array[idy - 1];
        array[idy - 1] = swap;
    }

    private static <T extends Comparable<T>> boolean less(T[] array, int idx, int idy) {
        return array[idx - 1].compareTo(array[idy - 1]) < 0;
    }
}
