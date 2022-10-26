package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class IntegerToRomanTest {

    @Test
    public void testIntegerToRoman() {
        assertEquals("MCMXCIV", IntegerToRoman.integerToRoman(1994));
        assertEquals("LVIII", IntegerToRoman.integerToRoman(58));
    }
}
