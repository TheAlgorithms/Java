package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CharToAsciiTest {

    @Test
    public void testUpperCaseCharacter() {
        //Test upper case characters
        assertEquals(65, CharToAscii.ConvertToAsciiDecimal('A'));
        assertEquals(66, CharToAscii.ConvertToAsciiDecimal('B'));
        assertEquals(67, CharToAscii.ConvertToAsciiDecimal('C'));
        assertEquals(68, CharToAscii.ConvertToAsciiDecimal('D'));
    }
    
    @Test
    public void testLowerCaseCharacter() {
        //Test lower case characters
        assertEquals(97, CharToAscii.ConvertToAsciiDecimal('a'));
        assertEquals(98, CharToAscii.ConvertToAsciiDecimal('b'));
        assertEquals(103, CharToAscii.ConvertToAsciiDecimal('g'));
        assertEquals(122, CharToAscii.ConvertToAsciiDecimal('z'));
    }

}