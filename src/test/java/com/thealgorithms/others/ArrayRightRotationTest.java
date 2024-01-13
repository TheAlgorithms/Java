package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class ArrayRightRotationTest {

    @Test
    void testArrayRightRotation() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        int[] expected = {5, 6, 7, 1, 2, 3, 4};
        int[] result = ArrayRightRotation.rotateRight(arr, k);
        assertArrayEquals(expected, result);
    }

    @Test
    void testArrayRightRotationWithZeroStep() {
        int[] arr = {1, 2, 3, 4, 5};
        int k = 0;
        int[] expected = {1, 2, 3, 4, 5};
        int[] result = ArrayRightRotation.rotateRight(arr, k);
        assertArrayEquals(expected, result);
    }

    @Test
    void testArrayRightRotationWithLargeStep() {
        int[] arr = {1, 2, 3, 4, 5};
        int k = 7;
        int[] expected = {4, 5, 1, 2, 3};
        int[] result = ArrayRightRotation.rotateRight(arr, k);
        assertArrayEquals(expected, result);
    }

    @Test
    void testArrayRightRotationWithNegativeStep() {
        int[] arr = {1, 2, 3, 4, 5};
        int k = -2;
        int[] expected = {4, 5, 1, 2, 3};
        int[] result = ArrayRightRotation.rotateRight(arr, k);
        assertArrayEquals(expected, result);
    }

    @Test
    void testArrayRightRotationWithSingleElement() {
        int[] arr = {7};
        int k = 1;
        int[] expected = {7};
        int[] result = ArrayRightRotation.rotateRight(arr, k);
        assertArrayEquals(expected, result);
    }

    @Test
    void testArrayRightRotationWithEmptyArray() {
        int[] arr = {};
        int k = 3;
        int[] expected = {};
        int[] result = ArrayRightRotation.rotateRight(arr, k);
        assertArrayEquals(expected, result);
    }
}
