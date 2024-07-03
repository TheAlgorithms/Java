package com.thealgorithms.sorts;

public class CountingSortUsingStreamTest extends SortingAlgorithmTest {
    @Override
    SortAlgorithm getSortAlgorithm() {
        return new CountingSortUsingStream();
    }
}
