package com.thealgorithms.bitmanipulation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ClearBitTest {
    @Test
    public void clearBitTest() {
        assertEquals(5, ClearBit.clearBit(7, 1));
        assertEquals(5, ClearBit.clearBit(5, 1));
    }
}
