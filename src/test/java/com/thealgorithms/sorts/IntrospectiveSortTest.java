package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class IntrospectiveSortTest {
    @Test
    // valid test case
    public void StrandSortNonDuplicateTest() {
        Integer[] expectedArray = {1, 2, 3, 4, 5};
        Integer[] actualList = new IntrospectiveSort().sort(expectedArray);
        assertArrayEquals(expectedArray, actualList);
    }

    @Test
    // valid test case
    public void StrandSortDuplicateTest() {
        Integer[] expectedArray = {2, 2, 2, 5, 7};
        Integer[] actualList = new IntrospectiveSort().sort(expectedArray);
        assertArrayEquals(expectedArray, actualList);
    }

    @Test
    // valid test case
    public void StrandSortEmptyTest() {
        Integer[] expectedArray = {};
        Integer[] actualList = new IntrospectiveSort().sort(expectedArray);
        assertArrayEquals(expectedArray, actualList);
    }

    @Test
    // valid test case
    public void StrandSortNullTest() {
        Integer[] expectedArray = null;
        assertThrows(NullPointerException.class, () -> { new IntrospectiveSort().sort(expectedArray); });
    }

    @Test
    // valid test case
    public void StrandSortNegativeTest() {
        Integer[] expectedArray = {-1, -2, -3, -4, -5};
        Integer[] actualList = new IntrospectiveSort().sort(expectedArray);
        assertArrayEquals(expectedArray, actualList);
    }

    @Test
    // valid test case
    public void StrandSortNegativeAndPositiveTest() {
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
