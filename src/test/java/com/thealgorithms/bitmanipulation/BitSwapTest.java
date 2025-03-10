package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
public class BitSwapTest {
    @Test
    void testHighestSetBit() {
        assertEquals(3, BitSwap.bitSwap(3, 0, 1));
        assertEquals(5, BitSwap.bitSwap(6, 0, 1));
        assertEquals(7, BitSwap.bitSwap(7, 1, 1));
    }
}
