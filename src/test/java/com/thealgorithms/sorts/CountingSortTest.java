package com.thealgorithms.sorts;

public class CountingSortTest extends SortingAlgorithmTest {
    @Override
    SortAlgorithm getSortAlgorithm() {
        return new CountingSort();
    }
}
