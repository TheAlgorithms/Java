package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UniformNumbersTest {

    @Test
    void testSingleUniformDigitRange() {
        assertEquals(1, UniformNumbers.countUniformIntegers(1, 1)); // Only one number, which is uniform
        assertEquals(9, UniformNumbers.countUniformIntegers(1, 9)); // All single-digit numbers are uniform
    }

    @Test
    void testSmallRange() {
        assertEquals(2, UniformNumbers.countUniformIntegers(10, 11)); // Only uniform number is 11
        assertEquals(4, UniformNumbers.countUniformIntegers(22, 33)); // Uniform numbers are 22, 33
    }

    @Test
    void testRangeWithNoUniformNumbers() {
        assertEquals(0, UniformNumbers.countUniformIntegers(10, 21)); // No uniform numbers between 10 and 21
        assertEquals(0, UniformNumbers.countUniformIntegers(123, 128)); // No uniform numbers between 123 and 128
    }

    @Test
    void testRangeWithAllUniformNumbers() {
        assertEquals(9, UniformNumbers.countUniformIntegers(1, 9)); // All are uniform numbers
        assertEquals(18, UniformNumbers.countUniformIntegers(1, 99)); // 1-9 and 11, 22, ..., 99
    }

    @Test
    void testMultiDigitRangeWithUniformNumbers() {
        assertEquals(2, UniformNumbers.countUniformIntegers(100, 111)); // Only 111 is uniform
        assertEquals(4, UniformNumbers.countUniformIntegers(111, 222)); // Uniform numbers are 111, 222
    }

    @Test
    void testExactUniformBoundary() {
        assertEquals(1, UniformNumbers.countUniformIntegers(111, 111)); // Only one number, which is uniform
        assertEquals(2, UniformNumbers.countUniformIntegers(111, 222)); // Uniform numbers are 111, 222
    }

    @Test
    void testLargeRange() {
        assertEquals(36, UniformNumbers.countUniformIntegers(1, 999)); // Counts all 1-digit, 2-digit, and 3-digit uniform numbers
        assertEquals(45, UniformNumbers.countUniformIntegers(1, 9999)); // Adds 4-digit uniform numbers too
    }

    @Test
    void testUpperBoundary() {
        assertEquals(9, UniformNumbers.countUniformIntegers(1, Integer.MAX_VALUE)); // Test with maximum possible integer range
    }

    @Test
    void testInvalidRange() {
        assertEquals(0, UniformNumbers.countUniformIntegers(500, 100)); // Invalid range (A > B)
        assertEquals(0, UniformNumbers.countUniformIntegers(-100, -1)); // Negative numbers, not in range of positive integers
    }

}
