package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Test case for Non Repeating Number Finder
 * This test class validates the functionality of the
 * NonRepeatingNumberFinder by checking various scenarios.
 *
 * @author Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */
class NonRepeatingNumberFinderTest {

    @ParameterizedTest
    @MethodSource("testCases")
    void testNonRepeatingNumberFinder(int[] arr, int expected) {
        assertEquals(expected, NonRepeatingNumberFinder.findNonRepeatingNumber(arr));
    }

    private static Arguments[] testCases() {
        return new Arguments[] {
            Arguments.of(new int[] {1, 2, 1, 2, 6}, 6), Arguments.of(new int[] {1, 2, 1, 2}, 0), // All numbers repeat
            Arguments.of(new int[] {12}, 12), // Single non-repeating number
            Arguments.of(new int[] {3, 5, 3, 4, 4}, 5), // More complex case
            Arguments.of(new int[] {7, 8, 7, 9, 8, 10, 10}, 9), // Non-repeating in the middle
            Arguments.of(new int[] {0, -1, 0, -1, 2}, 2), // Testing with negative numbers
            Arguments.of(new int[] {Integer.MAX_VALUE, 1, 1}, Integer.MAX_VALUE), // Edge case with max int
            Arguments.of(new int[] {2, 2, 3, 3, 4, 5, 4}, 5), // Mixed duplicates
            Arguments.of(new int[] {}, 0) // Edge case: empty array (should be handled as per design)
        };
    }
}
