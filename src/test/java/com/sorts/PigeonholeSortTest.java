package com.sorts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PigeonholeSortTest {

    @Test
    void testPigeonholeSort() {

        PigeonholeSort pigeonholeSort = new PigeonholeSort();

        // Test Case 1
        Integer[] unsorted1 = new Integer[]{5, 1, 7, 2, 9, 6, 3, 4, 8};
        Integer[] sorted1 = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        Assertions.assertArrayEquals(sorted1, pigeonholeSort.sort(unsorted1));

        // Test Case 2
        Integer[] unsorted2 = new Integer[]{-5, 1, 7, 2, -9, 6, -3, 4, 8};
        Integer[] sorted2 = new Integer[]{-9, -5, -3, 1, 2, 4, 6, 7, 8};
        Assertions.assertArrayEquals(sorted2, pigeonholeSort.sort(unsorted2));

        // Test Case 3
        Integer[] unsorted3 = new Integer[]{-5, 1, 7, 2, -9, 6, -3, 4, 1, 8, 1, 1};
        Integer[] sorted3 = new Integer[]{-9, -5, -3, 1, 1, 1, 1, 2, 4, 6, 7, 8};
        Assertions.assertArrayEquals(sorted3, pigeonholeSort.sort(unsorted3));

    }
}
