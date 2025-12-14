package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PowerOfFourTest {

    @Test
    void testPowersOfFour() {
        assertTrue(PowerOfFour.isPowerOfFour(1));
        assertTrue(PowerOfFour.isPowerOfFour(4));
        assertTrue(PowerOfFour.isPowerOfFour(16));
        assertTrue(PowerOfFour.isPowerOfFour(64));
        assertTrue(PowerOfFour.isPowerOfFour(256));
        assertTrue(PowerOfFour.isPowerOfFour(1024));
    }

    @Test
    void testNonPowersOfFour() {
        assertFalse(PowerOfFour.isPowerOfFour(2));
        assertFalse(PowerOfFour.isPowerOfFour(3));
        assertFalse(PowerOfFour.isPowerOfFour(5));
        assertFalse(PowerOfFour.isPowerOfFour(8));
        assertFalse(PowerOfFour.isPowerOfFour(15));
        assertFalse(PowerOfFour.isPowerOfFour(32));
    }

    @Test
    void testEdgeCases() {
        assertFalse(PowerOfFour.isPowerOfFour(0));
        assertFalse(PowerOfFour.isPowerOfFour(-1));
        assertFalse(PowerOfFour.isPowerOfFour(-4));
    }
}
