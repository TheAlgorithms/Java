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
    void selectionSortTestForRandomNumberArray() {
        SelectionSort selectionSort = new SelectionSort();

        Integer[] unsortedInt = new Integer[]{0, 5, 9, 2, 1, 3, 4, 8, 6, 7};
        Integer[] sortedInt = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Assertions.assertArrayEquals(sortedInt, selectionSort.sort(unsortedInt));
    }

    @Test
    void selectionSortTestForRandomCharNumberArray() {
        SelectionSort selectionSort = new SelectionSort();

        Character[] unsortedChar = new Character[]{'f', 'h', 'c', 'a', 'b', 'd', 'g', 'e'};
        Character[] sortedChar = new Character[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        Assertions.assertArrayEquals(sortedChar, selectionSort.sort(unsortedChar));
    }

    @Test
    void selectionSortTestForDESCNumberArray() {
        SelectionSort selectionSort = new SelectionSort();

        Integer[] unsortedInt = new Integer[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        Integer[] sortedInt = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Assertions.assertArrayEquals(sortedInt, selectionSort.sort(unsortedInt));
    }

    @Test
    void selectionSortTestForDESCCharNumberArray() {
        SelectionSort selectionSort = new SelectionSort();

        Character[] unsortedChar = new Character[]{'h', 'g', 'f', 'e', 'd', 'c', 'b', 'a'};
        Character[] sortedChar = new Character[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        Assertions.assertArrayEquals(sortedChar, selectionSort.sort(unsortedChar));
    }

    @Test
    void selectionSortTestForASCNumberArray() {
        SelectionSort selectionSort = new SelectionSort();

        Integer[] unsortedInt = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Integer[] sortedInt = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Assertions.assertArrayEquals(sortedInt, selectionSort.sort(unsortedInt));
    }

    @Test
    void selectionSortTestForASCCharNumberArray() {
        SelectionSort selectionSort = new SelectionSort();

        Character[] unsortedChar = new Character[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        Character[] sortedChar = new Character[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        Assertions.assertArrayEquals(sortedChar, selectionSort.sort(unsortedChar));
    }

}
