package com.thealgorithms.sorts;

/**
 * @author Tabbygray (https://github.com/Tabbygray)
 * @see OddEvenSort
 */

public class OddEvenSortTest extends SortingAlgorithmTest {
    private final OddEvenSort oddEvenSort = new OddEvenSort();

    @Override
    SortAlgorithm getSortAlgorithm() {
        return oddEvenSort;
    }
}
