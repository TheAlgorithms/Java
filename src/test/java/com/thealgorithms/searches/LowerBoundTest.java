package com.thealgorithms.searches;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

class LowerBoundTest {

    /**
     * Test finding the lower bound for an element present in the array.
     */
    @Test
    void testLowerBoundElementPresent() {
        Integer[] array = {1, 2, 3, 4, 5};
        LowerBound lowerBound = new LowerBound();

        // Test for a value that is present
        assertEquals(2, lowerBound.find(array, 3), "Lower bound for 3 should be at index 2");
        assertEquals(0, lowerBound.find(array, 1), "Lower bound for 1 should be at index 0");
        assertEquals(4, lowerBound.find(array, 5), "Lower bound for 5 should be at index 4");
    }

    /**
     * Test finding the lower bound for a value greater than the maximum element in the array.
     */
    @Test
    void testLowerBoundElementGreaterThanMax() {
        Integer[] array = {1, 2, 3, 4, 5};
        LowerBound lowerBound = new LowerBound();

        // Test for a value greater than the maximum
        assertEquals(4, lowerBound.find(array, 6), "Lower bound for 6 should be at index 4");
    }

    /**
     * Test finding the lower bound for a value less than the minimum element in the array.
     */
    @Test
    void testLowerBoundElementLessThanMin() {
        Integer[] array = {1, 2, 3, 4, 5};
        LowerBound lowerBound = new LowerBound();

        // Test for a value less than the minimum
        assertEquals(0, lowerBound.find(array, 0), "Lower bound for 0 should be at index 0");
    }

    /**
     * Test finding the lower bound for a non-existent value that falls between two elements.
     */
    @Test
    void testLowerBoundNonExistentValue() {
        Integer[] array = {1, 2, 3, 4, 5};
        LowerBound lowerBound = new LowerBound();

        // Test for a value that is not present
        assertEquals(4, lowerBound.find(array, 7), "Lower bound for 7 should be at index 4");
        assertEquals(0, lowerBound.find(array, 0), "Lower bound for 0 should be at index 0");
    }

    /**
     * Test finding the lower bound in a large sorted array with random integers.
     */
    @Test
    void testLowerBoundRandomNumbers() {
        Random random = new Random();
        int size = 100;
        Integer[] array = random.ints(size, 1, 100).sorted().boxed().toArray(Integer[]::new);

        int target = random.nextInt(100) + 1; // Random target value between 1 and 100

        Arrays.sort(array); // Ensure the array is sorted
        LowerBound lowerBound = new LowerBound();
        int lowerBoundIndex = lowerBound.find(array, target);

        // Check if the found index is valid
        if (lowerBoundIndex < size) {
            assertTrue(array[lowerBoundIndex] >= target, "Lower bound index should point to a value >= target.");
        }
    }
}
