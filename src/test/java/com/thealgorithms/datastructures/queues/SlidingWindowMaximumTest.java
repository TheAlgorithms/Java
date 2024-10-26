package com.thealgorithms.datastructures.queues;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class SlidingWindowMaximumTest {

    @ParameterizedTest
    @MethodSource("provideTestCases")
    public void testMaxSlidingWindow(int[] nums, int k, int[] expected) {
        assertArrayEquals(expected, SlidingWindowMaximum.maxSlidingWindow(nums, k));
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(
            // Test case 1: Example from the problem statement
            Arguments.of(new int[] {1, 3, -1, -3, 5, 3, 6, 7}, 3, new int[] {3, 3, 5, 5, 6, 7}),

            // Test case 2: All elements are the same
            Arguments.of(new int[] {4, 4, 4, 4, 4}, 2, new int[] {4, 4, 4, 4}),

            // Test case 3: Window size equals the array length
            Arguments.of(new int[] {2, 1, 5, 3, 6}, 5, new int[] {6}),

            // Test case 4: Single element array with window size 1
            Arguments.of(new int[] {7}, 1, new int[] {7}),

            // Test case 5: Window size larger than the array length
            Arguments.of(new int[] {1, 2, 3}, 4, new int[] {}),

            // Test case 6: Decreasing sequence
            Arguments.of(new int[] {9, 8, 7, 6, 5, 4}, 3, new int[] {9, 8, 7, 6}),

            // Test case 7: Increasing sequence
            Arguments.of(new int[] {1, 2, 3, 4, 5}, 2, new int[] {2, 3, 4, 5}),

            // Test case 8: k is zero
            Arguments.of(new int[] {1, 3, -1, -3, 5, 3, 6, 7}, 0, new int[] {}),

            // Test case 9: Array with negative numbers
            Arguments.of(new int[] {-4, -2, -5, -1, -3}, 3, new int[] {-2, -1, -1}),

            // Test case 10: Empty array
            Arguments.of(new int[] {}, 3, new int[] {}));
    }
}
