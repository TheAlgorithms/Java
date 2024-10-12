package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HigherLowerPowerOfTwoTest {

    @Test
    public void testNextHigherPowerOfTwo() {
        assertEquals(32, HigherLowerPowerOfTwo.nextHigherPowerOfTwo(19)); // next higher power of two is 32
        assertEquals(1, HigherLowerPowerOfTwo.nextHigherPowerOfTwo(1)); // next higher power of two is 1
        assertEquals(16, HigherLowerPowerOfTwo.nextHigherPowerOfTwo(15)); // next higher power of two is 16
        assertEquals(8, HigherLowerPowerOfTwo.nextHigherPowerOfTwo(8)); // next higher power of two is 8
        assertEquals(16, HigherLowerPowerOfTwo.nextHigherPowerOfTwo(9)); // next higher power of two is 16
    }

    @Test
    public void testNextLowerPowerOfTwo() {
        assertEquals(16, HigherLowerPowerOfTwo.nextLowerPowerOfTwo(19)); // next lower power of two is 16
        assertEquals(1, HigherLowerPowerOfTwo.nextLowerPowerOfTwo(1)); // next lower power of two is 1
        assertEquals(8, HigherLowerPowerOfTwo.nextLowerPowerOfTwo(9)); // next lower power of two is 8
        assertEquals(8, HigherLowerPowerOfTwo.nextLowerPowerOfTwo(15)); // next lower power of two is 8
        assertEquals(8, HigherLowerPowerOfTwo.nextLowerPowerOfTwo(8)); // next lower power of two is 8
    }
}
