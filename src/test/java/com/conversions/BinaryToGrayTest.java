package com.conversions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BinaryToGrayTest {

    @Test
    void testBinaryToGray() {
        BinaryToGray binaryToGray = new BinaryToGray();
        Assertions.assertEquals("1101", binaryToGray.binaryToGray("1001"));
        Assertions.assertEquals("11010011101", binaryToGray.binaryToGray("10011101001"));
    }

}
