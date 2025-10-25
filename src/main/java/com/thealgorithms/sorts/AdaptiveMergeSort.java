package com.thealgorithms.sorts;

public class AdaptiveMergeSort implements SortAlgorithm {
    @SuppressWarnings("unchecked")
    public <T extends Comparable<T>> T[] sort(T[] array) {
        if (array.length <= 1) {
            return array;
        }
        T[] aux = array.clone();
        sort(array, aux, 0, array.length - 1);
        return array;
    }

    private <T extends Comparable<T>> void sort(T[] array, T[] aux, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = low + (high - low) / 2;
        sort(array, aux, low, mid);
        sort(array, aux, mid + 1, high);
        merge(array, aux, low, mid, high);
    }

    private <T extends Comparable<T>> void merge(T[] array, T[] aux, int low, int mid, int high) {
        System.arraycopy(array, low, aux, low, high - low + 1);
        int i = low;
        int j = mid + 1;
        for (int k = low; k <= high; k++) {
            if (i > mid) {
                array[k] = aux[j++];
            } else if (j > high) {
                array[k] = aux[i++];
            } else if (SortUtils.less(aux[j], aux[i])) {
                array[k] = aux[j++];
            } else {
                array[k] = aux[i++];
            }
        }
    }
}
