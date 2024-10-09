package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TernarySearchTest {

    @Test
    void testFindElementInSortedArray() {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        TernarySearch search = new TernarySearch();

        int index = search.find(arr, 5);

        assertEquals(4, index, "Should find the element 5 at index 4");
    }

    @Test
    void testElementNotFound() {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        TernarySearch search = new TernarySearch();

        int index = search.find(arr, 11);

        assertEquals(-1, index, "Should return -1 for element 11 which is not present");
    }

    @Test
    void testFindFirstElement() {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        TernarySearch search = new TernarySearch();

        int index = search.find(arr, 1);

        assertEquals(0, index, "Should find the first element 1 at index 0");
    }

    @Test
    void testFindLastElement() {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        TernarySearch search = new TernarySearch();

        int index = search.find(arr, 10);

        assertEquals(9, index, "Should find the last element 10 at index 9");
    }

    @Test
    void testFindInLargeArray() {
        Integer[] arr = new Integer[1000];
        for (int i = 0; i < 1000; i++) {
            arr[i] = i + 1; // Array from 1 to 1000
        }
        TernarySearch search = new TernarySearch();

        int index = search.find(arr, 500);

        assertEquals(499, index, "Should find element 500 at index 499");
    }

    @Test
    void testNegativeNumbers() {
        Integer[] arr = {-10, -5, -3, -1, 0, 1, 3, 5, 7, 10};
        TernarySearch search = new TernarySearch();

        int index = search.find(arr, -3);

        assertEquals(2, index, "Should find the element -3 at index 2");
    }

    @Test
    void testEdgeCaseEmptyArray() {
        Integer[] arr = {};
        TernarySearch search = new TernarySearch();

        int index = search.find(arr, 5);

        assertEquals(-1, index, "Should return -1 for an empty array");
    }
}
