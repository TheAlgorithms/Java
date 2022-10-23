package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FindMaxTest {

    @Test
    public void testFindMaxValue() {
        assertEquals(
            10,
            FindMax.findMax(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 })
        );
    }
}
