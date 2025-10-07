package com.thealgorithms.stacks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class NearestElementTest {

    @Test
    public void testNearestGreaterToRight() {
        int[] arr = {4, 5, 2, 10, 8};
        int[] expected = {5, 10, 10, -1, -1};
        assertArrayEquals(expected, NearestElement.nearestGreaterToRight(arr));
    }

    @Test
    public void testNearestGreaterToLeft() {
        int[] arr = {4, 5, 2, 10, 8};
        int[] expected = {-1, -1, 5, -1, 10};
        assertArrayEquals(expected, NearestElement.nearestGreaterToLeft(arr));
    }

    @Test
    public void testNearestSmallerToRight() {
        int[] arr = {4, 5, 2, 10, 8};
        int[] expected = {2, 2, -1, 8, -1};
        assertArrayEquals(expected, NearestElement.nearestSmallerToRight(arr));
    }

    @Test
    public void testNearestSmallerToLeft() {
        int[] arr = {4, 5, 2, 10, 8};
        int[] expected = {-1, 4, -1, 2, 2};
        assertArrayEquals(expected, NearestElement.nearestSmallerToLeft(arr));
    }

    @Test
    void testEmptyArray() {
        int[] arr = {};
        assertArrayEquals(new int[]{}, NearestElement.nearestGreaterToRight(arr));
        assertArrayEquals(new int[]{}, NearestElement.nearestGreaterToLeft(arr));
    }

    @Test
    void testAllEqualElements() {
        int[] arr = {5, 5, 5, 5};
        assertArrayEquals(new int[]{-1, -1, -1, -1}, NearestElement.nearestGreaterToRight(arr));
        assertArrayEquals(new int[]{-1, -1, -1, -1}, NearestElement.nearestGreaterToLeft(arr));
    }

    @Test
    void testPrivateConstructor() throws Exception {
        var constructor = NearestElement.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        constructor.newInstance();
    }
}
