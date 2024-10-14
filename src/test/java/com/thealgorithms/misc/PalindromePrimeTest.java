package com.thealgorithms.misc;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the PalindromePrime class.
 */
public class PalindromePrimeTest {

    /**
     * Test the findPalindromicPrimes method with a positive number.
     */
    @Test
    public void testFindPalindromicPrimesPositive() {
        int[] result = PalindromePrime.findPalindromicPrimes(5);
        assertArrayEquals(new int[] {2, 3, 5, 7, 11}, result);
    }

    /**
     * Test the findPalindromicPrimes method with zero.
     */
    @Test
    public void testFindPalindromicPrimesZero() {
        int[] result = PalindromePrime.findPalindromicPrimes(0);
        assertArrayEquals(new int[] {}, result);
    }

    /**
     * Test the findNthPalindromicPrime method with a positive number.
     */
    @Test
    public void testFindNthPalindromicPrimePositive() {
        int result = PalindromePrime.findNthPalindromicPrime(5);
        assertEquals(11, result);
    }

    /**
     * Test the findNthPalindromicPrime method with one.
     */
    @Test
    public void testFindNthPalindromicPrimeOne() {
        int result = PalindromePrime.findNthPalindromicPrime(1);
        assertEquals(2, result);
    }

    /**
     * Test the findNthPalindromicPrime method with zero.
     */
    @Test
    public void testFindNthPalindromicPrimeZero() {
        assertThrows(IllegalArgumentException.class, () -> PalindromePrime.findNthPalindromicPrime(0));
    }

    /**
     * Test the findNthPalindromicPrime method with a negative number.
     */
    @Test
    public void testFindNthPalindromicPrimeNegative() {
        assertThrows(IllegalArgumentException.class, () -> PalindromePrime.findNthPalindromicPrime(-1));
    }

    /**
     * Test the findPalindromicPrimes method with a large number.
     */
    @Test
    public void testFindPalindromicPrimesLarge() {
        int[] result = PalindromePrime.findPalindromicPrimes(10);
        assertArrayEquals(new int[] {2, 3, 5, 7, 11, 101, 131, 151, 181, 191}, result);
    }

    /**
     * Test the findNthPalindromicPrime method with a large number.
     */
    @Test
    public void testFindNthPalindromicPrimeLarge() {
        int result = PalindromePrime.findNthPalindromicPrime(10);
        assertEquals(191, result);
    }
}
