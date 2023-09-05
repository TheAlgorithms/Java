package com.thealgorithms.bitmanipulation;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



class SetBitTest {
    @Test
    void testSetBit() {
        assertEquals(5, SetBit.setBit(4,0));
        assertEquals(3, SetBit.setBit(3,1));
    }
}
