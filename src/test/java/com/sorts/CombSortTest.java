package com.sorts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CombSortTest {

  @Test
  void testCombSort() {
    CombSort combSort = new CombSort();

    // Test positive integers
    Integer[] integerInput = new Integer[] { 9, 6, 2, 3, 0, 4, 5, 1, 7, 8 };
    Integer[] integerExpected = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    Assertions.assertArrayEquals(integerExpected, combSort.sort(integerInput));

    // Test negative integers
    integerInput = new Integer[] { -9, -6, -2, -3, 0, -4, -5, -1, -7, -8 };
    integerExpected = new Integer[] { -9, -8, -7, -6, -5, -4, -3, -2, -1, 0 };
    Assertions.assertArrayEquals(integerExpected, combSort.sort(integerInput));

    // Test mixed integers
    integerInput = new Integer[] { -50, 3, -1, 90, 77, 2, 5, 0, -100 };
    integerExpected = new Integer[] { -100, -50, -1, 0, 2, 3, 5, 77, 90 };
    Assertions.assertArrayEquals(integerExpected, combSort.sort(integerInput));

    // Test duplicate integers
    integerInput = new Integer[] { 0, 1, 0, 1, 0, 0 };
    integerExpected = new Integer[] { 0, 0, 0, 0, 1, 1 };
    Assertions.assertArrayEquals(integerExpected, combSort.sort(integerInput));

    // Test characters
    Character[] charInput = new Character[] { 'g', 'f', 'e', 'd', 'c', 'b', 'a' };
    Character[] charExpected = new Character[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g' };
    Assertions.assertArrayEquals(charExpected, combSort.sort(charInput));

    // Test duplicate characters
    charInput = new Character[] { 'g', 'g', 'g', 'd', 'c', 'b', 'a' };
    charExpected = new Character[] { 'a', 'b', 'c', 'd', 'g', 'g', 'g' };
    Assertions.assertArrayEquals(charExpected, combSort.sort(charInput));

    // Test string
    String[] stringInput = new String[] { "aaaa", "aaaaa", "cca", "bba", "bbba", "zz" };
    String[] stringExpected = new String[] { "aaaa", "aaaaa", "bba", "bbba", "cca", "zz" };
    Assertions.assertArrayEquals(stringExpected, combSort.sort(stringInput));
  }
}
