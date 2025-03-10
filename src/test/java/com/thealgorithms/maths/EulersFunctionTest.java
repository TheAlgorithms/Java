package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class EulersFunctionTest {

    @ParameterizedTest
    @MethodSource("provideNumbersForGetEuler")
    void testGetEuler(int input, int expected) {
        assertEquals(expected, EulersFunction.getEuler(input));
    }

    @ParameterizedTest
    @MethodSource("provideInvalidNumbersForGetEuler")
    void testGetEulerThrowsExceptionForNonPositiveInput(int input) {
        assertThrows(IllegalArgumentException.class, () -> EulersFunction.getEuler(input));
    }

    private static Stream<Arguments> provideNumbersForGetEuler() {
        return Stream.of(Arguments.of(1, 1), Arguments.of(2, 1), Arguments.of(3, 2), Arguments.of(4, 2), Arguments.of(5, 4), Arguments.of(6, 2), Arguments.of(10, 4), Arguments.of(21, 12), Arguments.of(69, 44), Arguments.of(47, 46), Arguments.of(46, 22), Arguments.of(55, 40), Arguments.of(34, 16),
            Arguments.of(20, 8), Arguments.of(1024, 512));
    }

    private static Stream<Arguments> provideInvalidNumbersForGetEuler() {
        return Stream.of(Arguments.of(0), Arguments.of(-1), Arguments.of(-10));
    }
}
