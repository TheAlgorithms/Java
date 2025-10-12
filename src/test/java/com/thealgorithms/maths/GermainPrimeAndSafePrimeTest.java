package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GermainPrimeAndSafePrimeTest {

    static Stream<Arguments> provideNumbersForGermainPrimes() {
        return Stream.of(Arguments.of(2, Boolean.TRUE), Arguments.of(3, Boolean.TRUE), Arguments.of(5, Boolean.TRUE), Arguments.of(11, Boolean.TRUE), Arguments.of(23, Boolean.TRUE), Arguments.of(293, Boolean.TRUE), Arguments.of(4, Boolean.FALSE), Arguments.of(7, Boolean.FALSE),
            Arguments.of(9, Boolean.FALSE), Arguments.of(1, Boolean.FALSE));
    }

    static Stream<Arguments> provideNumbersForSafePrimes() {
        return Stream.of(Arguments.of(5, Boolean.TRUE), Arguments.of(7, Boolean.TRUE), Arguments.of(11, Boolean.TRUE), Arguments.of(23, Boolean.TRUE), Arguments.of(1283, Boolean.TRUE), Arguments.of(4, Boolean.FALSE), Arguments.of(13, Boolean.FALSE), Arguments.of(9, Boolean.FALSE),
            Arguments.of(1, Boolean.FALSE));
    }

    static Stream<Integer> provideNegativeNumbers() {
        return Stream.of(-10, -1, 0);
    }

    @ParameterizedTest
    @MethodSource("provideNumbersForGermainPrimes")
    @DisplayName("Check whether a number is a Germain prime")
    void testValidGermainPrimes(int number, boolean expected) {
        assertEquals(expected, GermainPrimeAndSafePrime.isGermainPrime(number));
    }

    @ParameterizedTest
    @MethodSource("provideNumbersForSafePrimes")
    @DisplayName("Check whether a number is a Safe prime")
    void testValidSafePrimes(int number, boolean expected) {
        assertEquals(expected, GermainPrimeAndSafePrime.isSafePrime(number));
    }

    @ParameterizedTest
    @MethodSource("provideNegativeNumbers")
    @DisplayName("Negative numbers and zero should throw IllegalArgumentException")
    void testNegativeNumbersThrowException(int number) {
        assertThrows(IllegalArgumentException.class, () -> GermainPrimeAndSafePrime.isGermainPrime(number));
        assertThrows(IllegalArgumentException.class, () -> GermainPrimeAndSafePrime.isSafePrime(number));
    }
}
