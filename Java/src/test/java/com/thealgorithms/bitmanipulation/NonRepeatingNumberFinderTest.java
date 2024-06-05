package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test case for Non Repeating Number Finder
 * @author Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */

class NonRepeatingNumberFinderTest {

    @Test
    void testNonRepeatingNumberFinder() {
        int[] arr = {1, 2, 1, 2, 6};
        assertEquals(6, NonRepeatingNumberFinder.findNonRepeatingNumber(arr));
        int[] arr1 = {1, 2, 1, 2};
        assertEquals(0, NonRepeatingNumberFinder.findNonRepeatingNumber(arr1));
        int[] arr2 = {12};
        assertEquals(12, NonRepeatingNumberFinder.findNonRepeatingNumber(arr2));
    }
}
