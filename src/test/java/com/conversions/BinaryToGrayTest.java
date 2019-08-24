package com.conversions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinaryToGrayTest {

    @Test
    public void testBinaryToGray() {
        BinaryToGray binaryToGray = new BinaryToGray();
        assertEquals("1101", binaryToGray.binaryToGray("1001"));
        assertEquals("11010011101", binaryToGray.binaryToGray("10011101001"));
    }

}
