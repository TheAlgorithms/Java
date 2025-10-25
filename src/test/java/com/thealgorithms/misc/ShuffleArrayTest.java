package com.thealgorithms.misc;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ShuffleArrayTest {

    @Test
    void testShuffleBasic() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] originalArr = arr.clone(); // Clone original array for comparison
        ShuffleArray.shuffle(arr);

        // Check that the shuffled array is not the same as the original
        assertNotEquals(originalArr, arr);
    }

    @Test
    void testShuffleSingleElement() {
        int[] arr = {1};
        int[] originalArr = arr.clone();
        ShuffleArray.shuffle(arr);

        // Check that the shuffled array is the same as the original
        assertArrayEquals(originalArr, arr);
    }

    @Test
    void testShuffleTwoElements() {
        int[] arr = {1, 2};
        int[] originalArr = arr.clone();
        ShuffleArray.shuffle(arr);

        // Check that the shuffled array is not the same as the original
        assertNotEquals(originalArr, arr);
        // Check that the shuffled array still contains the same elements
        assertTrue(arr[0] == 1 || arr[0] == 2);
        assertTrue(arr[1] == 1 || arr[1] == 2);
    }

    @Test
    void testShuffleEmptyArray() {
        int[] arr = {};
        int[] originalArr = arr.clone();
        ShuffleArray.shuffle(arr);

        // Check that the shuffled array is the same as the original (still empty)
        assertArrayEquals(originalArr, arr);
    }

    @Test
    void testShuffleLargeArray() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] originalArr = arr.clone();
        ShuffleArray.shuffle(arr);

        // Check that the shuffled array is not the same as the original
        assertNotEquals(originalArr, arr);
    }

    @Test
    void testShuffleRetainsElements() {
        int[] arr = {1, 2, 3, 4, 5};
        ShuffleArray.shuffle(arr);

        // Check that the shuffled array contains the same elements
        assertTrue(arr.length == 5);
        for (int i = 1; i <= 5; i++) {
            assertTrue(contains(arr, i));
        }
    }

    private boolean contains(int[] arr, int value) {
        for (int num : arr) {
            if (num == value) {
                return true;
            }
        }
        return false;
    }
}
