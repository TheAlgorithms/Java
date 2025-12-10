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

    @Test
    public void testEmptyArray() {
        int[] arr = {};
        assertEquals(0, CountingInversions.countInversions(arr));
    }

    @Test
    public void testArrayWithDuplicates() {
        int[] arr = {1, 3, 2, 3, 1};
        // Inversions: (3,2), (3,1), (3,1), (2,1)
        assertEquals(4, CountingInversions.countInversions(arr));
    }

    @Test
    public void testLargeArray() {
        int n = 1000;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = n - i; // descending order -> max inversions = n*(n-1)/2
        }
        int expected = n * (n - 1) / 2;
        assertEquals(expected, CountingInversions.countInversions(arr));
    }

    @Test
    public void testArrayWithAllSameElements() {
        int[] arr = {7, 7, 7, 7};
        // No inversions since all elements are equal
        assertEquals(0, CountingInversions.countInversions(arr));
    }
}
