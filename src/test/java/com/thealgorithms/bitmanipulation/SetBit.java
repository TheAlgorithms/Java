package com.thealgorithms.bitmanipulation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

class SetBitTest {
    @Test
    void testSetBit() {
        assertEquals(5, SetBit.setBit(4,0));
        assertEquals(3, IsEven.setBit(3,1));
    }
}
