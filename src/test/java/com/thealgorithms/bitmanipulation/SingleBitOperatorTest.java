package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SingleBitOperatorTest {

    @Test
    public void flipBitTest() {
        assertEquals(1, SingleBitOperator.flipBit(3, 1));
        assertEquals(11, SingleBitOperator.flipBit(3, 3));
    }

    @Test
    public void setBitTest() {
        assertEquals(5, SingleBitOperator.setBit(4, 0));
        assertEquals(4, SingleBitOperator.setBit(4, 2));
    }

    @Test
    public void clearBitTest() {
        assertEquals(5, SingleBitOperator.clearBit(7, 1));
        assertEquals(5, SingleBitOperator.clearBit(5, 1));
    }

    @Test
    public void getBitTest() {
        assertEquals(0, SingleBitOperator.getBit(6, 0));
        assertEquals(1, SingleBitOperator.getBit(7, 1));
    }
}
