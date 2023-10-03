package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CountSetBitsTest {

    @Test
    void testSetBits() {
        assertEquals(1L, CountSetBits.countsetBits(16));
        assertEquals(4, CountSetBits.countsetBits(15));
        assertEquals(5, CountSetBits.countsetBits(10000));
        assertEquals(5, CountSetBits.countsetBits(31));
    }
}
