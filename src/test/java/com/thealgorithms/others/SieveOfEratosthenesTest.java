package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class SieveOfEratosthenesTest {
    @Test
    public void testfFindPrimesTill1() {
        assertArrayEquals(new int[] {}, SieveOfEratosthenes.findPrimesTill(1));
    }

    @Test
    public void testfFindPrimesTill2() {
        assertArrayEquals(new int[] {2}, SieveOfEratosthenes.findPrimesTill(2));
    }

    @Test
    public void testfFindPrimesTill4() {
        var primesTill4 = new int[] {2, 3};
        assertArrayEquals(primesTill4, SieveOfEratosthenes.findPrimesTill(3));
        assertArrayEquals(primesTill4, SieveOfEratosthenes.findPrimesTill(4));
    }

    @Test
    public void testfFindPrimesTill40() {
        var primesTill40 = new int[] {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37};
        assertArrayEquals(primesTill40, SieveOfEratosthenes.findPrimesTill(37));
        assertArrayEquals(primesTill40, SieveOfEratosthenes.findPrimesTill(38));
        assertArrayEquals(primesTill40, SieveOfEratosthenes.findPrimesTill(39));
        assertArrayEquals(primesTill40, SieveOfEratosthenes.findPrimesTill(40));
    }

    @Test
    public void testfFindPrimesTill240() {
        var primesTill240 = new int[] {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239};
        assertArrayEquals(primesTill240, SieveOfEratosthenes.findPrimesTill(239));
        assertArrayEquals(primesTill240, SieveOfEratosthenes.findPrimesTill(240));
    }

    @Test
    public void testFindPrimesTillThrowsExceptionForNonPositiveInput() {
        assertThrows(IllegalArgumentException.class, () -> SieveOfEratosthenes.findPrimesTill(0));
    }
}
