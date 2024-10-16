package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class StalinSortTest {

    @Test
    public void testSortIntegers() {
        StalinSort stalinSort = new StalinSort();
        Integer[] input = {4, 23, 6, 78, 1, 54, 231, 9, 12};
        Integer[] expected = {4, 23, 78, 231};
        Integer[] result = stalinSort.sort(input);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testSortStrings() {
        StalinSort stalinSort = new StalinSort();
        String[] input = {"c", "a", "e", "b", "d"};
        String[] expected = {"c", "e"};
        String[] result = stalinSort.sort(input);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testSortWithDuplicates() {
        StalinSort stalinSort = new StalinSort();
        Integer[] input = {1, 3, 2, 2, 5, 4};
        Integer[] expected = {1, 3, 5};
        Integer[] result = stalinSort.sort(input);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testSortEmptyArray() {
        StalinSort stalinSort = new StalinSort();
        Integer[] input = {};
        Integer[] expected = {};
        Integer[] result = stalinSort.sort(input);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testSortSingleElement() {
        StalinSort stalinSort = new StalinSort();
        Integer[] input = {42};
        Integer[] expected = {42};
        Integer[] result = stalinSort.sort(input);
        assertArrayEquals(expected, result);
    }
}
