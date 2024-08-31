package com.thealgorithms.sorts;

public class StoogeSortTest extends SortingAlgorithmTest {
    protected int getGeneratedArraySize() {
        return 1000;
    }

    @Override
    SortAlgorithm getSortAlgorithm() {
        return new StoogeSort();
    }
}
