package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaximumSumOfNonAdjacentElementsTest {

    // Tests for Approach1
    @Test
    public void testGetMaxSumApproach1WithEmptyArray() {
        assertEquals(0, MaximumSumOfNonAdjacentElements.getMaxSumApproach1(new int[] {})); // Empty array
    }

    @Test
    public void testGetMaxSumApproach1WithSingleElement() {
        assertEquals(1, MaximumSumOfNonAdjacentElements.getMaxSumApproach1(new int[] {1})); // Single element
    }

    @Test
    public void testGetMaxSumApproach1WithTwoElementsTakeMax() {
        assertEquals(2, MaximumSumOfNonAdjacentElements.getMaxSumApproach1(new int[] {1, 2})); // Take max of both
    }

    @Test
    public void testGetMaxSumApproach1WithMultipleElements() {
        assertEquals(15, MaximumSumOfNonAdjacentElements.getMaxSumApproach1(new int[] {3, 2, 5, 10, 7})); // 3 + 7 + 5
        assertEquals(10, MaximumSumOfNonAdjacentElements.getMaxSumApproach1(new int[] {5, 1, 1, 5})); // 5 + 5
    }

    // Tests for Approach2
    @Test
    public void testGetMaxSumApproach2WithEmptyArray() {
        assertEquals(0, MaximumSumOfNonAdjacentElements.getMaxSumApproach2(new int[] {})); // Empty array
    }

    @Test
    public void testGetMaxSumApproach2WithSingleElement() {
        assertEquals(1, MaximumSumOfNonAdjacentElements.getMaxSumApproach2(new int[] {1})); // Single element
    }

    @Test
    public void testGetMaxSumApproach2WithTwoElementsTakeMax() {
        assertEquals(2, MaximumSumOfNonAdjacentElements.getMaxSumApproach2(new int[] {1, 2})); // Take max of both
    }

    @Test
    public void testGetMaxSumApproach2WithMultipleElements() {
        assertEquals(15, MaximumSumOfNonAdjacentElements.getMaxSumApproach2(new int[] {3, 2, 5, 10, 7})); // 3 + 7 + 5
        assertEquals(10, MaximumSumOfNonAdjacentElements.getMaxSumApproach2(new int[] {5, 1, 1, 5})); // 5 + 5
    }
}
