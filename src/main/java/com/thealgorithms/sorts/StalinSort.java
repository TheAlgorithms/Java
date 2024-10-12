package com.thealgorithms.sorts;

import java.util.Arrays;

public class StalinSort implements SortAlgorithm {
    public <T extends Comparable<T>> T[] sort(T[] array) {
        validateInput(array);
        int currentIndex = 0;

        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(array[currentIndex]) >= 0) {
                currentIndex++;
                array[currentIndex] = array[i];
            }
        }

        T[] result = (T[]) new Comparable[currentIndex + 1];
        System.arraycopy(array, 0, result, 0, currentIndex + 1);
        return result;
    }

    private void validateInput(final Comparable<?>[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Input array must not be empty.");
        }
    }

    public static void main(String[] args) {
        Integer[] integers = {4, 23, 6, 78, 1, 54, 231, 9, 12};
        StalinSort stalinSort = new StalinSort();
        SortUtils.print(stalinSort.sort(integers));
        
        String[] strings = {"c", "a", "e", "b", "d"};
        SortUtils.print(stalinSort.sort(strings));
    }
}
