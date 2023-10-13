package com.thealgorithms.stacks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class LargestAreaInHistogramTest {

    @Test
    void testForExample1() {
        int[] a = {6, 2, 5, 4, 5, 6};
        int result = LargestAreaInHistogram.getMaxArea(a);
        assertEquals(12, result);
    }

    @Test
    void testForExample2() {
        int[] a = {2, 4, 2, 6, 1, 5, 4, 2, 4};
        int result = LargestAreaInHistogram.getMaxArea(a);
        assertEquals(10, result);
    }

    @Test
    void testForEmptyHistogram() {
        int[] a = {};
        int result = LargestAreaInHistogram.getMaxArea(a);
        assertEquals(0, result);
    }

    @Test
    void testForSingleElementHistogram() {
        int[] a = {5};
        int result = LargestAreaInHistogram.getMaxArea(a);
        assertEquals(5, result);
    }

    @Test
    void testForAllEqualElements() {
        int[] a = {3, 3, 3, 3, 3, 3};
        int result = LargestAreaInHistogram.getMaxArea(a);
        assertEquals(18, result);
    }
}
