package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class HowManyTimesRotatedTest {

    @Test
    public void testHowManyTimesRotated() {
        int[] arr1 = {5, 1, 2, 3, 4};
        assertEquals(1, HowManyTimesRotated.rotated(arr1));
        int[] arr2 = {15, 17, 2, 3, 5};
        assertEquals(2, HowManyTimesRotated.rotated(arr2));
    }
}
