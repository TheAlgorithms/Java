package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Test class for {@link MaximumSumOfDistinctSubarraysWithLengthK}.
 *
 * This class contains comprehensive test cases to verify the correctness of the
 * maximum subarray sum algorithm with distinct elements constraint.
 */
class MaximumSumOfDistinctSubarraysWithLengthKTest {

    /**
     * Parameterized test for various input scenarios.
     *
     * @param expected the expected maximum sum
     * @param k        the subarray size
     * @param arr      the input array
     */
    @ParameterizedTest
    @MethodSource("inputStream")
    void testMaximumSubarraySum(long expected, int k, int[] arr) {
        assertEquals(expected, MaximumSumOfDistinctSubarraysWithLengthK.maximumSubarraySum(k, arr));
    }

    /**
     * Provides test cases for the parameterized test.
     *
     * Test cases cover:
     * - Normal cases with distinct and duplicate elements
     * - Edge cases (empty array, k = 0, k > array length)
     * - Single element arrays
     * - Arrays with all duplicates
     * - Negative numbers
     * - Large sums
     *
     * @return stream of test arguments
     */
    private static Stream<Arguments> inputStream() {
        return Stream.of(
            // Normal case: [5, 4, 2] has distinct elements with sum 11, but [4, 2, 9] also
            // distinct with sum 15
            Arguments.of(15L, 3, new int[] {1, 5, 4, 2, 9, 9, 9}),
            // All elements are same, no distinct subarray of size 3
            Arguments.of(0L, 3, new int[] {4, 4, 4}),
            // First three have duplicates, but [1, 2, 3] are distinct with sum 6, wait
            // [9,1,2] has sum 12
            Arguments.of(12L, 3, new int[] {9, 9, 9, 1, 2, 3}),
            // k = 0, should return 0
            Arguments.of(0L, 0, new int[] {9, 9, 9}),
            // k > array length, should return 0
            Arguments.of(0L, 5, new int[] {9, 9, 9}),
            // k = 1, single element (always distinct)
            Arguments.of(9L, 1, new int[] {9, 2, 3, 7}),
            // All distinct elements, size matches array
            Arguments.of(15L, 5, new int[] {1, 2, 3, 4, 5}),
            // Array with negative numbers
            Arguments.of(6L, 3, new int[] {-1, 2, 3, 1, -2, 4}),
            // Single element array
            Arguments.of(10L, 1, new int[] {10}),
            // All duplicates with k = 2
            Arguments.of(0L, 2, new int[] {7, 7, 7, 7}),
            // Empty array
            Arguments.of(0L, 3, new int[] {}),
            // k much larger than array length
            Arguments.of(0L, 10, new int[] {1, 2, 3}));
    }

    /**
     * Test with a larger array and larger k value.
     */
    @Test
    void testLargerArray() {
        int[] arr = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        long result = MaximumSumOfDistinctSubarraysWithLengthK.maximumSubarraySum(5, arr);
        // Maximum sum with 5 distinct elements: [6,7,8,9,10] = 40
        assertEquals(40L, result);
    }

    /**
     * Test with negative k value.
     */
    @Test
    void testNegativeK() {
        int[] arr = new int[] {1, 2, 3, 4, 5};
        long result = MaximumSumOfDistinctSubarraysWithLengthK.maximumSubarraySum(-1, arr);
        assertEquals(0L, result);
    }

    /**
     * Test with null array.
     */
    @Test
    void testNullArray() {
        int[] nullArray = null;
        long result = MaximumSumOfDistinctSubarraysWithLengthK.maximumSubarraySum(3, new int[][] {nullArray}[0]);
        assertEquals(0L, result);
    }

    /**
     * Test with array containing duplicates at boundaries.
     */
    @Test
    void testDuplicatesAtBoundaries() {
        int[] arr = new int[] {1, 1, 2, 3, 4, 4};
        // [2, 3, 4] is the only valid window with sum 9
        long result = MaximumSumOfDistinctSubarraysWithLengthK.maximumSubarraySum(3, arr);
        assertEquals(9L, result);
    }

    /**
     * Test with large numbers to verify long return type.
     */
    @Test
    void testLargeNumbers() {
        int[] arr = new int[] {1000000, 2000000, 3000000, 4000000};
        // All elements are distinct, max sum with k=3 is [2000000, 3000000, 4000000] =
        // 9000000
        long result = MaximumSumOfDistinctSubarraysWithLengthK.maximumSubarraySum(3, arr);
        assertEquals(9000000L, result);
    }

    /**
     * Test where multiple windows have the same maximum sum.
     */
    @Test
    void testMultipleMaxWindows() {
        int[] arr = new int[] {1, 2, 3, 4, 3, 2, 1};
        // Windows [1,2,3], [2,3,4], [4,3,2], [3,2,1] - max is [2,3,4] = 9
        long result = MaximumSumOfDistinctSubarraysWithLengthK.maximumSubarraySum(3, arr);
        assertEquals(9L, result);
    }

    /**
     * Test with only two elements and k=2.
     */
    @Test
    void testTwoElementsDistinct() {
        int[] arr = new int[] {5, 10};
        long result = MaximumSumOfDistinctSubarraysWithLengthK.maximumSubarraySum(2, arr);
        assertEquals(15L, result);
    }

    /**
     * Test with only two elements (duplicates) and k=2.
     */
    @Test
    void testTwoElementsDuplicate() {
        int[] arr = new int[] {5, 5};
        long result = MaximumSumOfDistinctSubarraysWithLengthK.maximumSubarraySum(2, arr);
        assertEquals(0L, result);
    }
}
