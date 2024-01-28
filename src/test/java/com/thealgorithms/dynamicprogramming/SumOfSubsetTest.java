package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SumOfSubsetTest {

    @Test
    void basicCheck() {
        assertEquals(false, SumOfSubset.subsetSum(new int[] {1, 2, 7, 10, 9}, 4, 14));
        assertEquals(false, SumOfSubset.subsetSum(new int[] {2, 15, 1, 6, 7}, 4, 4));
        assertEquals(true, SumOfSubset.subsetSum(new int[] {7, 3, 2, 5, 8}, 4, 14));
        assertEquals(true, SumOfSubset.subsetSum(new int[] {4, 3, 2, 1}, 3, 5));
        assertEquals(true, SumOfSubset.subsetSum(new int[] {1, 7, 2, 9, 10}, 4, 13));
    }
}
