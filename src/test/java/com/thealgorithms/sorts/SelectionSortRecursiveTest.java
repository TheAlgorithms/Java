package com.thealgorithms.sorts;

public class SelectionSortRecursiveTest extends SortingAlgorithmTest {
    @Override
    SortAlgorithm getSortAlgorithm() {
        return new SelectionSortRecursive();
    }

    protected int getGeneratedArraySize() {
        return 5000;
    }
}
