package com.thealgorithms.sorts;

/**
 * OddEvenSort class implements the SortAlgorithm interface using the odd-even sort technique.
 * Odd-even sort is a comparison sort related to bubble sort.
 * It operates by comparing all (odd, even)-indexed pairs of adjacent elements in the list and, if a pair is in the wrong order, swapping them.
 * The next step repeats this process for (even, odd)-indexed pairs. This process continues until the list is sorted.
 *
 */
public final class OddEvenSort implements SortAlgorithm {

    /**
     * Sorts the given array using the Odd-Even Sort algorithm.
     *
     * @param <T> the type of elements in the array, which must implement the Comparable interface
     * @param array the array to be sorted
     * @return the sorted array
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        boolean sorted = false;
        while (!sorted) {
            sorted = performOddSort(array);
            sorted = performEvenSort(array) && sorted;
        }

        return array;
    }

    private <T extends Comparable<T>> boolean performOddSort(T[] array) {
        boolean sorted = true;
        for (int i = 1; i < array.length - 1; i += 2) {
            if (array[i].compareTo(array[i + 1]) > 0) {
                SortUtils.swap(array, i, i + 1);
                sorted = false;
            }
        }
        return sorted;
    }

    private <T extends Comparable<T>> boolean performEvenSort(T[] array) {
        boolean sorted = true;
        for (int i = 0; i < array.length - 1; i += 2) {
            if (array[i].compareTo(array[i + 1]) > 0) {
                SortUtils.swap(array, i, i + 1);
                sorted = false;
            }
        }
        return sorted;
    }
}
