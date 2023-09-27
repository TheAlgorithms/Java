package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SingleBitOperatorTest {

    @Test
    public void flipBitTest() {
        assertEquals(1, SingleBitOperators.flipBit(3, 1));
        assertEquals(11, SingleBitOperators.flipBit(3, 3));
    }

    @Test
    public void setBitTest() {
        assertEquals(5, SingleBitOperators.setBit(4, 0));
        assertEquals(4, SingleBitOperators.setBit(4, 2));
    }

    @Test
    public void clearBitTest() {
        assertEquals(5, SingleBitOperators.clearBit(7, 1));
        assertEquals(5, SingleBitOperators.clearBit(5, 1));
    }

    @Test
    public void getBitTest() {
        assertEquals(0, SingleBitOperators.getBit(6, 0));
        assertEquals(1, SingleBitOperators.getBit(7, 1));
    }
}
