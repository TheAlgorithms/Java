package com.thealgorithms.prefixsum;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link SubarraySumEqualsK}.
 */
class SubarraySumEqualsKTest {

    @Test
    void testBasicExample() {
        int[] nums = {1, 1, 1};
        int k = 2;
        assertEquals(2, SubarraySumEqualsK.countSubarrays(nums, k));
    }

    @Test
    void testWithNegativeNumbers() {
        int[] nums = {1, -1, 0};
        int k = 0;
        assertEquals(3, SubarraySumEqualsK.countSubarrays(nums, k));
    }

    @Test
    void testSingleElementEqualToK() {
        int[] nums = {5};
        int k = 5;
        assertEquals(1, SubarraySumEqualsK.countSubarrays(nums, k));
    }

    @Test
    void testSingleElementNotEqualToK() {
        int[] nums = {5};
        int k = 3;
        assertEquals(0, SubarraySumEqualsK.countSubarrays(nums, k));
    }

    @Test
    void testAllZeros() {
        int[] nums = {0, 0, 0};
        int k = 0;
        assertEquals(6, SubarraySumEqualsK.countSubarrays(nums, k));
    }

    @Test
    void testEmptyArray() {
        int[] nums = {};
        int k = 0;
        assertEquals(0, SubarraySumEqualsK.countSubarrays(nums, k));
    }

    @Test
    void testNullArrayThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> SubarraySumEqualsK.countSubarrays(null, 0));
    }
}
