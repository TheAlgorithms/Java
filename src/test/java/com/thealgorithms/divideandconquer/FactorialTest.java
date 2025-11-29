package com.thealgorithms.divideandconquer;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class FactorialTest {

    // --------------------------------------------------------
    // SECTION 1: Basic Correctness Tests
    // --------------------------------------------------------

    @Test
    void testFactorialOfFive() {
        assertEquals(120, Factorial.factorial(5));
    }

    @Test
    void testFactorialOfZero() {
        assertEquals(1, Factorial.factorial(0));
    }

    @Test
    void testNegativeInputThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> Factorial.factorial(-5));
    }

    // --------------------------------------------------------
    // SECTION 2: Analysis-Oriented Test (Performance Awareness)
    // --------------------------------------------------------

    @Test
    void testLargeInputPerformance() {
        long start = System.currentTimeMillis();
        long result = Factorial.factorial(15);
        long end = System.currentTimeMillis();

        assertEquals(1307674368000L, result);
        assertTrue((end - start) < 50, "Factorial(15) took too long to compute");
    }

    // --------------------------------------------------------
    // SECTION 3: Algorithmic Improvement Demonstration
    // --------------------------------------------------------

    /**
     * Local copy of the original recursive implementation used only for comparing performance inside
     * the test.
     */
    private long recursiveFactorial(long n) {
        if (n < 0) {
            throw new IllegalArgumentException("Negative input not allowed");
        }
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * recursiveFactorial(n - 1);
        }
    }

    @Test
    void testIterativeFasterThanRecursive() {
        long n = 18;

        long startRec = System.nanoTime();
        long recResult = recursiveFactorial(n);
        long endRec = System.nanoTime();

        long startIter = System.nanoTime();
        long iterResult = Factorial.factorial(n);
        long endIter = System.nanoTime();

        assertEquals(recResult, iterResult);
        assertTrue(
                endIter - startIter < endRec - startRec,
                "Iterative version should outperform recursive version");
    }

    @Test
    void testIterativeHandlesLargerInputsSafely() {
        assertDoesNotThrow(() -> Factorial.factorial(20));
    }
}
