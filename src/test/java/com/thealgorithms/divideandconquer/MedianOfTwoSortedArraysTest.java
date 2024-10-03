package com.thealgorithms.divideandconquer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MedianOfTwoSortedArraysTest {

    @Test
    void testFindMedianSortedArrays() {
        // Test case 1: Arrays of equal length
        assertEquals(2.5, MedianOfTwoSortedArrays.findMedianSortedArrays(new int[] {1, 3}, new int[] {2, 4}));

        // Test case 2: Arrays of different lengths
        assertEquals(2.0, MedianOfTwoSortedArrays.findMedianSortedArrays(new int[] {1, 3}, new int[] {2}));

        // Test case 3: Arrays with even total length
        assertEquals(4.5, MedianOfTwoSortedArrays.findMedianSortedArrays(new int[] {1, 2, 8}, new int[] {3, 4, 5, 6, 7}));

        // Test case 4: Arrays with odd total length
        assertEquals(3.5, MedianOfTwoSortedArrays.findMedianSortedArrays(new int[] {1, 2, 8}, new int[] {3, 4, 5}));

        // Test case 5: Single element arrays
        assertEquals(2.0, MedianOfTwoSortedArrays.findMedianSortedArrays(new int[] {1}, new int[] {3}));

        // Test case 6: Empty arrays
        assertEquals(0.0, MedianOfTwoSortedArrays.findMedianSortedArrays(new int[] {}, new int[] {0}));

        // Test case 7: Same element arrays
        assertEquals(2.0, MedianOfTwoSortedArrays.findMedianSortedArrays(new int[] {2, 2, 2}, new int[] {2, 2, 2}));
    }
}
