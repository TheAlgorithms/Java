package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author D Sunil (https://github.com/sunilnitdgp)
 * @see PerfectBinarySearch
 */
public class PerfectBinarySearchTest {

    @Test
    public void testIntegerBinarySearch() {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        PerfectBinarySearch<Integer> binarySearch = new PerfectBinarySearch<>();

        // Test cases for elements present in the array
        assertEquals(0, binarySearch.find(array, 1)); // First element
        assertEquals(4, binarySearch.find(array, 5)); // Middle element
        assertEquals(9, binarySearch.find(array, 10)); // Last element
        assertEquals(6, binarySearch.find(array, 7)); // Element in the middle

        // Test cases for elements not in the array
        assertEquals(-1, binarySearch.find(array, 0)); // Element before the array
        assertEquals(-1, binarySearch.find(array, 11)); // Element after the array
        assertEquals(-1, binarySearch.find(array, 100)); // Element not in the array
    }

    @Test
    public void testStringBinarySearch() {
        String[] array = {"apple", "banana", "cherry", "date", "fig"};
        PerfectBinarySearch<String> binarySearch = new PerfectBinarySearch<>();

        // Test cases for elements not in the array
        assertEquals(-1, binarySearch.find(array, "apricot")); // Element not in the array
        assertEquals(-1, binarySearch.find(array, "bananaa")); // Element not in the array

        // Test cases for elements present in the array
        assertEquals(0, binarySearch.find(array, "apple")); // First element
        assertEquals(2, binarySearch.find(array, "cherry")); // Middle element
        assertEquals(4, binarySearch.find(array, "fig")); // Last element
    }
}
