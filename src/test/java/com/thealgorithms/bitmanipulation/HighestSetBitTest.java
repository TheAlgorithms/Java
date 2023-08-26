package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test case for Highest Set Bit
 * @author Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */

class HighestSetBitTest {

    @Test
    void testHighestSetBit() {
        assertEquals(5, HighestSetBit.findHighestSetBit(37));
        assertEquals(5, HighestSetBit.findHighestSetBit(-37));
        assertEquals(-1, HighestSetBit.findHighestSetBit(0));
        assertEquals(2, HighestSetBit.findHighestSetBit(4));
    }
}