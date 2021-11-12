package com.thealgorithms.sorts;

import static com.thealgorithms.sorts.SortUtils.*;

public class ShellSort implements SortAlgorithm {

    /**
     * Implements generic shell sort.
     *
     * @param array the array to be sorted.
     * @param <T> the type of elements in the array.
     * @return the sorted array.
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        int length = array.length;
        int gap = 1;

        /* Calculate gap for optimization purpose */
        while (gap < length / 3) {
            gap = 3 * gap + 1;
        }

        for (; gap > 0; gap /= 3) {
            for (int i = gap; i < length; i++) {
                int j;
                T temp = array[i];
                for (j = i; j >= gap && less(temp, array[j - gap]); j -= gap) {
                    array[j] = array[j - gap];
                }
                array[j] = temp;
            }
        }
        return array;
    }

    /* Driver Code */
    public static void main(String[] args) {
        Integer[] toSort = {4, 23, 6, 78, 1, 54, 231, 9, 12};

        ShellSort sort = new ShellSort();
        sort.sort(toSort);
        for (int i = 0; i < toSort.length - 1; ++i) {
            assert toSort[i] <= toSort[i + 1];
        }
        print(toSort);
    }
}
