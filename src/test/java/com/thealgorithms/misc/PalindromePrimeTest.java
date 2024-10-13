package com.thealgorithms.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

public class PalindromePrimeTest {

    @Test
    public void testPrimeWithPrimeNumbers() {
        assertTrue(PalindromePrime.prime(2), "2 should be prime");
        assertTrue(PalindromePrime.prime(3), "3 should be prime");
        assertTrue(PalindromePrime.prime(5), "5 should be prime");
        assertTrue(PalindromePrime.prime(11), "11 should be prime");
    }

    @Test
    public void testPrimeWithNonPrimeNumbers() {
        assertFalse(PalindromePrime.prime(1), "1 is not prime");
        assertFalse(PalindromePrime.prime(4), "4 is not prime");
        assertFalse(PalindromePrime.prime(9), "9 is not prime");
        assertFalse(PalindromePrime.prime(15), "15 is not prime");
    }

    @Test
    public void testReverse() {
        assertEquals(123, PalindromePrime.reverse(321), "Reverse of 321 should be 123");
        assertEquals(7, PalindromePrime.reverse(7), "Reverse of 7 should be 7");
        assertEquals(1221, PalindromePrime.reverse(1221), "Reverse of 1221 should be 1221");
    }

    @Test
    public void testGeneratePalindromePrimes() {
        List<Integer> result = PalindromePrime.generatePalindromePrimes(5);
        List<Integer> expected = List.of(2, 3, 5, 7, 11);
        assertEquals(expected, result, "The first 5 palindromic primes should be [2, 3, 5, 7, 11]");
    }

    @Test
    public void testGeneratePalindromePrimesWithZero() {
        List<Integer> result = PalindromePrime.generatePalindromePrimes(0);
        assertTrue(result.isEmpty(), "Generating 0 palindromic primes should return an empty list");
    }

    @Test
    public void testGeneratePalindromePrimesWithNegativeInput() {
        List<Integer> result = PalindromePrime.generatePalindromePrimes(-5);
        assertTrue(result.isEmpty(), "Generating a negative number of palindromic primes should return an empty list");
    }
}
