package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class SortingAlgorithmTest {
    abstract SortAlgorithm getSortAlgorithm();

    @Test
    void shouldAcceptWhenEmptyArrayIsPassed() {
        Integer[] array = new Integer[] {};
        Integer[] expected = new Integer[] {};

        Integer[] sorted = getSortAlgorithm().sort(array);

        assertArrayEquals(expected, sorted);
    }

    @Test
    void shouldAcceptWhenEmptyListIsPassed() {
        List<Integer> list = new ArrayList<>();
        List<Integer> expected = new ArrayList<>();

        List<Integer> sorted = getSortAlgorithm().sort(list);

        assertIterableEquals(expected, sorted);
    }

    @Test
    void shouldAcceptWhenSingleValuedArrayIsPassed() {
        Integer[] array = new Integer[] {2};
        Integer[] expected = new Integer[] {2};

        Integer[] sorted = getSortAlgorithm().sort(array);

        assertArrayEquals(expected, sorted);
    }

    @Test
    void shouldAcceptWhenSingleValuedListIsPassed() {
        List<Integer> list = List.of(2);
        List<Integer> expected = List.of(2);

        List<Integer> sorted = getSortAlgorithm().sort(list);

        assertIterableEquals(expected, sorted);
    }

    @Test
    void shouldAcceptWhenListWithAllPositiveValuesIsPassed() {
        Integer[] array = new Integer[] {60, 7, 55, 9, 999, 3};
        Integer[] expected = new Integer[] {3, 7, 9, 55, 60, 999};

        Integer[] sorted = getSortAlgorithm().sort(array);

        assertArrayEquals(expected, sorted);
    }

    @Test
    void shouldAcceptWhenArrayWithAllPositiveValuesIsPassed() {
        List<Integer> list = List.of(60, 7, 55, 9, 999, 3);
        List<Integer> expected = List.of(3, 7, 9, 55, 60, 999);

        List<Integer> sorted = getSortAlgorithm().sort(list);

        assertIterableEquals(expected, sorted);
    }

    @Test
    void shouldAcceptWhenArrayWithAllNegativeValuesIsPassed() {
        Integer[] array = new Integer[] {-60, -7, -55, -9, -999, -3};
        Integer[] expected = new Integer[] {-999, -60, -55, -9, -7, -3};

        Integer[] sorted = getSortAlgorithm().sort(array);

        assertArrayEquals(expected, sorted);
    }

    @Test
    void shouldAcceptWhenListWithAllNegativeValuesIsPassed() {
        List<Integer> list = List.of(-60, -7, -55, -9, -999, -3);
        List<Integer> expected = List.of(-999, -60, -55, -9, -7, -3);

        List<Integer> sorted = getSortAlgorithm().sort(list);

        assertIterableEquals(expected, sorted);
    }

    @Test
    void shouldAcceptWhenArrayWithRealNumberValuesIsPassed() {
        Integer[] array = new Integer[] {60, -7, 55, 9, -999, -3};
        Integer[] expected = new Integer[] {-999, -7, -3, 9, 55, 60};

        Integer[] sorted = getSortAlgorithm().sort(array);

        assertArrayEquals(expected, sorted);
    }

    @Test
    void shouldAcceptWhenListWithRealNumberValuesIsPassed() {
        List<Integer> list = List.of(60, -7, 55, 9, -999, -3);
        List<Integer> expected = List.of(-999, -7, -3, 9, 55, 60);

        List<Integer> sorted = getSortAlgorithm().sort(list);

        assertIterableEquals(expected, sorted);
    }

    @Test
    void shouldAcceptWhenArrayWithDuplicateValueIsPassed() {
        Integer[] array = new Integer[] {60, 7, 55, 55, 999, 3};
        Integer[] expected = new Integer[] {3, 7, 55, 55, 60, 999};

        Integer[] sorted = getSortAlgorithm().sort(array);

        assertArrayEquals(expected, sorted);
    }

    @Test
    void shouldAcceptWhenListWithDuplicateValueIsPassed() {
        List<Integer> list = List.of(60, 7, 55, 55, 999, 3);
        List<Integer> expected = List.of(3, 7, 55, 55, 60, 999);

        List<Integer> sorted = getSortAlgorithm().sort(list);

        assertIterableEquals(expected, sorted);
    }

    @Test
    void shouldAcceptWhenStringValueArrayIsPassed() {
        String[] array = {"z", "a", "x", "b", "y"};
        String[] expected = {"a", "b", "x", "y", "z"};

        String[] sorted = getSortAlgorithm().sort(array);

        assertArrayEquals(expected, sorted);
    }

    @Test
    void shouldAcceptWhenStringValueListIsPassed() {
        List<String> list = List.of("z", "a", "x", "b", "y");
        List<String> expected = List.of("a", "b", "x", "y", "z");

        List<String> sorted = getSortAlgorithm().sort(list);

        assertIterableEquals(expected, sorted);
    }

    @Test
    void shouldAcceptWhenRandomArrayIsPassed() {
        int randomSize = SortUtilsRandomGenerator.generateInt(10_000);
        Double[] array = SortUtilsRandomGenerator.generateArray(randomSize);
        Double[] sorted = getSortAlgorithm().sort(array);
        assertTrue(SortUtils.isSorted(sorted));
    }

    @Test
    void shouldAcceptWhenRandomListIsPassed() {
        int randomSize = SortUtilsRandomGenerator.generateInt(10_000);
        Double[] array = SortUtilsRandomGenerator.generateArray(randomSize);
        List<Double> list = List.of(array);
        List<Double> sorted = getSortAlgorithm().sort(list);
        assertTrue(SortUtils.isSorted(sorted));
    }
}
