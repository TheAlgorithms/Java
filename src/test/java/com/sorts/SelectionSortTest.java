package src.test.java.com.sorts;

import org.junit.Assert;
import org.junit.Test;
import src.main.java.com.sorts.SelectionSort;

import java.util.Arrays;

public class SelectionSortTest {

  @Test
  public void selectionSortTest() {
    SelectionSort selectionSort = new SelectionSort();
    Integer[] unsortedInt = new Integer[]{0, 5, 9, 2, 1, 3, 4, 8, 6, 7};
    Integer[] sortedInt = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    System.out.println(Arrays.toString(selectionSort.sort(unsortedInt)));

    Assert.assertArrayEquals(sortedInt, selectionSort.sort(unsortedInt));

    Character[] unsortedChar = new Character[]{'f', 'h', 'c', 'a', 'b', 'd', 'g', 'e'};
    Character[] sortedChar = new Character[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
    System.out.println(Arrays.toString(selectionSort.sort(unsortedChar)));

    Assert.assertArrayEquals(sortedChar, selectionSort.sort(unsortedChar));
  }
}
