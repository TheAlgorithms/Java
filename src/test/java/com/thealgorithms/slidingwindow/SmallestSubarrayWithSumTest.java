package com.thealgorithms.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link SmallestSubarrayWithSum}.
 */
public class SmallestSubarrayWithSumTest {

    @Test
    public void testBasicCase() {
        int[] arr = {2, 3, 1, 2, 4, 3};
        int target = 7;
        int expected = 2; // subarray [4, 3]
        assertEquals(expected, SmallestSubarrayWithSum.smallestSubarrayLen(arr, target));
    }

    @Test
    public void testNoValidSubarray() {
        int[] arr = {1, 1, 1, 1};
        int target = 10;
        int expected = 0;
        assertEquals(expected, SmallestSubarrayWithSum.smallestSubarrayLen(arr, target));
    }

    @Test
    public void testSingleElement() {
        int[] arr = {5};
        int target = 5;
        int expected = 1;
        assertEquals(expected, SmallestSubarrayWithSum.smallestSubarrayLen(arr, target));
    }

    @Test
    public void testAllElementsSame() {
        int[] arr = {2, 2, 2, 2, 2};
        int target = 6;
        int expected = 3;
        assertEquals(expected, SmallestSubarrayWithSum.smallestSubarrayLen(arr, target));
    }

    @Test
    public void testLargeInput() {
        int[] arr = {1, 2, 3, 4, 5, 6};
        int target = 11;
        int expected = 2; // subarray [5, 6]
        assertEquals(expected, SmallestSubarrayWithSum.smallestSubarrayLen(arr, target));
    }
}
