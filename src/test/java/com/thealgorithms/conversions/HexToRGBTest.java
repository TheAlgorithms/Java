package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class HexToRGBTest {

    @Test
    void testHexToRGB() {
        assertArrayEquals(new int[] {255, 255, 255}, HexToRGB.hexStringToRGB("#FFFFFF"));
        assertArrayEquals(new int[] {0, 0, 0}, HexToRGB.hexStringToRGB("#000000"));
        assertArrayEquals(new int[] {255, 87, 51}, HexToRGB.hexStringToRGB("#FF5733"));
    }
}
