package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RowColumnWiseSorted2dArrayBinarySearchTest {

    @Test
    public void rowColumnSorted2dArrayBinarySearchTestMiddle() {
        Integer[][] arr = {
            {10, 20, 30, 40},
            {15, 25, 35, 45},
            {18, 28, 38, 48},
            {21, 31, 41, 51},
        };
        Integer target = 35;
        int[] ans = RowColumnWiseSorted2dArrayBinarySearch.search(arr, target);
        int[] expected = {1, 2};
        assertEquals(expected[0], ans[0]);
        assertEquals(expected[1], ans[1]);
    }

    @Test
    public void rowColumnSorted2dArrayBinarySearchTestSide() {
        Integer[][] arr = {
            {10, 20, 30, 40},
            {15, 25, 35, 45},
            {18, 28, 38, 48},
            {21, 31, 41, 51},
        };
        Integer target = 48;
        int[] ans = RowColumnWiseSorted2dArrayBinarySearch.search(arr, target);
        int[] expected = {2, 3};
        assertEquals(expected[0], ans[0]);
        assertEquals(expected[1], ans[1]);
    }

    @Test
    public void rowColumnSorted2dArray_BinarySearchTestUpper() {
        Integer[][] arr = {
            {10, 20, 30, 40},
            {15, 25, 35, 45},
            {18, 28, 38, 48},
            {21, 31, 41, 51},
        };
        Integer target = 20;
        int[] ans = RowColumnWiseSorted2dArrayBinarySearch.search(arr, target);
        int[] expected = {0, 1};
        assertEquals(expected[0], ans[0]);
        assertEquals(expected[1], ans[1]);
    }

    @Test
    public void rowColumnSorted2dArray_BinarySearchTestUpperSide() {
        Integer[][] arr = {
            {10, 20, 30, 40},
            {15, 25, 35, 45},
            {18, 28, 38, 48},
            {21, 31, 41, 51},
        };
        Integer target = 40;
        int[] ans = RowColumnWiseSorted2dArrayBinarySearch.search(arr, target);
        int[] expected = {0, 3};
        assertEquals(expected[0], ans[0]);
        assertEquals(expected[1], ans[1]);
    }

    @Test
    public void rowColumnSorted2dArray_BinarySearchTestLower() {
        Integer[][] arr = {
            {10, 20, 30, 40},
            {15, 25, 35, 45},
            {18, 28, 38, 48},
            {21, 31, 41, 51},
        };
        Integer target = 31;
        int[] ans = RowColumnWiseSorted2dArrayBinarySearch.search(arr, target);
        int[] expected = {3, 1};
        assertEquals(expected[0], ans[0]);
        assertEquals(expected[1], ans[1]);
    }

    @Test
    public void rowColumnSorted2dArray_BinarySearchTestLowerSide() {
        Integer[][] arr = {
            {10, 20, 30, 40},
            {15, 25, 35, 45},
            {18, 28, 38, 48},
            {21, 31, 41, 51},
        };
        Integer target = 51;
        int[] ans = RowColumnWiseSorted2dArrayBinarySearch.search(arr, target);
        int[] expected = {3, 3};
        assertEquals(expected[0], ans[0]);
        assertEquals(expected[1], ans[1]);
    }

    @Test
    public void rowColumnSorted2dArray_BinarySearchTestNotFound() {
        Integer[][] arr = {
            {10, 20, 30, 40},
            {15, 25, 35, 45},
            {18, 28, 38, 48},
            {21, 31, 41, 51},
        };
        Integer target = 101;
        int[] ans = RowColumnWiseSorted2dArrayBinarySearch.search(arr, target);
        int[] expected = {-1, -1};
        assertEquals(expected[0], ans[0]);
        assertEquals(expected[1], ans[1]);
    }
}
