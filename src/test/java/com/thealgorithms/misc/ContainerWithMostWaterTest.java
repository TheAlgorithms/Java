package com.thealgorithms.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ContainerWithMostWaterTest {

    @Test
    public void testExample() {
        assertEquals(49, ContainerWithMostWater.maxArea(new int[] {1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }

    @Test
    public void testTwoElements() {
        assertEquals(1, ContainerWithMostWater.maxArea(new int[] {1, 1}));
    }

    @Test
    public void testDescending() {
        assertEquals(4, ContainerWithMostWater.maxArea(new int[] {4, 3, 2, 1}));
    }

    @Test
    public void testEqualHeights() {
        assertEquals(8, ContainerWithMostWater.maxArea(new int[] {2, 2, 2, 2, 2}));
    }
}
