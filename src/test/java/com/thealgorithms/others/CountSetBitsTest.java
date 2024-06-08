package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CountSetBitsTest {

    @Test
    void testSetBits() {
        CountSetBits csb = new CountSetBits();
        assertEquals(1L, csb.countSetBits(16));
        assertEquals(4, csb.countSetBits(15));
        assertEquals(5, csb.countSetBits(10000));
        assertEquals(5, csb.countSetBits(31));
    }
}
