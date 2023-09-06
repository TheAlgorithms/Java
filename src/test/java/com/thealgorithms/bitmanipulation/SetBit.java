package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class SetBitTest {
    @Test
    void testSetBit() {
        assertEquals(5, SetBit.setBit(4, 0));
        assertEquals(3, SetBit.setBit(3, 1));
    }
}
