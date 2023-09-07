package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class GetBitTest {
    @Test
    public void getBitTest() {
        assertEquals(1, GetBit.getBit(7, 1));
        assertEquals(0, GetBit.getBit(5, 1));
    }
}
