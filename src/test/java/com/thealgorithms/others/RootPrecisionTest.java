package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RootPrecisionTest {

    @Test
    public void testSquareRootWithZeroPrecision() {
        assertEquals(2.0, RootPrecision.calculateSquareRoot(4, 0));
        assertEquals(3.0, RootPrecision.calculateSquareRoot(9, 0));
        assertEquals(5.0, RootPrecision.calculateSquareRoot(25, 0));
    }

    @Test
    public void testSquareRootWithPrecision() {
        assertEquals(1.414, RootPrecision.calculateSquareRoot(2, 3));
        assertEquals(3.162, RootPrecision.calculateSquareRoot(10, 3));
        assertEquals(5.000, RootPrecision.calculateSquareRoot(25, 3));
    }

    @Test
    public void testSquareRootWithHighPrecision() {
        assertEquals(1.41421, RootPrecision.calculateSquareRoot(2, 5));
        assertEquals(3.16228, RootPrecision.calculateSquareRoot(10, 5));
    }

    @Test
    public void testSquareRootOfZero() {
        assertEquals(0.0, RootPrecision.calculateSquareRoot(0, 3));
    }
}
