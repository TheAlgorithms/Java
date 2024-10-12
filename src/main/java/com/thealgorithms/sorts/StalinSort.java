package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class StalinSortTest {

    private StalinSort stalinSort = new StalinSort();

    @Test
    public void stalinSortEmptyArray() {
        Integer[] inputArray = {};
        Integer[] outputArray = stalinSort.sort(inputArray);
        Integer[] expectedOutput = {};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void stalinSortSingleIntegerArray() {
        Integer[] inputArray = {4};
        Integer[] outputArray = stalinSort.sort(inputArray);
        Integer[] expectedOutput = {4};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void stalinSortSingleStringArray() {
        String[] inputArray = {"s"};
        String[] outputArray = stalinSort.sort(inputArray);
        String[] expectedOutput = {"s"};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void stalinSortNonDuplicateIntegerArray() {
        Integer[] inputArray = {6, -1, 99, 27, -15, 23, -36};
        Integer[] outputArray = stalinSort.sort(inputArray);
        Integer[] expectedOutput = {-36, -15, -1, 6, 23, 27, 99};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void stalinSortDuplicateIntegerArray() {
        Integer[] inputArray = {6, -1, 27, -15, 23, 27, -36, 23};
        Integer[] outputArray = stalinSort.sort(inputArray);
        Integer[] expectedOutput = {-36, -15, -1, 6, 23, 23, 27, 27};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void stalinSortNonDuplicateStringArray() {
        String[] inputArray = {"s", "b", "k", "a", "d", "c", "h"};
        String[] outputArray = stalinSort.sort(inputArray);
        String[] expectedOutput = {"a", "b", "c", "d", "h", "k", "s"};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void stalinSortDuplicateStringArray() {
        String[] inputArray = {"s", "b", "d", "a", "d", "c", "h", "b"};
        String[] outputArray = stalinSort.sort(inputArray);
        String[] expectedOutput = {"a", "b", "b", "c", "d", "d", "h", "s"};
        assertArrayEquals(outputArray, expectedOutput);
    }
}
