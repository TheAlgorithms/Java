package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ArrayLeftRotationTest {

    @Test
    void testForOneElement() {
        int[] arr = {3};
        int[] result = ArrayLeftRotation.rotateLeft(arr, 3);
        assertArrayEquals(arr, result);
    }

    @Test
    void testForZeroStep() {
        int[] arr = {3, 1, 5, 8, 6};
        int[] result = ArrayLeftRotation.rotateLeft(arr, 0);
        assertArrayEquals(arr, result);
    }

    @Test
    void testForEqualSizeStep() {
        int[] arr = {3, 1, 5, 8, 6};
        int[] result = ArrayLeftRotation.rotateLeft(arr, 5);
        assertArrayEquals(arr, result);
    }

    @Test
    void testForLowerSizeStep() {
        int[] arr = {3, 1, 5, 8, 6};
        int n = 2;
        int[] expected = {5, 8, 6, 3, 1};
        int[] result = ArrayLeftRotation.rotateLeft(arr, n);
        assertArrayEquals(expected, result);
    }

    @Test
    void testForHigherSizeStep() {
        int[] arr = {3, 1, 5, 8, 6};
        int n = 7;
        int[] expected = {5, 8, 6, 3, 1};
        int[] result = ArrayLeftRotation.rotateLeft(arr, n);
        assertArrayEquals(expected, result);
    }
}
