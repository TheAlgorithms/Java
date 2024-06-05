package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class IntrospectiveSortTest {
    @Test
    // valid test case
    public void strandSortNonDuplicateTest() {
        Integer[] expectedArray = {1, 2, 3, 4, 5};
        Integer[] actualList = new IntrospectiveSort().sort(expectedArray);
        assertArrayEquals(expectedArray, actualList);
    }

    @Test
    // valid test case
    public void strandSortDuplicateTest() {
        Integer[] expectedArray = {2, 2, 2, 5, 7};
        Integer[] actualList = new IntrospectiveSort().sort(expectedArray);
        assertArrayEquals(expectedArray, actualList);
    }

    @Test
    // valid test case
    public void strandSortEmptyTest() {
        Integer[] expectedArray = {};
        Integer[] actualList = new IntrospectiveSort().sort(expectedArray);
        assertArrayEquals(expectedArray, actualList);
    }

    @Test
    // valid test case
    public void strandSortNullTest() {
        Integer[] expectedArray = null;
        assertThrows(NullPointerException.class, () -> { new IntrospectiveSort().sort(expectedArray); });
    }

    @Test
    // valid test case
    public void strandSortNegativeTest() {
        Integer[] expectedArray = {-1, -2, -3, -4, -5};
        Integer[] actualList = new IntrospectiveSort().sort(expectedArray);
        assertArrayEquals(expectedArray, actualList);
    }

    @Test
    // valid test case
    public void strandSortNegativeAndPositiveTest() {
        Integer[] expectedArray = {-1, -2, -3, 4, 5};
        Integer[] actualList = new IntrospectiveSort().sort(expectedArray);
        assertArrayEquals(expectedArray, actualList);
    }

    @Test
    // valid test case
    public void allSameTest() {
        Integer[] expectedArray = {1, 1, 1, 1, 1};
        Integer[] actualList = new IntrospectiveSort().sort(expectedArray);
        assertArrayEquals(expectedArray, actualList);
    }
}
