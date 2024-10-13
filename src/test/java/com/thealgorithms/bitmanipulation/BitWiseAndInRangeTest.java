package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the BitWiseAndInRange class.
 */
public class BitWiseAndInRangeTest {

    /**
     * Test the rangeBitwiseAnd method with a range that includes multiple numbers.
     */
    @Test
    public void testRangeBitwiseAndMultipleNumbers() {
        int result = BitWiseAndInRange.rangeBitwiseAnd(5, 7);
        assertEquals(4, result); // Bitwise AND of range [5, 7] should be 4
    }

    /**
     * Test the rangeBitwiseAnd method with a range that includes a single number.
     */
    @Test
    public void testRangeBitwiseAndSingleNumber() {
        int result = BitWiseAndInRange.rangeBitwiseAnd(5, 5);
        assertEquals(5, result); // Bitwise AND of range [5, 5] should be 5
    }

    /**
     * Test the rangeBitwiseAnd method with a range that includes zero.
     */
    @Test
    public void testRangeBitwiseAndIncludesZero() {
        int result = BitWiseAndInRange.rangeBitwiseAnd(0, 1);
        assertEquals(0, result); // Bitwise AND of range [0, 1] should be 0
    }

    /**
     * Test the rangeBitwiseAnd method with a large range.
     */
    @Test
    public void testRangeBitwiseAndLargeRange() {
        int result = BitWiseAndInRange.rangeBitwiseAnd(0, 2147483647);
        assertEquals(0, result); // Bitwise AND of range [0, 2147483647] should be 0
    }

    /**
     * Test the rangeBitwiseAnd method with a range that includes negative numbers.
     */
    @Test
    public void testRangeBitwiseAndNegativeNumbers() {
        int result = BitWiseAndInRange.rangeBitwiseAnd(-5, -3);
        assertEquals(-8, result); // Bitwise AND of range [-5, -3] should be -8
    }
}
