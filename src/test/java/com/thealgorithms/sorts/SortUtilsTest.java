package com.thealgorithms.sorts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortUtilsTest {

    @Test
    void isSortedEmptyArray() {
        Double[] emptyArray = {};
        assertTrue(SortUtils.isSorted(emptyArray));
    }

    @Test
    void isSortedWithSingleElement() {
        Double[] singleElementArray = {1.0};
        assertTrue(SortUtils.isSorted(singleElementArray));
    }

    @Test
    void isSortedTrue() {
        Integer[] array = {1, 1, 2, 3, 5, 8, 11};
        assertTrue(SortUtils.isSorted(array));

        Integer[] identicalArray = {1, 1, 1, 1, 1};
        assertTrue(SortUtils.isSorted(identicalArray));

        Double[] doubles = {-15.123, -15.111, 0.0, 0.12, 0.15};
        assertTrue(SortUtils.isSorted(doubles));
    }

    @Test
    void isSortedFalse() {
        Double[] array = {1.0, 3.0, -0.15};
        assertFalse(SortUtils.isSorted(array));

        Integer[] array2 = {14, 15, 16, 1};
        assertFalse(SortUtils.isSorted(array2));

        Integer[] array3 = {5, 4, 3, 2, 1};
        assertFalse(SortUtils.isSorted(array3));
    }
}
