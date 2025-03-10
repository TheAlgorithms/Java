package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class NumberAppearingOddTimesTest {

    /**
     * Parameterized test for findOddOccurrence method. Tests multiple
     * input arrays and their expected results.
     */
    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testFindOddOccurrence(int[] input, int expected) {
        assertEquals(expected, NumberAppearingOddTimes.findOddOccurrence(input));
    }

    /**
     * Provides test cases for the parameterized test.
     * Each test case consists of an input array and the expected result.
     */
    private static Stream<Arguments> provideTestCases() {
        return Stream.of(
            // Single element appearing odd times (basic case)
            Arguments.of(new int[] {5, 6, 7, 8, 6, 7, 5}, 8),

            // More complex case with multiple pairs
            Arguments.of(new int[] {2, 3, 5, 4, 5, 2, 4, 3, 5, 2, 4, 4, 2}, 5),

            // Case with only one element appearing once
            Arguments.of(new int[] {10, 10, 20, 20, 30}, 30),

            // Negative numbers with an odd occurrence
            Arguments.of(new int[] {-5, -5, -3, -3, -7, -7, -7}, -7),

            // All elements cancel out to 0 (even occurrences of all elements)
            Arguments.of(new int[] {1, 2, 1, 2}, 0),

            // Array with a single element (trivial case)
            Arguments.of(new int[] {42}, 42),

            // Large array with repeated patterns
            Arguments.of(new int[] {1, 1, 2, 2, 3, 3, 3, 4, 4}, 3));
    }
}
