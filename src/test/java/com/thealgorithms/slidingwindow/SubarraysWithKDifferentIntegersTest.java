package com.thealgorithms.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test cases for SubarraysWithKDifferentIntegers algorithm.
 *
 * <p>
 * This class provides test cases to verify the correctness of the subarraysWithKDistinct
 * method.
 *
 * @author https://github.com/Chiefpatwal
 */
public class SubarraysWithKDifferentIntegersTest {

    @Test
    public void testEdgeCases() {
        // Test with an empty array
        assertEquals(0, SubarraysWithKDifferentIntegers.subarraysWithKDistinct(new int[] {}, 1));

        // Test with k greater than the number of distinct elements
        assertEquals(0, SubarraysWithKDifferentIntegers.subarraysWithKDistinct(new int[] {1, 2, 3}, 5));

        // Test with k = 0
        assertEquals(0, SubarraysWithKDifferentIntegers.subarraysWithKDistinct(new int[] {1, 2, 3}, 0));

        // Test with single element arrays
        assertEquals(1, SubarraysWithKDifferentIntegers.subarraysWithKDistinct(new int[] {1}, 1));
        assertEquals(0, SubarraysWithKDifferentIntegers.subarraysWithKDistinct(new int[] {1}, 2));

        // Test case where all elements are the same
        assertEquals(6, SubarraysWithKDifferentIntegers.subarraysWithKDistinct(new int[] {2, 2, 2}, 1)); // Corrected expected value
        assertEquals(0, SubarraysWithKDifferentIntegers.subarraysWithKDistinct(new int[] {2, 2, 2}, 2)); // Not enough distinct elements
    }
}
