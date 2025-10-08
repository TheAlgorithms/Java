package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@code SieveOfAtkin} class.
 */
class SieveOfAtkinTest {

    /**
     * Tests prime generation for a small limit of 10.
     * Expected primes are 2, 3, 5, 7.
     */
    @Test
    void testGeneratePrimesLimit10() {
        List<Integer> primes = SieveOfAtkin.generatePrimes(10);
        assertEquals(List.of(2, 3, 5, 7), primes);
    }

    /**
     * Tests prime generation when limit is 2.
     * Expected result is a single prime number 2.
     */
    @Test
    void testGeneratePrimesLimit2() {
        List<Integer> primes = SieveOfAtkin.generatePrimes(2);
        assertEquals(List.of(2), primes);
    }

    /**
     * Tests prime generation when limit is less than 2.
     * Expected result is an empty list.
     */
    @Test
    void testGeneratePrimesLimit1() {
        List<Integer> primes = SieveOfAtkin.generatePrimes(1);
        assertTrue(primes.isEmpty());
    }

    /**
     * Tests prime generation for a moderate limit of 50.
     * Verifies that the list contains all correct primes up to 50.
     */
    @Test
    void testGeneratePrimesLimit50() {
        List<Integer> primes = SieveOfAtkin.generatePrimes(50);
        assertEquals(List.of(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47), primes);
    }

    /**
     * Tests prime generation when limit is negative.
     * Expected result is an empty list.
     */
    @Test
    void testGeneratePrimesNegativeLimit() {
        List<Integer> primes = SieveOfAtkin.generatePrimes(-10);
        assertTrue(primes.isEmpty());
    }
}
