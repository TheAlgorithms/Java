package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class LongDivisionTest {

    // Requirement: Dividend (positive) is  greater than divisor (positive), returns correct integer
    // after division
    @Test
    void testOne() {
        assertEquals(3, LongDivision.divide(10, 3));
    }

    // Requirement: Dividend (positive) is  greater than divisor (negative), returns correct integer
    // after division
    @Test
    void testTwo() {
        assertEquals(-2, LongDivision.divide(7, -3));
    }

    // Requirement: Dividend (positive) is  greater than divisor (negative), returns correct integer
    // after division Basically the same as in the first test
    @Test
    void testThree() {
        assertEquals(10, LongDivision.divide(105, 10));
    }

    // Requirement: Dividend (negative), divisor (positive), returns correct integer after division
    // Tests the case where the dividend is less than 0.
    @Test
    void testNegativeDividend() {
        assertEquals(-1, LongDivision.divide(-5, 3));
    }

    // Requirement: Dividend (positive), divisor (positive), returns correct integer after division
    // Tests the case where the dividend is less than the divisor. The test should return 0 in this
    // case.
    @Test
    void testDividendLessThanDivisor() {
        assertEquals(0, LongDivision.divide(3, 5));
    }

    // Requirement: Dividend (neither), divisor (positive), returns correct integer after division
    // Tests the case where the dividend is 0. This should return a 0.
    @Test
    void testDividendIsZero() {
        assertEquals(0, LongDivision.divide(0, 5));
    }

    // Requirement: Dividend (positive), divisor (neither), returns correct integer after division
    // Tests the case where the divisor is 0. This should return a 0.
    @Test
    void testDivisionByZero() {
        assertEquals(0, LongDivision.divide(5, 0));
    }
}
