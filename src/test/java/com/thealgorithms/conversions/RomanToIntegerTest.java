package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RomanToIntegerTest {

    @Test
    public void testRomanToInteger() {
        assertEquals(1994, RomanToInteger.romanToInt("MCMXCIV"));
        assertEquals(58, RomanToInteger.romanToInt("LVIII"));
    }
}
