package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TrappedRainWaterTest {
    @ParameterizedTest
    @MethodSource("testCases")
    void testTrappedRainWater(int[] heights, int expected) {
        assertEquals(expected, TrappedRainWater.trap(heights));
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
            Arguments.of(new int[] {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}, 6), Arguments.of(new int[] {0, 2, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}, 7), Arguments.of(new int[] {4, 2, 0, 3, 2, 5}, 9), Arguments.of(new int[] {3, 2, 1, 0, 1, 2, 3}, 9), Arguments.of(new int[] {5, 4, 3, 2, 1, 2, 3, 4, 5}, 16));
    }
}
