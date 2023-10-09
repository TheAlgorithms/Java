package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class countSetBitsTest {

    @Test
    void testSetBits() {
        countSetBits csb = new countSetBits();
        assertEquals(1L, csb.countsetBits(16));
        assertEquals(4, csb.countsetBits(15));
        assertEquals(5, csb.countsetBits(10000));
        assertEquals(5, csb.countsetBits(31));
    }
}
