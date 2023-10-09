package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SubsetCountTest {
    public static SubsetCount obj = new SubsetCount();

    @Test
    void hasMultipleSubset() {
        int[] arr = new int[] {1, 2, 3, 3};
        assertEquals(3, obj.getCount(arr, 6));
    }
    @Test
    void singleElementSubset() {
        int[] arr = new int[] {1, 1, 1, 1};
        assertEquals(4, obj.getCount(arr, 1));
    }

    @Test
    void hasMultipleSubsetSO() {
        int[] arr = new int[] {1, 2, 3, 3};
        assertEquals(3, obj.getCountSO(arr, 6));
    }
    @Test
    void singleSubsetSO() {
        int[] arr = new int[] {1, 1, 1, 1};
        assertEquals(1, obj.getCountSO(arr, 4));
    }
}
