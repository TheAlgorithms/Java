package com.thealgorithms.datastructures.heaps;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MergeKSortedArraysTest {

    @ParameterizedTest
    @MethodSource("provideTestCases")
    public void testMergeKArrays(int[][] arrays, int[] expected) {
        assertArrayEquals(expected, MergeKSortedArrays.mergeKArrays(arrays));
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(
            // Basic test case with multiple arrays
            Arguments.of(new int[][] {{1, 4, 5}, {1, 3, 4}, {2, 6}}, new int[] {1, 1, 2, 3, 4, 4, 5, 6}),

            // Edge case: All arrays are empty
            Arguments.of(new int[][] {{}, {}, {}}, new int[] {}),

            // Edge case: One array is empty
            Arguments.of(new int[][] {{1, 3, 5}, {}, {2, 4, 6}}, new int[] {1, 2, 3, 4, 5, 6}),

            // Single array
            Arguments.of(new int[][] {{1, 2, 3}}, new int[] {1, 2, 3}),

            // Arrays with negative numbers
            Arguments.of(new int[][] {{-5, 1, 3}, {-10, 0, 2}}, new int[] {-10, -5, 0, 1, 2, 3}),

            // Arrays with duplicate elements
            Arguments.of(new int[][] {{1, 1, 2}, {1, 3, 3}, {2, 2, 4}}, new int[] {1, 1, 1, 2, 2, 2, 3, 3, 4}),

            // Edge case: Arrays of varying lengths
            Arguments.of(new int[][] {{1, 2}, {3}, {4, 5, 6, 7}}, new int[] {1, 2, 3, 4, 5, 6, 7}));
    }
}
