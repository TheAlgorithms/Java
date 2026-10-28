package com.thealgorithms.prefixsum;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PrefixSumTest {

    @Test
    @DisplayName("Test basic sum with positive integers")
    void testStandardCase() {
        int[] input = {1, 2, 3, 4, 5};
        PrefixSum ps = new PrefixSum(input);

        // Sum of range [0, 4] -> 15
        assertEquals(15L, ps.sumRange(0, 4));

        // Sum of range [1, 3] -> 9
        assertEquals(9L, ps.sumRange(1, 3));
    }

    @Test
    @DisplayName("Test array with negative numbers and zeros")
    void testNegativeAndZeros() {
        int[] input = {-2, 0, 3, -5, 2, -1};
        PrefixSum ps = new PrefixSum(input);

        assertEquals(1L, ps.sumRange(0, 2));
        assertEquals(-1L, ps.sumRange(2, 5));
        assertEquals(0L, ps.sumRange(1, 1));
    }

    @Test
    @DisplayName("Test with large integers to verify overflow handling")
    void testLargeNumbers() {
        // Two values that fit in int, but their sum exceeds Integer.MAX_VALUE
        // Integer.MAX_VALUE is approx 2.14 billion.
        int val = 2_000_000_000;
        int[] input = {val, val, val};
        PrefixSum ps = new PrefixSum(input);

        // Sum of three 2 billion values is 6 billion (fits in long, overflows int)
        assertEquals(6_000_000_000L, ps.sumRange(0, 2));
    }

    @Test
    @DisplayName("Test single element array")
    void testSingleElement() {
        int[] input = {42};
        PrefixSum ps = new PrefixSum(input);
        assertEquals(42L, ps.sumRange(0, 0));
    }

    @Test
    @DisplayName("Test constructor with null input")
    void testNullInput() {
        assertThrows(IllegalArgumentException.class, () -> new PrefixSum(null));
    }

    @Test
    @DisplayName("Test empty array behavior")
    void testEmptyArray() {
        int[] input = {};
        PrefixSum ps = new PrefixSum(input);
        assertThrows(IndexOutOfBoundsException.class, () -> ps.sumRange(0, 0));
    }

    @Test
    @DisplayName("Test invalid range indices")
    void testInvalidIndices() {
        int[] input = {10, 20, 30};
        PrefixSum ps = new PrefixSum(input);

        assertThrows(IndexOutOfBoundsException.class, () -> ps.sumRange(-1, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> ps.sumRange(0, 3));
        assertThrows(IndexOutOfBoundsException.class, () -> ps.sumRange(2, 1));
    }
}
