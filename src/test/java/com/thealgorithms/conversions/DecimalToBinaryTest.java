package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DecimalToBinaryTest {

    @ParameterizedTest
    @CsvSource({"0, 0", "1, 1", "2, 10", "5, 101", "10, 1010", "15, 1111", "100, 1100100"})
    void testConvertUsingConventionalAlgorithm(int decimal, int expectedBinary) {
        assertEquals(expectedBinary, DecimalToBinary.convertUsingConventionalAlgorithm(decimal));
    }

    @ParameterizedTest
    @CsvSource({"0, 0", "1, 1", "2, 10", "5, 101", "10, 1010", "15, 1111", "100, 1100100"})
    void testConvertUsingBitwiseAlgorithm(int decimal, int expectedBinary) {
        assertEquals(expectedBinary, DecimalToBinary.convertUsingBitwiseAlgorithm(decimal));
    }
}
