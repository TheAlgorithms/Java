package com.thealgorithms.sorts;

public class IntrospectiveSortTest extends SortingAlgorithmTest {
    @Override
    SortAlgorithm getSortAlgorithm() {
        return new IntrospectiveSort();
    }
}
