package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class SmoothSortTest {
    @Test
    public void testSmoothSort() {
        int[] input = { 1, 7, 8, 2, 3, 5, 4, 6 };
        int[] expectedOutput = { 1, 2, 3, 4, 5, 6, 7, 8 };
        
        int[] result = SmoothSort.smoothSort(input);
        
        assertArrayEquals(expectedOutput, result);
    }
}
