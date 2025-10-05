package com.thealgorithms.stacks;

import java.lang.reflect.Constructor;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

import com.thealgorithms.datastructures.stacks.NearestElement;


class NearestElementTest {

    @Test
    void testNearestGreaterToRight() {
        int[] arr = {4, 5, 2, 10, 8};
        int[] result = NearestElement.nearestGreaterToRight(arr);
        System.out.println("nearestGreaterToRight: " + Arrays.toString(result));
        int[] expected = {5, 10, 10, -1, -1};
        assertArrayEquals(expected, result);
    }

    @Test
    void testNearestGreaterToLeft() {
        int[] arr = {4, 5, 2, 10, 8};
        int[] result = NearestElement.nearestGreaterToLeft(arr);
        System.out.println("nearestGreaterToLeft: " + Arrays.toString(result));
        int[] expected = {-1, -1, 5, -1, 10};
        assertArrayEquals(expected, result);
    }

    @Test
    void testNearestSmallerToRight() {
        int[] arr = {4, 5, 2, 10, 8};
        int[] result = NearestElement.nearestSmallerToRight(arr);
        System.out.println("nearestSmallerToRight: " + Arrays.toString(result));
        int[] expected = {2, 2, -1, 8, -1};
        assertArrayEquals(expected, result);
    }

    @Test
    void testNearestSmallerToLeft() {
        int[] arr = {4, 5, 2, 10, 8};
        int[] result = NearestElement.nearestSmallerToLeft(arr);
        System.out.println("nearestSmallerToLeft: " + Arrays.toString(result));
        int[] expected = {-1, 4, -1, 2, 2};
        assertArrayEquals(expected, result);
    }

    @Test
    void testPrivateConstructor() throws Exception {
        Constructor<NearestElement> constructor = NearestElement.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        try {
            constructor.newInstance();
        } catch (Exception ignored) {
        }
    }
}
