package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Test case for Highest Set Bit
 * @author Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */

class HighestSetBitTest {

    @Test
    void testHighestSetBit() {
        assertFalse(HighestSetBit.findHighestSetBit(0).isPresent());
        assertEquals(0, HighestSetBit.findHighestSetBit(1).get());
        assertEquals(1, HighestSetBit.findHighestSetBit(2).get());
        assertEquals(1, HighestSetBit.findHighestSetBit(3).get());
        assertEquals(2, HighestSetBit.findHighestSetBit(4).get());
        assertEquals(2, HighestSetBit.findHighestSetBit(5).get());
        assertEquals(2, HighestSetBit.findHighestSetBit(7).get());
        assertEquals(3, HighestSetBit.findHighestSetBit(8).get());
        assertEquals(3, HighestSetBit.findHighestSetBit(9).get());
        assertEquals(3, HighestSetBit.findHighestSetBit(15).get());
        assertEquals(4, HighestSetBit.findHighestSetBit(16).get());
        assertEquals(4, HighestSetBit.findHighestSetBit(17).get());
        assertEquals(4, HighestSetBit.findHighestSetBit(31).get());
        assertEquals(5, HighestSetBit.findHighestSetBit(32).get());
        assertEquals(5, HighestSetBit.findHighestSetBit(33).get());
        assertEquals(7, HighestSetBit.findHighestSetBit(255).get());
        assertEquals(8, HighestSetBit.findHighestSetBit(256).get());
        assertEquals(8, HighestSetBit.findHighestSetBit(511).get());
        assertEquals(9, HighestSetBit.findHighestSetBit(512).get());
        assertThrows(IllegalArgumentException.class, () -> HighestSetBit.findHighestSetBit(-37));
    }
}
