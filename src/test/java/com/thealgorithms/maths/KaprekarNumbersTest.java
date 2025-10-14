package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link KaprekarNumbers}.
 * Tests various Kaprekar numbers and edge cases to ensure full coverage.
 */
class KaprekarNumbersTest {

    @Test
    void testZeroIsKaprekarNumber() {
        assertTrue(KaprekarNumbers.isKaprekarNumber(0));
    }

    @Test
    void testOneIsKaprekarNumber() {
        assertTrue(KaprekarNumbers.isKaprekarNumber(1));
    }

    @Test
    void testNineIsKaprekarNumber() {
        // 9^2 = 81, 8 + 1 = 9
        assertTrue(KaprekarNumbers.isKaprekarNumber(9));
    }

    @Test
    void testFortyFiveIsKaprekarNumber() {
        // 45^2 = 2025, 20 + 25 = 45
        assertTrue(KaprekarNumbers.isKaprekarNumber(45));
    }

    @Test
    void testFiftyFiveIsKaprekarNumber() {
        // 55^2 = 3025, 30 + 25 = 55
        assertTrue(KaprekarNumbers.isKaprekarNumber(55));
    }

    @Test
    void testNinetyNineIsKaprekarNumber() {
        // 99^2 = 9801, 98 + 01 = 99
        assertTrue(KaprekarNumbers.isKaprekarNumber(99));
    }

    @Test
    void testTwoNinetySevenIsKaprekarNumber() {
        // 297^2 = 88209, 88 + 209 = 297
        assertTrue(KaprekarNumbers.isKaprekarNumber(297));
    }

    @Test
    void testSevenZeroThreeIsKaprekarNumber() {
        // 703^2 = 494209, 494 + 209 = 703
        assertTrue(KaprekarNumbers.isKaprekarNumber(703));
    }

    @Test
    void testNineNineNineIsKaprekarNumber() {
        // 999^2 = 998001, 998 + 001 = 999
        assertTrue(KaprekarNumbers.isKaprekarNumber(999));
    }

    @Test
    void testTwoTwoTwoThreeIsKaprekarNumber() {
        // 2223^2 = 4941729, 4941 + 729 = 5670 (not directly obvious)
        // Actually: 494 + 1729 = 2223
        assertTrue(KaprekarNumbers.isKaprekarNumber(2223));
    }

    @Test
    void testEightFiveSevenOneFortyThreeIsKaprekarNumber() {
        assertTrue(KaprekarNumbers.isKaprekarNumber(857143));
    }

    @Test
    void testTwoIsNotKaprekarNumber() {
        assertFalse(KaprekarNumbers.isKaprekarNumber(2));
    }

    @Test
    void testThreeIsNotKaprekarNumber() {
        assertFalse(KaprekarNumbers.isKaprekarNumber(3));
    }

    @Test
    void testTenIsNotKaprekarNumber() {
        assertFalse(KaprekarNumbers.isKaprekarNumber(10));
    }

    @Test
    void testTwentySixIsNotKaprekarNumber() {
        assertFalse(KaprekarNumbers.isKaprekarNumber(26));
    }

    @Test
    void testNinetyEightIsNotKaprekarNumber() {
        assertFalse(KaprekarNumbers.isKaprekarNumber(98));
    }

    @Test
    void testOneHundredIsNotKaprekarNumber() {
        assertFalse(KaprekarNumbers.isKaprekarNumber(100));
    }

    @Test
    void testNegativeNumberThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> KaprekarNumbers.isKaprekarNumber(-5));
    }

    @Test
    void testKaprekarNumbersInSmallRange() {
        List<Long> result = KaprekarNumbers.kaprekarNumberInRange(1, 10);
        List<Long> expected = Arrays.asList(1L, 9L);
        assertEquals(expected, result);
    }

    @Test
    void testKaprekarNumbersInMediumRange() {
        List<Long> result = KaprekarNumbers.kaprekarNumberInRange(1, 100);
        List<Long> expected = Arrays.asList(1L, 9L, 45L, 55L, 99L);
        assertEquals(expected, result);
    }

    @Test
    void testKaprekarNumbersInLargeRange() {
        List<Long> rangedNumbers = KaprekarNumbers.kaprekarNumberInRange(1, 100000);
        List<Long> expectedNumbers = Arrays.asList(1L, 9L, 45L, 55L, 99L, 297L, 703L, 999L, 2223L, 2728L, 4950L, 5050L, 7272L, 7777L, 9999L, 17344L, 22222L, 77778L, 82656L, 95121L, 99999L);
        assertEquals(expectedNumbers, rangedNumbers);
    }

    @Test
    void testKaprekarNumbersInSingleElementRange() {
        List<Long> result = KaprekarNumbers.kaprekarNumberInRange(9, 9);
        List<Long> expected = Arrays.asList(9L);
        assertEquals(expected, result);
    }

    @Test
    void testKaprekarNumbersInRangeWithNoKaprekarNumbers() {
        List<Long> result = KaprekarNumbers.kaprekarNumberInRange(2, 8);
        assertTrue(result.isEmpty());
    }

    @Test
    void testKaprekarNumbersInRangeStartingFromZero() {
        List<Long> result = KaprekarNumbers.kaprekarNumberInRange(0, 5);
        List<Long> expected = Arrays.asList(0L, 1L);
        assertEquals(expected, result);
    }

    @Test
    void testInvalidRangeThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> KaprekarNumbers.kaprekarNumberInRange(100, 50));
    }

    @Test
    void testNegativeStartThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> KaprekarNumbers.kaprekarNumberInRange(-10, 100));
    }

    @Test
    void testEmptyRange() {
        List<Long> result = KaprekarNumbers.kaprekarNumberInRange(10, 44);
        assertTrue(result.isEmpty());
    }

    @Test
    void testLargeKaprekarNumber() {
        // Test a larger known Kaprekar number
        assertTrue(KaprekarNumbers.isKaprekarNumber(142857));
    }

    @Test
    void testFourDigitKaprekarNumbers() {
        // Test some 4-digit Kaprekar numbers
        assertTrue(KaprekarNumbers.isKaprekarNumber(2728));
        assertTrue(KaprekarNumbers.isKaprekarNumber(4950));
        assertTrue(KaprekarNumbers.isKaprekarNumber(5050));
        assertTrue(KaprekarNumbers.isKaprekarNumber(7272));
    }
}
