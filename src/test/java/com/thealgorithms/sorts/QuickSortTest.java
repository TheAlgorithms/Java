package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

/**
 * @author Akshay Dubey (https://github.com/itsAkshayDubey)
 * @see QuickSort
 */
class QuickSortTest {

    private QuickSort quickSort = new QuickSort();

    @Test
    void quickSortEmptyArrayShouldPass() {
        Integer[] array = {};
        Integer[] sorted = quickSort.sort(array);
        Integer[] expected = {};
        assertArrayEquals(expected, sorted);
    }

    @Test
    void quickSortSingleValueArrayShouldPass() {
        Integer[] array = { 7 };
        Integer[] sorted = quickSort.sort(array);
        Integer[] expected = { 7 };
        assertArrayEquals(expected, sorted);
    }

    @Test
    void quickSortWithIntegerArrayShouldPass() {
        Integer[] array = { 49, 4, 36, 9, 144, 1 };
        Integer[] sorted = quickSort.sort(array);
        Integer[] expected = { 1, 4, 9, 36, 49, 144 };
        assertArrayEquals(expected, sorted);
    }

    @Test
    void quickSortForArrayWithNegativeValuesShouldPass() {
        Integer[] array = { 49, -36, -144, -49, 1, 9 };
        Integer[] sorted = quickSort.sort(array);
        Integer[] expected = { -144, -49, -36, 1, 9, 49 };
        assertArrayEquals(expected, sorted);
    }

    @Test
    void quickSortForArrayWithDuplicateValuesShouldPass() {
        Integer[] array = { 36, 1, 49, 1, 4, 9 };
        Integer[] sorted = quickSort.sort(array);
        Integer[] expected = { 1, 1, 4, 9, 36, 49 };
        assertArrayEquals(expected, sorted);
    }

    @Test
    void quickSortWithStringArrayShouldPass() {
        String[] array = { "c", "a", "e", "b", "d" };
        String[] sorted = quickSort.sort(array);
        String[] expected = { "a", "b", "c", "d", "e" };
        assertArrayEquals(expected, sorted);
    }
}
