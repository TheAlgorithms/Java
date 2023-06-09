package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BinaryInsertionSortTest {

    BinaryInsertionSort BIS = new BinaryInsertionSort();

    @Test
    // valid test case
    public void BinaryInsertionSortTestNonDuplicate() {
        int[] array = {1, 0, 2, 5, 3, 4, 9, 8, 10, 6, 7};
        int[] expResult = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] actResult = BIS.binaryInsertSort(array);
        assertArrayEquals(expResult, actResult);
    }

    @Test
    public void BinaryInsertionSortTestDuplicate() {
        int[] array = {1, 1, 1, 5, 9, 8, 7, 2, 6};
        int[] expResult = {1, 1, 1, 2, 5, 6, 7, 8, 9};
        int[] actResult = BIS.binaryInsertSort(array);
        assertArrayEquals(expResult, actResult);
    }
}
