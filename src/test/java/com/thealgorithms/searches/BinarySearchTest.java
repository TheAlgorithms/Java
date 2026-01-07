package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the BinarySearch class.
 */
class BinarySearchTest {

    private final BinarySearch binarySearch = new BinarySearch();

    @Test
    @DisplayName("BinarySearch should find existing element")
    void testBinarySearchFound() {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int key = 7;
        assertEquals(6, binarySearch.find(array, key));
    }

    @Test
    @DisplayName("BinarySearch should return -1 when element is not found")
    void testBinarySearchNotFound() {
        Integer[] array = {1, 2, 3, 4, 5};
        int key = 6;
        assertEquals(-1, binarySearch.find(array, key));
    }

    @Test
    @DisplayName("BinarySearch should find first element")
    void testBinarySearchFirstElement() {
        Integer[] array = {1, 2, 3, 4, 5};
        assertEquals(0, binarySearch.find(array, 1));
    }

    @Test
    @DisplayName("BinarySearch should find last element")
    void testBinarySearchLastElement() {
        Integer[] array = {1, 2, 3, 4, 5};
        assertEquals(4, binarySearch.find(array, 5));
    }

    @Test
    @DisplayName("BinarySearch should handle single-element array (found)")
    void testBinarySearchSingleElementFound() {
        Integer[] array = {1};
        assertEquals(0, binarySearch.find(array, 1));
    }

    @Test
    @DisplayName("BinarySearch should handle single-element array (not found)")
    void testBinarySearchSingleElementNotFound() {
        Integer[] array = {1};
        assertEquals(-1, binarySearch.find(array, 2));
    }

    @Test
    @DisplayName("BinarySearch should return -1 for empty array")
    void testBinarySearchEmptyArray() {
        Integer[] array = {};
        assertEquals(-1, binarySearch.find(array, 1));
    }

    @Test
    @DisplayName("BinarySearch should handle large sorted array")
    void testBinarySearchLargeArray() {
        Integer[] array = IntStream.range(0, 10_000)
            .boxed()
            .toArray(Integer[]::new);

        assertEquals(9999, binarySearch.find(array, 9999));
    }

    @Test
    @DisplayName("BinarySearch should throw exception for null array")
    void testBinarySearchNullArray() {
        assertThrows(
            IllegalArgumentException.class,
            () -> binarySearch.find(null, 5)
        );
    }
}
