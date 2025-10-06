package com.thealgorithms.sorts;

public class PostmanSortTest extends SortingAlgorithmTest {
    @Override
    SortAlgorithm getSortAlgorithm() {
        return new PostmanSort();
    }
}