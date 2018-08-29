package src.test.java.com.sorts;

import org.junit.Assert;
import org.junit.Test;
import src.main.java.com.sorts.ShellSort;

public class ShellSortTest {

  @Test
  public void shellSortTest() {
    ShellSort shellSort = new ShellSort();

    Integer[] unsortedInt = new Integer[]{0, 5, 9, 2, 1, 3, 4, 8, 6, 7};
    Integer[] sortedInt = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    Assert.assertArrayEquals(sortedInt, shellSort.sort(unsortedInt));

    Character[] unsortedChar = new Character[]{'f', 'h', 'c', 'a', 'b', 'd', 'g', 'e'};
    Character[] sortedChar = new Character[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
    Assert.assertArrayEquals(sortedChar, shellSort.sort(unsortedChar));
  }
}
