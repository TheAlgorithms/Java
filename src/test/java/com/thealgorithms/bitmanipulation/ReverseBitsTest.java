package com.thealgorithms.bitmanipulation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ReverseBitsTest {

    @Test
    void testReverseBits() {
        assertEquals(0, ReverseBits.reverseBits(0));
        assertEquals(-1, ReverseBits.reverseBits(-1));
        assertEquals(964176192, ReverseBits.reverseBits(43261596));
    }
}
