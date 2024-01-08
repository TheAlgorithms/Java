package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SingleBitOperationsTest {

    @Test
    public void flipBitTest() {
        assertEquals(1, SingleBitOperations.flipBit(3, 1));
        assertEquals(11, SingleBitOperations.flipBit(3, 3));
    }

    @Test
    public void setBitTest() {
        assertEquals(5, SingleBitOperations.setBit(4, 0));
        assertEquals(4, SingleBitOperations.setBit(4, 2));
        assertEquals(5, SingleBitOperations.setBit(5, 0));
        assertEquals(14, SingleBitOperations.setBit(10, 2));
        assertEquals(15, SingleBitOperations.setBit(15, 3));
        assertEquals(2, SingleBitOperations.setBit(0, 1));
    }

    @Test
    public void clearBitTest() {
        assertEquals(5, SingleBitOperations.clearBit(7, 1));
        assertEquals(5, SingleBitOperations.clearBit(5, 1));
    }

    @Test
    public void getBitTest() {
        assertEquals(0, SingleBitOperations.getBit(6, 0));
        assertEquals(1, SingleBitOperations.getBit(7, 1));
    }
}
