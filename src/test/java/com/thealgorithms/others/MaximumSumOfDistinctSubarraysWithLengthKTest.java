package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MaximumSumOfDistinctSubarraysWithLengthKTest {

    @ParameterizedTest
    @MethodSource("inputStream")
    void testMaximumSubarraySum(int expected, int k, int[] arr) {
        assertEquals(expected, MaximumSumOfDistinctSubarraysWithLengthK.maximumSubarraySum(k, arr));
    }

    private static Stream<Arguments> inputStream() {
        return Stream.of(Arguments.of(15, 3, new int[] {1, 5, 4, 2, 9, 9, 9}), Arguments.of(0, 3, new int[] {4, 4, 4}), Arguments.of(12, 3, new int[] {9, 9, 9, 1, 2, 3}), Arguments.of(0, 0, new int[] {9, 9, 9}), Arguments.of(0, 5, new int[] {9, 9, 9}), Arguments.of(9, 1, new int[] {9, 2, 3, 7}),
            Arguments.of(15, 5, new int[] {1, 2, 3, 4, 5}), Arguments.of(6, 3, new int[] {-1, 2, 3, 1, -2, 4}), Arguments.of(10, 1, new int[] {10}), Arguments.of(0, 2, new int[] {7, 7, 7, 7}), Arguments.of(0, 3, new int[] {}), Arguments.of(0, 10, new int[] {1, 2, 3}));
    }
}
