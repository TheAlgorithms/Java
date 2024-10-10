package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SquareRootBinarySearchTest {

    @Test
    void testPerfectSquare() {
        long input = 16;
        long expected = 4;
        assertEquals(expected, SquareRootBinarySearch.squareRoot(input), "Square root of 16 should be 4");
    }

    @Test
    void testNonPerfectSquare() {
        long input = 15;
        long expected = 3;
        assertEquals(expected, SquareRootBinarySearch.squareRoot(input), "Square root of 15 should be 3");
    }

    @Test
    void testZero() {
        long input = 0;
        long expected = 0;
        assertEquals(expected, SquareRootBinarySearch.squareRoot(input), "Square root of 0 should be 0");
    }

    @Test
    void testOne() {
        long input = 1;
        long expected = 1;
        assertEquals(expected, SquareRootBinarySearch.squareRoot(input), "Square root of 1 should be 1");
    }

    @Test
    void testLargeNumberPerfectSquare() {
        long input = 1000000;
        long expected = 1000;
        assertEquals(expected, SquareRootBinarySearch.squareRoot(input), "Square root of 1000000 should be 1000");
    }

    @Test
    void testLargeNumberNonPerfectSquare() {
        long input = 999999;
        long expected = 999;
        assertEquals(expected, SquareRootBinarySearch.squareRoot(input), "Square root of 999999 should be 999");
    }

    @Test
    void testNegativeInput() {
        long input = -4;
        long expected = 0; // Assuming the implementation should return 0 for negative input
        assertEquals(expected, SquareRootBinarySearch.squareRoot(input), "Square root of negative number should return 0");
    }
}
