package com.thealgorithms.sorts;

public class SpreadSortTest extends SortingAlgorithmTest {

    protected int getGeneratedArraySize() {
        return 1000;
    }

    @Override
    SortAlgorithm getSortAlgorithm() {
        return new SpreadSort();
    }
}
