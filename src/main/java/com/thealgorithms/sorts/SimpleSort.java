package com.thealgorithms.sorts;

public class SimpleSort implements SortAlgorithm {
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (SortUtils.less(array[j], array[i])) {
                    SortUtils.swap(array, i, j);
                }
            }
        }
        return array;
    }
}
