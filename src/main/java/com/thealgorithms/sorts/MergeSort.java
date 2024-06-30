package com.thealgorithms.sorts;

import static com.thealgorithms.sorts.SortUtils.less;

/**
 * Generic merge sort algorithm.
 *
 * @see SortAlgorithm
 */
class MergeSort implements SortAlgorithm {

    private Comparable[] aux;

    /**
     * Generic merge sort algorithm implements.
     *
     * @param unsorted the array which should be sorted.
     * @param <T> Comparable class.
     * @return sorted array.
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] unsorted) {
        aux = new Comparable[unsorted.length];
        doSort(unsorted, 0, unsorted.length - 1);
        return unsorted;
    }

    /**
     * @param arr the array to be sorted.
     * @param left the first index of the array.
     * @param right the last index of the array.
     */
    private <T extends Comparable<T>> void doSort(T[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) >>> 1;
            doSort(arr, left, mid);
            doSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    /**
     * Merges two parts of an array.
     *
     * @param arr the array to be merged.
     * @param left the first index of the array.
     * @param mid the middle index of the array.
     * @param right the last index of the array merges two parts of an array in
     * increasing order.
     */
    @SuppressWarnings("unchecked")
   private <T extends Comparable<T>> void merge(T[] arr, int left, int mid, int right) {
    // Create a temporary array to hold the merged result
    Comparable[] aux = new Comparable[right - left + 1];
    System.arraycopy(arr, left, aux, 0, aux.length);

    int i = 0;      // Index for the left half
    int j = mid - left + 1; // Index for the right half

    for (int k = left; k <= right; k++) {
        if (i >= mid - left + 1) {
            arr[k] = (T) aux[j++];
        } else if (j >= right - left + 1 || less((T) aux[i], (T) aux[j])) {
            arr[k] = (T) aux[i++];
        } else {
            arr[k] = (T) aux[j++];
        }
    }
}


}
