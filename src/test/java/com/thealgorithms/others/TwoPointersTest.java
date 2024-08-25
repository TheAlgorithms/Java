package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TwoPointersTest {

    @Test
    void testPositivePairExists() {
        int[] arr = {2, 6, 9, 22, 121};
        int key = 28;
        assertTrue(TwoPointers.isPairedSum(arr, key));
    }

    @Test
    void testNegativePairExists() {
        int[] arr = {-12, -1, 0, 8, 12};
        int key = 0;
        assertTrue(TwoPointers.isPairedSum(arr, key));
    }

    @Test
    void testPairDoesNotExist() {
        int[] arr = {0, 12, 12, 35, 152};
        int key = 13;
        assertFalse(TwoPointers.isPairedSum(arr, key));
    }

    @Test
    void testNegativeSumPair() {
        int[] arr = {-10, -3, 1, 2, 5, 9};
        int key = -8;
        assertTrue(TwoPointers.isPairedSum(arr, key));
    }

    @Test
    void testPairDoesNotExistWithPositiveSum() {
        int[] arr = {1, 2, 3, 4, 5};
        int key = 10;
        assertFalse(TwoPointers.isPairedSum(arr, key));
    }

    @Test
    void testEmptyArray() {
        int[] arr = {};
        int key = 5;
        assertFalse(TwoPointers.isPairedSum(arr, key));
    }

    @Test
    void testSingleElementArray() {
        int[] arr = {5};
        int key = 5;
        assertFalse(TwoPointers.isPairedSum(arr, key));
    }

    @Test
    void testArrayWithDuplicateElements() {
        int[] arr = {1, 1, 3, 5, 5};
        int key = 6;
        assertTrue(TwoPointers.isPairedSum(arr, key));
    }

    @Test
    void testPairExistsAtEdges() {
        int[] arr = {1, 3, 4, 7, 8};
        int key = 9;
        assertTrue(TwoPointers.isPairedSum(arr, key));
    }
}
