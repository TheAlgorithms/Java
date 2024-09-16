package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BoardPathTest {

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testBpR(int start, int end, int expected) {
        assertEquals(expected, BoardPath.bpR(start, end));
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testBpRS(int start, int end, int expected) {
        assertEquals(expected, BoardPath.bpRS(start, end, new int[end + 1]));
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testBpIS(int start, int end, int expected) {
        assertEquals(expected, BoardPath.bpIS(start, end, new int[end + 1]));
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(Arguments.of(0, 10, 492), Arguments.of(0, 5, 16), Arguments.of(0, 6, 32), Arguments.of(0, 3, 4), Arguments.of(0, 1, 1));
    }
}
