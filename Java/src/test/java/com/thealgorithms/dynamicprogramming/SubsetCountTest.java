package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SubsetCountTest {
    @Test
    void hasMultipleSubset() {
        int[] arr = new int[] {1, 2, 3, 3};
        assertEquals(3, SubsetCount.getCount(arr, 6));
    }
    @Test
    void singleElementSubset() {
        int[] arr = new int[] {1, 1, 1, 1};
        assertEquals(4, SubsetCount.getCount(arr, 1));
    }

    @Test
    void hasMultipleSubsetSO() {
        int[] arr = new int[] {1, 2, 3, 3};
        assertEquals(3, SubsetCount.getCountSO(arr, 6));
    }
    @Test
    void singleSubsetSO() {
        int[] arr = new int[] {1, 1, 1, 1};
        assertEquals(1, SubsetCount.getCountSO(arr, 4));
    }
}
