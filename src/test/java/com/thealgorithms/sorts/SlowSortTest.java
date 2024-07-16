package com.thealgorithms.sorts;

/**
 * @author Rebecca Velez (https://github.com/rebeccavelez)
 * @see SlowSort
 */

public class SlowSortTest extends SortingAlgorithmTest {
    protected int getGeneratedArraySize() {
        return 100;
    }

    @Override
    SortAlgorithm getSortAlgorithm() {
        return new SlowSort();
    }
}
