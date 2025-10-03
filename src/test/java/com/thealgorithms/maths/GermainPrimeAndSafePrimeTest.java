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
        return Stream.of(Arguments.of(2, true), Arguments.of(3, true), Arguments.of(5, true), Arguments.of(11, true), Arguments.of(23, true), Arguments.of(293, true), Arguments.of(4, false), Arguments.of(7, false), Arguments.of(9, false), Arguments.of(1, false));
    }

    static Stream<Arguments> provideNumbersForSafePrimes() {
        return Stream.of(Arguments.of(5, true), Arguments.of(7, true), Arguments.of(11, true), Arguments.of(23, true), Arguments.of(1283, true), Arguments.of(4, false), Arguments.of(13, false), Arguments.of(9, false), Arguments.of(1, false));
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
