package com.thealgorithms.sorts;

/**
 * Introspective Sort Algorithm Implementation
 *
 * @see <a href="https://en.wikipedia.org/wiki/Introsort">IntroSort Algorithm</a>
 */
public class IntrospectiveSort implements SortAlgorithm {

    private static final int INSERTION_SORT_THRESHOLD = 16;

    /**
     * Sorts the given array using Introspective Sort, which combines quicksort, heapsort, and insertion sort.
     *
     * @param array The array to be sorted
     * @param <T>   The type of elements in the array, which must be comparable
     * @return The sorted array
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        final int depth = 2 * (int) (Math.log(array.length) / Math.log(2));
        introspectiveSort(array, 0, array.length - 1, depth);
        return array;
    }

    /**
     * Performs introspective sort on the specified subarray.
     *
     * @param array The array to be sorted
     * @param low   The starting index of the subarray
     * @param high  The ending index of the subarray
     * @param depth The current depth of recursion
     * @param <T>   The type of elements in the array, which must be comparable
     */
    private static <T extends Comparable<T>> void introspectiveSort(T[] array, final int low, int high, final int depth) {
        while (high - low > INSERTION_SORT_THRESHOLD) {
            if (depth == 0) {
                heapSort(array, low, high);
                return;
            }
            final int pivotIndex = partition(array, low, high);
            introspectiveSort(array, pivotIndex + 1, high, depth - 1);
            high = pivotIndex - 1;
        }
        insertionSort(array, low, high);
    }

    /**
     * Partitions the array around a pivot.
     *
     * @param array The array to be partitioned
     * @param low   The starting index of the subarray
     * @param high  The ending index of the subarray
     * @param <T>   The type of elements in the array, which must be comparable
     * @return The index of the pivot
     */
    private static <T extends Comparable<T>> int partition(T[] array, final int low, final int high) {
        final int pivotIndex = low + (int) (Math.random() * (high - low + 1));
        SortUtils.swap(array, pivotIndex, high);
        final T pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (SortUtils.greaterOrEqual(pivot, array[j])) {
                i++;
                SortUtils.swap(array, i, j);
            }
        }
        SortUtils.swap(array, i + 1, high);
        return i + 1;
    }

    /**
     * Sorts a subarray using insertion sort.
     *
     * @param array The array to be sorted
     * @param low   The starting index of the subarray
     * @param high  The ending index of the subarray
     * @param <T>   The type of elements in the array, which must be comparable
     */
    private static <T extends Comparable<T>> void insertionSort(T[] array, final int low, final int high) {
        for (int i = low + 1; i <= high; i++) {
            final T key = array[i];
            int j = i - 1;
            while (j >= low && SortUtils.greater(array[j], key)) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    /**
     * Sorts a subarray using heapsort.
     *
     * @param array The array to be sorted
     * @param low   The starting index of the subarray
     * @param high  The ending index of the subarray
     * @param <T>   The type of elements in the array, which must be comparable
     */
    private static <T extends Comparable<T>> void heapSort(T[] array, final int low, final int high) {
        final int n = high - low + 1;
        for (int i = (n / 2) - 1; i >= 0; i--) {
            heapify(array, i, n, low);
        }
        for (int i = high; i > low; i--) {
            SortUtils.swap(array, low, i);
            heapify(array, 0, i - low, low);
        }
    }

    /**
     * Maintains the heap property for a subarray.
     *
     * @param array The array to be heapified
     * @param i     The index to be heapified
     * @param n     The size of the heap
     * @param low   The starting index of the subarray
     * @param <T>   The type of elements in the array, which must be comparable
     */
    private static <T extends Comparable<T>> void heapify(T[] array, final int i, final int n, final int low) {
        final int left = 2 * i + 1;
        final int right = 2 * i + 2;
        int largest = i;

        if (left < n && SortUtils.greater(array[low + left], array[low + largest])) {
            largest = left;
        }
        if (right < n && SortUtils.greater(array[low + right], array[low + largest])) {
            largest = right;
        }
        if (largest != i) {
            SortUtils.swap(array, low + i, low + largest);
            heapify(array, largest, n, low);
        }
    }
}
