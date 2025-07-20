package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BinaryPowTest {

    @Test
    void testBinPow() {
        assertEquals(4, BinaryPow.binPow(2, 2));
        assertEquals(256, BinaryPow.binPow(4, 4));
        assertEquals(729, BinaryPow.binPow(9, 3));
        assertEquals(262144, BinaryPow.binPow(8, 6));
    }

    @Test
    void testZeroExponent() {
        assertEquals(1, BinaryPow.binPow(2, 0));
        assertEquals(1, BinaryPow.binPow(100, 0));
        assertEquals(1, BinaryPow.binPow(-5, 0));
    }

    @Test
    void testZeroBase() {
        assertEquals(0, BinaryPow.binPow(0, 5));
        assertEquals(1, BinaryPow.binPow(0, 0));
    }

    @Test
    void testOneBase() {
        assertEquals(1, BinaryPow.binPow(1, 100));
        assertEquals(1, BinaryPow.binPow(1, 0));
    }

    @Test
    void testNegativeBase() {
        assertEquals(-8, BinaryPow.binPow(-2, 3));
        assertEquals(16, BinaryPow.binPow(-2, 4));
    }

    @Test
    void testLargeExponent() {
        assertEquals(1073741824, BinaryPow.binPow(2, 30));
    }
}
