package com.thealgorithms.sorts;

import static com.thealgorithms.sorts.SortUtils.less;
import static com.thealgorithms.sorts.SortUtils.print;

class InsertionSort implements SortAlgorithm {

    /**
     * Generic insertion sort algorithm in increasing order.
     *
     * @param array the array to be sorted.
     * @param <T> the class of array.
     * @return sorted array.
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        for (int i = 1; i < array.length; i++) {
            T insertValue = array[i];
            int j;
            for (j = i - 1; j >= 0 && less(insertValue, array[j]); j--) {
                array[j + 1] = array[j];
            }
            if (j != i - 1) {
                array[j + 1] = insertValue;
            }
        }
        return array;
    }

    /**
     * Driver Code
     */
    public static void main(String[] args) {
        Integer[] integers = {4, 23, 6, 78, 1, 54, 231, 9, 12};
        InsertionSort sort = new InsertionSort();
        sort.sort(integers);
        print(integers);
        /* [1, 4, 6, 9, 12, 23, 54, 78, 231] */

        String[] strings = {"c", "a", "e", "b", "d"};
        sort.sort(strings);
        print(strings);
        /* [a, b, c, d, e] */
    }
}
