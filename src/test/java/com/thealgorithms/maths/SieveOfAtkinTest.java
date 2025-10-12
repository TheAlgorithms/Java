package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@code SieveOfAtkin} class.
 */
class SieveOfAtkinTest {

    @Test
    void testGeneratePrimesLimit10() {
        List<Integer> primes = SieveOfAtkin.generatePrimes(10);
        // Assert the full expected list of primes
        List<Integer> expected = List.of(2, 3, 5, 7);
        assertEquals(expected, primes, "Primes up to 10 should match expected list");
    }

    @Test
    void testGeneratePrimesLimit2() {
        List<Integer> primes = SieveOfAtkin.generatePrimes(2);
        List<Integer> expected = List.of(2);
        assertEquals(expected, primes, "Primes up to 2 should include 2");
    }

    @Test
    void testGeneratePrimesLimit1() {
        List<Integer> primes = SieveOfAtkin.generatePrimes(1);
        assertTrue(primes.isEmpty(), "Primes list should be empty when limit < 2");
    }

    @Test
    void testGeneratePrimesLimit50() {
        List<Integer> primes = SieveOfAtkin.generatePrimes(50);
        List<Integer> expected = List.of(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47);
        assertEquals(expected, primes, "Primes up to 50 should match expected list");
    }

    @Test
    void testGeneratePrimesNegativeLimit() {
        List<Integer> primes = SieveOfAtkin.generatePrimes(-10);
        assertTrue(primes.isEmpty(), "Primes list should be empty for negative limit");
    }
}
