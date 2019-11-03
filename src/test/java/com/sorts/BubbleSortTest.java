package com.sorts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BubbleSortTest {

    @Test
    void bubbleSortTestIntegers() {
        BubbleSort<Integer> bubbleSort = new BubbleSort<>();

        Integer[] unsortedInt = {0, 5, 9, 2, 1, 3, 4, 8, 6, 7};
        Integer[] sortedInt = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Assertions.assertArrayEquals(sortedInt, bubbleSort.sort(unsortedInt));

    }

    @Test
    void bubbleSortTestCharacters() {
        BubbleSort<Character> bubbleSort = new BubbleSort<>();

        Character[] unsortedChar = {'f', 'h', 'c', 'a', 'b', 'd', 'g', 'e'};
        Character[] sortedChar = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        Assertions.assertArrayEquals(sortedChar, bubbleSort.sort(unsortedChar));

    }

    @Test
    void bubbleSortTestStrings() {
        BubbleSort<String> bubbleSort = new BubbleSort<>();

        String[] unsortedChar = {"abc", "adc", "bcd", "abb", "abc", "acb"};
        String[] sortedChar = {"abb", "abc", "abc", "acb", "adc", "bcd"};
        Assertions.assertArrayEquals(sortedChar, bubbleSort.sort(unsortedChar));

    }
}
