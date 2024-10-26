package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class IntegerToRomanTest {

    @Test
    public void testIntegerToRoman() {
        assertEquals("MCMXCIV", IntegerToRoman.integerToRoman(1994));
        assertEquals("LVIII", IntegerToRoman.integerToRoman(58));
        assertEquals("IV", IntegerToRoman.integerToRoman(4));
        assertEquals("IX", IntegerToRoman.integerToRoman(9));
        assertEquals("MMM", IntegerToRoman.integerToRoman(3000));
    }

    @Test
    public void testSmallNumbers() {
        assertEquals("I", IntegerToRoman.integerToRoman(1));
        assertEquals("II", IntegerToRoman.integerToRoman(2));
        assertEquals("III", IntegerToRoman.integerToRoman(3));
    }

    @Test
    public void testRoundNumbers() {
        assertEquals("X", IntegerToRoman.integerToRoman(10));
        assertEquals("L", IntegerToRoman.integerToRoman(50));
        assertEquals("C", IntegerToRoman.integerToRoman(100));
        assertEquals("D", IntegerToRoman.integerToRoman(500));
        assertEquals("M", IntegerToRoman.integerToRoman(1000));
    }

    @Test
    public void testEdgeCases() {
        assertEquals("", IntegerToRoman.integerToRoman(0)); // Non-positive number case
        assertEquals("", IntegerToRoman.integerToRoman(-5)); // Negative number case
    }
}
