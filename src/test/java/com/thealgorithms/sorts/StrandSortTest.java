package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.LinkedList;
import org.junit.jupiter.api.Test;

class StrandSortTest {

    @Test
    // valid test case
    public void StrandSortNonDuplicateTest() {
        int[] expectedArray = {1, 2, 3, 4, 5};
        LinkedList<Integer> actualList = StrandSort.strandSort(new LinkedList<Integer>(Arrays.asList(3, 1, 2, 4, 5)));
        int[] actualArray = new int[actualList.size()];
        for (int i = 0; i < actualList.size(); i++) {
            actualArray[i] = actualList.get(i);
        }
        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    // valid test case
    public void StrandSortDuplicateTest() {
        int[] expectedArray = {2, 2, 2, 5, 7};
        LinkedList<Integer> actualList = StrandSort.strandSort(new LinkedList<Integer>(Arrays.asList(7, 2, 2, 2, 5)));
        int[] actualArray = new int[actualList.size()];
        for (int i = 0; i < actualList.size(); i++) {
            actualArray[i] = actualList.get(i);
        }
        assertArrayEquals(expectedArray, actualArray);
    }
}
