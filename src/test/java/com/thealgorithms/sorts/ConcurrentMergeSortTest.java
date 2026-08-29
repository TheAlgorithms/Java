package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * JUnit 5 test class for {@link ConcurrentMergeSort}.
 */
public class ConcurrentMergeSortTest {

    @Test
    public void testAlreadySortedArray() {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        ConcurrentMergeSort.sort(array);

        assertArrayEquals(expected, array, "Already sorted array should remain unchanged.");
    }

    @Test
    public void testReverseSortedArray() {
        int[] array = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        ConcurrentMergeSort.sort(array);

        assertArrayEquals(expected, array, "Reverse sorted array should be sorted correctly.");
    }

    @Test
    public void testIdenticalElementsArray() {
        int[] array = {5, 5, 5, 5, 5, 5, 5};
        int[] expected = {5, 5, 5, 5, 5, 5, 5};

        ConcurrentMergeSort.sort(array);

        assertArrayEquals(expected, array, "Array with identical elements should be sorted correctly (unchanged).");
    }

    @Test
    public void testLargeRandomArray() {
        int size = 100_000;
        int[] array = new int[size];
        int[] expected = new int[size];
        // Using a fixed seed for deterministic testing
        Random random = new Random(42);

        for (int i = 0; i < size; i++) {
            int value = random.nextInt();
            array[i] = value;
            expected[i] = value;
        }

        // Generate the expected result using Java's highly optimized built-in sort
        Arrays.sort(expected);

        // This will easily trigger the concurrency threshold (8192) in the implementation
        ConcurrentMergeSort.sort(array);

        assertArrayEquals(expected, array, "Large random array should be sorted correctly utilizing concurrency.");
    }

    @Test
    public void testEmptyArray() {
        int[] array = {};
        int[] expected = {};

        ConcurrentMergeSort.sort(array);

        assertArrayEquals(expected, array, "Empty array should be handled without errors.");
    }

    @Test
    public void testSingleElementArray() {
        int[] array = {42};
        int[] expected = {42};

        ConcurrentMergeSort.sort(array);

        assertArrayEquals(expected, array, "Single element array should be handled without errors.");
    }

    @Test
    public void testNullArray() {
        int[] array = null;
        ConcurrentMergeSort.sort(array);
        org.junit.jupiter.api.Assertions.assertNull(array, "Null array should be handled without errors.");
    }
}
