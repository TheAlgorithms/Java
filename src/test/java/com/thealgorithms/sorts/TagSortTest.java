package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TagSortTest {

    private TagSort tagSort;

    @BeforeEach
    public void setUp() {
        tagSort = new TagSort();
    }

    @Test
    @DisplayName("TagSort empty array")
    public void testEmptyArray() {
        Integer[] input = {};
        Integer[] result = tagSort.sort(input);
        assertArrayEquals(new Integer[] {}, result);
    }

    @Test
    @DisplayName("TagSort single element array")
    public void testSingleElement() {
        Integer[] input = {42};
        Integer[] result = tagSort.sort(input);
        assertArrayEquals(new Integer[] {42}, result);
    }

    @Test
    @DisplayName("TagSort non-duplicate integer array")
    public void testNonDuplicateIntegers() {
        Integer[] input = {5, 3, 8, 1, 9, 2};
        Integer[] result = tagSort.sort(input);
        assertArrayEquals(new Integer[] {1, 2, 3, 5, 8, 9}, result);
    }

    @Test
    @DisplayName("TagSort integer array with duplicates")
    public void testDuplicateIntegers() {
        Integer[] input = {4, 2, 7, 2, 4, 1};
        Integer[] result = tagSort.sort(input);
        assertArrayEquals(new Integer[] {1, 2, 2, 4, 4, 7}, result);
    }

    @Test
    @DisplayName("TagSort negative integers")
    public void testNegativeIntegers() {
        Integer[] input = {-3, 5, -1, 0, 2, -8};
        Integer[] result = tagSort.sort(input);
        assertArrayEquals(new Integer[] {-8, -3, -1, 0, 2, 5}, result);
    }

    @Test
    @DisplayName("TagSort already sorted array")
    public void testAlreadySorted() {
        Integer[] input = {1, 2, 3, 4, 5};
        Integer[] result = tagSort.sort(input);
        assertArrayEquals(new Integer[] {1, 2, 3, 4, 5}, result);
    }

    @Test
    @DisplayName("TagSort reverse sorted array")
    public void testReverseSorted() {
        Integer[] input = {5, 4, 3, 2, 1};
        Integer[] result = tagSort.sort(input);
        assertArrayEquals(new Integer[] {1, 2, 3, 4, 5}, result);
    }

    @Test
    @DisplayName("TagSort string array")
    public void testStringArray() {
        String[] input = {"banana", "apple", "cherry", "date"};
        String[] result = tagSort.sort(input);
        assertArrayEquals(new String[] {"apple", "banana", "cherry", "date"}, result);
    }

    @Test
    @DisplayName("TagSort getSortedTags returns correct indices")
    public void testGetSortedTags() {
        Integer[] input = {30, 10, 20};
        Integer[] tags = tagSort.getSortedTags(input);
        // index 1 (value 10), index 2 (value 20), index 0 (value 30)
        assertArrayEquals(new Integer[] {1, 2, 0}, tags);
    }

    @Test
    @DisplayName("TagSort all equal elements")
    public void testAllEqualElements() {
        Integer[] input = {7, 7, 7, 7};
        Integer[] result = tagSort.sort(input);
        assertArrayEquals(new Integer[] {7, 7, 7, 7}, result);
    }
}
