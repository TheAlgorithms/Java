package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MedianTest {
    @Test
    void medianSingleValue() {
        int[] arr = {0};
        assertEquals(0, Median.median(arr));
    }

    @Test
    void medianTwoValues() {
        int[] arr = {1, 2};
        assertEquals(1.5, Median.median(arr));
    }

    @Test
    void medianThreeValues() {
        int[] arr = {1, 2, 3};
        assertEquals(2, Median.median(arr));
    }

    @Test
    void medianDecimalValueReturn() {
        int[] arr = {1, 2, 3, 4, 5, 6, 8, 9};
        assertEquals(4.5, Median.median(arr));
    }

    @Test
    void medianNegativeValues() {
        int[] arr = {-27, -16, -7, -4, -2, -1};
        assertEquals(-5.5, Median.median(arr));
    }
}
