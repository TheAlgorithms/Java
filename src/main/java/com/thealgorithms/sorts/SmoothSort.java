package com.thealgorithms.sorts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import com.thealgorithms.bitmanipulation.SingleBitOperations;
import com.thealgorithms.datastructures.heaps.LeonardoHeap;

/**
 * Wikipedia: https://en.wikipedia.org/wiki/Smoothsort
 */
public final class SmoothSort implements SortAlgorithm {

    public SmoothSort() {
    }

    private static <T extends Comparable<T>> void smoothSort(T[] array) {
        int length = array.length;
        LeonardoHeap<T> leonardoHeap = new LeonardoHeap<T>();

        for(int i = 0; i < length ; i++) {
            leonardoHeap.insertElement(array[i]);
        }

        for(int i = 0; i < length; i++) {
            T maxElement = leonardoHeap.deleteElement();
            array[length - i - 1] = maxElement;
        }
    }

    @Override
    public <T extends Comparable<T>> T[] sort(T[] unsorted) {
        smoothSort(unsorted);
        return unsorted;
    }
}
