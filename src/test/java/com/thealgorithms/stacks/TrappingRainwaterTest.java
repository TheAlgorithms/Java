package com.thealgorithms.stacks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TrappingRainwaterTest {

    @Test
    public void testExampleCase() {
        int[] height = {4, 2, 0, 3, 2, 5};
        assertEquals(9, TrappingRainwater.trap(height));
    }

    @Test
    public void testNoTrapping() {
        int[] height = {1, 2, 3, 4, 5};
        assertEquals(0, TrappingRainwater.trap(height));
    }

    @Test
    public void testFlatSurface() {
        int[] height = {0, 0, 0, 0};
        assertEquals(0, TrappingRainwater.trap(height));
    }

    @Test
    public void testSymmetricPit() {
        int[] height = {3, 0, 2, 0, 3};
        assertEquals(7, TrappingRainwater.trap(height));
    }

    @Test
    public void testSingleBar() {
        int[] height = {5};
        assertEquals(0, TrappingRainwater.trap(height));
    }
}
