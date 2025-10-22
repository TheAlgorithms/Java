package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TrappingRainWaterTest {

    @Test
    public void testExample() {
        int[] height = {4, 2, 0, 3, 2, 5};
        assertEquals(9, TrappingRainWater.trap(height));
    }

    @Test
    public void testEmpty() {
        int[] height = {};
        assertEquals(0, TrappingRainWater.trap(height));
    }

    @Test
    public void testNoTrapping() {
        int[] height = {0, 1, 2, 3, 4};
        assertEquals(0, TrappingRainWater.trap(height));
    }

    @Test
    public void testFlat() {
        int[] height = {3, 3, 3, 3};
        assertEquals(0, TrappingRainWater.trap(height));
    }

    @Test
    public void testNull() {
        assertThrows(IllegalArgumentException.class, () -> TrappingRainWater.trap(null));
    }
}
