package com.thealgorithms.shufflealgo;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public
class ConstrainedShuffleTest {

    private
    int[] originalArray;

    @BeforeEach void setUp() {
        originalArray = new int[]{1, 2, 3, 4, 5};
    }

    // Test that shuffling preserves the length and original elements
    @Test void testPreserveArrayLengthAndElements() {
        int[] arrayCopy = Arrays.copyOf(originalArray, originalArray.length);
        ConstrainedShuffle.constrainedShuffle(arrayCopy);

        assertEquals(originalArray.length, arrayCopy.length,
                "Array length should remain the same");

        Set<Integer> originalElements = new HashSet<>();
        for (int num : originalArray) {
            originalElements.add(num);
        }

        for (int num : arrayCopy) {
            assertTrue(originalElements.contains(num),
                    "Array elements should be preserved");
        }
    }

    // Test that the first and last elements remain unchanged after shuffle
    @Test void testFirstAndLastElementsFixed() {
        int[] arrayCopy = Arrays.copyOf(originalArray, originalArray.length);
        ConstrainedShuffle.constrainedShuffle(arrayCopy);

        assertEquals(originalArray[0], arrayCopy[0],
                "First element should remain fixed");
        assertEquals(originalArray[originalArray.length - 1],
                arrayCopy[arrayCopy.length - 1],
                "Last element should remain fixed");
    }

    // Test that the function handles arrays with fewer than 3 elements without
    // changes
    @Test void testArrayTooSmall() {
        int[] smallArray = {1, 2};
        int[] expectedArray = {1, 2};

        ConstrainedShuffle.constrainedShuffle(smallArray);

        assertArrayEquals(
                expectedArray, smallArray,
                "Array with fewer than 3 elements should remain unchanged");
    }

    // Test with null input (should handle gracefully without error)
    @Test void testNullArray() {
        int[] nullArray = null;
        ConstrainedShuffle.constrainedShuffle(nullArray);

        assertEquals(null, nullArray, "Null input should remain null");
    }

    // Test that elements between the first and last positions change order in
    // larger arrays
    @Test void testInternalElementsShuffled() {
        int[] arrayCopy = Arrays.copyOf(originalArray, originalArray.length);
        boolean hasShuffled = false;

        for (int i = 0; i < 10; i++) { // Repeat shuffle to ensure randomness
            ConstrainedShuffle.constrainedShuffle(arrayCopy);

            if (!Arrays.equals(arrayCopy, originalArray)) {
                hasShuffled = true;
                break;
            }
        }

        assertTrue(
                hasShuffled,
                "Internal elements should shuffle between first and last positions");
    }

    // Test with an array that has all identical elements
    @Test void testArrayWithIdenticalElements() {
        int[] identicalArray = {1, 1, 1, 1, 1};
        int[] expectedArray = {1, 1, 1, 1, 1};

        ConstrainedShuffle.constrainedShuffle(identicalArray);

        assertArrayEquals(
                expectedArray, identicalArray,
                "Array with all identical elements should remain unchanged");
    }
}
