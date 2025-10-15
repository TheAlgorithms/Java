package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the SentinelLinearSearch class.
 */
class SentinelLinearSearchTest {

    /**
     * Test for finding an element present in the array.
     */
    @Test
    void testSentinelLinearSearchFound() {
        SentinelLinearSearch sentinelLinearSearch = new SentinelLinearSearch();
        Integer[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Integer key = 5; // Element to find
        assertEquals(5, sentinelLinearSearch.find(array, key), "The index of the found element should be 5.");
    }

    /**
     * Test for finding the first element in the array.
     */
    @Test
    void testSentinelLinearSearchFirstElement() {
        SentinelLinearSearch sentinelLinearSearch = new SentinelLinearSearch();
        Integer[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Integer key = 0; // First element
        assertEquals(0, sentinelLinearSearch.find(array, key), "The index of the first element should be 0.");
    }

    /**
     * Test for finding the last element in the array.
     */
    @Test
    void testSentinelLinearSearchLastElement() {
        SentinelLinearSearch sentinelLinearSearch = new SentinelLinearSearch();
        Integer[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Integer key = 10; // Last element
        assertEquals(10, sentinelLinearSearch.find(array, key), "The index of the last element should be 10.");
    }

    /**
     * Test for finding an element not present in the array.
     */
    @Test
    void testSentinelLinearSearchNotFound() {
        SentinelLinearSearch sentinelLinearSearch = new SentinelLinearSearch();
        Integer[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Integer key = -1; // Element not in the array
        assertEquals(-1, sentinelLinearSearch.find(array, key), "The element should not be found in the array.");
    }

    /**
     * Test for finding an element in an empty array.
     */
    @Test
    void testSentinelLinearSearchEmptyArray() {
        SentinelLinearSearch sentinelLinearSearch = new SentinelLinearSearch();
        Integer[] array = {}; // Empty array
        Integer key = 1; // Key not present
        assertEquals(-1, sentinelLinearSearch.find(array, key), "The element should not be found in an empty array.");
    }

    /**
     * Test for finding an element in a single-element array when present.
     */
    @Test
    void testSentinelLinearSearchSingleElementFound() {
        SentinelLinearSearch sentinelLinearSearch = new SentinelLinearSearch();
        Integer[] array = {42}; // Single element array
        Integer key = 42; // Element present
        assertEquals(0, sentinelLinearSearch.find(array, key), "The element should be found at index 0.");
    }

    /**
     * Test for finding an element in a single-element array when not present.
     */
    @Test
    void testSentinelLinearSearchSingleElementNotFound() {
        SentinelLinearSearch sentinelLinearSearch = new SentinelLinearSearch();
        Integer[] array = {42}; // Single element array
        Integer key = 24; // Element not present
        assertEquals(-1, sentinelLinearSearch.find(array, key), "The element should not be found in the array.");
    }

    /**
     * Test for finding multiple occurrences of the same element in the array.
     */
    @Test
    void testSentinelLinearSearchMultipleOccurrences() {
        SentinelLinearSearch sentinelLinearSearch = new SentinelLinearSearch();
        Integer[] array = {1, 2, 3, 4, 5, 3, 6, 7, 3}; // 3 occurs multiple times
        Integer key = 3; // Key to find
        assertEquals(2, sentinelLinearSearch.find(array, key), "The index of the first occurrence of the element should be 2.");
    }

    /**
     * Test for finding an element in a large array.
     */
    @Test
    void testSentinelLinearSearchLargeArray() {
        SentinelLinearSearch sentinelLinearSearch = new SentinelLinearSearch();
        Integer[] array = new Integer[1000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i; // Fill the array with integers 0 to 999
        }
        Integer key = 256; // Present in the array
        assertEquals(256, sentinelLinearSearch.find(array, key), "The index of the found element should be 256.");
    }

    /**
     * Test for finding an element in a large array when it is not present.
     */
    @Test
    void testSentinelLinearSearchLargeArrayNotFound() {
        SentinelLinearSearch sentinelLinearSearch = new SentinelLinearSearch();
        Integer[] array = new Integer[1000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i; // Fill the array with integers 0 to 999
        }
        Integer key = 1001; // Key not present
        assertEquals(-1, sentinelLinearSearch.find(array, key), "The element should not be found in the array.");
    }

    /**
     * Test for performance with random large array.
     */
    @Test
    void testSentinelLinearSearchRandomArray() {
        SentinelLinearSearch sentinelLinearSearch = new SentinelLinearSearch();
        Random random = new Random();
        Integer[] array = random.ints(0, 1000).distinct().limit(1000).boxed().toArray(Integer[] ::new);
        Integer key = array[random.nextInt(array.length)]; // Key should be in the array
        assertEquals(java.util.Arrays.asList(array).indexOf(key), sentinelLinearSearch.find(array, key), "The index of the found element should match.");
    }

    /**
     * Test for handling null array.
     */
    @Test
    void testSentinelLinearSearchNullArray() {
        SentinelLinearSearch sentinelLinearSearch = new SentinelLinearSearch();
        Integer[] array = null; // Null array
        Integer key = 1; // Any key
        assertThrows(IllegalArgumentException.class, () -> sentinelLinearSearch.find(array, key), "Should throw IllegalArgumentException for null array.");
    }

    /**
     * Test for handling null key in array with null elements.
     */
    @Test
    void testSentinelLinearSearchNullKey() {
        SentinelLinearSearch sentinelLinearSearch = new SentinelLinearSearch();
        Integer[] array = {1, null, 3, 4, null}; // Array with null elements
        Integer key = null; // Null key
        assertEquals(1, sentinelLinearSearch.find(array, key), "The index of the first null element should be 1.");
    }

    /**
     * Test for handling null key when not present in array.
     */
    @Test
    void testSentinelLinearSearchNullKeyNotFound() {
        SentinelLinearSearch sentinelLinearSearch = new SentinelLinearSearch();
        Integer[] array = {1, 2, 3, 4, 5}; // Array without null elements
        Integer key = null; // Null key
        assertEquals(-1, sentinelLinearSearch.find(array, key), "Null key should not be found in array without null elements.");
    }

    /**
     * Test with String array to verify generic functionality.
     */
    @Test
    void testSentinelLinearSearchStringArray() {
        SentinelLinearSearch sentinelLinearSearch = new SentinelLinearSearch();
        String[] array = {"apple", "banana", "cherry", "date", "elderberry"};
        String key = "cherry"; // Element to find
        assertEquals(2, sentinelLinearSearch.find(array, key), "The index of 'cherry' should be 2.");
    }

    /**
     * Test with String array when element not found.
     */
    @Test
    void testSentinelLinearSearchStringArrayNotFound() {
        SentinelLinearSearch sentinelLinearSearch = new SentinelLinearSearch();
        String[] array = {"apple", "banana", "cherry", "date", "elderberry"};
        String key = "grape"; // Element not in array
        assertEquals(-1, sentinelLinearSearch.find(array, key), "The element 'grape' should not be found in the array.");
    }

    /**
     * Test that the original array is not modified after search.
     */
    @Test
    void testSentinelLinearSearchArrayIntegrity() {
        SentinelLinearSearch sentinelLinearSearch = new SentinelLinearSearch();
        Integer[] array = {1, 2, 3, 4, 5};
        Integer[] originalArray = array.clone(); // Keep a copy of the original
        Integer key = 3; // Element to find

        sentinelLinearSearch.find(array, key);

        // Verify array is unchanged
        for (int i = 0; i < array.length; i++) {
            assertEquals(originalArray[i], array[i], "Array should remain unchanged after search.");
        }
    }

    /**
     * Test edge case where the key is the same as the last element.
     */
    @Test
    void testSentinelLinearSearchKeyEqualsLastElement() {
        SentinelLinearSearch sentinelLinearSearch = new SentinelLinearSearch();
        Integer[] array = {1, 2, 3, 4, 5, 3}; // Last element is 3, and 3 also appears earlier
        Integer key = 3; // Key equals last element
        assertEquals(2, sentinelLinearSearch.find(array, key), "Should find the first occurrence at index 2, not the last.");
    }
}
