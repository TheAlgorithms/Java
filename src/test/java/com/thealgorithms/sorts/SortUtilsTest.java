package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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

    @ParameterizedTest
    @MethodSource("provideArraysForSwap")
    public <T> void testSwap(T[] array, int i, int j, T[] expected) {
        SortUtils.swap(array, i, j);
        assertArrayEquals(expected, array);
    }

    @ParameterizedTest
    @MethodSource("provideArraysForSwap")
    public <T> void testSwapFlippedIndices(T[] array, int i, int j, T[] expected) {
        SortUtils.swap(array, j, i);
        assertArrayEquals(expected, array);
    }

    private static Stream<Arguments> provideArraysForSwap() {
        return Stream.of(Arguments.of(new Integer[] {1, 2, 3, 4}, 1, 2, new Integer[] {1, 3, 2, 4}), Arguments.of(new Integer[] {1, 2, 3, 4}, 0, 3, new Integer[] {4, 2, 3, 1}), Arguments.of(new Integer[] {1, 2, 3, 4}, 2, 2, new Integer[] {1, 2, 3, 4}),
            Arguments.of(new String[] {"a", "b", "c", "d"}, 0, 3, new String[] {"d", "b", "c", "a"}), Arguments.of(new String[] {null, "b", "c", null}, 0, 3, new String[] {null, "b", "c", null}));
    }
}
