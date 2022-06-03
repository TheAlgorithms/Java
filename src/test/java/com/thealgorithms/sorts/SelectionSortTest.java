package com.thealgorithms.sorts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SelectionSortTest {

    @Test
    void IntegerArrTest() {
        Integer[] arr = {4, 23, 6, 78, 1, 54, 231, 9, 12};
        SelectionSort selectionSort = new SelectionSort();

        assertArrayEquals(new Integer[]{1, 4, 6, 9, 12, 23, 54, 78, 231}, selectionSort.sort(arr));
    }

    @Test
    void StringArrTest() {
        String[] arr = {"c", "a", "e", "b", "d"};
        SelectionSort selectionSort = new SelectionSort();

        assertArrayEquals(new String[]{"a", "b", "c", "d", "e"}, selectionSort.sort(arr));
    }
}
