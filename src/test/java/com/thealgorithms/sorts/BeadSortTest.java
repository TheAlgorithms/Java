package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BeadSortTest {
    @ParameterizedTest
    @MethodSource("provideArraysForBeadSort")
    public void testBeadSort(int[] inputArray, int[] expectedArray) {
        BeadSort beadSort = new BeadSort();
        assertArrayEquals(expectedArray, beadSort.sort(inputArray));
    }

    private static Stream<Arguments> provideArraysForBeadSort() {
        return Stream.of(Arguments.of(new int[] {}, new int[] {}), Arguments.of(new int[] {4}, new int[] {4}), Arguments.of(new int[] {6, 1, 99, 27, 15, 23, 36}, new int[] {1, 6, 15, 23, 27, 36, 99}), Arguments.of(new int[] {6, 1, 27, 15, 23, 27, 36, 23}, new int[] {1, 6, 15, 23, 23, 27, 27, 36}),
            Arguments.of(new int[] {5, 5, 5, 5, 5}, new int[] {5, 5, 5, 5, 5}), Arguments.of(new int[] {1, 2, 3, 4, 5}, new int[] {1, 2, 3, 4, 5}), Arguments.of(new int[] {5, 4, 3, 2, 1}, new int[] {1, 2, 3, 4, 5}));
    }

    @Test
    public void testWithNegativeNumbers() {
        assertThrows(IllegalArgumentException.class, () -> new BeadSort().sort(new int[] {3, 1, 4, 1, 5, -9}));
    }
}
