package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SelectionSortTest {

    @Test
    // valid test case
    void IntegerArrTest() {
        Integer[] arr = {4, 23, 6, 78, 1, 54, 231, 9, 12};
        SelectionSort selectionSort = new SelectionSort();

        assertArrayEquals(new Integer[] {1, 4, 6, 9, 12, 23, 54, 78, 231}, selectionSort.sort(arr));
    }

    @Test
    // valid test case
    void StringArrTest() {
        String[] arr = {"c", "a", "e", "b", "d"};
        SelectionSort selectionSort = new SelectionSort();

        assertArrayEquals(new String[] {"a", "b", "c", "d", "e"}, selectionSort.sort(arr));
    }

    @Test
    // invalid test case
    void emptyArrTest() {
        Integer[] arr = {};
        SelectionSort selectionSort = new SelectionSort();

        assertArrayEquals(new Integer[] {}, selectionSort.sort(arr));
    }
}
