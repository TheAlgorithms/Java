package com.thealgorithms.stacks;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class StockSpanProblemTest {

    @ParameterizedTest
    @MethodSource("validTestCases")
    void testCalculateSpan(int[] prices, int[] expectedSpans) {
        assertArrayEquals(expectedSpans, StockSpanProblem.calculateSpan(prices));
    }

    private static Stream<Arguments> validTestCases() {
        return Stream.of(Arguments.of(new int[] {10, 4, 5, 90, 120, 80}, new int[] {1, 1, 2, 4, 5, 1}), Arguments.of(new int[] {100, 50, 60, 70, 80, 90}, new int[] {1, 1, 2, 3, 4, 5}), Arguments.of(new int[] {5, 4, 3, 2, 1}, new int[] {1, 1, 1, 1, 1}),
            Arguments.of(new int[] {1, 2, 3, 4, 5}, new int[] {1, 2, 3, 4, 5}), Arguments.of(new int[] {10, 20, 30, 40, 50}, new int[] {1, 2, 3, 4, 5}), Arguments.of(new int[] {100, 80, 60, 70, 60, 75, 85}, new int[] {1, 1, 1, 2, 1, 4, 6}),
            Arguments.of(new int[] {7, 7, 7, 7}, new int[] {1, 2, 3, 4}), Arguments.of(new int[] {}, new int[] {}), Arguments.of(new int[] {42}, new int[] {1}));
    }

    @ParameterizedTest
    @MethodSource("invalidTestCases")
    void testCalculateSpanInvalidInput(int[] prices) {
        assertThrows(IllegalArgumentException.class, () -> StockSpanProblem.calculateSpan(prices));
    }

    private static Stream<Arguments> invalidTestCases() {
        return Stream.of(Arguments.of((int[]) null));
    }
}
