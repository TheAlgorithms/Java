package com.thealgorithms.dynamicprogramming;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinimumSumPartitionTest {

    @Test
    public void testMinimumSumPartitionWithEvenSum() {
        int[] array = {3, 1, 4, 2, 2};
        assertEquals(0, MinimumSumPartition.minimumSumPartition(array));
    }

    @Test
    public void testMinimumSumPartitionWithOddSum() {
        int[] array = {3, 7, 4, 5, 2};
        assertEquals(1, MinimumSumPartition.minimumSumPartition(array));
    }

    @Test
    public void testMinimumSumPartitionWithSingleElement() {
        int[] array = {7};
        assertEquals(7, MinimumSumPartition.minimumSumPartition(array));
    }

    @Test
    public void testMinimumSumPartitionWithLargeNumbers() {
        int[] array = {100, 200, 300, 400, 500};
        assertEquals(100, MinimumSumPartition.minimumSumPartition(array));
    }

    @Test
    public void testMinimumSumPartitionWithEmptyArray() {
        int[] array = {};
        assertEquals(0, MinimumSumPartition.minimumSumPartition(array));
    }
}
