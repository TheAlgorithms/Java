package src.test.com.java.sorts;


import org.junit.Assert;
import org.junit.Test;
import src.main.com.java.sorts.HeapSort;

import java.util.Arrays;

public class HeapSortTest {

  @Test
  public void heapSortTest() {
    HeapSort heapSort = new HeapSort();
    Integer[] unsortedInt = new Integer[]{0, 5, 9, 2, 1, 3, 4, 8, 6, 7};
    Integer[] sortedInt = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    System.out.println(Arrays.toString(heapSort.sort(unsortedInt)));

    Assert.assertArrayEquals(sortedInt, heapSort.sort(unsortedInt));

    Character[] unsortedChar = new Character[]{'f', 'h', 'c', 'a', 'b', 'd', 'g', 'e'};
    Character[] sortedChar = new Character[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
    System.out.println(Arrays.toString(heapSort.sort(unsortedChar)));

    Assert.assertArrayEquals(sortedChar, heapSort.sort(unsortedChar));
  }
}
