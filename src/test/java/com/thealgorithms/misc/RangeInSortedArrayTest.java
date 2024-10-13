package com.thealgorithms.misc;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RangeInSortedArrayTest {

    @Test
    public void testSortedRangeWithMultipleOccurrences() {
        int[] nums = {1, 2, 3, 3, 3, 4, 5};
        assertArrayEquals(new int[] {2, 4}, RangeInSortedArray.sortedRange(nums, 3), "Range for key 3 should be [2, 4]");
    }

    @Test
    public void testSortedRangeWithSingleOccurrence() {
        int[] nums = {1, 2, 3, 3, 3, 4, 5};
        assertArrayEquals(new int[] {5, 5}, RangeInSortedArray.sortedRange(nums, 4), "Range for key 4 should be [5, 5]");
    }

    @Test
    public void testSortedRangeWithNoOccurrences() {
        int[] nums = {0, 1, 2};
        assertArrayEquals(new int[] {-1, -1}, RangeInSortedArray.sortedRange(nums, 3), "Range for key 3 should be [-1, -1] since it doesn't exist");
    }

    @Test
    public void testSortedRangeInEmptyArray() {
        int[] nums = {};
        assertArrayEquals(new int[] {-1, -1}, RangeInSortedArray.sortedRange(nums, 1), "Range for any key in an empty array should be [-1, -1]");
    }

    @Test
    public void testSortedRangeAtStartAndEnd() {
        int[] nums = {1, 1, 1, 2, 3, 4, 5, 5, 5};
        assertArrayEquals(new int[] {0, 2}, RangeInSortedArray.sortedRange(nums, 1), "Range for key 1 should be [0, 2]");
        assertArrayEquals(new int[] {6, 8}, RangeInSortedArray.sortedRange(nums, 5), "Range for key 5 should be [6, 8]");
    }

    @Test
    public void testGetCountLessThanWithExistingKey() {
        int[] nums = {1, 2, 3, 3, 4, 5};
        assertEquals(4, RangeInSortedArray.getCountLessThan(nums, 3), "Count of elements less than 3 should be 2");
    }

    @Test
    public void testGetCountLessThanWithNonExistingKey() {
        int[] nums = {1, 2, 3, 3, 4, 5};
        assertEquals(5, RangeInSortedArray.getCountLessThan(nums, 4), "Count of elements less than 4 should be 5");
    }

    @Test
    public void testGetCountLessThanWithAllSmallerElements() {
        int[] nums = {1, 2, 2, 3};
        assertEquals(4, RangeInSortedArray.getCountLessThan(nums, 5), "Count of elements less than 5 should be 4");
    }

    @Test
    public void testGetCountLessThanWithNoSmallerElements() {
        int[] nums = {2, 3, 4, 5};
        assertEquals(0, RangeInSortedArray.getCountLessThan(nums, 1), "Count of elements less than 1 should be 0");
    }

    @Test
    public void testGetCountLessThanWithEmptyArray() {
        int[] nums = {};
        assertEquals(0, RangeInSortedArray.getCountLessThan(nums, 1), "Count of elements less than 1 in an empty array should be 0");
    }
}
