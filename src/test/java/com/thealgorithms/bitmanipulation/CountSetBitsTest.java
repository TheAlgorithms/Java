package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CountSetBitsTest {

    @Test
    void testCountSetBitsZero() {
        assertEquals(0, CountSetBits.countSetBits(0));
    }

    @Test
    void testCountSetBitsOne() {
        assertEquals(1, CountSetBits.countSetBits(1));
    }

    @Test
    void testCountSetBitsSmallNumber() {
        assertEquals(4, CountSetBits.countSetBits(3)); // 1(1) + 10(1) + 11(2) = 4
    }

    @Test
    void testCountSetBitsFive() {
        assertEquals(7, CountSetBits.countSetBits(5)); // 1 + 1 + 2 + 1 + 2 = 7
    }

    @Test
    void testCountSetBitsTen() {
        assertEquals(17, CountSetBits.countSetBits(10));
    }

    @Test
    void testCountSetBitsLargeNumber() {
        assertEquals(42, CountSetBits.countSetBits(20)); // Changed from 93 to 42
    }

    @Test
    void testCountSetBitsPowerOfTwo() {
        assertEquals(13, CountSetBits.countSetBits(8)); // Changed from 9 to 13
    }

    @Test
    void testCountSetBitsNegativeInput() {
        assertThrows(IllegalArgumentException.class, () -> CountSetBits.countSetBits(-1));
    }

    @Test
    void testNaiveApproachMatchesOptimized() {
        for (int i = 0; i <= 100; i++) {
            assertEquals(CountSetBits.countSetBitsNaive(i), CountSetBits.countSetBits(i), "Mismatch at n = " + i);
        }
    }
}
