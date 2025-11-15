package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Test cases for Sieve of Eratosthenes algorithm
 *
 * @author Navadeep0007
 */
class SieveOfEratosthenesTest {

    @Test
    void testPrimesUpTo10() {
        List<Integer> expected = Arrays.asList(2, 3, 5, 7);
        assertEquals(expected, SieveOfEratosthenes.findPrimes(10));
    }

    @Test
    void testPrimesUpTo30() {
        List<Integer> expected = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        assertEquals(expected, SieveOfEratosthenes.findPrimes(30));
    }

    @Test
    void testPrimesUpTo2() {
        List<Integer> expected = Arrays.asList(2);
        assertEquals(expected, SieveOfEratosthenes.findPrimes(2));
    }

    @Test
    void testPrimesUpTo1() {
        assertTrue(SieveOfEratosthenes.findPrimes(1).isEmpty());
    }

    @Test
    void testPrimesUpTo0() {
        assertTrue(SieveOfEratosthenes.findPrimes(0).isEmpty());
    }

    @Test
    void testNegativeInput() {
        assertThrows(IllegalArgumentException.class, () -> { SieveOfEratosthenes.findPrimes(-1); });
    }

    @Test
    void testCountPrimes() {
        assertEquals(4, SieveOfEratosthenes.countPrimes(10));
        assertEquals(25, SieveOfEratosthenes.countPrimes(100));
    }

    @Test
    void testLargeNumber() {
        List<Integer> primes = SieveOfEratosthenes.findPrimes(1000);
        assertEquals(168, primes.size()); // There are 168 primes up to 1000
        assertEquals(2, primes.get(0)); // First prime
        assertEquals(997, primes.get(primes.size() - 1)); // Last prime up to 1000
    }
}
