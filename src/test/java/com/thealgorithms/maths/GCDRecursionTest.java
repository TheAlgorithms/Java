package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class GCDRecursionTest {

    @ParameterizedTest
    @CsvSource({"7, 5, 1", "9, 12, 3", "18, 24, 6", "36, 60, 12"})
    void testGcdPositiveNumbers(int a, int b, int expectedGcd) {
        assertEquals(expectedGcd, GCDRecursion.gcd(a, b));
    }

    @ParameterizedTest
    @CsvSource({"0, 5, 5", "8, 0, 8"})
    void testGcdOneZero(int a, int b, int expectedGcd) {
        assertEquals(expectedGcd, GCDRecursion.gcd(a, b));
    }

    @Test
    void testGcdBothZero() {
        assertEquals(0, GCDRecursion.gcd(0, 0));
    }

    @ParameterizedTest
    @ValueSource(ints = {-5, -15})
    void testGcdNegativeNumbers(int negativeValue) {
        assertThrows(ArithmeticException.class, () -> GCDRecursion.gcd(negativeValue, 15));
        assertThrows(ArithmeticException.class, () -> GCDRecursion.gcd(15, negativeValue));
    }

    @ParameterizedTest
    @CsvSource({"5, 5, 5", "8, 8, 8"})
    void testGcdWithSameNumbers(int a, int b, int expectedGcd) {
        assertEquals(expectedGcd, GCDRecursion.gcd(a, b));
    }

    @ParameterizedTest
    @CsvSource({"7, 13, 1", "11, 17, 1"})
    void testGcdWithPrimeNumbers(int a, int b, int expectedGcd) {
        assertEquals(expectedGcd, GCDRecursion.gcd(a, b));
    }
}
