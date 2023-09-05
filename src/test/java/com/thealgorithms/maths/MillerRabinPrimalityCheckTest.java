package com.thealgorithms.maths;

import static com.thealgorithms.maths.MillerRabinPrimalityCheck.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MillerRabinPrimalityCheckTest {
    @Test
    void testDeterministicMillerRabinForPrimes() {
        assertTrue(deterministicMillerRabin(2));
        assertTrue(deterministicMillerRabin(37));
        assertTrue(deterministicMillerRabin(123457));
        assertTrue(deterministicMillerRabin(6472601713L));
    }
    @Test
    void testDeterministicMillerRabinForNotPrimes() {
        assertFalse(deterministicMillerRabin(1));
        assertFalse(deterministicMillerRabin(35));
        assertFalse(deterministicMillerRabin(123453));
        assertFalse(deterministicMillerRabin(647260175));
    }
    @Test
    void testMillerRabinForPrimes() {
        assertTrue(millerRabin(11, 5));
        assertTrue(millerRabin(97, 5));
        assertTrue(millerRabin(6720589, 5));
        assertTrue(millerRabin(9549401549L, 5));
    }
}
