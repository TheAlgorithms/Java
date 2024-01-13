package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class ArrayRightRotationTest {

    @Test
    void testArrayRightRotation() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        int[] expected = {5, 6, 7, 1, 2, 3, 4};
        int[] result = ArrayRightRotation.rotateRight(arr, k);
        assertArrayEquals(expected, result);
    }

    @Test
    void testArrayRightRotationWithZeroSteps() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int k = 0;
        int[] expected = {1, 2, 3, 4, 5, 6, 7};
        int[] result = ArrayRightRotation.rotateRight(arr, k);
        assertArrayEquals(expected, result);
    }

    @Test
    void testArrayRightRotationWithEqualSizeSteps() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int k = arr.length;
        int[] expected = {1, 2, 3, 4, 5, 6, 7};
        int[] result = ArrayRightRotation.rotateRight(arr, k);
        assertArrayEquals(expected, result);
    }

    @Test
    void testArrayRightRotationWithLowerSizeSteps() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int k = 2;
        int[] expected = {6, 7, 1, 2, 3, 4, 5};
        int[] result = ArrayRightRotation.rotateRight(arr, k);
        assertArrayEquals(expected, result);
    }

    @Test
    void testArrayRightRotationWithHigherSizeSteps() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int k = 10;
        int[] expected = {5, 6, 7, 1, 2, 3, 4};
        int[] result = ArrayRightRotation.rotateRight(arr, k);
        assertArrayEquals(expected, result);
    }
}
