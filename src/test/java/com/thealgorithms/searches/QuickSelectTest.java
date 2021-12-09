package com.thealgorithms.searches;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class QuickSelectTest {
    @Test
    void quickSelectMinimumOfOneElement() {
        List<Integer> elements = Collections.singletonList(42);
        int minimum = QuickSelect.select(elements, 0);
        assertEquals(42, minimum);
    }

    @Test
    void quickSelectMinimumOfTwoElements() {
        List<Integer> elements1 = Arrays.asList(42, 90);
        List<Integer> elements2 = Arrays.asList(90, 42);

        int minimum1 = QuickSelect.select(elements1, 0);
        int minimum2 = QuickSelect.select(elements2, 0);

        assertEquals(42, minimum1);
        assertEquals(42, minimum2);
    }

    @Test
    void quickSelectMinimumOfThreeElements() {
        List<Integer> elements1 = Arrays.asList(1, 2, 3);
        List<Integer> elements2 = Arrays.asList(2, 1, 3);
        List<Integer> elements3 = Arrays.asList(2, 3, 1);

        int minimum1 = QuickSelect.select(elements1, 0);
        int minimum2 = QuickSelect.select(elements2, 0);
        int minimum3 = QuickSelect.select(elements3, 0);

        assertEquals(1, minimum1);
        assertEquals(1, minimum2);
        assertEquals(1, minimum3);
    }

    @Test
    void quickSelectMinimumOfManyElements() {
        List<Integer> elements = generateRandomIntegers(NUM_RND_ELEMENTS);
        int actual = QuickSelect.select(elements, 0);
        int expected = elements.stream().min(Comparator.naturalOrder()).get();
        assertEquals(expected, actual);
    }

    @Test
    void quickSelectMaximumOfOneElement() {
        List<Integer> elements = Collections.singletonList(42);
        int maximum = QuickSelect.select(elements, 0);
        assertEquals(42, maximum);
    }

    @Test
    void quickSelectMaximumOfTwoElements() {
        List<Integer> elements1 = Arrays.asList(42, 90);
        List<Integer> elements2 = Arrays.asList(90, 42);

        int maximum1 = QuickSelect.select(elements1, 1);
        int maximum2 = QuickSelect.select(elements2, 1);

        assertEquals(90, maximum1);
        assertEquals(90, maximum2);
    }

    @Test
    void quickSelectMaximumOfThreeElements() {
        List<Integer> elements1 = Arrays.asList(1, 2, 3);
        List<Integer> elements2 = Arrays.asList(2, 1, 3);
        List<Integer> elements3 = Arrays.asList(2, 3, 1);

        int maximum1 = QuickSelect.select(elements1, 2);
        int maximum2 = QuickSelect.select(elements2, 2);
        int maximum3 = QuickSelect.select(elements3, 2);

        assertEquals(3, maximum1);
        assertEquals(3, maximum2);
        assertEquals(3, maximum3);
    }

    @Test
    void quickSelectMaximumOfManyElements() {
        List<Integer> elements = generateRandomIntegers(NUM_RND_ELEMENTS);
        int actual = QuickSelect.select(elements, NUM_RND_ELEMENTS - 1);
        int expected = elements.stream().max(Comparator.naturalOrder()).get();
        assertEquals(expected, actual);
    }

    @Test
    void quickSelectMedianOfOneElement() {
        List<Integer> elements = Collections.singletonList(42);
        int median = QuickSelect.select(elements, 0);
        assertEquals(42, median);
    }

    @Test
    void quickSelectMedianOfThreeElements() {
        List<Integer> elements1 = Arrays.asList(1, 2, 3);
        List<Integer> elements2 = Arrays.asList(2, 1, 3);
        List<Integer> elements3 = Arrays.asList(2, 3, 1);

        int median1 = QuickSelect.select(elements1, 1);
        int median2 = QuickSelect.select(elements2, 1);
        int median3 = QuickSelect.select(elements3, 1);

        assertEquals(2, median1);
        assertEquals(2, median2);
        assertEquals(2, median3);
    }

    @Test
    void quickSelectMedianOfManyElements() {
        int medianIndex = NUM_RND_ELEMENTS / 2;
        List<Integer> elements = generateRandomIntegers(NUM_RND_ELEMENTS);
        int actual = QuickSelect.select(elements, medianIndex);

        List<Integer> elementsSorted = getSortedCopyOfList(elements);
        assertEquals(elementsSorted.get(medianIndex), actual);
    }

    @Test
    void quickSelect30thPercentileOf10Elements() {
        List<Integer> elements = generateRandomIntegers(10);
        int actual = QuickSelect.select(elements, 2);

        List<Integer> elementsSorted = getSortedCopyOfList(elements);
        assertEquals(elementsSorted.get(2), actual);
    }

    @Test
    void quickSelect30thPercentileOfManyElements() {
        int percentile30th = NUM_RND_ELEMENTS / 10 * 3;
        List<Integer> elements = generateRandomIntegers(NUM_RND_ELEMENTS);
        int actual = QuickSelect.select(elements, percentile30th);

        List<Integer> elementsSorted = getSortedCopyOfList(elements);
        assertEquals(elementsSorted.get(percentile30th), actual);
    }

    @Test
    void quickSelect70thPercentileOf10Elements() {
        List<Integer> elements = generateRandomIntegers(10);
        int actual = QuickSelect.select(elements, 6);

        List<Integer> elementsSorted = getSortedCopyOfList(elements);
        assertEquals(elementsSorted.get(6), actual);
    }

    @Test
    void quickSelect70thPercentileOfManyElements() {
        int percentile70th = NUM_RND_ELEMENTS / 10 * 7;
        List<Integer> elements = generateRandomIntegers(NUM_RND_ELEMENTS);
        int actual = QuickSelect.select(elements, percentile70th);

        List<Integer> elementsSorted = getSortedCopyOfList(elements);
        assertEquals(elementsSorted.get(percentile70th), actual);
    }

    @Test
    void quickSelectMedianOfThreeCharacters() {
        List<Character> elements = Arrays.asList('X', 'Z', 'Y');
        char actual = QuickSelect.select(elements, 1);
        assertEquals(actual, 'Y');
    }

    @Test
    void quickSelectMedianOfManyCharacters() {
        List<Character> elements = generateRandomCharacters(NUM_RND_ELEMENTS);
        char actual = QuickSelect.select(elements, NUM_RND_ELEMENTS / 30);

        List<Character> elementsSorted = getSortedCopyOfList(elements);
        assertEquals(elementsSorted.get(NUM_RND_ELEMENTS / 30), actual);
    }

    @Test
    void quickSelectNullList() {
        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> QuickSelect.select(null, 0)
        );
        String expectedMsg = "The list of elements must not be null.";
        assertEquals(expectedMsg, exception.getMessage());
    }

    @Test
    void quickSelectEmptyList() {
        List<String> objects = Collections.emptyList();
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> QuickSelect.select(objects, 0)
        );
        String expectedMsg = "The list of elements must not be empty.";
        assertEquals(expectedMsg, exception.getMessage());
    }

    @Test
    void quickSelectIndexOutOfLeftBound() {
        IndexOutOfBoundsException exception = assertThrows(
                IndexOutOfBoundsException.class,
                () -> QuickSelect.select(Collections.singletonList(1), -1)
        );
        String expectedMsg = "The index must not be negative.";
        assertEquals(expectedMsg, exception.getMessage());
    }

    @Test
    void quickSelectIndexOutOfRightBound() {
        IndexOutOfBoundsException exception = assertThrows(
                IndexOutOfBoundsException.class,
                () -> QuickSelect.select(Collections.singletonList(1), 1)
        );
        String expectedMsg = "The index must be less than the number of elements.";
        assertEquals(expectedMsg, exception.getMessage());
    }

    private static final int NUM_RND_ELEMENTS = 99;
    private static final Random RANDOM = new Random(42);
    private static final int ASCII_A = 0x41;
    private static final int ASCII_Z = 0x5A;

    private static List<Integer> generateRandomIntegers(int n) {
        return RANDOM.ints(n).boxed().collect(Collectors.toList());
    }

    private static List<Character> generateRandomCharacters(int n) {
        return RANDOM.ints(n, ASCII_A, ASCII_Z)
                .mapToObj(i -> (char) i)
                .collect(Collectors.toList());
    }

    private static <T extends Comparable<T>> List<T> getSortedCopyOfList(List<T> list) {
        return list.stream().sorted().collect(Collectors.toList());
    }
}
