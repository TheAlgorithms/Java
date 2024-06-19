package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

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

    @Test
    public void oddEvenSortEmptyArray() {
        Integer[] inputArray = {};
        Integer[] expectedOutput = {};
        assertArrayEquals(inputArray, expectedOutput);
    }

    @Test
    public void oddEvenSortNaturalNumberArray() {
        Integer[] inputArray = {18, 91, 86, 60, 21, 44, 37, 78, 98, 67};
        oddEvenSort.sort(inputArray);
        Integer[] expectedOutput = {18, 21, 37, 44, 60, 67, 78, 86, 91, 98};
        assertArrayEquals(inputArray, expectedOutput);
    }

    @Test
    public void oddEvenSortIntegerArray() {
        Integer[] inputArray = {57, 69, -45, 12, -85, 3, -76, 36, 67, -14};
        oddEvenSort.sort(inputArray);
        Integer[] expectedOutput = {-85, -76, -45, -14, 3, 12, 36, 57, 67, 69};
        assertArrayEquals(inputArray, expectedOutput);
    }
}
