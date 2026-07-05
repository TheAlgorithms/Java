package com.thealgorithms.sorts;

/**
 * @author Tabbygray (<a href="https://github.com/Tabbygray">...</a>)
 * @see OddEvenSort
 */

public class OddEvenSortTest extends SortingAlgorithmTest {
    private final OddEvenSort oddEvenSort = new OddEvenSort();

    @Override
    SortAlgorithm getSortAlgorithm() {
        return oddEvenSort;
    }
}
