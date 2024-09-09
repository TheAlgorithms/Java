package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BinaryToHexadecimalTest {

    @ParameterizedTest
    @CsvSource({"0, 0", "1, 1", "10, 2", "1111, F", "1101010, 6A", "1100, C"})
    void testBinToHex(int binary, String expectedHex) {
        assertEquals(expectedHex, BinaryToHexadecimal.binToHex(binary));
    }

    @ParameterizedTest
    @CsvSource({"2", "1234", "11112"})
    void testInvalidBinaryInput(int binary) {
        assertThrows(IllegalArgumentException.class, () -> BinaryToHexadecimal.binToHex(binary));
    }
}
