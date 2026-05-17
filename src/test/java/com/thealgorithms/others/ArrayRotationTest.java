package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class ArrayRotationTest {

    @Test
    void shouldRotateArrayRightByTwoPositions() {
        int[] values = {1, 2, 3, 4, 5};

        ArrayRotation.rotateRight(values, 2);

        assertArrayEquals(new int[] {4, 5, 1, 2, 3}, values);
     }

    @Test
    void shouldRotateArrayLeftByTwoPositions() {
        int[] values = {1, 2, 3, 4, 5};

        ArrayRotation.rotateLeft(values, 2);

        assertArrayEquals(new int[] {3, 4, 5, 1, 2}, values);
    }

    @Test
    void shouldHandleRotationGreaterThanArrayLength() {
        int[] values = {10, 20, 30, 40};

        ArrayRotation.rotateRight(values, 2);

        assertArrayEquals(new int[] {30, 40, 10, 20}, values);
    }

    @Test
    void shouldKeepSingleElementArrayUnchanged() {
        int[] values = {99};

        ArrayRotation.rotateLeft(values, 5);

        assertArrayEquals(new int[] {99}, values);
    }

    @Test
    void shouldHandleEmptyArrayWithoutErrors() {
        int[] values = {};

        ArrayRotation.rotateRight(values, 3);

        assertArrayEquals(new int[] {}, values);
     }

    @Test
    void shouldReturnOriginalArrayWhenRotationIsZero() {
        int[] values = {7, 8, 9};

        ArrayRotation.rotateLeft(values, 0);

        assertArrayEquals(new int[] {7, 8, 9}, values);
    }
 }

