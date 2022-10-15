package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

/**
 * @author Debasish Biswas (https://github.com/debasishbsws)
 * @see DualPivotQuickSort
 */
class DualPivotQuickSortTest {

    private DualPivotQuickSort dualPivotquickSort = new DualPivotQuickSort();

    @Test
    void quickSortEmptyArrayShouldPass() {
        Integer[] array = {};
        Integer[] sorted = dualPivotquickSort.sort(array);
        Integer[] expected = {};
        assertArrayEquals(expected, sorted);
    }

    @Test
    void quickSortSingleValueArrayShouldPass() {
        Integer[] array = { 7 };
        Integer[] sorted = dualPivotquickSort.sort(array);
        Integer[] expected = { 7 };
        assertArrayEquals(expected, sorted);
    }

    @Test
    void quickSortWithIntegerArrayShouldPass() {
        Integer[] array = { 49, 4, 36, 9, 144, 1 };
        Integer[] sorted = dualPivotquickSort.sort(array);
        Integer[] expected = { 1, 4, 9, 36, 49, 144 };
        assertArrayEquals(expected, sorted);
    }

    @Test
    void quickSortForArrayWithNegativeValuesShouldPass() {
        Integer[] array = { 49, -36, -124, -49, 12, 9 };
        Integer[] sorted = dualPivotquickSort.sort(array);
        Integer[] expected = { -124, -49, -36, 9, 12, 49 };
        assertArrayEquals(expected, sorted);
    }

    @Test
    void quickSortForArrayWithDuplicateValuesShouldPass() {
        Integer[] array = { 36, 1, 49, 1, 4, 9 };
        Integer[] sorted = dualPivotquickSort.sort(array);
        Integer[] expected = { 1, 1, 4, 9, 36, 49 };
        assertArrayEquals(expected, sorted);
    }

    @Test
    void quickSortWithStringArrayShouldPass() {
        String[] array = { "cat", "ant", "eat", "boss", "dog", "apple" };
        String[] sorted = dualPivotquickSort.sort(array);
        String[] expected = { "ant", "apple", "boss", "cat", "dog", "eat" };
        assertArrayEquals(expected, sorted);
    }

}