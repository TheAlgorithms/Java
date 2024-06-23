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
    // Copy only the necessary portion of arr into aux
    System.arraycopy(arr, left, aux, left, right - left + 1);

    int i = left;
    int j = mid + 1;

    for (int k = left; k <= right; k++) {
        if (i > mid) {
            arr[k] = aux[j++];
        } else if (j > right) {
            arr[k] = aux[i++];
        } else if (less(aux[j], aux[i])) {
            arr[k] = aux[j++];
        } else {
            arr[k] = aux[i++];
        }
    }
}

}
