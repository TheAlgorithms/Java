package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class MillerRabinPrimalityCheckTest {
    @Test
    void testDeterministicMillerRabinForPrimes() {
        assertTrue(MillerRabinPrimalityCheck.deterministicMillerRabin(2));
        assertTrue(MillerRabinPrimalityCheck.deterministicMillerRabin(37));
        assertTrue(MillerRabinPrimalityCheck.deterministicMillerRabin(123457));
        assertTrue(MillerRabinPrimalityCheck.deterministicMillerRabin(6472601713L));
    }
    @Test
    void testDeterministicMillerRabinForNotPrimes() {
        assertFalse(MillerRabinPrimalityCheck.deterministicMillerRabin(1));
        assertFalse(MillerRabinPrimalityCheck.deterministicMillerRabin(35));
        assertFalse(MillerRabinPrimalityCheck.deterministicMillerRabin(123453));
        assertFalse(MillerRabinPrimalityCheck.deterministicMillerRabin(647260175));
    }
    @Test
    void testMillerRabinForPrimes() {
        assertTrue(MillerRabinPrimalityCheck.millerRabin(11, 5));
        assertTrue(MillerRabinPrimalityCheck.millerRabin(97, 5));
        assertTrue(MillerRabinPrimalityCheck.millerRabin(6720589, 5));
        assertTrue(MillerRabinPrimalityCheck.millerRabin(9549401549L, 5));
    }
}
