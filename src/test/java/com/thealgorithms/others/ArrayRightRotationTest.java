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
}
