package com.thealgorithms.sorts;

/**
 * Test class for DoubleHashingSort algorithm
 */
public class DoubleHashingSortTest extends SortingAlgorithmTest {

    @Override
    SortAlgorithm getSortAlgorithm() {
        return new DoubleHashingSort();
    }
}