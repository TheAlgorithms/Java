package com.thealgorithms.slidingwindow;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link MaxFrequencyAfterOperations}.
 */
public class MaxFrequencyAfterOperationsTest {

    @Test
    public void testExample1() {
        int[] nums = { 1, 4, 5 };
        int k = 1, ops = 2;
        int result = MaxFrequencyAfterOperations.maxFrequency(nums, k, ops);
        assertEquals(2, result);
    }

    @Test
    public void testExample2() {
        int[] nums = { 5, 11, 20, 20 };
        int k = 5, ops = 1;
        int result = MaxFrequencyAfterOperations.maxFrequency(nums, k, ops);
        assertEquals(2, result);
    }

    @Test
    public void testNoOperations() {
        int[] nums = { 1, 2, 3, 4 };
        int k = 2, ops = 0;
        int result = MaxFrequencyAfterOperations.maxFrequency(nums, k, ops);
        assertEquals(1, result);
    }

    @Test
    public void testAllSameElements() {
        int[] nums = { 7, 7, 7, 7 };
        int k = 5, ops = 2;
        int result = MaxFrequencyAfterOperations.maxFrequency(nums, k, ops);
        assertEquals(4, result);
    }

    @Test
    public void testLargeK() {
        int[] nums = { 1, 100, 200 };
        int k = 200, ops = 2;
        int result = MaxFrequencyAfterOperations.maxFrequency(nums, k, ops);
        assertEquals(3, result);
    }

    @Test
    public void testEmptyArray() {
        int[] nums = {};
        int result = MaxFrequencyAfterOperations.maxFrequency(nums, 5, 2);
        assertEquals(0, result);
    }

    @Test
    public void testSingleElement() {
        int[] nums = { 10 };
        int result = MaxFrequencyAfterOperations.maxFrequency(nums, 3, 1);
        assertEquals(1, result);
    }

    @Test
    public void testNullInputThrowsException() {
        assertThrows(NullPointerException.class, () -> MaxFrequencyAfterOperations.maxFrequency(null, 1, 2));
    }
}
