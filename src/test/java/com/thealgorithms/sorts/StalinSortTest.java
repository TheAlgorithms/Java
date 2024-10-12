package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class StalinSortTest {

    @ParameterizedTest
    @MethodSource("provideArraysForStalinSort")
    public void testStalinSort(Comparable[] inputArray, Comparable[] expectedArray) {
        StalinSort stalinSort = new StalinSort();
        // Explicitly call the sort method that accepts arrays
        assertArrayEquals(expectedArray, stalinSort.sort((Comparable[]) inputArray));
    }

    private static Stream<Arguments> provideArraysForStalinSort() {
        return Stream.of(
            Arguments.of(new Integer[] {4}, new Integer[] {4}),
            Arguments.of(new Integer[] {4, 23, 6, 78, 1, 54, 231, 9, 12}, new Integer[] {4, 23, 78, 231}),
            Arguments.of(new Integer[] {5, 5, 5, 5, 5}, new Integer[] {5, 5, 5, 5, 5}),
            Arguments.of(new Integer[] {1, 2, 3, 4, 5}, new Integer[] {1, 2, 3, 4, 5}),
            Arguments.of(new Integer[] {5, 4, 3, 2, 1}, new Integer[] {5}),
            Arguments.of(new String[] {"c", "a", "e", "b", "d"}, new String[] {"c", "e"}),
            Arguments.of(new Integer[] {-1, -2, -3, 4, 5}, new Integer[] {-1, 4, 5}),
            Arguments.of(new Integer[] {0, -1, -2, 1, 2}, new Integer[] {0, 1, 2}),
            Arguments.of(new Integer[] {3, -1, 0, -5, 2}, new Integer[] {3}),
            Arguments.of(new Integer[] {10, 10, 10, 10}, new Integer[] {10, 10, 10, 10}),
            Arguments.of(new Integer[] {1, 2, 2, 3, 3, 3, 4}, new Integer[] {1, 2, 2, 3, 3, 3, 4}),
            Arguments.of(new Integer[] {Integer.MAX_VALUE, Integer.MIN_VALUE, 0}, new Integer[] {Integer.MAX_VALUE}),
            Arguments.of(new Character[] {'d', 'a', 'c', 'b'}, new Character[] {'d'}),
            Arguments.of(new Integer[] {100, 200, 150, 300, 250, 400, 350, 450, 500}, new Integer[] {100, 200, 300, 400, 500})
        );
    }

    @Test
    public void testWithEmptyArray() {
        StalinSort stalinSort = new StalinSort();
        Integer[] inputArray = {};
        Integer[] expectedArray = {};
        assertArrayEquals(expectedArray, stalinSort.sort(inputArray));
    }

    @Test
    public void testWithNullValues() {
        assertThrows(NullPointerException.class, () -> {
            StalinSort stalinSort = new StalinSort();
            stalinSort.sort(null);
        });
    }

    @Test
    public void testWithMixedDataTypes() {
        assertThrows(ClassCastException.class, () -> {
            StalinSort stalinSort = new StalinSort();
            stalinSort.sort(new Comparable[] {"a", 1});
        });
    }
}
