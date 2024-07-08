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

        for (int i = 0; i < array.length; i++) {
            leonardoHeap.addElement(array[i]);
        }

        for (int i = 0; i < array.length; i++) {
            final T maxElement = leonardoHeap.removeElement();
            array[array.length - i - 1] = maxElement;
        }
    }

    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        smoothSort(array);
        return array;
    }
}
