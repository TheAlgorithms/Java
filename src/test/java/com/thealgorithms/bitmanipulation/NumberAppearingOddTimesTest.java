package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class NumberAppearingOddTimesTest {

    @Test
    void testFindOddOccurrence() {
        int[] arr1 = {5, 6, 7, 8};
        assertEquals(12, NumberAppearingOddTimes.findOddOccurrence(arr1));

        int[] arr2 = {2, 3, 5, 4, 5, 2, 4, 3, 5, 2, 4, 4, 2};
        assertEquals(5, NumberAppearingOddTimes.findOddOccurrence(arr2));

        int[] arr3 = {10, 10, 20, 20, 30};
        assertEquals(30, NumberAppearingOddTimes.findOddOccurrence(arr3));

        int[] arr4 = {-5, -5, -3, -3, -7, -7, -7};
        assertEquals(-7, NumberAppearingOddTimes.findOddOccurrence(arr4));
    }
}
