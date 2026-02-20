package com.thealgorithms.prefixsum;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link RangeSumQuery}.
 */
class RangeSumQueryTest {

    @Test
    void testBasicExample() {
        int[] nums = {1, 2, 3, 4, 5};
        int[] prefixSum = RangeSumQuery.buildPrefixSum(nums);

        assertEquals(6, RangeSumQuery.sumRange(prefixSum, 0, 2)); // 1+2+3
        assertEquals(9, RangeSumQuery.sumRange(prefixSum, 1, 3)); // 2+3+4
        assertEquals(15, RangeSumQuery.sumRange(prefixSum, 0, 4)); // 1+2+3+4+5
        assertEquals(12, RangeSumQuery.sumRange(prefixSum, 2, 4)); // 3+4+5
    }

    @Test
    void testSingleElement() {
        int[] nums = {7};
        int[] prefixSum = RangeSumQuery.buildPrefixSum(nums);

        assertEquals(7, RangeSumQuery.sumRange(prefixSum, 0, 0));
    }

    @Test
    void testAllZeros() {
        int[] nums = {0, 0, 0, 0};
        int[] prefixSum = RangeSumQuery.buildPrefixSum(nums);

        assertEquals(0, RangeSumQuery.sumRange(prefixSum, 0, 3));
        assertEquals(0, RangeSumQuery.sumRange(prefixSum, 1, 2));
    }

    @Test
    void testNegativeNumbers() {
        int[] nums = {-1, 2, -3, 4};
        int[] prefixSum = RangeSumQuery.buildPrefixSum(nums);

        assertEquals(-2, RangeSumQuery.sumRange(prefixSum, 0, 2)); // -1+2-3
        assertEquals(3, RangeSumQuery.sumRange(prefixSum, 1, 3)); // 2-3+4
    }

    @Test
    void testEmptyArrayThrowsException() {
        int[] nums = {};
        int[] prefixSum = RangeSumQuery.buildPrefixSum(nums);

        assertThrows(IllegalArgumentException.class, () -> RangeSumQuery.sumRange(prefixSum, 0, 0));
    }

    @Test
    void testNullArrayThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> RangeSumQuery.buildPrefixSum(null));
        assertThrows(IllegalArgumentException.class, () -> RangeSumQuery.sumRange(null, 0, 0));
    }

    @Test
    void testInvalidIndicesThrowsException() {
        int[] nums = {1, 2, 3};
        int[] prefixSum = RangeSumQuery.buildPrefixSum(nums);

        assertThrows(IllegalArgumentException.class, () -> RangeSumQuery.sumRange(prefixSum, -1, 2));
        assertThrows(IllegalArgumentException.class, () -> RangeSumQuery.sumRange(prefixSum, 1, 5));
        assertThrows(IllegalArgumentException.class, () -> RangeSumQuery.sumRange(prefixSum, 2, 1));
    }
}
