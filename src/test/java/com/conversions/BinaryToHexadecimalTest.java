package com.conversions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinaryToHexadecimalTest {

    @Test
    public void testBinaryToHexadecimal(){
        BinaryToHexadecimal binaryToHexadecimal = new BinaryToHexadecimal();
        assertEquals("2A", binaryToHexadecimal.binToHex("101010"));
        assertEquals("24", binaryToHexadecimal.binToHex("100100"));
        assertEquals("AAAAAAAAAAAAAAAAAA1", binaryToHexadecimal.binToHex("1010101010101010101010101010101010101010101010101010101010101010101010100001"));
    }
}
