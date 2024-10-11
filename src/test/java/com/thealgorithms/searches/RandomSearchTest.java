package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RandomSearchTest {

    private RandomSearch randomSearch;

    @BeforeEach
    void setUp() {
        randomSearch = new RandomSearch();
    }

    @Test
    void testElementFound() {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Integer key = 5;
        int index = randomSearch.find(array, key);

        assertNotEquals(-1, index, "Element should be found in the array.");
        assertEquals(key, array[index], "Element found should match the key.");
    }

    @Test
    void testElementNotFound() {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Integer key = 11;
        int index = randomSearch.find(array, key);

        assertEquals(-1, index, "Element not present in the array should return -1.");
    }

    @Test
    void testEmptyArray() {
        Integer[] emptyArray = {};
        Integer key = 5;
        int index = randomSearch.find(emptyArray, key);

        assertEquals(-1, index, "Searching in an empty array should return -1.");
    }

    @Test
    void testSingleElementArrayFound() {
        Integer[] array = {5};
        Integer key = 5;
        int index = randomSearch.find(array, key);

        assertEquals(0, index, "The key should be found at index 0 in a single-element array.");
    }

    @Test
    void testSingleElementArrayNotFound() {
        Integer[] array = {1};
        Integer key = 5;
        int index = randomSearch.find(array, key);

        assertEquals(-1, index, "The key should not be found in a single-element array if it does not match.");
    }

    @Test
    void testDuplicateElementsFound() {
        Integer[] array = {1, 2, 3, 4, 5, 5, 5, 7, 8, 9, 10};
        Integer key = 5;
        int index = randomSearch.find(array, key);

        assertNotEquals(-1, index, "The key should be found in the array with duplicates.");
        assertEquals(key, array[index], "The key found should be 5.");
    }

    @Test
    void testLargeArray() {
        Integer[] largeArray = new Integer[1000];
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = i + 1; // Fill with values 1 to 1000
        }

        Integer key = 500;
        int index = randomSearch.find(largeArray, key);

        assertNotEquals(-1, index, "The key should be found in the large array.");
        assertEquals(key, largeArray[index], "The key found should match 500.");
    }
}
