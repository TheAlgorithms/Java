package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class RomanToIntegerTest {

    @Test
    public void testRomanToInteger() {
        assertEquals(1994, RomanToInteger.romanToInt("MCMXCIV"));
        assertEquals(58, RomanToInteger.romanToInt("LVIII"));
        assertEquals(1804, RomanToInteger.romanToInt("MDCCCIV"));
    }

    @Test
    void testRomanToIntegerThrows() {
        assertThrows(IllegalArgumentException.class, () -> RomanToInteger.romanToInt("Z"));
        assertThrows(IllegalArgumentException.class, () -> RomanToInteger.romanToInt("MZI"));
        assertThrows(IllegalArgumentException.class, () -> RomanToInteger.romanToInt("MMMO"));
    }
}
