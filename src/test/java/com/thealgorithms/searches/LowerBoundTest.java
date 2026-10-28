package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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
     * Test finding the lower bound for an element with duplicate occurrences.
     * Lower bound should point to the FIRST element not less than key.
     */
    @Test
    void testLowerBoundDuplicates() {
        LowerBound lowerBound = new LowerBound();
        Integer[] arrayWithDuplicates = {1, 2, 2, 2, 3, 4, 5};

        assertEquals(1, lowerBound.find(arrayWithDuplicates, 2), "Lower bound for 2 should be at index 1 (first occurrence)");

        Integer[] allSame = {2, 2, 2, 2, 2};
        assertEquals(0, lowerBound.find(allSame, 2), "Lower bound for 2 in all-same array should be at index 0");
    }

    /**
     * Test edge cases: empty array, null array, and null key.
     */
    @Test
    void testLowerBoundNullAndEmptyEdgeCases() {
        LowerBound lowerBound = new LowerBound();
        Integer[] emptyArray = {};
        assertEquals(0, lowerBound.find(emptyArray, 5), "Lower bound for an empty array should be 0");
        assertEquals(0, lowerBound.find(null, 5), "Lower bound for a null array should be 0");
        assertEquals(0, lowerBound.find(new Integer[] {1, 2, 3}, null), "Lower bound for a null key should be 0");
    }
}
