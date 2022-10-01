package com.thealgorithms.searches;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class RowColumnWiseSorted2dArray_BinarySearchTest {

    @Test
    // valid test case
    public void RowColumnSorted2dArray_BinarySearchTestMiddle() {
        int[][] arr = { {10, 20, 30, 40},
                        {15, 25, 35, 45},
                        {18, 28, 38, 48},
                        {21, 31, 41, 51}
                      };
        int target = 35;
        int[] ans = RowColumnWiseSorted2dArray_BinarySearch.Search(arr, target);
        int[] expected = {1,2};
        System.out.println(Arrays.toString(ans));
        assertEquals(1, ans[0]);
        assertEquals(2, ans[1]);
    }

    @Test
    // valid test case
    public void RowColumnSorted2dArray_BinarySearchTestSide() {
        int[][] arr = { {10, 20, 30, 40},
                        {15, 25, 35, 45},
                        {18, 28, 38, 48},
                        {21, 31, 41, 51}
                      };
        int target = 48;
        int[] ans = RowColumnWiseSorted2dArray_BinarySearch.Search(arr, target);
        int[] expected = {2,3};
        System.out.println(Arrays.toString(ans));
        assertEquals(2, ans[0]);
        assertEquals(3, ans[1]);
    }

    @Test
    // valid test case
    public void RowColumnSorted2dArray_BinarySearchTestUpper() {
        int[][] arr = { {10, 20, 30, 40},
                        {15, 25, 35, 45},
                        {18, 28, 38, 48},
                        {21, 31, 41, 51}
                      };
        int target = 20;
        int[] ans = RowColumnWiseSorted2dArray_BinarySearch.Search(arr, target);
        int[] expected = {0,1};
        System.out.println(Arrays.toString(ans));
        assertEquals(0, ans[0]);
        assertEquals(1, ans[1]);
    }

    @Test
    // valid test case
    public void RowColumnSorted2dArray_BinarySearchTestUpperSide() {
        int[][] arr = { {10, 20, 30, 40},
                        {15, 25, 35, 45},
                        {18, 28, 38, 48},
                        {21, 31, 41, 51}
                      };
        int target = 40;
        int[] ans = RowColumnWiseSorted2dArray_BinarySearch.Search(arr, target);
        int[] expected = {0,3};
        System.out.println(Arrays.toString(ans));
        assertEquals(0, ans[0]);
        assertEquals(3, ans[1]);
    }

    @Test
    // valid test case
    public void RowColumnSorted2dArray_BinarySearchTestLower() {
        int[][] arr = { {10, 20, 30, 40},
                        {15, 25, 35, 45},
                        {18, 28, 38, 48},
                        {21, 31, 41, 51}
                      };
        int target = 31;
        int[] ans = RowColumnWiseSorted2dArray_BinarySearch.Search(arr, target);
        int[] expected = {3,1};
        System.out.println(Arrays.toString(ans));
        assertEquals(3, ans[0]);
        assertEquals(1, ans[1]);
    }

    @Test
    // valid test case
    public void RowColumnSorted2dArray_BinarySearchTestLowerSide() {
        int[][] arr = { {10, 20, 30, 40},
                        {15, 25, 35, 45},
                        {18, 28, 38, 48},
                        {21, 31, 41, 51}
                      };
        int target = 51;
        int[] ans = RowColumnWiseSorted2dArray_BinarySearch.Search(arr, target);
        int[] expected = {3,3};
        System.out.println(Arrays.toString(ans));
        assertEquals(3, ans[0]);
        assertEquals(3, ans[1]);
    }

    @Test
    // valid test case
    public void RowColumnSorted2dArray_BinarySearchTestNotFound() {
        int[][] arr = { {10, 20, 30, 40},
                        {15, 25, 35, 45},
                        {18, 28, 38, 48},
                        {21, 31, 41, 51}
                      };
        int target = 101;
        int[] ans = RowColumnWiseSorted2dArray_BinarySearch.Search(arr, target);
        int[] expected = {-1,-1};
        System.out.println(Arrays.toString(ans));
        assertEquals(-1, ans[0]);
        assertEquals(-1, ans[1]);
    }

}
