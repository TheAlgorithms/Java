package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link KrishnamurthyNumber} class.
 */
class KrishnamurthyNumberTest {

    /**
     * Test with known Krishnamurthy number 145.
     * 1! + 4! + 5! = 1 + 24 + 120 = 145
     */
    @Test
    void testIsKrishnamurthyWith145() {
        assertTrue(KrishnamurthyNumber.isKrishnamurthy(145));
    }

    /**
     * Test with a number that is not a Krishnamurthy number.
     */
    @Test
    void testIsKrishnamurthyWithNonKrishnamurthyNumber() {
        assertFalse(KrishnamurthyNumber.isKrishnamurthy(123));
    }

    /**
     * Test with zero, which is not a Krishnamurthy number.
     */
    @Test
    void testIsKrishnamurthyWithZero() {
        assertFalse(KrishnamurthyNumber.isKrishnamurthy(0));
    }

    /**
     * Test with negative numbers, which cannot be Krishnamurthy numbers.
     */
    @Test
    void testIsKrishnamurthyWithNegativeNumbers() {
        assertFalse(KrishnamurthyNumber.isKrishnamurthy(-1));
        assertFalse(KrishnamurthyNumber.isKrishnamurthy(-145));
        assertFalse(KrishnamurthyNumber.isKrishnamurthy(-100));
    }

    /**
     * Test with single-digit Krishnamurthy numbers.
     * 1! = 1 and 2! = 2, so both 1 and 2 are Krishnamurthy numbers.
     */
    @Test
    void testIsKrishnamurthyWithSingleDigitKrishnamurthyNumbers() {
        assertTrue(KrishnamurthyNumber.isKrishnamurthy(1));
        assertTrue(KrishnamurthyNumber.isKrishnamurthy(2));
    }

    /**
     * Test with single-digit numbers that are not Krishnamurthy numbers.
     */
    @Test
    void testIsKrishnamurthyWithSingleDigitNonKrishnamurthyNumbers() {
        assertFalse(KrishnamurthyNumber.isKrishnamurthy(3));
        assertFalse(KrishnamurthyNumber.isKrishnamurthy(4));
        assertFalse(KrishnamurthyNumber.isKrishnamurthy(5));
        assertFalse(KrishnamurthyNumber.isKrishnamurthy(6));
        assertFalse(KrishnamurthyNumber.isKrishnamurthy(7));
        assertFalse(KrishnamurthyNumber.isKrishnamurthy(8));
        assertFalse(KrishnamurthyNumber.isKrishnamurthy(9));
    }

    /**
     * Test with the largest Krishnamurthy number: 40585.
     * 4! + 0! + 5! + 8! + 5! = 24 + 1 + 120 + 40320 + 120 = 40585
     */
    @Test
    void testIsKrishnamurthyWithLargestKrishnamurthyNumber() {
        assertTrue(KrishnamurthyNumber.isKrishnamurthy(40585));
    }

    /**
     * Test with various non-Krishnamurthy numbers.
     */
    @Test
    void testIsKrishnamurthyWithVariousNonKrishnamurthyNumbers() {
        assertFalse(KrishnamurthyNumber.isKrishnamurthy(10));
        assertFalse(KrishnamurthyNumber.isKrishnamurthy(50));
        assertFalse(KrishnamurthyNumber.isKrishnamurthy(100));
        assertFalse(KrishnamurthyNumber.isKrishnamurthy(144));
        assertFalse(KrishnamurthyNumber.isKrishnamurthy(146));
        assertFalse(KrishnamurthyNumber.isKrishnamurthy(150));
        assertFalse(KrishnamurthyNumber.isKrishnamurthy(200));
        assertFalse(KrishnamurthyNumber.isKrishnamurthy(999));
        assertFalse(KrishnamurthyNumber.isKrishnamurthy(1000));
        assertFalse(KrishnamurthyNumber.isKrishnamurthy(40584));
        assertFalse(KrishnamurthyNumber.isKrishnamurthy(40586));
    }

    /**
     * Test with numbers close to known Krishnamurthy numbers.
     */
    @Test
    void testIsKrishnamurthyWithNumbersCloseToKrishnamurthy() {
        assertFalse(KrishnamurthyNumber.isKrishnamurthy(144));
        assertFalse(KrishnamurthyNumber.isKrishnamurthy(146));
        assertFalse(KrishnamurthyNumber.isKrishnamurthy(40584));
        assertFalse(KrishnamurthyNumber.isKrishnamurthy(40586));
    }

    /**
     * Test with all known Krishnamurthy numbers in base 10.
     */
    @Test
    void testAllKnownKrishnamurthyNumbers() {
        assertTrue(KrishnamurthyNumber.isKrishnamurthy(1));
        assertTrue(KrishnamurthyNumber.isKrishnamurthy(2));
        assertTrue(KrishnamurthyNumber.isKrishnamurthy(145));
        assertTrue(KrishnamurthyNumber.isKrishnamurthy(40585));
    }
}
