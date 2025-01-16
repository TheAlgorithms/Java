package com.thealgorithms.sorts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DarkSortTest {

    @Test
    void testSortWithIntegers() {
        Integer[] unsorted = {5, 3, 8, 6, 2, 7, 4, 1};
        Integer[] expected = {1, 2, 3, 4, 5, 6, 7, 8};

        DarkSort darkSort = new DarkSort();
        Integer[] sorted = darkSort.sort(unsorted);

        assertArrayEquals(expected, sorted);
    }

    @Test
    void testSortWithStrings() {
        String[] unsorted = {"zebra", "apple", "mango", "banana"};
        String[] expected = {"apple", "banana", "mango", "zebra"};

        DarkSort darkSort = new DarkSort();
        String[] sorted = darkSort.sort(unsorted);

        assertArrayEquals(expected, sorted);
    }

    @Test
    void testEmptyArray() {
        Integer[] unsorted = {};
        Integer[] expected = {};

        DarkSort darkSort = new DarkSort();
        Integer[] sorted = darkSort.sort(unsorted);

        assertArrayEquals(expected, sorted);
    }

    @Test
    void testSingleElementArray() {
        Integer[] unsorted = {42};
        Integer[] expected = {42};

        DarkSort darkSort = new DarkSort();
        Integer[] sorted = darkSort.sort(unsorted);

        assertArrayEquals(expected, sorted);
    }

    @Test
    void testAlreadySortedArray() {
        Integer[] unsorted = {1, 2, 3, 4, 5};
        Integer[] expected = {1, 2, 3, 4, 5};

        DarkSort darkSort = new DarkSort();
        Integer[] sorted = darkSort.sort(unsorted);

        assertArrayEquals(expected, sorted);
    }

    @Test
    void testNullArray() {
        Integer[] unsorted = null;

        DarkSort darkSort = new DarkSort();
        Integer[] sorted = darkSort.sort(unsorted);

        assertNull(sorted, "Sorting a null array should return null");
    }

}
