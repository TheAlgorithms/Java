package com.thealgorithms.sorts;

/**
 * Heap Sort Algorithm Implementation
 *
 * @see <a href="https://en.wikipedia.org/wiki/Heapsort">Heap Sort Algorithm</a>
 */
public class HeapSort implements SortAlgorithm {

    /**
     * For simplicity, we are considering the heap root index as 1 instead of 0.
     * This approach simplifies future calculations. As a result, we decrease
     * the indexes by 1 when calling {@link SortUtils#less(Comparable, Comparable)}
     * and provide adjusted values to the {@link SortUtils#swap(Object[], int, int)} methods.
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] unsorted) {
        int n = unsorted.length;
        heapify(unsorted, n);
        while (n > 1) {
            SortUtils.swap(unsorted, 0, n - 1);
            n--;
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
            if (j < n && SortUtils.less(unsorted[j - 1], unsorted[j])) {
                j++;
            }
            if (!SortUtils.less(unsorted[k - 1], unsorted[j - 1])) {
                break;
            }
            SortUtils.swap(unsorted, k - 1, j - 1);
            k = j;
        }
    }
}
