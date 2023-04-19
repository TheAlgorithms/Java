package com.thealgorithms.divideandconquer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MaximumSubarrayAlgorithmTest {

    @Test
    void testFindMaxCrossingSubarray() {
        int[] A = {1, -3, 2, -5, 7, 6, -1, -4, 11, -23};
        int[] result = MaximumSubarrayAlgorithm.findMaxCrossingSubarray(A, 0, 4, 9);
        assertArrayEquals(new int[]{4, 8, 19}, result);
    }

    @Test
    void testFindMaximumSubarray() {
        int[] A = {1, -3, 2, -5, 7, 6, -1, -4, 11, -23};
        int[] result = MaximumSubarrayAlgorithm.findMaximumSubarray(A, 0, A.length - 1);
        assertArrayEquals(new int[]{4, 8, 19}, result);
    }
}
