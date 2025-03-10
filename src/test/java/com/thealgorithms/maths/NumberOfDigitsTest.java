package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.IntFunction;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class NumberOfDigitsTest {

    @ParameterizedTest
    @MethodSource("testCases")
    void testNumberOfDigits(final int expected, final int number, final IntFunction<Integer> methodUnderTest) {
        assertEquals(expected, methodUnderTest.apply(number));
        assertEquals(expected, methodUnderTest.apply(-number));
    }

    private static Stream<Arguments> testCases() {
        final Integer[][] inputs = new Integer[][] {
            {3, 100},
            {1, 0},
            {2, 12},
            {3, 123},
            {4, 1234},
            {5, 12345},
            {6, 123456},
            {7, 1234567},
            {8, 12345678},
            {9, 123456789},
            {9, 987654321},
        };

        final IntFunction<Integer>[] methods = new IntFunction[] {NumberOfDigits::numberOfDigits, NumberOfDigits::numberOfDigitsFast, NumberOfDigits::numberOfDigitsFaster, NumberOfDigits::numberOfDigitsRecursion};

        return Stream.of(inputs).flatMap(input -> Stream.of(methods).map(method -> Arguments.of(input[0], input[1], method)));
    }
}
