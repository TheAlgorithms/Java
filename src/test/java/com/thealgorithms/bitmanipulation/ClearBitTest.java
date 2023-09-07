package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ClearBitTest {
    @Test
    public void clearBitTest() {
        assertEquals(5, ClearBit.clearBit(7, 1));
        assertEquals(5, ClearBit.clearBit(5, 1));
    }
}
