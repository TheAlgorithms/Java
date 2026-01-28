package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test cases for BinarySearchStrings algorithm
 * 
 * @author Jeevan Yewale (https://github.com/JeevanYewale)
 */
class BinarySearchStringsTest {

    @Test
    void testBasicSearch() {
        String[] array = {"apple", "banana", "cherry", "date", "elderberry"};
        
        assertEquals(0, BinarySearchStrings.search(array, "apple"));
        assertEquals(2, BinarySearchStrings.search(array, "cherry"));
        assertEquals(4, BinarySearchStrings.search(array, "elderberry"));
        assertEquals(-1, BinarySearchStrings.search(array, "grape"));
    }

    @Test
    void testEmptyArray() {
        String[] array = {};
        assertEquals(-1, BinarySearchStrings.search(array, "test"));
    }

    @Test
    void testNullArray() {
        assertEquals(-1, BinarySearchStrings.search(null, "test"));
    }

    @Test
    void testNullTarget() {
        String[] array = {"apple", "banana"};
        assertEquals(-1, BinarySearchStrings.search(array, null));
    }

    @Test
    void testSingleElement() {
        String[] array = {"single"};
        assertEquals(0, BinarySearchStrings.search(array, "single"));
        assertEquals(-1, BinarySearchStrings.search(array, "other"));
    }

    @Test
    void testCaseInsensitiveSearch() {
        String[] array = {"apple", "banana", "cherry", "date", "elderberry"};
        
        assertEquals(0, BinarySearchStrings.searchIgnoreCase(array, "APPLE"));
        assertEquals(2, BinarySearchStrings.searchIgnoreCase(array, "Cherry"));
        assertEquals(-1, BinarySearchStrings.searchIgnoreCase(array, "GRAPE"));
    }
}