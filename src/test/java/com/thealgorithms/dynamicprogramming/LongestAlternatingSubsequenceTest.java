package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LongestAlternatingSubsequenceTest {

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testAlternatingLength(int[] arr, int expected) {
        assertEquals(expected, LongestAlternatingSubsequence.alternatingLength(arr, arr.length));
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(Arguments.of(new int[] {1}, 1), Arguments.of(new int[] {1, 2}, 2), Arguments.of(new int[] {2, 1}, 2), Arguments.of(new int[] {1, 3, 2, 4, 3, 5}, 6), Arguments.of(new int[] {1, 2, 3, 4, 5}, 2), Arguments.of(new int[] {5, 4, 3, 2, 1}, 2),
            Arguments.of(new int[] {10, 22, 9, 33, 49, 50, 31, 60}, 6), Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 2));
    }
}
