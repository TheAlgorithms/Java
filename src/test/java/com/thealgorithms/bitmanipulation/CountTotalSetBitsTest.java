package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test cases for {@link CountTotalSetBits}.
 */
public class CountTotalSetBitsTest {

    @Test
    void testSmallNumbers() {
        assertEquals(4, CountTotalSetBits.countTotalSetBits(3)); // 1->1,2->1,3->2
        assertEquals(5, CountTotalSetBits.countTotalSetBits(4)); // 1,2,3,4 -> total 5
    }

    @Test
    void testPowerOfTwo() {
        assertEquals(12, CountTotalSetBits.countTotalSetBits(7)); // from 1 to 7
    }

    @Test
    void testLargerNumber() {
        assertEquals(17, CountTotalSetBits.countTotalSetBits(10)); // verified manually
    }

    @Test
    void testZero() {
        assertEquals(0, CountTotalSetBits.countTotalSetBits(0));
    }
}
