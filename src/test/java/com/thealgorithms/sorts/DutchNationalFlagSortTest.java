package com.thealgorithms.sorts;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class DutchNationalFlagSortTest {
    @Test
    /*
      1 will be used as intended middle.
      Partitions on the result array: [ smaller than 1 , equal 1, greater than 1]
     */
    void DNFSTestOdd() {
        Integer[] integers = {1, 3, 1, 4, 0};
        Integer[] integersResult = {0, 1, 1, 4, 3};
        DutchNationalFlagSort dutchNationalFlagSort = new DutchNationalFlagSort();
        dutchNationalFlagSort.sort(integers);
        assertArrayEquals(integers, integersResult);
    }

    @Test
    /*
      3 will be used as intended middle.
      Partitions on the result array: [ smaller than 3 , equal 3, greater than 3]
     */
    void DNFSTestEven() {
        Integer[] integers = {8, 1, 3, 1, 4, 0};
        Integer[] integersResult = {0, 1, 1, 3, 4, 8};
        DutchNationalFlagSort dutchNationalFlagSort = new DutchNationalFlagSort();
        dutchNationalFlagSort.sort(integers);
        assertArrayEquals(integers, integersResult);
    }

    @Test
    /*
      "b" will be used as intended middle.
      Partitions on the result array: [ smaller than b , equal b, greater than b]
     */
    void DNFSTestEvenStrings() {
        String[] strings = {"a", "d", "b", "s", "e", "e"};
        String[] stringsResult = {"a", "b", "s", "e", "e", "d"};
        DutchNationalFlagSort dutchNationalFlagSort = new DutchNationalFlagSort();
        dutchNationalFlagSort.sort(strings);
        assertArrayEquals(strings, stringsResult);
    }

    @Test
    /*
      "b" will be used as intended middle.
      Partitions on the result array: [ smaller than b , equal b, greater than b]
     */
    void DNFSTestOddStrings() {
        String[] strings = {"a", "d", "b", "s", "e"};
        String[] stringsResult = {"a", "b", "s", "e", "d"};
        DutchNationalFlagSort dutchNationalFlagSort = new DutchNationalFlagSort();
        dutchNationalFlagSort.sort(strings);
        assertArrayEquals(strings, stringsResult);
    }

    @Test
    /*
      0 will be used as intended middle.
      Partitions on the result array: [ smaller than 0 , equal 0, greater than 0]
     */
    void DNFSTestOddMidGiven() {
        Integer[] integers = {1, 3, 1, 4, 0};
        Integer[] integersResult = {0, 1, 4, 3, 1};
        DutchNationalFlagSort dutchNationalFlagSort = new DutchNationalFlagSort();
        dutchNationalFlagSort.sort(integers, 0);
        assertArrayEquals(integers, integersResult);
    }

    @Test
    /*
      4 will be used as intended middle.
      Partitions on the result array: [ smaller than 4 , equal 4, greater than 4]
     */
    void DNFSTestEvenMidGiven() {
        Integer[] integers = {8, 1, 3, 1, 4, 0};
        Integer[] integersResult = {0, 1, 3, 1, 4, 8};
        DutchNationalFlagSort dutchNationalFlagSort = new DutchNationalFlagSort();
        dutchNationalFlagSort.sort(integers, 4);
        assertArrayEquals(integers, integersResult);
    }

    @Test
    /*
      "s" will be used as intended middle.
      Partitions on the result array: [ smaller than s , equal s, greater than s]
     */
    void DNFSTestEvenStringsMidGiven() {
        String[] strings = {"a", "d", "b", "s", "e", "e"};
        String[] stringsResult = {"a", "d", "b", "e", "e", "s"};
        DutchNationalFlagSort dutchNationalFlagSort = new DutchNationalFlagSort();
        dutchNationalFlagSort.sort(strings, "s");
        assertArrayEquals(strings, stringsResult);
    }

    @Test
    /*
      "e" will be used as intended middle.
      Partitions on the result array: [ smaller than e , equal e, greater than e]
     */
    void DNFSTestOddStringsMidGiven() {
        String[] strings = {"a", "d", "b", "s", "e"};
        String[] stringsResult = {"a", "d", "b", "e", "s"};
        DutchNationalFlagSort dutchNationalFlagSort = new DutchNationalFlagSort();
        dutchNationalFlagSort.sort(strings, "e");
        assertArrayEquals(strings, stringsResult);
    }
}
