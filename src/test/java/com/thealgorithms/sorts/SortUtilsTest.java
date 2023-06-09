package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

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
    void isSortedArrayTrue() {
        Integer[] array = {1, 1, 2, 3, 5, 8, 11};
        assertTrue(SortUtils.isSorted(array));

        Integer[] identicalArray = {1, 1, 1, 1, 1};
        assertTrue(SortUtils.isSorted(identicalArray));

        Double[] doubles = {-15.123, -15.111, 0.0, 0.12, 0.15};
        assertTrue(SortUtils.isSorted(doubles));
    }

    @Test
    void isSortedArrayFalse() {
        Double[] array = {1.0, 3.0, -0.15};
        assertFalse(SortUtils.isSorted(array));

        Integer[] array2 = {14, 15, 16, 1};
        assertFalse(SortUtils.isSorted(array2));

        Integer[] array3 = {5, 4, 3, 2, 1};
        assertFalse(SortUtils.isSorted(array3));
    }

    @Test
    void isSortedListTrue() {
        List<Integer> list = List.of(1, 1, 2, 3, 5, 8, 11);
        assertTrue(SortUtils.isSorted(list));

        List<Integer> identicalList = List.of(1, 1, 1, 1, 1);
        assertTrue(SortUtils.isSorted(identicalList));

        List<Double> doubles = List.of(-15.123, -15.111, 0.0, 0.12, 0.15);
        assertTrue(SortUtils.isSorted(doubles));
    }

    @Test
    void isSortedListFalse() {
        List<Double> list = List.of(1.0, 3.0, -0.15);
        assertFalse(SortUtils.isSorted(list));

        List<Integer> array2 = List.of(14, 15, 16, 1);
        assertFalse(SortUtils.isSorted(array2));

        List<Integer> array3 = List.of(5, 4, 3, 2, 1);
        assertFalse(SortUtils.isSorted(array3));
    }
}
