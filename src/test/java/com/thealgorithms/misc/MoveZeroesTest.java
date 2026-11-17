package com.thealgorithms.misc;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class MoveZeroesTest {

    @Test
    public void testMoveZeroes() {
        int[] nums = {0, 1, 0, 3, 12};
        MoveZeroes.moveZeroes(nums);
        assertArrayEquals(new int[] {1, 3, 12, 0, 0}, nums);
    }

    @Test
    public void testNoZeroes() {
        int[] nums = {1, 2, 3};
        MoveZeroes.moveZeroes(nums);
        assertArrayEquals(new int[] {1, 2, 3}, nums);
    }

    @Test
    public void testAllZeroes() {
        int[] nums = {0, 0, 0};
        MoveZeroes.moveZeroes(nums);
        assertArrayEquals(new int[] {0, 0, 0}, nums);
    }

    @Test
    public void testSingleElement() {
        int[] nums = {0};
        MoveZeroes.moveZeroes(nums);
        assertArrayEquals(new int[] {0}, nums);
    }
}
