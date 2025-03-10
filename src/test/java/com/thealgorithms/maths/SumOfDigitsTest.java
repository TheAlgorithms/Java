package com.thealgorithms.maths;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SumOfDigitsTest {
    @ParameterizedTest
    @MethodSource("testCases")
    void sumOfDigitsTest(final int expected, final int input) {
        Assertions.assertEquals(expected, SumOfDigits.sumOfDigits(input));
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void sumOfDigitsRecursionTest(final int expected, final int input) {
        Assertions.assertEquals(expected, SumOfDigits.sumOfDigitsRecursion(input));
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void sumOfDigitsFastTest(final int expected, final int input) {
        Assertions.assertEquals(expected, SumOfDigits.sumOfDigitsFast(input));
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(Arguments.of(0, 0), Arguments.of(1, 1), Arguments.of(15, 12345), Arguments.of(6, -123), Arguments.of(1, -100000), Arguments.of(8, 512));
    }
}
