package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the FibonacciSearch class.
 */
class FibonacciSearchTest {

    /**
     * Test for basic Fibonacci search functionality.
     */
    @Test
    void testFibonacciSearchFound() {
        FibonacciSearch fibonacciSearch = new FibonacciSearch();
        Integer[] array = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512};
        int key = 128;
        int expectedIndex = 7; // Index of the key in the array
        assertEquals(expectedIndex, fibonacciSearch.find(array, key), "The index of the found element should be 7.");
    }

    /**
     * Test for Fibonacci search when the element is not present.
     */
    @Test
    void testFibonacciSearchNotFound() {
        FibonacciSearch fibonacciSearch = new FibonacciSearch();
        Integer[] array = {1, 2, 4, 8, 16};
        int key = 6; // Element not present in the array
        int expectedIndex = -1; // Key not found
        assertEquals(expectedIndex, fibonacciSearch.find(array, key), "The element should not be found in the array.");
    }

    /**
     * Test for Fibonacci search with the first element as the key.
     */
    @Test
    void testFibonacciSearchFirstElement() {
        FibonacciSearch fibonacciSearch = new FibonacciSearch();
        Integer[] array = {1, 2, 4, 8, 16};
        int key = 1; // First element
        int expectedIndex = 0; // Index of the key in the array
        assertEquals(expectedIndex, fibonacciSearch.find(array, key), "The index of the first element should be 0.");
    }

    /**
     * Test for Fibonacci search with the last element as the key.
     */
    @Test
    void testFibonacciSearchLastElement() {
        FibonacciSearch fibonacciSearch = new FibonacciSearch();
        Integer[] array = {1, 2, 4, 8, 16};
        int key = 16; // Last element
        int expectedIndex = 4; // Index of the key in the array
        assertEquals(expectedIndex, fibonacciSearch.find(array, key), "The index of the last element should be 4.");
    }

    /**
     * Test for Fibonacci search with a single element present.
     */
    @Test
    void testFibonacciSearchSingleElementFound() {
        FibonacciSearch fibonacciSearch = new FibonacciSearch();
        Integer[] array = {1};
        int key = 1; // Only element present
        int expectedIndex = 0; // Index of the key in the array
        assertEquals(expectedIndex, fibonacciSearch.find(array, key), "The index of the single element should be 0.");
    }

    /**
     * Test for Fibonacci search with a single element not present.
     */
    @Test
    void testFibonacciSearchSingleElementNotFound() {
        FibonacciSearch fibonacciSearch = new FibonacciSearch();
        Integer[] array = {1};
        int key = 2; // Key not present
        int expectedIndex = -1; // Key not found
        assertEquals(expectedIndex, fibonacciSearch.find(array, key), "The element should not be found in the array.");
    }

    /**
     * Test for Fibonacci search with an empty array.
     */
    @Test
    void testFibonacciSearchEmptyArray() {
        FibonacciSearch fibonacciSearch = new FibonacciSearch();
        Integer[] array = {}; // Empty array
        int key = 1; // Key not present
        assertThrows(IllegalArgumentException.class, () -> fibonacciSearch.find(array, key), "An empty array should throw an IllegalArgumentException.");
    }

    @Test
    void testFibonacciSearchUnsortedArray() {
        FibonacciSearch fibonacciSearch = new FibonacciSearch();
        Integer[] array = {2, 1, 4, 3, 6, 5};
        int key = 3; // Key not present
        assertThrows(IllegalArgumentException.class, () -> fibonacciSearch.find(array, key), "An unsorted array should throw an IllegalArgumentException.");
    }

    @Test
    void testFibonacciSearchNullKey() {
        FibonacciSearch fibonacciSearch = new FibonacciSearch();
        Integer[] array = {1, 2, 4, 8, 16};
        Integer key = null; // Null key
        assertThrows(IllegalArgumentException.class, () -> fibonacciSearch.find(array, key), "A null key should throw an IllegalArgumentException.");
    }

    /**
     * Test for Fibonacci search on large array.
     */
    @Test
    void testFibonacciSearchLargeArray() {
        FibonacciSearch fibonacciSearch = new FibonacciSearch();
        Integer[] array = IntStream.range(0, 10000).boxed().toArray(Integer[] ::new); // Array from 0 to 9999
        int key = 9999;
        int expectedIndex = 9999;
        assertEquals(expectedIndex, fibonacciSearch.find(array, key), "The index of the last element should be 9999.");
    }

    /**
     * A key greater than every element used to throw {@link ArrayIndexOutOfBoundsException},
     * because the final probe read {@code array[offset + 1]} without checking the bound.
     */
    @Test
    void testFibonacciSearchKeyGreaterThanLastElement() {
        FibonacciSearch fibonacciSearch = new FibonacciSearch();
        for (int length = 1; length <= 50; length++) {
            Integer[] array = new Integer[length];
            for (int i = 0; i < length; i++) {
                array[i] = i;
            }
            assertEquals(-1, fibonacciSearch.find(array, length), "A key above the maximum should not be found for length " + length + ".");
        }
    }

    /**
     * The final probe used reference equality, so a key that is equal but not identical to the
     * stored element was reported as missing. Values above 127 are outside the {@link Integer}
     * cache and therefore are not the same object as the boxed array element.
     */
    @Test
    void testFibonacciSearchFindsEqualButNotIdenticalKey() {
        FibonacciSearch fibonacciSearch = new FibonacciSearch();
        Integer[] array = {10, 20, 300};
        assertEquals(2, fibonacciSearch.find(array, Integer.valueOf(300)), "The index of the found element should be 2.");

        String[] words = {"a", "b", "c"};
        String equalButDistinct = new StringBuilder("c").toString();
        assertEquals(2, fibonacciSearch.find(words, equalButDistinct), "The index of the found element should be 2.");
    }

    /**
     * Every element must be found regardless of the array length.
     */
    @Test
    void testFibonacciSearchFindsEveryElement() {
        FibonacciSearch fibonacciSearch = new FibonacciSearch();
        for (int length = 1; length <= 50; length++) {
            Integer[] array = new Integer[length];
            for (int i = 0; i < length; i++) {
                array[i] = 1000 + i * 2;
            }
            for (int i = 0; i < length; i++) {
                assertEquals(i, fibonacciSearch.find(array, Integer.valueOf(1000 + i * 2)), "Element at index " + i + " should be found for length " + length + ".");
            }
        }
    }
}
