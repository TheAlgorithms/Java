package com.thealgorithms.shufflealgo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public
class ShuffleByRangeTest {

    // Test case for a basic shuffle within a valid range
    @Test void testShuffleByRangeBasic() {
        int[] array = {1, 2, 3, 4, 5, 6};
        ShuffleByRange.shuffleByRange(array, 1, 5);

        // Verify that elements outside the specified range remain unchanged
        Assertions.assertEquals(1, array[0], "First element should be unchanged");
        Assertions.assertEquals(6, array[5], "Last element should be unchanged");
    }

    // Test case for an empty array
    @Test void testShuffleByRangeEmptyArray() {
        int[] array = {};
        ShuffleByRange.shuffleByRange(array, 0, 1);
        Assertions.assertArrayEquals(new int[]{}, array);
    }

    // Test case for a single element array
    @Test void testShuffleByRangeSingleElementArray() {
        int[] array = {1};
        ShuffleByRange.shuffleByRange(array, 0, 1);
        Assertions.assertArrayEquals(new int[]{1}, array);
    }

    // Test case for invalid range: start index equal to end index
    @Test void testShuffleByRangeStartEqualsEnd() {
        int[] array = {1, 2, 3, 4, 5};
        ShuffleByRange.shuffleByRange(array, 2, 2);
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, array);
    }

    // Test case for invalid range: start index out of bounds
    @Test void testShuffleByRangeStartOutOfBounds() {
        int[] array = {1, 2, 3, 4, 5};
        ShuffleByRange.shuffleByRange(array, -1, 5);
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, array);
    }

    // Test case for invalid range: end index out of bounds
    @Test void testShuffleByRangeEndOutOfBounds() {
        int[] array = {1, 2, 3, 4, 5};
        ShuffleByRange.shuffleByRange(array, 1, 6);
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, array);
    }

    // Test case for invalid range: start index greater than end index
    @Test void testShuffleByRangeStartGreaterThanEnd() {
        int[] array = {1, 2, 3, 4, 5};
        ShuffleByRange.shuffleByRange(array, 3, 2);
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, array);
    }

    // Test case for shuffling a large array
    @Test void testShuffleByRangeLargeArray() {
        int[] array = new int[1000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1; // Filling array with values 1 to 1000
        }
        ShuffleByRange.shuffleByRange(array, 250, 750);

        // Verify that the first 250 and last 250 elements remain unchanged
        for (int i = 0; i < 250; i++) {
            Assertions.assertEquals(
                    i + 1, array[i], "Elements at index " + i + " should be unchanged");
        }
        for (int i = 750; i < 1000; i++) {
            Assertions.assertEquals(
                    i + 1, array[i], "Elements at index " + i + " should be unchanged");
        }
    }
}
