package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class RotatedBinarySearchTest {

    @Test
    void shouldFindElementInRotatedArrayLeftSide() {
        RotatedBinarySearch search = new RotatedBinarySearch();
        Integer[] array = {8, 9, 10, 11, 12, 1, 2, 3, 4, 5, 6, 7};
        assertEquals(2, search.find(array, 10));
    }

    @Test
    void shouldFindElementInRotatedArrayRightSide() {
        RotatedBinarySearch search = new RotatedBinarySearch();
        Integer[] array = {8, 9, 10, 11, 12, 1, 2, 3, 4, 5, 6, 7};
        assertEquals(6, search.find(array, 2));
    }

    @Test
    void shouldFindElementInNotRotatedArray() {
        RotatedBinarySearch search = new RotatedBinarySearch();
        Integer[] array = {1, 2, 3, 4, 5, 6, 7};
        assertEquals(4, search.find(array, 5));
    }

    @Test
    void shouldReturnMinusOneWhenNotFound() {
        RotatedBinarySearch search = new RotatedBinarySearch();
        Integer[] array = {4, 5, 6, 7, 0, 1, 2};
        assertEquals(-1, search.find(array, 3));
    }

    @Test
    void shouldHandleWhenMiddleIsGreaterThanKeyInRightSortedHalf() {
        RotatedBinarySearch search = new RotatedBinarySearch();
        Integer[] array = {6, 7, 0, 1, 2, 3, 4, 5};
        assertEquals(2, search.find(array, 0));
    }

    @Test
    void shouldHandleDuplicates() {
        RotatedBinarySearch search = new RotatedBinarySearch();
        Integer[] array = {2, 2, 2, 3, 4, 2};
        int index = search.find(array, 3);
        assertTrue(index >= 0 && index < array.length);
        assertEquals(3, array[index]);
    }
}
