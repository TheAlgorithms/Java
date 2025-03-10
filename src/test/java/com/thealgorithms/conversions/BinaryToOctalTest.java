package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BinaryToOctalTest {

    @ParameterizedTest
    @CsvSource({"0, 0", "1, 1", "10, 2", "111, 7", "1000, 10", "1111, 17", "110101, 65", "1010101, 125", "110110011, 663", "111111111, 777", "10010110, 226", "1011101, 135"})
    void testConvertBinaryToOctal(int binary, String expectedOctal) {
        assertEquals(expectedOctal, BinaryToOctal.convertBinaryToOctal(binary));
    }

    @Test
    void testIncorrectInput() {
        assertThrows(IllegalArgumentException.class, () -> BinaryToOctal.convertBinaryToOctal(1234));
        assertThrows(IllegalArgumentException.class, () -> BinaryToOctal.convertBinaryToOctal(102));
        assertThrows(IllegalArgumentException.class, () -> BinaryToOctal.convertBinaryToOctal(-1010));
    }
}
