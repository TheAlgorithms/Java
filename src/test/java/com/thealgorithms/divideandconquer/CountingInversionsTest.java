package com.thealgorithms.divideandconquer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CountingInversionsTest {

    @Test
    public void testCountInversions() {
        int[] arr = {2, 3, 8, 6, 1};
        assertEquals(5, CountingInversions.countInversions(arr));
    }

    @Test
    public void testNoInversions() {
        int[] arr = {1, 2, 3, 4, 5};
        assertEquals(0, CountingInversions.countInversions(arr));
    }

    @Test
    public void testSingleElement() {
        int[] arr = {1};
        assertEquals(0, CountingInversions.countInversions(arr));
    }

    @Test
    public void testAllInversions() {
        int[] arr = {5, 4, 3, 2, 1};
        assertEquals(10, CountingInversions.countInversions(arr));
    }
}
