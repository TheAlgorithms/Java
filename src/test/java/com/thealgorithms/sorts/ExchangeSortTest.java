package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class ExchangeSortTest {
   
  @Test
    public void ExchangeSortTestDuplicate() {
        int[] array = {1, 1, 1, 5, 9, 8, 7, 2, 6};
        int[] expResult = {1, 1, 1, 2, 5, 6, 7, 8, 9};
        int[] actResult = ExchangeSort.binaryInsertSort(array, 0);
        assertArrayEquals(expResult, actResult);
    }
    @Test
    // valid test case
    public void ExchangeSortTestNonDuplicate() {
        int[] array = {1, 0, 2, 5, 3, 4, 9, 8, 10, 6, 7};
        int[] expResult = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] actResult = ExchangeSort.binaryInsertSort(array, 0);
        assertArrayEquals(expResult, actResult);
    }
}
