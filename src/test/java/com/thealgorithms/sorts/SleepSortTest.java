package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class SleepSortTest {

    @Test
    void testSleepSort() {
        int[] input = {5, 3, 6, 2, 10};
        int[] expected = {2, 3, 5, 6, 10};
        int[] result = SleepSort.sort(input);
        assertArrayEquals(expected, result);
    }

    @Test
    void testEmptyArray() {
        int[] input = {};
        int[] expected = {};
        int[] result = SleepSort.sort(input);
        assertArrayEquals(expected, result);
    }

    @Test
    void testSingleElement() {
        int[] input = {42};
        int[] expected = {42};
        int[] result = SleepSort.sort(input);
        assertArrayEquals(expected, result);
    }
}
