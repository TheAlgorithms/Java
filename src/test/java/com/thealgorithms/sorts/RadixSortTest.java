package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class RadixSortTest {

    private RadixSort radixSort = new RadixSort();

    @Test
    public void testRadixSortEmptyArray() {
        int[] array = {};
        radixSort.radixSort(array);
        //catch exception if array is empty//
        assertArrayEquals(new int[] {}, array);

//        int[] expected = {};
//        assertArrayEquals(expected, array);
    }

    @Test
    public void testRadixSortSingleValueArray() {
        int[] array = { 7 };
        radixSort.radixSort(array);
        int[] expected = { 7 };
        assertArrayEquals(expected, array);
    }

    @Test
    public void testRadixSort() {
        int[] array = { 170, 45, 75, 90, 802, 24, 2, 66 };
        radixSort.radixSort(array);
        int[] expected = { 2, 24, 45, 66, 75, 90, 170, 802 };
        assertArrayEquals(expected, array);
    }

    @Test
    public void testRadixSortBigNumbers() {
        int[] array = { 170, 45, 75, 90, 802, 24, 2, 66, 1000000, 1000000000 };
        radixSort.radixSort(array);
        int[] expected = { 2, 24, 45, 66, 75, 90, 170, 802, 1000000, 1000000000 };
        assertArrayEquals(expected, array);
    }
}


