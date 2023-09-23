package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class MinimumSumPartitionTest {
    @Test
    public void testMinimumSumPartitionWithEvenSum() {
        int[] array = {1, 6, 11, 4};
        assertEquals(0, MinimumSumPartition.minimumSumPartition(array));
    }

    @Test
    public void testMinimumSumPartitionWithOddSum() {
        int[] array = {36, 7, 46, 40};
        assertEquals(23, MinimumSumPartition.minimumSumPartition(array));
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

    @Test
    public void testMinimumSumPartitionThrowsForNegativeArray() {
        int[] array = {4, 1, -6, 7};
        assertThrows(IllegalArgumentException.class, () -> { MinimumSumPartition.minimumSumPartition(array); });
    }
}
