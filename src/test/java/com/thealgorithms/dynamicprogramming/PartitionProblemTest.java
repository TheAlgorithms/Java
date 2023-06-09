package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PartitionProblemTest {
    @Test
    public void testIfSumOfTheArrayIsOdd() {
        assertFalse(PartitionProblem.partition(new int[] {1, 2, 2}));
    }
    @Test
    public void testIfSizeOfTheArrayIsOne() {
        assertFalse(PartitionProblem.partition(new int[] {2}));
    }
    @Test
    public void testIfSumOfTheArrayIsEven1() {
        assertTrue(PartitionProblem.partition(new int[] {1, 2, 3, 6}));
    }
    @Test
    public void testIfSumOfTheArrayIsEven2() {
        assertFalse(PartitionProblem.partition(new int[] {1, 2, 3, 8}));
    }
}