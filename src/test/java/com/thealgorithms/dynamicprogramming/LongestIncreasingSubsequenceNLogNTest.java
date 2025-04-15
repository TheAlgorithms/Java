package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LongestIncreasingSubsequenceNLogNTest {

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(Arguments.of(new int[] {10, 9, 2, 5, 3, 7, 101, 18}, 4), Arguments.of(new int[] {0, 1, 0, 3, 2, 3}, 4), Arguments.of(new int[] {7, 7, 7, 7, 7}, 1), Arguments.of(new int[] {1, 3, 5, 4, 7}, 4), Arguments.of(new int[] {}, 0), Arguments.of(new int[] {10}, 1),
            Arguments.of(new int[] {3, 10, 2, 1, 20}, 3), Arguments.of(new int[] {50, 3, 10, 7, 40, 80}, 4));
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    public void testLengthOfLIS(int[] input, int expected) {
        assertEquals(expected, LongestIncreasingSubsequenceNLogN.lengthOfLIS(input));
    }
}
