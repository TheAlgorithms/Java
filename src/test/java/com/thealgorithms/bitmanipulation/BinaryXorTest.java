package com.thealgorithms.bitmanipulation;

/**
 * Test cases for Binary Xor
 * @author Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BinaryXorTest {
    
    @Test
    public void testBinaryXor() {
        assertEquals("0b111001", BinaryXOR.binaryXOR(25, 32));
        assertEquals("0b0", BinaryXOR.binaryXOR(0, 0));
        assertEquals("0b1110011", BinaryXOR.binaryXOR(58, 73));
        assertEquals("0b000000000", BinaryXOR.binaryXOR(256, 256));
    }
}
