package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CharToAsciiTest {

    @Test
    public void testUpperCaseCharacter() {
        //Test upper case characters
        assertEquals(65, CharToAscii.convertToAsciiDecimal('A'));
        assertEquals(66, CharToAscii.convertToAsciiDecimal('B'));
        assertEquals(67, CharToAscii.convertToAsciiDecimal('C'));
        assertEquals(68, CharToAscii.convertToAsciiDecimal('D'));
    }
    
    @Test
    public void testLowerCaseCharacter() {
        //Test lower case characters
        assertEquals(97, CharToAscii.convertToAsciiDecimal('a'));
        assertEquals(98, CharToAscii.convertToAsciiDecimal('b'));
        assertEquals(103, CharToAscii.convertToAsciiDecimal('g'));
        assertEquals(122, CharToAscii.convertToAsciiDecimal('z'));
    }

}