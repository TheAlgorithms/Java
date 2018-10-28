package src.test.java.com.sorts;


import org.junit.Assert;
import org.junit.Test;
import src.main.java.com.sorts.CountingSort;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class CountingSortTest {

  @Test
  public void countingSortTest() {
    CountingSort countingSort = new CountingSort();

    Integer[] unsortedInt = new Integer[]{0, 5, 9, 2, 1, 3, 4, 8, 6, 7};
    Integer[] sortedInt = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    Assert.assertArrayEquals(sortedInt, countingSort.sort(unsortedInt));

    unsortedInt = new Integer[]{5, 4, 3, 2, 1, 0};
    sortedInt = new Integer[]{0, 1, 2, 3, 4, 5};
    Assert.assertArrayEquals(sortedInt, countingSort.sort(unsortedInt));

    unsortedInt = new Integer[]{-1, -2, -3, -4, -5};
    sortedInt = new Integer[]{-5, -4, -3, -2, -1};
    Assert.assertArrayEquals(sortedInt, countingSort.sort(unsortedInt));

    unsortedInt = new Integer[]{-1, -5, -10, -990, 990, 1010};
    sortedInt = new Integer[]{-990, -10, -5, -1, 990, 1010};
    Assert.assertArrayEquals(sortedInt, countingSort.sort(unsortedInt));


    Character[] unsortedChar = new Character[]{'f', 'h', 'c', 'a', 'b', 'd', 'g', 'e'};
    Character[] sortedChar = new Character[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
    Assert.assertArrayEquals(sortedChar, countingSort.sort(unsortedChar));

    unsortedChar = new Character[]{'c', 'a', 'e', 'b', 'd', 'a', 'f', 'g', 'c'};
    sortedChar = new Character[]{'a', 'a', 'b', 'c', 'c', 'd', 'e', 'f', 'g'};
    Assert.assertArrayEquals(sortedChar, countingSort.sort(unsortedChar));

    List<Integer> unsortedInts = Stream.of(4, 23, 6, 78, 1, 54, 23, 1, 9, 231, 9, 12).collect(toList());
    List<Integer> sortedInts = Stream.of(1, 1, 4, 6, 9, 9, 12, 23, 23, 54, 78, 231).collect(toList());
    Assert.assertEquals(sortedInts, countingSort.sort(unsortedInts));
    Assert.assertEquals(sortedInts, countingSort.streamSort(unsortedInts));

    List<String> unsortedStrings = Stream.of("c", "a", "e", "b", "d", "a", "f", "g", "c").collect(toList());
    List<String> sortedStrings = Stream.of("a", "a", "b", "c", "c", "d", "e", "f", "g").collect(toList());
    Assert.assertEquals(sortedStrings, countingSort.sort(unsortedStrings));
    Assert.assertEquals(sortedStrings, countingSort.streamSort(unsortedStrings));
  }
}
