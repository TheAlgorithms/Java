package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LongestArithmeticSubsequenceTest {
    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testGetLongestArithmeticSubsequenceLength(int[] nums, int expected) {
        assertEquals(expected, LongestArithmeticSubsequence.getLongestArithmeticSubsequenceLength(nums));
    }
    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testGetLongestArithmeticSubsequenceLengthReversedInput(int[] nums, int expected) {
        ArrayUtils.reverse(nums);
        assertEquals(expected, LongestArithmeticSubsequence.getLongestArithmeticSubsequenceLength(nums));
    }

    @Test
    void testGetLongestArithmeticSubsequenceLengthThrowsForNullInput() {
        assertThrows(IllegalArgumentException.class, () -> LongestArithmeticSubsequence.getLongestArithmeticSubsequenceLength(null));
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(Arguments.of(new int[] {3, 6, 9, 12, 15}, 5), Arguments.of(new int[] {1, 7, 10, 13, 14, 19}, 4), Arguments.of(new int[] {1, 2, 3, 4}, 4), Arguments.of(new int[] {}, 0), Arguments.of(new int[] {10}, 1), Arguments.of(new int[] {9, 4, 7, 2, 10}, 3),
            Arguments.of(new int[] {1, 2, 2, 2, 2, 5}, 4));
    }
}
