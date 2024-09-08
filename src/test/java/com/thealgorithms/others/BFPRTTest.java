package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BFPRTTest {

    @ParameterizedTest
    @MethodSource("minKNumsTestData")
    void testGetMinKNumsByBFPRT(int[] arr, int k, int[] expected) {
        int[] result = BFPRT.getMinKNumsByBFPRT(arr, k);
        assertArrayEquals(expected, result);
    }

    private static Stream<Arguments> minKNumsTestData() {
        return Stream.of(Arguments.of(new int[] {11, 9, 1, 3, 9, 2, 2, 5, 6, 5, 3, 5, 9, 7, 2, 5, 5, 1, 9}, 5, new int[] {1, 1, 2, 2, 2}), Arguments.of(new int[] {3, 2, 1}, 2, new int[] {1, 2}), Arguments.of(new int[] {7, 5, 9, 1, 3, 8, 2, 4, 6}, 3, new int[] {1, 2, 3}));
    }

    @ParameterizedTest
    @MethodSource("minKthTestData")
    void testGetMinKthByBFPRT(int[] arr, int k, int expected) {
        int result = BFPRT.getMinKthByBFPRT(arr, k);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> minKthTestData() {
        return Stream.of(Arguments.of(new int[] {3, 2, 1}, 2, 2), Arguments.of(new int[] {7, 5, 9, 1, 3, 8, 2, 4, 6}, 3, 3), Arguments.of(new int[] {5, 8, 6, 3, 2, 7, 1, 4}, 4, 4));
    }
}
