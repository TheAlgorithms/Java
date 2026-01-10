package com.thealgorithms.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CountNiceSubarraysTest {
    @Test
    void testExampleCase() {
        int[] nums = {1, 1, 2, 1, 1};
        assertEquals(2, CountNiceSubarrays.countNiceSubarrays(nums, 3));
    }

    @Test
    void testAllEvenNumbers() {
        int[] nums = {2, 4, 6, 8};
        assertEquals(0, CountNiceSubarrays.countNiceSubarrays(nums, 1));
    }

    @Test
    void testSingleOdd() {
        int[] nums = {1};
        assertEquals(1, CountNiceSubarrays.countNiceSubarrays(nums, 1));
    }

    @Test
    void testMultipleChoices() {
        int[] nums = {2, 2, 1, 2, 2, 1, 2};
        assertEquals(6, CountNiceSubarrays.countNiceSubarrays(nums, 2));
    }
}
