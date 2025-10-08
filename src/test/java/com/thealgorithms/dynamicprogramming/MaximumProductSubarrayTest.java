package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MaximumProductSubarrayTest {

    /**
     * Test case for an array with all positive numbers.
     * The expected maximum product is the product of all elements.
     */
    @Test
    void testAllPositiveNumbers() {
        int[] nums = {2, 3, 4};
        int expected = 24;
        int actual = MaximumProductSubarray.maxProduct(nums);
        assertEquals(expected, actual, "The maximum product should be 24.");
    }

    /**
     * Test case for an array with positive and negative numbers.
     * The expected maximum product is 24 (subarray [2, -3, -4]).
     */
    @Test
    void testMixedPositiveAndNegative() {
        int[] nums = {2, -3, -4, 1};
        int expected = 24;
        int actual = MaximumProductSubarray.maxProduct(nums);
        assertEquals(expected, actual, "The maximum product should be 24.");
    }

    /**
     * Test case for an array containing zeros.
     * The expected maximum product is 24 (subarray [2, 3, 4]).
     */
    @Test
    void testArrayWithZeros() {
        int[] nums = {2, 3, 0, 4, 6};
        int expected = 24;
        int actual = MaximumProductSubarray.maxProduct(nums);
        assertEquals(expected, actual, "The maximum product should be 24.");
    }

    /**
     * Test case for an array with a single element.
     * The expected maximum product is the element itself.
     */
    @Test
    void testSingleElement() {
        int[] nums = {5};
        int expected = 5;
        int actual = MaximumProductSubarray.maxProduct(nums);
        assertEquals(expected, actual, "The maximum product should be 5.");
    }

    /**
     * Test case for an array with all negative numbers.
     * The expected maximum product is the largest single negative number.
     */
    @Test
    void testAllNegativeNumbers() {
        int[] nums = {-2, -3, -4};
        int expected = 12;
        int actual = MaximumProductSubarray.maxProduct(nums);
        assertEquals(expected, actual, "The maximum product should be 12.");
    }

    /**
     * Test case for an array with negative numbers where odd count of negatives
     * breaks the chain. The expected maximum product is 60 (subarray [-2, -3, 10]).
     */
    @Test
    void testOddNegativeNumbers() {
        int[] nums = {-2, -3, 10, -1};
        int expected = 60;
        int actual = MaximumProductSubarray.maxProduct(nums);
        assertEquals(expected, actual, "The maximum product should be 60.");
    }

    /**
     * Test case for an empty array.
     * The expected maximum product is 0.
     */
    @Test
    void testEmptyArray() {
        int[] nums = {};
        int expected = 0;
        int actual = MaximumProductSubarray.maxProduct(nums);
        assertEquals(expected, actual, "The maximum product should be 0 for an empty array.");
    }

    /**
     * Test case for an array with alternating positive and negative numbers.
     * The expected maximum product is 6 (subarray [2, 3]).
     */
    @Test
    void testAlternatingNumbers() {
        int[] nums = {2, 3, -2, 4};
        int expected = 6;
        int actual = MaximumProductSubarray.maxProduct(nums);
        assertEquals(expected, actual, "The maximum product should be 6.");
    }

    /**
     * Test case for the memoized version with all positive numbers.
     * The expected maximum product is 24.
     */
    @Test
    void testMemoizedAllPositiveNumbers() {
        int[] nums = {2, 3, 4};
        int expected = 24;
        int actual = MaximumProductSubarray.maxProductMemoized(nums);
        assertEquals(expected, actual, "The maximum product should be 24.");
    }

    /**
     * Test case for the memoized version with mixed positive and negative numbers.
     * The expected maximum product is 24.
     */
    @Test
    void testMemoizedMixedNumbers() {
        int[] nums = {2, -3, -4, 1};
        int expected = 24;
        int actual = MaximumProductSubarray.maxProductMemoized(nums);
        assertEquals(expected, actual, "The maximum product should be 24.");
    }

    /**
     * Test case for an array with large positive and negative numbers.
     * The expected maximum product is 720 (subarray [6, -3, -20]).
     */
    @Test
    void testLargeNumbers() {
        int[] nums = {6, -3, -20, 0, 5};
        int expected = 360;
        int actual = MaximumProductSubarray.maxProduct(nums);
        assertEquals(expected, actual, "The maximum product should be 360.");
    }
}