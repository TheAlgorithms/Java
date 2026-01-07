package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for GCD implementation.
 */
class GCDTest {

    @Test
    @DisplayName("GCD should handle zero and positive number")
    void testZeroAndPositiveReturnsPositive() {
        assertEquals(2, GCD.gcd(0, 2));
        assertEquals(10, GCD.gcd(10, 0));
        assertEquals(1, GCD.gcd(1, 0));
    }

    @Test
    @DisplayName("GCD should work with negative inputs using absolute values")
    void testNegativeInputsHandledCorrectly() {
        assertEquals(1, GCD.gcd(-1, 0));
        assertEquals(2, GCD.gcd(10, -2));
        assertEquals(1, GCD.gcd(-5, -3));
        assertEquals(3, GCD.gcd(-9, 6));
    }

    @Test
    @DisplayName("GCD should return correct result for two positive numbers")
    void testTwoPositiveNumbers() {
        assertEquals(3, GCD.gcd(9, 6));
    }

    @Test
    @DisplayName("GCD should return same number when both inputs are equal")
    void testSameNumbers() {
        assertEquals(7, GCD.gcd(7, 7));
    }

    @Test
    @DisplayName("GCD of two prime numbers should be one")
    void testPrimeNumbersHaveGcdOne() {
        assertEquals(1, GCD.gcd(13, 17));
    }

    @Test
    @DisplayName("GCD should handle multiple arguments")
    void testMultipleArgumentsGcd() {
        assertEquals(6, GCD.gcd(48, 18, 30, 12));
    }

    @Test
    @DisplayName("GCD should handle array input")
    void testArrayInputGcd() {
        assertEquals(3, GCD.gcd(new int[] {9, 6}));
    }

    @Test
    @DisplayName("GCD should handle array with common factor")
    void testArrayWithCommonFactor() {
        assertEquals(
            5,
            GCD.gcd(new int[] {
                2 * 3 * 5 * 7,
                2 * 5 * 5 * 5,
                2 * 5 * 11,
                5 * 5 * 5 * 13
            })
        );
    }

    @Test
    @DisplayName("GCD of empty array should return zero")
    void testEmptyArrayReturnsZero() {
        assertEquals(0, GCD.gcd(new int[] {}));
    }

    @Test
    @DisplayName("GCD of single-element array should return that element")
    void testSingleElementArrayReturnsElement() {
        assertEquals(42, GCD.gcd(new int[] {42}));
    }

    @Test
    @DisplayName("GCD should handle large numbers")
    void testLargeNumbers() {
        assertEquals(12, GCD.gcd(123456, 789012));
    }

    @Test
    @DisplayName("GCD should throw exception for null array")
    void testNullArrayThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> GCD.gcd((int[]) null));
    }
}
