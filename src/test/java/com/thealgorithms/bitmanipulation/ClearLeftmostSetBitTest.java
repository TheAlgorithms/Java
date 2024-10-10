package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ClearLeftmostSetBitTest {

    @Test
    public void testClearLeftmostSetBit() {
        assertEquals(10, ClearLeftmostSetBit.clearLeftmostSetBit(26)); // 11010 -> 01010
        assertEquals(0, ClearLeftmostSetBit.clearLeftmostSetBit(1)); // 1 -> 0
        assertEquals(3, ClearLeftmostSetBit.clearLeftmostSetBit(7)); // 111 -> 011
        assertEquals(2, ClearLeftmostSetBit.clearLeftmostSetBit(6)); // 0110 -> 0010
    }
}
