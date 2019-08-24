package com.sorts;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class InsertionSortTest {

  @Test
  public void insertionSortTest() {
    InsertionSort insertionSort = new InsertionSort();

    Integer[] unsortedInt = new Integer[]{0, 5, 9, 2, 1, 3, 4, 8, 6, 7};
    Integer[] sortedInt = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    assertArrayEquals(sortedInt, insertionSort.sort(unsortedInt));

    unsortedInt = new Integer[]{5,4,3,2,1,0};
    sortedInt = new Integer[]{0, 1, 2, 3, 4, 5};
    assertArrayEquals(sortedInt, insertionSort.sort(unsortedInt));
    
    unsortedInt = new Integer[]{-1,-2,-3,-4,-5};
    sortedInt = new Integer[]{-5,-4,-3,-2,-1};
    assertArrayEquals(sortedInt, insertionSort.sort(unsortedInt));
    
    unsortedInt = new Integer[]{-1,-5,-10,-990,990,1010};
    sortedInt = new Integer[]{-990,-10,-5,-1,990,1010};
    assertArrayEquals(sortedInt, insertionSort.sort(unsortedInt));
    
    Character[] unsortedChar = new Character[]{'f', 'h', 'c', 'a', 'b', 'd', 'g', 'e'};
    Character[] sortedChar = new Character[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
    assertArrayEquals(sortedChar, insertionSort.sort(unsortedChar));
  }
}
