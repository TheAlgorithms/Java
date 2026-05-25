package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class SentinelBinarySearchTest {

    private final SentinelBinarySearch search = new SentinelBinarySearch();

    @Test
    void testElementFound() {
        int[] arr = {1, 3, 5, 7, 9};
        assertEquals(2, search.find(arr, 5));
    }

    @Test
    void testElementNotFound() {
        int[] arr = {1, 3, 5, 7, 9};
        assertEquals(-1, search.find(arr, 4));
    }

    @Test
    void testFirstElement() {
        int[] arr = {1, 3, 5, 7, 9};
        assertEquals(0, search.find(arr, 1));
    }

    @Test
    void testLastElement() {
        int[] arr = {1, 3, 5, 7, 9};
        assertEquals(4, search.find(arr, 9));
    }

    @Test
    void testEmptyArray() {
        int[] arr = {};
        assertEquals(-1, search.find(arr, 5));
    }
}
