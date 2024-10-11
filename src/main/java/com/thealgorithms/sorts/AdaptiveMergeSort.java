package com.thealgorithms.sorts;

/**
 * @author Anant Jain (https://github.com/anant-jain01)
 * @see https://www.tutorialspoint.com/adaptive-merging-and-sorting-in-data-structure
 */
public class AdaptiveMergeSort implements SortAlgorithm {

    public <T extends Comparable<T>> T[] sort(T[] array) {
        if (array == null || array.length < 2) {
            return array;
        }

        T[] aux = array.clone();
        adaptiveMergeSort(array, aux, 0, array.length - 1);
        return array;
    }

    private <T extends Comparable<T>> void adaptiveMergeSort(T[] array, T[] aux, int low, int high) {
        if (high <= low) {
            return;
        }

        int mid = low + (high - low) / 2;

        adaptiveMergeSort(aux, array, low, mid);
        adaptiveMergeSort(aux, array, mid + 1, high);

        if (array[mid].compareTo(array[mid + 1]) <= 0) {
            System.arraycopy(array, low, aux, low, high - low + 1);
            return;
        }

        merge(array, aux, low, mid, high);
    }

    private <T extends Comparable<T>> void merge(T[] array, T[] aux, int low, int mid, int high) {
        int i = low;
        int j = mid + 1;

        for (int k = low; k <= high; k++) {
            if (i > mid) {
                aux[k] = array[j++];
            } else if (j > high) {
                aux[k] = array[i++];
            } else if (array[j].compareTo(array[i]) < 0) {
                aux[k] = array[j++];
            } else {
                aux[k] = array[i++];
            }
        }
    }

    public static void main(String[] args) {
        Integer[] integers = {12, 11, 13, 5, 6, 7};
        AdaptiveMergeSort mergeSort = new AdaptiveMergeSort();
        SortUtils.print(mergeSort.sort(integers));

        String[] strings = {"zebra", "apple", "mango", "banana"};
        SortUtils.print(mergeSort.sort(strings));
    }
}
