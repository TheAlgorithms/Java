package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaximumSumOfNonAdjacentElementsTest {

    @Test
    public void testGetMaxSumApproach1() {
        // Test with various cases
        assertEquals(15, MaximumSumOfNonAdjacentElements.getMaxSumApproach1(new int[] {3, 2, 5, 10, 7})); // 3 + 7 + 5
        assertEquals(10, MaximumSumOfNonAdjacentElements.getMaxSumApproach1(new int[] {5, 1, 1, 5})); // 5 + 5
        assertEquals(0, MaximumSumOfNonAdjacentElements.getMaxSumApproach1(new int[] {})); // Empty array
        assertEquals(1, MaximumSumOfNonAdjacentElements.getMaxSumApproach1(new int[] {1})); // Single element
        assertEquals(2, MaximumSumOfNonAdjacentElements.getMaxSumApproach1(new int[] {1, 2})); // Take max of both
        assertEquals(3, MaximumSumOfNonAdjacentElements.getMaxSumApproach1(new int[] {3, 2})); // Take 3
    }

    @Test
    public void testGetMaxSumApproach2() {
        // Test with various cases
        assertEquals(15, MaximumSumOfNonAdjacentElements.getMaxSumApproach2(new int[] {3, 2, 5, 10, 7})); // 3 + 7 + 5
        assertEquals(10, MaximumSumOfNonAdjacentElements.getMaxSumApproach2(new int[] {5, 1, 1, 5})); // 5 + 5
        assertEquals(0, MaximumSumOfNonAdjacentElements.getMaxSumApproach2(new int[] {})); // Empty array
        assertEquals(1, MaximumSumOfNonAdjacentElements.getMaxSumApproach2(new int[] {1})); // Single element
        assertEquals(2, MaximumSumOfNonAdjacentElements.getMaxSumApproach2(new int[] {1, 2})); // Take max of both
        assertEquals(3, MaximumSumOfNonAdjacentElements.getMaxSumApproach2(new int[] {3, 2})); // Take 3
    }
}
