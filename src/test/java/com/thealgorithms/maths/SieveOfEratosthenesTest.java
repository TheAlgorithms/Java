package com.thealgorithms.maths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link SieveOfEratosthenes}.
 */
public final class SieveOfEratosthenesTest {

    @Test
    void testPrimesUpTo30() {
        int[] expected = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
        Assertions.assertArrayEquals(expected, SieveOfEratosthenes.sieve(30));
    }

    @Test
    void testLessThanTwo() {
        Assertions.assertArrayEquals(new int[0], SieveOfEratosthenes.sieve(1));
        Assertions.assertArrayEquals(new int[0], SieveOfEratosthenes.sieve(0));
        Assertions.assertArrayEquals(new int[0], SieveOfEratosthenes.sieve(-5));
    }
}
