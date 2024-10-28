package com.thealgorithms.shufflealgo;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public
class UnderstandingShuffleAlgoTest {

    // Test case for basic functionality with a standard array
    @Test void testShuffleStandardArray() {
        int[] array = {1, 2, 3, 4, 5};
        int[] originalArray = Arrays.copyOf(array, array.length);

        UnderstandingShuffleAlgo.shuffle(array);

        // Check that the shuffled array contains the same elements
        Arrays.sort(originalArray);
        Arrays.sort(array);
        assertArrayEquals(originalArray, array);
    }

    // Test case for an empty array
    @Test void testShuffleEmptyArray() {
        int[] array = {};
        UnderstandingShuffleAlgo.shuffle(array);
        assertArrayEquals(new int[]{}, array); // Should remain empty
    }

    // Test case for a single element array
    @Test void testShuffleSingleElementArray() {
        int[] array = {1};
        UnderstandingShuffleAlgo.shuffle(array);
        assertArrayEquals(new int[]{1}, array); // Should remain unchanged
    }

    // Test case for a null array
    @Test void testShuffleNullArray() {
        int[] array = null;
        assertThrows(
                NullPointerException.class, ()->{
                    UnderstandingShuffleAlgo.shuffle(
                            array); // Expect a NullPointerException
                });
    }

    // Test case for an array with duplicate elements
    @Test void testShuffleArrayWithDuplicates() {
        int[] array = {1, 1, 2, 2, 3, 3};
        int[] originalArray = Arrays.copyOf(array, array.length);

        UnderstandingShuffleAlgo.shuffle(array);

        // Check that the shuffled array contains the same elements
        Arrays.sort(originalArray);
        Arrays.sort(array);
        assertArrayEquals(originalArray, array);
    }

    // Test case for large array
    @Test void testShuffleLargeArray() {
        int[] array = new int[1000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1; // Fill with numbers 1 to 1000
        }

        int[] originalArray = Arrays.copyOf(array, array.length);
        UnderstandingShuffleAlgo.shuffle(array);

        // Check that the shuffled array contains the same elements
        Arrays.sort(originalArray);
        Arrays.sort(array);
        assertArrayEquals(originalArray, array);
    }

    // Test case for an already sorted array
    @Test void testShuffleSortedArray() {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] originalArray = Arrays.copyOf(array, array.length);

        UnderstandingShuffleAlgo.shuffle(array);

        // Check that the shuffled array contains the same elements
        Arrays.sort(originalArray);
        Arrays.sort(array);
        assertArrayEquals(originalArray, array);
    }
}