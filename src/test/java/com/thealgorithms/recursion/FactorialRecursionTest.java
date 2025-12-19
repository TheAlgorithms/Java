package com.thealgorithms.recursion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class FactorialRecursionTest {

    @Test
    void testFactorialOfZero() {
        assertEquals(1, FactorialRecursion.factorial(0));
    }

    @Test
    void testFactorialOfOne() {
        assertEquals(1, FactorialRecursion.factorial(1));
    }

    @Test
    void testFactorialOfPositiveNumber() {
        assertEquals(120, FactorialRecursion.factorial(5));
    }

    @Test
    void testFactorialOfLargerNumber() {
        assertEquals(3628800, FactorialRecursion.factorial(10));
    }

    @Test
    void testFactorialOfNegativeNumber() {
        assertThrows(IllegalArgumentException.class, () -> FactorialRecursion.factorial(-1));
    }
}
