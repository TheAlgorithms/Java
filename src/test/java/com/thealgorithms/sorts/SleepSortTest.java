package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

public class SleepSortTest {
    @Test
    public void testSleepSort() {
        List<Integer> numbers = new ArrayList<>();
        Collections.addAll(numbers, 4, 6, 8, 1, 10);

        // Get sorted result from sleepSort
        List<Integer> sortedNumbers = SleepSort.sleepSort(numbers);

        // Expected sorted result
        List<Integer> expected = List.of(1, 4, 6, 8, 10);

        // Check if the sorted list matches the expected result
        assertEquals(expected, sortedNumbers, "The sorted numbers should match the expected list.");
    }

    @Test
    public void testSleepSortWithAdjacentNumbers() {
        List<Integer> numbers = new ArrayList<>();
        Collections.addAll(numbers, 1, 2, 3, 4);

        // Get sorted result from sleepSort
        List<Integer> sortedNumbers = SleepSort.sleepSort(numbers);

        // Expected sorted result
        List<Integer> expected = List.of(1, 2, 3, 4);

        // Check if the sorted list matches the expected result
        assertEquals(expected, sortedNumbers, "The sorted numbers should match the expected list.");
    }

    @Test
    public void testSleepSortWithLargeNumbers() {
        List<Integer> numbers = new ArrayList<>();
        Collections.addAll(numbers, 1000, 500, 2000, 1500);

        // Get sorted result from sleepSort
        List<Integer> sortedNumbers = SleepSort.sleepSort(numbers);

        // Expected sorted result
        List<Integer> expected = List.of(500, 1000, 1500, 2000);

        // Check if the sorted list matches the expected result
        assertEquals(expected, sortedNumbers, "The sorted numbers should match the expected list.");
    }

    @Test
    public void testSleepSortWithNegativeNumbers() {
        List<Integer> numbers = List.of(15, -23, 8, 41, 30);

        // Expect IllegalArgumentException when a negative number is present
        assertThrows(IllegalArgumentException.class, () -> SleepSort.sleepSort(numbers), "Expected sleepSort() to throw IllegalArgumentException when negative number is present");
    }
}
