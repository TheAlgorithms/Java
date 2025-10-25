package com.thealgorithms.maths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link HarshadNumber}.
 * Tests various scenarios including positive cases, edge cases, and exception
 * handling.
 */
class HarshadNumberTest {

    @Test
    void testValidHarshadNumbers() {
        // Single digit Harshad numbers (all single digits except 0 are Harshad numbers)
        Assertions.assertTrue(HarshadNumber.isHarshad(1));
        Assertions.assertTrue(HarshadNumber.isHarshad(2));
        Assertions.assertTrue(HarshadNumber.isHarshad(3));
        Assertions.assertTrue(HarshadNumber.isHarshad(4));
        Assertions.assertTrue(HarshadNumber.isHarshad(5));
        Assertions.assertTrue(HarshadNumber.isHarshad(6));
        Assertions.assertTrue(HarshadNumber.isHarshad(7));
        Assertions.assertTrue(HarshadNumber.isHarshad(8));
        Assertions.assertTrue(HarshadNumber.isHarshad(9));

        // Two digit Harshad numbers
        Assertions.assertTrue(HarshadNumber.isHarshad(10)); // 10 / (1 + 0) = 10
        Assertions.assertTrue(HarshadNumber.isHarshad(12)); // 12 / (1 + 2) = 4
        Assertions.assertTrue(HarshadNumber.isHarshad(18)); // 18 / (1 + 8) = 2
        Assertions.assertTrue(HarshadNumber.isHarshad(20)); // 20 / (2 + 0) = 10
        Assertions.assertTrue(HarshadNumber.isHarshad(21)); // 21 / (2 + 1) = 7

        // Three digit Harshad numbers
        Assertions.assertTrue(HarshadNumber.isHarshad(100)); // 100 / (1 + 0 + 0) = 100
        Assertions.assertTrue(HarshadNumber.isHarshad(102)); // 102 / (1 + 0 + 2) = 34
        Assertions.assertTrue(HarshadNumber.isHarshad(108)); // 108 / (1 + 0 + 8) = 12

        // Large Harshad numbers
        Assertions.assertTrue(HarshadNumber.isHarshad(1000)); // 1000 / (1 + 0 + 0 + 0) = 1000
        Assertions.assertTrue(HarshadNumber.isHarshad(1002)); // 1002 / (1 + 0 + 0 + 2) = 334
        Assertions.assertTrue(HarshadNumber.isHarshad(999999999)); // 999999999 / (9*9) = 12345679
    }

    @Test
    void testInvalidHarshadNumbers() {
        // Numbers that are not Harshad numbers
        Assertions.assertFalse(HarshadNumber.isHarshad(11)); // 11 / (1 + 1) = 5.5
        Assertions.assertFalse(HarshadNumber.isHarshad(13)); // 13 / (1 + 3) = 3.25
        Assertions.assertFalse(HarshadNumber.isHarshad(17)); // 17 / (1 + 7) = 2.125
        Assertions.assertFalse(HarshadNumber.isHarshad(19)); // 19 / (1 + 9) = 1.9
        Assertions.assertFalse(HarshadNumber.isHarshad(23)); // 23 / (2 + 3) = 4.6
        Assertions.assertFalse(HarshadNumber.isHarshad(101)); // 101 / (1 + 0 + 1) = 50.5
    }

    @Test
    void testZeroThrowsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> HarshadNumber.isHarshad(0));
    }

    @Test
    void testNegativeNumbersThrowException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> HarshadNumber.isHarshad(-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> HarshadNumber.isHarshad(-18));
        Assertions.assertThrows(IllegalArgumentException.class, () -> HarshadNumber.isHarshad(-100));
    }

    @Test
    void testValidHarshadNumbersWithString() {
        // Single digit Harshad numbers
        Assertions.assertTrue(HarshadNumber.isHarshad("1"));
        Assertions.assertTrue(HarshadNumber.isHarshad("2"));
        Assertions.assertTrue(HarshadNumber.isHarshad("9"));

        // Two digit Harshad numbers
        Assertions.assertTrue(HarshadNumber.isHarshad("10"));
        Assertions.assertTrue(HarshadNumber.isHarshad("12"));
        Assertions.assertTrue(HarshadNumber.isHarshad("18"));

        // Large Harshad numbers
        Assertions.assertTrue(HarshadNumber.isHarshad("1000"));
        Assertions.assertTrue(HarshadNumber.isHarshad("999999999"));
        Assertions.assertTrue(HarshadNumber.isHarshad("99999999999100"));
    }

    @Test
    void testInvalidHarshadNumbersWithString() {
        // Numbers that are not Harshad numbers
        Assertions.assertFalse(HarshadNumber.isHarshad("11"));
        Assertions.assertFalse(HarshadNumber.isHarshad("13"));
        Assertions.assertFalse(HarshadNumber.isHarshad("19"));
        Assertions.assertFalse(HarshadNumber.isHarshad("23"));
    }

    @Test
    void testStringWithZeroThrowsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> HarshadNumber.isHarshad("0"));
    }

    @Test
    void testStringWithNegativeNumbersThrowsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> HarshadNumber.isHarshad("-1"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> HarshadNumber.isHarshad("-18"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> HarshadNumber.isHarshad("-100"));
    }

    @Test
    void testNullStringThrowsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> HarshadNumber.isHarshad(null));
    }

    @Test
    void testEmptyStringThrowsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> HarshadNumber.isHarshad(""));
    }

    @Test
    void testInvalidStringThrowsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> HarshadNumber.isHarshad("abc"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> HarshadNumber.isHarshad("12.5"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> HarshadNumber.isHarshad("12a"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> HarshadNumber.isHarshad(" 12 "));
    }

    @Test
    void testMaxLongValue() {
        // Test with a large number close to Long.MAX_VALUE
        long largeHarshadCandidate = 9223372036854775800L;
        // This specific number may or may not be Harshad, just testing it doesn't crash
        try {
            HarshadNumber.isHarshad(largeHarshadCandidate);
        } catch (Exception e) {
            Assertions.fail("Should not throw exception for valid large numbers");
        }
    }
}
