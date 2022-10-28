package com.thealgorithms.sorts;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MergeSortTest {

    private static MergeSort mergeSort;
    Integer[] array;
    Integer[] expected;
    Integer[] sorted;

    @BeforeAll
    public static void setup() {
        mergeSort = new MergeSort();
    }

    @Test
    void shouldAcceptWhenEmptyArrayIsPassed() {
        array = new Integer[]{};
        expected = new Integer[]{};

        sorted = mergeSort.sort(array);

        assertArrayEquals(expected, sorted);
    }

    @Test
    void shouldAcceptWhenSingleValuedArrayIsPassed() {
        array = new Integer[]{2};
        expected = new Integer[]{2};

        sorted = mergeSort.sort(array);

        assertArrayEquals(expected, sorted);
    }

    @Test
    void shouldAcceptWhenArrayWithAllPositiveValuesIsPassed() {
        array = new Integer[]{60, 7, 55, 9, 999, 3};
        expected = new Integer[]{3, 7, 9, 55, 60, 999};

        sorted = mergeSort.sort(array);

        assertArrayEquals(expected, sorted);
    }

    @Test
    void shouldAcceptWhenArrayWithAllNegativeValuesIsPassed() {
        array = new Integer[]{-60, -7, -55, -9, -999, -3};
        expected = new Integer[]{-999, -60, -55, -9, -7, -3};

        sorted = mergeSort.sort(array);

        assertArrayEquals(expected, sorted);
    }

    @Test
    void shouldAcceptWhenArrayWithRealNumberValuesIsPassed() {
        array = new Integer[]{60, -7, 55, 9, -999, -3};
        expected = new Integer[]{-999, -7, -3, 9, 55, 60};

        sorted = mergeSort.sort(array);

        assertArrayEquals(expected, sorted);
    }

    @Test
    void shouldAcceptWhenArrayWithDuplicateValueIsPassed() {
        array = new Integer[]{60, 7, 55, 55, 999, 3};
        expected = new Integer[]{3, 7, 55, 55, 60, 999};

        sorted = mergeSort.sort(array);

        assertArrayEquals(expected, sorted);
    }

    @Test
    void shouldAcceptWhenStringValueArrayIsPassed() {
        String[] array = {"z", "a", "x", "b", "y"};
        String[] expected = {"a", "b", "x", "y", "z"};

        String[] sorted = mergeSort.sort(array);

        assertArrayEquals(expected, sorted);
    }


}
