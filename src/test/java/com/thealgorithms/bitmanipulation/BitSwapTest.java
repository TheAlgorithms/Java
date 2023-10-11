package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public final BitSwapTest {
    @Test
    void testHighestSetBit() {
        assertEquals(3, BitSwap.bitSwap(3, 0, 1));
        assertEquals(5, BitSwap.bitSwap(6, 0, 1));
    }
}
