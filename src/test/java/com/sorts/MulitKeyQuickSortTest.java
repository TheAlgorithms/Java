package com.sorts;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MultiKeyQuickSortTest {

    @Test
    void multiKeyQuickSortTest() {

        // Test Case with Empty input
        String[] emptyInput = new String[] { "", "", "", "", "", "", "", "", "", "" };

        MultiKeyQuickSort.sort(emptyInput);

        // The result of it would still be the same.
        Assertions.assertEquals(Arrays.toString(emptyInput), Arrays.toString(emptyInput));

        // Test case 1
        String[] unsortedString1 = new String[] { "apple", "cat", "banana", "donkey" };
        String[] sortedString1 = new String[] { "apple", "banana", "cat", "donkey" };

        MultiKeyQuickSort.sort(unsortedString1);

        Assertions.assertEquals(Arrays.toString(sortedString1), Arrays.toString(unsortedString1));

        // Test Case 2
        String[] unsortedString2 = new String[] { "aa", "ad", "ab", "boy" };
        String[] sortedString2 = new String[] { "aa", "ab", "ad", "boy" };

        MultiKeyQuickSort.sort(unsortedString2);

        Assertions.assertEquals(Arrays.toString(sortedString2), Arrays.toString(unsortedString2));

        // Test Case 3
        String[] unsortedString3 = new String[] { "z", "m", "", "a", "d", "tt", "tt", "tt", "foo", "bar" };
        String[] sortedString3 = new String[] { "", "a", "bar", "d", "foo", "m", "tt", "tt", "tt", "z" };

        MultiKeyQuickSort.sort(unsortedString3);

        Assertions.assertEquals(Arrays.toString(sortedString3), Arrays.toString(unsortedString3));

        // Test Case 4
        String[] unsortedString4 = new String[] { "1", "2", "4", "3" };
        String[] sortedString4 = new String[] { "1", "2", "3", "4" };

        MultiKeyQuickSort.sort(unsortedString4);

        Assertions.assertEquals(Arrays.toString(sortedString4), Arrays.toString(unsortedString4));



    }

}
