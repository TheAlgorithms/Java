package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test cases for {@link KeithNumber}.
 */
class KeithNumberTest {

    /**
     * Tests single-digit Keith numbers.
     * All single-digit numbers (1-9) are Keith numbers by definition.
     */
    @Test
    void testSingleDigitKeithNumbers() {
        assertTrue(KeithNumber.isKeith(1));
        assertTrue(KeithNumber.isKeith(2));
        assertTrue(KeithNumber.isKeith(3));
        assertTrue(KeithNumber.isKeith(4));
        assertTrue(KeithNumber.isKeith(5));
        assertTrue(KeithNumber.isKeith(6));
        assertTrue(KeithNumber.isKeith(7));
        assertTrue(KeithNumber.isKeith(8));
        assertTrue(KeithNumber.isKeith(9));
    }

    /**
     * Tests two-digit Keith numbers.
     * Known two-digit Keith numbers: 14, 19, 28, 47, 61, 75.
     */
    @Test
    void testTwoDigitKeithNumbers() {
        assertTrue(KeithNumber.isKeith(14));
        assertTrue(KeithNumber.isKeith(19));
        assertTrue(KeithNumber.isKeith(28));
        assertTrue(KeithNumber.isKeith(47));
        assertTrue(KeithNumber.isKeith(61));
        assertTrue(KeithNumber.isKeith(75));
    }

    /**
     * Tests three-digit Keith numbers.
     * Known three-digit Keith numbers: 197, 742.
     */
    @Test
    void testThreeDigitKeithNumbers() {
        assertTrue(KeithNumber.isKeith(197));
        assertTrue(KeithNumber.isKeith(742));
    }

    /**
     * Tests four-digit Keith numbers.
     * Known four-digit Keith numbers: 1104, 1537, 2208, 2580, 3684, 4788, 7385,
     * 7647, 7909.
     */
    @Test
    void testFourDigitKeithNumbers() {
        assertTrue(KeithNumber.isKeith(1104));
        assertTrue(KeithNumber.isKeith(1537));
        assertTrue(KeithNumber.isKeith(2208));
    }

    /**
     * Tests two-digit non-Keith numbers.
     */
    @Test
    void testTwoDigitNonKeithNumbers() {
        assertFalse(KeithNumber.isKeith(10));
        assertFalse(KeithNumber.isKeith(11));
        assertFalse(KeithNumber.isKeith(12));
        assertFalse(KeithNumber.isKeith(13));
        assertFalse(KeithNumber.isKeith(15));
        assertFalse(KeithNumber.isKeith(20));
        assertFalse(KeithNumber.isKeith(30));
        assertFalse(KeithNumber.isKeith(50));
    }

    /**
     * Tests three-digit non-Keith numbers.
     */
    @Test
    void testThreeDigitNonKeithNumbers() {
        assertFalse(KeithNumber.isKeith(100));
        assertFalse(KeithNumber.isKeith(123));
        assertFalse(KeithNumber.isKeith(196));
        assertFalse(KeithNumber.isKeith(198));
        assertFalse(KeithNumber.isKeith(456));
        assertFalse(KeithNumber.isKeith(741));
        assertFalse(KeithNumber.isKeith(743));
        assertFalse(KeithNumber.isKeith(999));
    }

    /**
     * Tests validation for edge case 14 in detail.
     * 14 is a Keith number: 1, 4, 5 (1+4), 9 (4+5), 14 (5+9).
     */
    @Test
    void testKeithNumber14() {
        assertTrue(KeithNumber.isKeith(14));
    }

    /**
     * Tests validation for edge case 197 in detail.
     * 197 is a Keith number: 1, 9, 7, 17, 33, 57, 107, 197.
     */
    @Test
    void testKeithNumber197() {
        assertTrue(KeithNumber.isKeith(197));
    }

    /**
     * Tests that zero throws an IllegalArgumentException.
     */
    @Test
    void testZeroThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> KeithNumber.isKeith(0));
    }

    /**
     * Tests that negative numbers throw an IllegalArgumentException.
     */
    @Test
    void testNegativeNumbersThrowException() {
        assertThrows(IllegalArgumentException.class, () -> KeithNumber.isKeith(-1));
        assertThrows(IllegalArgumentException.class, () -> KeithNumber.isKeith(-14));
        assertThrows(IllegalArgumentException.class, () -> KeithNumber.isKeith(-100));
    }

    /**
     * Tests various edge cases with larger numbers.
     */
    @Test
    void testLargerNumbers() {
        assertTrue(KeithNumber.isKeith(2208));
        assertFalse(KeithNumber.isKeith(2207));
        assertFalse(KeithNumber.isKeith(2209));
    }

    /**
     * Tests the expected behavior with all two-digit Keith numbers.
     */
    @Test
    void testAllKnownTwoDigitKeithNumbers() {
        int[] knownKeithNumbers = {14, 19, 28, 47, 61, 75};
        for (int number : knownKeithNumbers) {
            assertTrue(KeithNumber.isKeith(number), "Expected " + number + " to be a Keith number");
        }
    }
}
