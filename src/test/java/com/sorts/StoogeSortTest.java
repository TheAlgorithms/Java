package com.sorts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StoogeSortTest {

    @Test
    void stoogeSortTest() {
        StoogeSort stoogesort = new StoogeSort();

        Integer unsortedArr[] = {2, 4, 5, 3, 1};
        Integer n = unsortedArr.length;
        Integer sortedArr[] = {1, 2, 3, 4, 5};
        Assertions.assertArrayEquals(sortedArr, stoogesort.sort(unsortedArr, 0, n - 1));

        unsortedArr = new Integer[]{-22, -34, -25, -53, -11};
        sortedArr = new Integer[]{-53, -34, -25, -22, -11};
        Assertions.assertArrayEquals(sortedArr, stoogesort.sort(unsortedArr, 0, n - 1));

        Character[] unsortedCharArr = new Character[]{'a', 'r', 'd', 'k', 'p'};
        n = unsortedCharArr.length;
        Character[] sortedCharArr = new Character[]{'a', 'd', 'k', 'p', 'r'};
        Assertions.assertArrayEquals(sortedCharArr, stoogesort.sort(unsortedCharArr, 0, n - 1));
    }
}
