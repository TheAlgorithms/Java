package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LeastCommonMultipleTest {

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testLcm(int num1, int num2, int expected) {
        assertEquals(expected, LeastCommonMultiple.lcm(num1, num2));
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(Arguments.of(12, 18, 36), Arguments.of(5, 10, 10), Arguments.of(7, 3, 21), Arguments.of(21, 6, 42), Arguments.of(1, 1, 1), Arguments.of(8, 12, 24), Arguments.of(14, 35, 70), Arguments.of(15, 25, 75), Arguments.of(100, 25, 100), Arguments.of(0, 10, 0));
    }
}
