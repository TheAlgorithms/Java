package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Test class for {@link Median}.
 * Tests various scenarios including edge cases, odd/even length arrays,
 * negative values, and unsorted inputs.
 */
class MedianTest {

    @Test
    void testMedianSingleValue() {
        int[] arr = {0};
        assertEquals(0, Median.median(arr));
    }

    @Test
    void testMedianSinglePositiveValue() {
        int[] arr = {42};
        assertEquals(42, Median.median(arr));
    }

    @Test
    void testMedianSingleNegativeValue() {
        int[] arr = {-15};
        assertEquals(-15, Median.median(arr));
    }

    @Test
    void testMedianTwoValues() {
        int[] arr = {1, 2};
        assertEquals(1.5, Median.median(arr));
    }

    @Test
    void testMedianTwoIdenticalValues() {
        int[] arr = {5, 5};
        assertEquals(5.0, Median.median(arr));
    }

    @Test
    void testMedianThreeValues() {
        int[] arr = {1, 2, 3};
        assertEquals(2, Median.median(arr));
    }

    @Test
    void testMedianThreeUnsortedValues() {
        int[] arr = {3, 1, 2};
        assertEquals(2, Median.median(arr));
    }

    @Test
    void testMedianDecimalValueReturn() {
        int[] arr = {1, 2, 3, 4, 5, 6, 8, 9};
        assertEquals(4.5, Median.median(arr));
    }

    @Test
    void testMedianNegativeValues() {
        int[] arr = {-27, -16, -7, -4, -2, -1};
        assertEquals(-5.5, Median.median(arr));
    }

    @Test
    void testMedianMixedPositiveAndNegativeValues() {
        int[] arr = {-10, -5, 0, 5, 10};
        assertEquals(0, Median.median(arr));
    }

    @Test
    void testMedianMixedUnsortedValues() {
        int[] arr = {10, -5, 0, 5, -10};
        assertEquals(0, Median.median(arr));
    }

    @Test
    void testMedianLargeOddArray() {
        int[] arr = {9, 7, 5, 3, 1, 2, 4, 6, 8};
        assertEquals(5, Median.median(arr));
    }

    @Test
    void testMedianLargeEvenArray() {
        int[] arr = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        assertEquals(55.0, Median.median(arr));
    }

    @Test
    void testMedianAllSameValues() {
        int[] arr = {7, 7, 7, 7, 7};
        assertEquals(7.0, Median.median(arr));
    }

    @Test
    void testMedianWithZeros() {
        int[] arr = {0, 0, 0, 0, 0};
        assertEquals(0.0, Median.median(arr));
    }

    @Test
    void testMedianAlreadySorted() {
        int[] arr = {1, 2, 3, 4, 5};
        assertEquals(3, Median.median(arr));
    }

    @Test
    void testMedianReverseSorted() {
        int[] arr = {5, 4, 3, 2, 1};
        assertEquals(3, Median.median(arr));
    }

    @Test
    void testMedianWithDuplicates() {
        int[] arr = {1, 2, 2, 3, 3, 3, 4};
        assertEquals(3, Median.median(arr));
    }

    @Test
    void testMedianEmptyArrayThrows() {
        int[] arr = {};
        assertThrows(IllegalArgumentException.class, () -> Median.median(arr));
    }

    @Test
    void testMedianNullArrayThrows() {
        assertThrows(IllegalArgumentException.class, () -> Median.median(null));
    }

    @Test
    void testMedianExtremeValues() {
        int[] arr = {Integer.MAX_VALUE, Integer.MIN_VALUE};
        assertEquals(-0.5, Median.median(arr));
    }

    @Test
    void testMedianTwoNegativeValues() {
        int[] arr = {-10, -20};
        assertEquals(-15.0, Median.median(arr));
    }

    @Test
    void testMedianFourValuesEven() {
        int[] arr = {1, 2, 3, 4};
        assertEquals(2.5, Median.median(arr));
    }

    @Test
    void testMedianFiveValuesOdd() {
        int[] arr = {10, 20, 30, 40, 50};
        assertEquals(30.0, Median.median(arr));
    }
}
