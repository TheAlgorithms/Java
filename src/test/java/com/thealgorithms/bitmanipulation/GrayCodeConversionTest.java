package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GrayCodeConversionTest {

    @Test
    public void testBinaryToGray() {
        assertEquals(7, GrayCodeConversion.binaryToGray(5)); // 101 -> 111
        assertEquals(4, GrayCodeConversion.binaryToGray(7)); // 111 -> 100
        assertEquals(1, GrayCodeConversion.binaryToGray(1)); // 001 -> 001
    }

    @Test
    public void testGrayToBinary() {
        assertEquals(5, GrayCodeConversion.grayToBinary(7)); // 111 -> 101
        assertEquals(4, GrayCodeConversion.grayToBinary(6)); // 110 -> 100
        assertEquals(1, GrayCodeConversion.grayToBinary(1)); // 001 -> 001
    }

    @Test
    public void testBinaryGrayCycle() {
        int binary = 9; // 1001 in binary
        int gray = GrayCodeConversion.binaryToGray(binary);
        assertEquals(binary, GrayCodeConversion.grayToBinary(gray)); // Should return to original binary
    }
}
