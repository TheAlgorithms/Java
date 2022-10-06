package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class WiggleSortTest {

    @Test
    void WiggleTestNumbersEven() {
        WiggleSort wiggleSort = new WiggleSort();
        Integer[] values = { 1, 2, 3, 4 };
        Integer[] result = { 1, 4, 2, 3 };
        wiggleSort.sort(values);
        assertArrayEquals(values, result);
    }

    @Test
    void WiggleTestNumbersOdd() {
        WiggleSort wiggleSort = new WiggleSort();
        Integer[] values = { 1, 2, 3, 4, 5 };
        Integer[] result = { 3, 5, 1, 4, 2 };
        wiggleSort.sort(values);
        assertArrayEquals(values, result);
    }

    @Test
    void WiggleTestNumbersOddDuplicates() {
        WiggleSort wiggleSort = new WiggleSort();
        Integer[] values = { 7, 2, 2, 2, 5 };
        Integer[] result = { 2, 7, 2, 5, 2 };
        wiggleSort.sort(values);
        assertArrayEquals(values, result);
    }

    @Test
    void WiggleTestNumbersOddMultipleDuplicates() {
        WiggleSort wiggleSort = new WiggleSort();
        Integer[] values = { 1, 1, 2, 2, 5 };
        Integer[] result = { 2, 5, 1, 2, 1 };
        wiggleSort.sort(values);
        assertArrayEquals(values, result);
    }

    @Test
    void WiggleTestNumbersEvenMultipleDuplicates() {
        WiggleSort wiggleSort = new WiggleSort();
        Integer[] values = { 1, 1, 2, 2, 2, 5 };
        Integer[] result = { 2, 5, 1, 2, 1, 2 };
        wiggleSort.sort(values);
        System.out.println(Arrays.toString(values));
        assertArrayEquals(values, result);
    }

    @Test
    void WiggleTestNumbersEvenDuplicates() {
        WiggleSort wiggleSort = new WiggleSort();
        Integer[] values = { 1, 2, 4, 4 };
        Integer[] result = { 1, 4, 2, 4 };
        wiggleSort.sort(values);
        assertArrayEquals(values, result);
    }

    @Test
    void WiggleTestStrings() {
        WiggleSort wiggleSort = new WiggleSort();
        String[] values = { "a", "b", "d", "c" };
        String[] result = { "a", "d", "b", "c" };
        wiggleSort.sort(values);
        assertArrayEquals(values, result);
    }
}
