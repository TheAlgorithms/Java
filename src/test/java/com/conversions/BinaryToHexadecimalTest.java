package com.conversions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BinaryToHexadecimalTest {

    @Test
    void testBinaryToHexadecimal() {
        BinaryToHexadecimal binaryToHexadecimal = new BinaryToHexadecimal();
        Assertions.assertEquals("2A", binaryToHexadecimal.binToHex("101010"), "Incorrect Conversion");
        Assertions.assertEquals("24", binaryToHexadecimal.binToHex("100100"), "Incorrect Conversion");
        Assertions.assertEquals("AAAAAAAAAAAAAAAAAA1", binaryToHexadecimal.binToHex("1010101010101010101010101010101010101010101010101010101010101010101010100001"), "Incorrect Conversion");
    }
}
