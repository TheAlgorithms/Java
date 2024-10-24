package com.thealgorithms.datastructures.heaps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class KthElementFinderTest {
    @Test
    public void testFindKthLargest() {
        int[] nums = {3, 2, 1, 5, 6, 4};
        assertEquals(5, KthElementFinder.findKthLargest(nums, 2));
    }

    @Test
    public void testFindKthSmallest() {
        int[] nums = {7, 10, 4, 3, 20, 15};
        assertEquals(7, KthElementFinder.findKthSmallest(nums, 3));
    }
}
