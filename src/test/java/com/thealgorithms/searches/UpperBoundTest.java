package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UpperBoundTest {

    private UpperBound upperBound;
    private Integer[] sortedArray;

    @BeforeEach
    void setUp() {
        upperBound = new UpperBound();

        // Generate a sorted array of random integers for testing
        Random random = new Random();
        int size = 100;
        int maxElement = 100;
        sortedArray = random.ints(size, 1, maxElement)
                          .distinct() // Ensure all elements are unique
                          .sorted()
                          .boxed()
                          .toArray(Integer[] ::new);
    }

    @Test
    void testUpperBoundFound() {
        int key = sortedArray[sortedArray.length - 1] + 1; // Test with a key larger than max element
        int index = upperBound.find(sortedArray, key);

        // The upper bound should be equal to the length of the array
        assertEquals(sortedArray.length - 1, index, "Upper bound for a larger key should be the size of the array.");
    }

    @Test
    void testUpperBoundExactMatch() {
        int key = sortedArray[sortedArray.length / 2]; // Choose a key from the middle of the array
        int index = upperBound.find(sortedArray, key);

        // The index should point to the first element greater than the key
        assertTrue(index < sortedArray.length, "Upper bound should not exceed array length.");
        assertTrue(sortedArray[index] > key, "The element at the index should be greater than the key.");
    }

    @Test
    void testUpperBoundMultipleValues() {
        Integer[] arrayWithDuplicates = new Integer[] {1, 1, 2, 3, 4, 4, 5, 6, 7, 8, 9}; // Test array with duplicates
        int key = 4;
        int index = upperBound.find(arrayWithDuplicates, key);

        assertTrue(index < arrayWithDuplicates.length, "Upper bound index should be valid.");
        assertEquals(6, index, "The upper bound for 4 should be the index of the first 5.");
        assertTrue(arrayWithDuplicates[index] > key, "Element at the upper bound index should be greater than the key.");
    }

    @Test
    void testUpperBoundLowerThanMin() {
        int key = 0; // Test with a key lower than the minimum element
        int index = upperBound.find(sortedArray, key);

        assertEquals(0, index, "Upper bound for a key lower than minimum should be 0.");
        assertTrue(sortedArray[index] > key, "The element at index 0 should be greater than the key.");
    }

    @Test
    void testUpperBoundHigherThanMax() {
        int key = sortedArray[sortedArray.length - 1] + 1; // Test with a key higher than maximum element
        int index = upperBound.find(sortedArray, key);

        assertEquals(sortedArray.length - 1, index, "Upper bound for a key higher than maximum should be the size of the array.");
    }

    @Test
    void testUpperBoundEdgeCase() {
        // Edge case: empty array
        Integer[] emptyArray = {};
        int index = upperBound.find(emptyArray, 5);

        assertEquals(0, index, "Upper bound for an empty array should be 0.");
    }

    @Test
    void testUpperBoundSingleElementArray() {
        Integer[] singleElementArray = {10};
        int index = upperBound.find(singleElementArray, 5);

        assertEquals(0, index, "Upper bound for 5 in a single element array should be 0.");

        index = upperBound.find(singleElementArray, 10);
        assertEquals(0, index, "Upper bound for 10 in a single element array should be 0.");

        index = upperBound.find(singleElementArray, 15);
        assertEquals(0, index, "Upper bound for 15 in a single element array should be 0.");
    }
}
