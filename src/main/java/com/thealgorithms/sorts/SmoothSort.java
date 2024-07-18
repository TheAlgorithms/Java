package com.thealgorithms.sorts;

import com.thealgorithms.datastructures.heaps.LeonardoHeap;

/**
 * Wikipedia: https://en.wikipedia.org/wiki/Smoothsort
 */
public final class SmoothSort implements SortAlgorithm {

    public SmoothSort() {
    }

    private static <T extends Comparable<T>> void smoothSort(T[] array) {
        LeonardoHeap<T> leonardoHeap = new LeonardoHeap<T>();

        for (final var element : array) {
            leonardoHeap.addElement(element);
        }

        for (int i = 0; i < array.length; i++) {
            array[array.length - i - 1] = leonardoHeap.removeElement();
        }
    }

    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        smoothSort(array);
        return array;
    }
}
