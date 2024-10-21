package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class HexaDecimalToBinaryTest {

    private final HexaDecimalToBinary converter = new HexaDecimalToBinary();

    @ParameterizedTest
    @CsvSource({"1, 00000001", "A1, 10100001", "EF, 11101111", "BA, 10111010", "7, 00000111", "F, 00001111", "7FFFFFFF, 1111111111111111111111111111111", "ABCDEF, 101010111100110111101111"})
    public void testHexaDecimalToBinary(String hexInput, String expectedBinary) {
        assertEquals(expectedBinary, converter.convert(hexInput));
    }

    @ParameterizedTest
    @CsvSource({"0, 00000000", "2, 00000010", "3, 00000011"})
    public void testPaddingWithLeadingZeros(String hexInput, String expectedBinary) {
        assertEquals(expectedBinary, converter.convert(hexInput));
    }

    @ParameterizedTest
    @CsvSource({"G, NumberFormatException", "ZZ, NumberFormatException"})
    public void testInvalidHexInput(String hexInput, String expectedException) {
        try {
            converter.convert(hexInput);
        } catch (NumberFormatException e) {
            assertEquals(expectedException, e.getClass().getSimpleName());
        }
    }
}
