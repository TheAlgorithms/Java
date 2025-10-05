package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test class for {@link HappyNumbersSeq}.
 *
 * @author Hardvan (https://github.com/Hardvan)
 */
class HappyNumbersSeqTest {

    @Test
    void testIsHappyWithHappyNumbers() {
        // Test known happy numbers
        assertTrue(HappyNumbersSeq.isHappy(1));
        assertTrue(HappyNumbersSeq.isHappy(7));
        assertTrue(HappyNumbersSeq.isHappy(10));
        assertTrue(HappyNumbersSeq.isHappy(13));
        assertTrue(HappyNumbersSeq.isHappy(19));
        assertTrue(HappyNumbersSeq.isHappy(23));
        assertTrue(HappyNumbersSeq.isHappy(28));
        assertTrue(HappyNumbersSeq.isHappy(31));
        assertTrue(HappyNumbersSeq.isHappy(32));
        assertTrue(HappyNumbersSeq.isHappy(44));
        assertTrue(HappyNumbersSeq.isHappy(49));
        assertTrue(HappyNumbersSeq.isHappy(68));
        assertTrue(HappyNumbersSeq.isHappy(70));
        assertTrue(HappyNumbersSeq.isHappy(79));
        assertTrue(HappyNumbersSeq.isHappy(82));
        assertTrue(HappyNumbersSeq.isHappy(86));
        assertTrue(HappyNumbersSeq.isHappy(91));
        assertTrue(HappyNumbersSeq.isHappy(94));
        assertTrue(HappyNumbersSeq.isHappy(97));
        assertTrue(HappyNumbersSeq.isHappy(100));
    }

    @Test
    void testIsHappyWithSadNumbers() {
        // Test known sad numbers
        assertFalse(HappyNumbersSeq.isHappy(2));
        assertFalse(HappyNumbersSeq.isHappy(3));
        assertFalse(HappyNumbersSeq.isHappy(4));
        assertFalse(HappyNumbersSeq.isHappy(5));
        assertFalse(HappyNumbersSeq.isHappy(6));
        assertFalse(HappyNumbersSeq.isHappy(8));
        assertFalse(HappyNumbersSeq.isHappy(9));
        assertFalse(HappyNumbersSeq.isHappy(11));
        assertFalse(HappyNumbersSeq.isHappy(12));
        assertFalse(HappyNumbersSeq.isHappy(14));
        assertFalse(HappyNumbersSeq.isHappy(15));
        assertFalse(HappyNumbersSeq.isHappy(16));
        assertFalse(HappyNumbersSeq.isHappy(17));
        assertFalse(HappyNumbersSeq.isHappy(18));
        assertFalse(HappyNumbersSeq.isHappy(20));
    }

    @Test
    void testIsHappyWithLargeNumbers() {
        // Test larger happy numbers
        assertTrue(HappyNumbersSeq.isHappy(1000));
        assertFalse(HappyNumbersSeq.isHappy(999));
        assertFalse(HappyNumbersSeq.isHappy(1001));
    }

    @Test
    void testIsHappyWithInvalidInput() {
        // Test with zero
        assertThrows(IllegalArgumentException.class, () -> HappyNumbersSeq.isHappy(0));

        // Test with negative numbers
        assertThrows(IllegalArgumentException.class, () -> HappyNumbersSeq.isHappy(-1));
        assertThrows(IllegalArgumentException.class, () -> HappyNumbersSeq.isHappy(-10));
        assertThrows(IllegalArgumentException.class, () -> HappyNumbersSeq.isHappy(-100));
    }

    @Test
    void testSumSquaresSingleDigit() {
        assertEquals(0, HappyNumbersSeq.sumSquares(0));
        assertEquals(1, HappyNumbersSeq.sumSquares(1));
        assertEquals(4, HappyNumbersSeq.sumSquares(2));
        assertEquals(9, HappyNumbersSeq.sumSquares(3));
        assertEquals(16, HappyNumbersSeq.sumSquares(4));
        assertEquals(25, HappyNumbersSeq.sumSquares(5));
        assertEquals(36, HappyNumbersSeq.sumSquares(6));
        assertEquals(49, HappyNumbersSeq.sumSquares(7));
        assertEquals(64, HappyNumbersSeq.sumSquares(8));
        assertEquals(81, HappyNumbersSeq.sumSquares(9));
    }

    @Test
    void testSumSquaresMultipleDigits() {
        // 10: 1^2 + 0^2 = 1
        assertEquals(1, HappyNumbersSeq.sumSquares(10));

        // 23: 2^2 + 3^2 = 4 + 9 = 13
        assertEquals(13, HappyNumbersSeq.sumSquares(23));

        // 82: 8^2 + 2^2 = 64 + 4 = 68
        assertEquals(68, HappyNumbersSeq.sumSquares(82));

        // 130: 1^2 + 3^2 + 0^2 = 1 + 9 + 0 = 10
        assertEquals(10, HappyNumbersSeq.sumSquares(130));

        // 999: 9^2 + 9^2 + 9^2 = 81 + 81 + 81 = 243
        assertEquals(243, HappyNumbersSeq.sumSquares(999));
    }

    @Test
    void testSumSquaresLargeNumbers() {
        // 1234: 1^2 + 2^2 + 3^2 + 4^2 = 1 + 4 + 9 + 16 = 30
        assertEquals(30, HappyNumbersSeq.sumSquares(1234));

        // 9876: 9^2 + 8^2 + 7^2 + 6^2 = 81 + 64 + 49 + 36 = 230
        assertEquals(230, HappyNumbersSeq.sumSquares(9876));
    }

    @Test
    void testIsSadWithCycleNumbers() {
        // Test all known cycle numbers
        assertTrue(HappyNumbersSeq.isSad(4));
        assertTrue(HappyNumbersSeq.isSad(16));
        assertTrue(HappyNumbersSeq.isSad(20));
        assertTrue(HappyNumbersSeq.isSad(37));
        assertTrue(HappyNumbersSeq.isSad(58));
        assertTrue(HappyNumbersSeq.isSad(145));
    }

    @Test
    void testIsSadWithNonCycleNumbers() {
        // Test numbers that are not in the cycle
        assertFalse(HappyNumbersSeq.isSad(1));
        assertFalse(HappyNumbersSeq.isSad(7));
        assertFalse(HappyNumbersSeq.isSad(10));
        assertFalse(HappyNumbersSeq.isSad(13));
        assertFalse(HappyNumbersSeq.isSad(19));
        assertFalse(HappyNumbersSeq.isSad(23));
    }

    @Test
    void testHappyNumberSequenceFor7() {
        // Test the sequence for happy number 7: 7 → 49 → 97 → 130 → 10 → 1
        int n = 7;
        assertEquals(49, HappyNumbersSeq.sumSquares(n));
        n = 49;
        assertEquals(97, HappyNumbersSeq.sumSquares(n));
        n = 97;
        assertEquals(130, HappyNumbersSeq.sumSquares(n));
        n = 130;
        assertEquals(10, HappyNumbersSeq.sumSquares(n));
        n = 10;
        assertEquals(1, HappyNumbersSeq.sumSquares(n));
    }

    @Test
    void testHappyNumberSequenceFor19() {
        // Test the sequence for happy number 19: 19 → 82 → 68 → 100 → 1
        int n = 19;
        assertEquals(82, HappyNumbersSeq.sumSquares(n));
        n = 82;
        assertEquals(68, HappyNumbersSeq.sumSquares(n));
        n = 68;
        assertEquals(100, HappyNumbersSeq.sumSquares(n));
        n = 100;
        assertEquals(1, HappyNumbersSeq.sumSquares(n));
    }

    @Test
    void testSadNumberEntersCycle() {
        // Test that sad number 2 eventually reaches a cycle number
        int n = 2;
        assertEquals(4, HappyNumbersSeq.sumSquares(n)); // 2 → 4 (cycle number)
        assertTrue(HappyNumbersSeq.isSad(4));
    }
}
