package com.thealgorithms.maths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GCDTest {

    @Test
    void testNegativeAndZeroThrowsException() {
        Assertions.assertThrows(ArithmeticException.class, () -> GCD.gcd(-1, 0));
    }

    @Test
    void testPositiveAndNegativeThrowsException() {
        Assertions.assertThrows(ArithmeticException.class, () -> GCD.gcd(10, -2));
    }

    @Test
    void testBothNegativeThrowsException() {
        Assertions.assertThrows(ArithmeticException.class, () -> GCD.gcd(-5, -3));
    }

    @Test
    void testZeroAndPositiveReturnsPositive() {
        Assertions.assertEquals(2, GCD.gcd(0, 2));
    }

    @Test
    void testPositiveAndZeroReturnsPositive() {
        Assertions.assertEquals(10, GCD.gcd(10, 0));
    }

    @Test
    void testOneAndZeroReturnsOne() {
        Assertions.assertEquals(1, GCD.gcd(1, 0));
    }

    @Test
    void testTwoPositiveNumbers() {
        Assertions.assertEquals(3, GCD.gcd(9, 6));
    }

    @Test
    void testMultipleArgumentsGcd() {
        Assertions.assertEquals(6, GCD.gcd(48, 18, 30, 12));
    }

    @Test
    void testArrayInputGcd() {
        Assertions.assertEquals(3, GCD.gcd(new int[] {9, 6}));
    }

    @Test
    void testArrayWithCommonFactor() {
        Assertions.assertEquals(5, GCD.gcd(new int[] {2 * 3 * 5 * 7, 2 * 5 * 5 * 5, 2 * 5 * 11, 5 * 5 * 5 * 13}));
    }

    @Test
    void testEmptyArrayReturnsZero() {
        Assertions.assertEquals(0, GCD.gcd(new int[] {}));
    }

    @Test
    void testSameNumbers() {
        Assertions.assertEquals(7, GCD.gcd(7, 7));
    }

    @Test
    void testPrimeNumbersHaveGcdOne() {
        Assertions.assertEquals(1, GCD.gcd(13, 17));
    }

    @Test
    void testSingleElementArrayReturnsElement() {
        Assertions.assertEquals(42, GCD.gcd(new int[] {42}));
    }

    @Test
    void testLargeNumbers() {
        Assertions.assertEquals(12, GCD.gcd(123456, 789012));
    }
}
