package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CountSetBitsTest {

    @Test
    void testSetBits() {
        CountSetBits csb = new CountSetBits();
        assertEquals(1L, csb.CountSetBits(16));
        assertEquals(4, csb.CountSetBits(15));
        assertEquals(5, csb.CountSetBits(10000));
        assertEquals(5, csb.CountSetBits(31));
    }
}
