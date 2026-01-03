package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GCDUsingEuclidTest {

    @Test
    void testPositiveNumbers() {
        assertEquals(6, GCDUsingEuclid.gcd(54, 24));
        assertEquals(1, GCDUsingEuclid.gcd(17, 13));
    }

    @Test
    void testWithZero() {
        assertEquals(5, GCDUsingEuclid.gcd(0, 5));
        assertEquals(7, GCDUsingEuclid.gcd(7, 0));
    }

    @Test
    void testNegativeNumbers() {
        assertEquals(4, GCDUsingEuclid.gcd(-8, 12));
        assertEquals(9, GCDUsingEuclid.gcd(-9, -18));
    }
}
