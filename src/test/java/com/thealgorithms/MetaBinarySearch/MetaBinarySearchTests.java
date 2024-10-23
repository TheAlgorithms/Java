package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MetaBinarySearchTest {

    @Test
    public void testElementFound() {
        int[] sortedArray = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21};
        int target = 13;
        int expectedIndex = 6;

        int result = MetaBinarySearch.metaBinarySearch(sortedArray, target);
        assertEquals(expectedIndex, result, "The target element should be found at index 6");
    }

    @Test
    public void testElementNotFound() {
        int[] sortedArray = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20};
        int target = 7;

        int result = MetaBinarySearch.metaBinarySearch(sortedArray, target);
        assertEquals(-1, result, "The target element should not be found in the array");
    }

    @Test
    public void testSingleElementArrayFound() {
        int[] singleElementArray = {5};
        int target = 5;

        int result = MetaBinarySearch.metaBinarySearch(singleElementArray, target);
        assertEquals(0, result, "The target element should be found at index 0 in a single-element array");
    }

    @Test
    public void testSingleElementArrayNotFound() {
        int[] singleElementArray = {3};
        int target = 5;

        int result = MetaBinarySearch.metaBinarySearch(singleElementArray, target);
        assertEquals(-1, result, "The target element should not be found in a single-element array");
    }

    @Test
    public void testEmptyArray() {
        int[] emptyArray = {};
        int target = 10;

        int result = MetaBinarySearch.metaBinarySearch(emptyArray, target);
        assertEquals(-1, result, "The target element should not be found in an empty array");
    }

    @Test
    public void testFirstElement() {
        int[] sortedArray = {1, 2, 3, 4, 5};
        int target = 1;

        int result = MetaBinarySearch.metaBinarySearch(sortedArray, target);
        assertEquals(0, result, "The target element should be found at the first index");
    }

    @Test
    public void testLastElement() {
        int[] sortedArray = {1, 2, 3, 4, 5};
        int target = 5;

        int result = MetaBinarySearch.metaBinarySearch(sortedArray, target);
        assertEquals(4, result, "The target element should be found at the last index");
    }

    @Test
    public void testLargeArray() {
        int[] largeArray = new int[1000];
        for (int i = 0; i < 1000; i++) {
            largeArray[i] = i * 2; // Even numbers from 0 to 1998
        }
        int target = 998;

        int result = MetaBinarySearch.metaBinarySearch(largeArray, target);
        assertEquals(499, result, "The target element should be found at index 499");
    }
}
