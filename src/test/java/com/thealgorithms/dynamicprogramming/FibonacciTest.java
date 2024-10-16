package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FibonacciTest {

    @BeforeEach
    void setUp() {
        // Clear the cache before each test to avoid interference
        Fibonacci.CACHE.clear();
    }

    @Test
    void testFibMemo() {
        // Test memoization method
        assertEquals(0, Fibonacci.fibMemo(0));
        assertEquals(1, Fibonacci.fibMemo(1));
        assertEquals(1, Fibonacci.fibMemo(2));
        assertEquals(2, Fibonacci.fibMemo(3));
        assertEquals(3, Fibonacci.fibMemo(4));
        assertEquals(5, Fibonacci.fibMemo(5));
        assertEquals(8, Fibonacci.fibMemo(6));
        assertEquals(13, Fibonacci.fibMemo(7));
        assertEquals(21, Fibonacci.fibMemo(8));
        assertEquals(34, Fibonacci.fibMemo(9));
        assertEquals(55, Fibonacci.fibMemo(10));
    }

    @Test
    void testFibBotUp() {
        // Test bottom-up method
        assertEquals(0, Fibonacci.fibBotUp(0));
        assertEquals(1, Fibonacci.fibBotUp(1));
        assertEquals(1, Fibonacci.fibBotUp(2));
        assertEquals(2, Fibonacci.fibBotUp(3));
        assertEquals(3, Fibonacci.fibBotUp(4));
        assertEquals(5, Fibonacci.fibBotUp(5));
        assertEquals(8, Fibonacci.fibBotUp(6));
        assertEquals(13, Fibonacci.fibBotUp(7));
        assertEquals(21, Fibonacci.fibBotUp(8));
        assertEquals(34, Fibonacci.fibBotUp(9));
        assertEquals(55, Fibonacci.fibBotUp(10));
    }

    @Test
    void testFibOptimized() {
        // Test optimized Fibonacci method
        assertEquals(0, Fibonacci.fibOptimized(0));
        assertEquals(1, Fibonacci.fibOptimized(1));
        assertEquals(1, Fibonacci.fibOptimized(2));
        assertEquals(2, Fibonacci.fibOptimized(3));
        assertEquals(3, Fibonacci.fibOptimized(4));
        assertEquals(5, Fibonacci.fibOptimized(5));
        assertEquals(8, Fibonacci.fibOptimized(6));
        assertEquals(13, Fibonacci.fibOptimized(7));
        assertEquals(21, Fibonacci.fibOptimized(8));
        assertEquals(34, Fibonacci.fibOptimized(9));
        assertEquals(55, Fibonacci.fibOptimized(10));
    }

    @Test
    void testFibBinet() {
        // Test Binet's formula method
        assertEquals(0, Fibonacci.fibBinet(0));
        assertEquals(1, Fibonacci.fibBinet(1));
        assertEquals(1, Fibonacci.fibBinet(2));
        assertEquals(2, Fibonacci.fibBinet(3));
        assertEquals(3, Fibonacci.fibBinet(4));
        assertEquals(5, Fibonacci.fibBinet(5));
        assertEquals(8, Fibonacci.fibBinet(6));
        assertEquals(13, Fibonacci.fibBinet(7));
        assertEquals(21, Fibonacci.fibBinet(8));
        assertEquals(34, Fibonacci.fibBinet(9));
        assertEquals(55, Fibonacci.fibBinet(10));
    }

    @Test
    void testNegativeInput() {
        // Test negative input; Fibonacci is not defined for negative numbers
        assertThrows(IllegalArgumentException.class, () -> { Fibonacci.fibMemo(-1); });
        assertThrows(IllegalArgumentException.class, () -> { Fibonacci.fibBotUp(-1); });
        assertThrows(IllegalArgumentException.class, () -> { Fibonacci.fibOptimized(-1); });
        assertThrows(IllegalArgumentException.class, () -> { Fibonacci.fibBinet(-1); });
    }
}
