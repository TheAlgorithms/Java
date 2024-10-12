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
        for (int k = low; k <= high; k++) {
            aux[k] = array[k];
        }

        int i = low, j = mid + 1;
        for (int k = low; k <= high; k++) {
            if (i > mid)
                array[k] = aux[j++];
            else if (j > high)
                array[k] = aux[i++];
            else if (aux[j].compareTo(aux[i]) < 0)
                array[k] = aux[j++];
            else
                array[k] = aux[i++];
        }
    }

    public static void main(String[] args) {
        AdaptiveMergeSort adaptiveMergeSort = new AdaptiveMergeSort();
 
        Integer[] integers = {4, 23, 6, 78, 1, 54, 231, 9, 12};
        SortUtils.print(adaptiveMergeSort.sort(integers));

        String[] strings = {"c", "a", "e", "b", "d"};
        SortUtils.print(adaptiveMergeSort.sort(strings));
    }
}
