package com.thealgorithms.datastructures.hashmap.hashing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

public class IntersectionTest {

    @Test
    void testBasicIntersection() {
        int[] arr1 = {1, 2, 2, 1};
        int[] arr2 = {2, 2};
        List<Integer> result = Intersection.intersection(arr1, arr2);
        assertEquals(List.of(2, 2), result, "Intersection should return [2, 2]");
    }

    @Test
    void testNoIntersection() {
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {4, 5, 6};
        List<Integer> result = Intersection.intersection(arr1, arr2);
        assertTrue(result.isEmpty(), "Intersection should be empty for disjoint sets");
    }

    @Test
    void testEmptyArray() {
        int[] arr1 = {};
        int[] arr2 = {1, 2, 3};
        List<Integer> result = Intersection.intersection(arr1, arr2);
        assertTrue(result.isEmpty(), "Intersection should be empty when first array is empty");

        result = Intersection.intersection(arr2, arr1);
        assertTrue(result.isEmpty(), "Intersection should be empty when second array is empty");
    }

    @Test
    void testNullArray() {
        int[] arr1 = null;
        int[] arr2 = {1, 2, 3};
        List<Integer> result = Intersection.intersection(arr1, arr2);
        assertTrue(result.isEmpty(), "Intersection should be empty when first array is null");

        result = Intersection.intersection(arr2, arr1);
        assertTrue(result.isEmpty(), "Intersection should be empty when second array is null");
    }

    @Test
    void testMultipleOccurrences() {
        int[] arr1 = {5, 5, 5, 6};
        int[] arr2 = {5, 5, 6, 6, 6};
        List<Integer> result = Intersection.intersection(arr1, arr2);
        assertEquals(List.of(5, 5, 6), result, "Intersection should return [5, 5, 6]");
    }

    @Test
    void testSameElements() {
        int[] arr1 = {1, 1, 1};
        int[] arr2 = {1, 1, 1};
        List<Integer> result = Intersection.intersection(arr1, arr2);
        assertEquals(List.of(1, 1, 1), result, "Intersection should return [1, 1, 1] for same elements");
    }

    @Test
    void testLargeArrays() {
        int[] arr1 = new int[1000];
        int[] arr2 = new int[1000];
        for (int i = 0; i < 1000; i++) {
            arr1[i] = i;
            arr2[i] = i;
        }
        List<Integer> result = Intersection.intersection(arr1, arr2);
        assertEquals(1000, result.size(), "Intersection should return all elements for identical large arrays");
    }
}
