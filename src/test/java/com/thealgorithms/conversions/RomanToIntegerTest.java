package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RomanToIntegerTest {

    @Test
    public void testRomanToInteger() {
        assertEquals(1994, RomanToInteger.romanToInt("MCMXCIV"));
        assertEquals(58, RomanToInteger.romanToInt("LVIII"));
        assertEquals(3000, RomanToInteger.romanToInt("MMM"));
        assertEquals(1515, RomanToInteger.romanToInt("MDXV"));
        assertEquals(2010, RomanToInteger.romanToInt("MMX"));
        assertEquals(520, RomanToInteger.romanToInt("DXX"));
        assertEquals(65, RomanToInteger.romanToInt("LXV"));
        assertEquals(1600, RomanToInteger.romanToInt("MDC"));
        assertEquals(162, RomanToInteger.romanToInt("CLXII"));
        assertEquals(1666, RomanToInteger.romanToInt("MDCLXVI"));
    }
}
