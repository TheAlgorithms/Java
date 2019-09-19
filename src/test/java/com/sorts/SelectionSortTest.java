package com.sorts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SelectionSortTest {

    @Test
    void selectionSortTest() {
        SelectionSort selectionSort = new SelectionSort();

        Integer[] unsortedInt = new Integer[]{0, 5, 9, 2, 1, 3, 4, 8, 6, 7};
        Integer[] sortedInt = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Assertions.assertArrayEquals(sortedInt, selectionSort.sort(unsortedInt));

        Character[] unsortedChar = new Character[]{'f', 'h', 'c', 'a', 'b', 'd', 'g', 'e'};
        Character[] sortedChar = new Character[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        Assertions.assertArrayEquals(sortedChar, selectionSort.sort(unsortedChar));
    }

    @Test
    void test1() {
        SelectionSort selectionSort = new SelectionSort();

        Integer[] unsorted = new Integer[]{10, 20, 4, 5, 12, -1, 7, -32, 45, 56};
        Integer[] sorted = new Integer[]{-32, -1, 4, 5, 7, 10, 12, 20, 45, 56};
        Assertions.assertArrayEquals(sorted, selectionSort.sort(unsorted));
    }


    @Test
    void test2() {
        SelectionSort selectionSort = new SelectionSort();

        Integer[] unsortedInt = {};
        Integer[] sorted = {};
        Assertions.assertArrayEquals(sorted,selectionSort.sort(unsortedInt));
    }

    @Test
    void test3() {
        SelectionSort selectionSort = new SelectionSort();

        Integer[] unsorted = new Integer[] {11, 33, 25, 2, 7, 6, 67, 45, 11, 5};
        Integer[] sorted = new Integer[] {2, 5, 6, 7, 11, 11, 25, 33, 45, 67};
        Assertions.assertArrayEquals(sorted, selectionSort.sort(unsorted));
    }

    @Test
    void test4() {
        SelectionSort selectionSort = new SelectionSort();

        Character[] unsorted = new Character[] {'a', 'w', 'i', 'l', 'o', 'b', 'v', 'p', 'm', 'a'};
        Character[] sorted = new Character[] {'a', 'a', 'b', 'i', 'l', 'm', 'o', 'p', 'v', 'w'};
        Assertions.assertArrayEquals(sorted, selectionSort.sort(unsorted));
    }
}
