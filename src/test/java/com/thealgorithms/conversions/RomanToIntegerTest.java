package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class RomanToIntegerTest {

    @Test
    public void testValidRomanToInteger() {
        assertEquals(1994, RomanToInteger.romanToInt("MCMXCIV"));
        assertEquals(58, RomanToInteger.romanToInt("LVIII"));
        assertEquals(1804, RomanToInteger.romanToInt("MDCCCIV"));
        assertEquals(9, RomanToInteger.romanToInt("IX"));
        assertEquals(4, RomanToInteger.romanToInt("IV"));
        assertEquals(3000, RomanToInteger.romanToInt("MMM"));
    }

    @Test
    public void testLowercaseInput() {
        assertEquals(1994, RomanToInteger.romanToInt("mcmxciv"));
        assertEquals(58, RomanToInteger.romanToInt("lviii"));
    }

    @Test
    public void testInvalidRomanNumerals() {
        assertThrows(IllegalArgumentException.class, () -> RomanToInteger.romanToInt("Z"));
        assertThrows(IllegalArgumentException.class, () -> RomanToInteger.romanToInt("MZI"));
        assertThrows(IllegalArgumentException.class, () -> RomanToInteger.romanToInt("MMMO"));
    }

    @Test
    public void testEmptyAndNullInput() {
        assertEquals(0, RomanToInteger.romanToInt("")); // Empty string case
        assertThrows(NullPointerException.class, () -> RomanToInteger.romanToInt(null)); // Null input case
    }
}
