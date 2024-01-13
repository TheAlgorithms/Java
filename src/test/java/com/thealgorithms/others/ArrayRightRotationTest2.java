package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class ArrayRightRotationTest2 {

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
        int[] arr = {9};
        int k = 1;
        int[] expected = {9};
        int[] result = ArrayRightRotation.rotateRight(arr, k);
        assertArrayEquals(expected, result);
    }

    // Add more test cases as needed
}
