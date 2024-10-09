package com.thealgorithms.divideandconquer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MedianOfTwoSortedArraysTest {

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testFindMedianSortedArrays(int[] nums1, int[] nums2, double expectedMedian) {
        assertEquals(expectedMedian, MedianOfTwoSortedArrays.findMedianSortedArrays(nums1, nums2));
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(
            // Test case 1: Arrays of equal length
            Arguments.of(new int[] {1, 3}, new int[] {2, 4}, 2.5),

            // Test case 2: Arrays of different lengths
            Arguments.of(new int[] {1, 3}, new int[] {2}, 2.0),

            // Test case 3: Arrays with even total length
            Arguments.of(new int[] {1, 2, 8}, new int[] {3, 4, 5, 6, 7}, 4.5),

            // Test case 4: Arrays with odd total length
            Arguments.of(new int[] {1, 2, 8}, new int[] {3, 4, 5}, 3.5),

            // Test case 5: Single element arrays
            Arguments.of(new int[] {1}, new int[] {3}, 2.0),

            // Test case 6: Empty arrays
            Arguments.of(new int[] {}, new int[] {0}, 0.0),

            // Test case 7: Same element arrays
            Arguments.of(new int[] {2, 2, 2}, new int[] {2, 2, 2}, 2.0));
    }
}
