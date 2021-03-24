package com.sorts;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class TimSortTest {

    @Test
    public void TimSortIntegers() {
        TimSort<Integer> timSort = new TimSort<>();
        Integer[] unsortedInt = {0, 5, 9, 2, 1, 3, 4, 8, 6, 7};
        Integer[] sortedInt = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Assertions.assertArrayEquals(sortedInt, timSort.sort(unsortedInt));
    }

    @Test
    void TimSortCharacters() {
        TimSort<Character> timSort = new TimSort<>();
        Character[] unsortedChar = {'f', 'h', 'c', 'a', 'b', 'd', 'g', 'e'};
        Character[] sortedChar = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        Assertions.assertArrayEquals(sortedChar, timSort.sort(unsortedChar));
    }

    @Test
    void TimSortStrings() {
        TimSort<String> timSort = new TimSort<>();
        String[] unsortedString = {"Bob", "Alex", "Karen", "William","Toby"};
        String[] sortedString = {"Alex", "Bob", "Karen", "Toby", "William"};
        Assertions.assertArrayEquals(sortedString, timSort.sort(unsortedString));
    }
}
