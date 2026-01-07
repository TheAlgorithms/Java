package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for FibonacciNumberCheck.
 *
 * Fibonacci sequence:
 * 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144...
 *
 * @author Albina
 */
class FibonacciNumberCheckTest {

    @Test
    @DisplayName("Should return true for Fibonacci numbers")
    void testNumberIsFibonacciNumber() {
        assertTrue(FibonacciNumberCheck.isFibonacciNumber(0));
        assertTrue(FibonacciNumberCheck.isFibonacciNumber(1));
        assertTrue(FibonacciNumberCheck.isFibonacciNumber(2));
        assertTrue(FibonacciNumberCheck.isFibonacciNumber(21));
        assertTrue(FibonacciNumberCheck.isFibonacciNumber(6765));      // 20th number
        assertTrue(FibonacciNumberCheck.isFibonacciNumber(832040));    // 30th number
        assertTrue(FibonacciNumberCheck.isFibonacciNumber(102334155)); // 40th number
        assertTrue(FibonacciNumberCheck.isFibonacciNumber(701408733)); // 45th number
    }

    @Test
    @DisplayName("Should return false for non-Fibonacci numbers")
    void testNumberIsNotFibonacciNumber() {
        assertFalse(FibonacciNumberCheck.isFibonacciNumber(9));
        assertFalse(FibonacciNumberCheck.isFibonacciNumber(10));
        assertFalse(FibonacciNumberCheck.isFibonacciNumber(145));
        assertFalse(FibonacciNumberCheck.isFibonacciNumber(701408734));
    }

    @Test
    @DisplayName("Should return false for negative numbers")
    void testNegativeNumbersAreNotFibonacci() {
        assertFalse(FibonacciNumberCheck.isFibonacciNumber(-1));
        assertFalse(FibonacciNumberCheck.isFibonacciNumber(-5));
        assertFalse(FibonacciNumberCheck.isFibonacciNumber(Integer.MIN_VALUE));
    }
}
