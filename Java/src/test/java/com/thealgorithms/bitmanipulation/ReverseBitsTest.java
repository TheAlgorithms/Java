package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ReverseBitsTest {

    @Test
    void testReverseBits() {
        assertEquals(0, ReverseBits.reverseBits(0));
        assertEquals(-1, ReverseBits.reverseBits(-1));
        assertEquals(964176192, ReverseBits.reverseBits(43261596));
    }
}
