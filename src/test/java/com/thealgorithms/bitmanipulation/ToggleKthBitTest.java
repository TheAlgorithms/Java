package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ToggleKthBitTest {

    @Test
    public void testToggleKthBit() {
        assertEquals(8, ToggleKthBit.toggleKthBit(10, 1));  // 1010 ^ (1<<1) = 1000
        assertEquals(14, ToggleKthBit.toggleKthBit(10, 2)); // 1010 ^ (1<<2) = 1110
        assertEquals(2, ToggleKthBit.toggleKthBit(0, 1));   // 0000 ^ (1<<1) = 0010
        assertEquals(0, ToggleKthBit.toggleKthBit(1, 0));   // 0001 ^ (1<<0) = 0000
    }
}
