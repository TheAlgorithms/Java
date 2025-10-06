package com.thealgorithms.sorts;

/**
 * Test class for DoubleHashingSort algorithm
 *
 * Tests the DoubleHashingSort implementation against the standard
 * SortingAlgorithmTest suite which includes edge cases for:
 * - Negative numbers
 * - Floating point special values (NaN, Infinity, -Infinity)
 * - Empty strings and special characters
 * - Custom objects
 * - Mixed data types
 */
public class DoubleHashingSortTest extends SortingAlgorithmTest {

    @Override
    SortAlgorithm getSortAlgorithm() {
        return new DoubleHashingSort();
    }
}
