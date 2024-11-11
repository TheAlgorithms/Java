package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SubsetSumSpaceOptimizedTest {

    @Test
    void basicCheck() {
        assertTrue(SubsetSumSpaceOptimized.isSubsetSum(new int[] {7, 3, 2, 5, 8}, 14));
        assertTrue(SubsetSumSpaceOptimized.isSubsetSum(new int[] {4, 3, 2, 1}, 5));
        assertTrue(SubsetSumSpaceOptimized.isSubsetSum(new int[] {1, 7, 2, 9, 10}, 13));
        assertFalse(SubsetSumSpaceOptimized.isSubsetSum(new int[] {1, 2, 7, 10, 9}, 14));
        assertFalse(SubsetSumSpaceOptimized.isSubsetSum(new int[] {2, 15, 1, 6, 7}, 4));
    }
}
