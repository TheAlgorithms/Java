package com.thealgorithms.misc;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ZigZagConversionTest {

    @Test
    void testBasicCaseWith3Rows() {
        ZigZagConversion converter = new ZigZagConversion();
        String input = "PAYPALISHIRING";
        int numRows = 3;
        String expected = "PAHNAPLSIIGYIR";
        assertEquals(expected, converter.convert(input, numRows));
    }

    @Test
    void testBasicCaseWith4Rows() {
        ZigZagConversion converter = new ZigZagConversion();
        String input = "PAYPALISHIRING";
        int numRows = 4;
        String expected = "PINALSIGYAHRPI";
        assertEquals(expected, converter.convert(input, numRows));
    }

    @Test
    void testSingleCharacterInput() {
        ZigZagConversion converter = new ZigZagConversion();
        String input = "H";
        int numRows = 1;
        assertEquals(input, converter.convert(input, numRows));
    }

    @Test
    void testEmptyStringInput() {
        ZigZagConversion converter = new ZigZagConversion();
        String input = "";
        int numRows = 3;
        assertEquals("", converter.convert(input, numRows));
    }

    @Test
    void testNumRowsGreaterThanStringLength() {
        ZigZagConversion converter = new ZigZagConversion();
        String input = "12345";
        int numRows = 10;
        assertEquals("12345", converter.convert(input, numRows));
    }

    @Test
    void testInputWithSpacesAndSpecialChars() {
        ZigZagConversion converter = new ZigZagConversion();
        String input = "Hello World!";
        int numRows = 3;
        String expected = "Horel ol!lWd";
        assertEquals(expected, converter.convert(input, numRows));
    }
}
