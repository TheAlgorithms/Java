package com.thealgorithms.searches;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinarySearch2dArrayTest {

    @Test
    // valid test case
    public void BinarySearch2dArrayTestMiddle() {
        int[][] arr = { {1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9,10,11,12}};
        int target = 6;

        int[] ans = BinarySearch2dArray.BinarySearch(arr, target);
        int[] expected = {1,1};
        System.out.println(Arrays.toString(ans));
        assertEquals(1, ans[0]);
        assertEquals(1, ans[1]);
    }

    @Test
    // valid test case
    public void BinarySearch2dArrayTestMiddleSide() {
        int[][] arr = { {1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9,10,11,12}};
        int target = 8;

        int[] ans = BinarySearch2dArray.BinarySearch(arr, target);
        int[] expected = {1,3};
        System.out.println(Arrays.toString(ans));
        assertEquals(1, ans[0]);
        assertEquals(3, ans[1]);
    }

    @Test
    // valid test case
    public void BinarySearch2dArrayTestUpper() {
        int[][] arr = { {1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9,10,11,12}};
        int target = 2;

        int[] ans = BinarySearch2dArray.BinarySearch(arr, target);
        int[] expected = {0,1};
        System.out.println(Arrays.toString(ans));
        assertEquals(0, ans[0]);
        assertEquals(1, ans[1]);
    }

    @Test
    // valid test case
    public void BinarySearch2dArrayTestUpperSide() {
        int[][] arr = { {1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9,10,11,12}};
        int target = 1;

        int[] ans = BinarySearch2dArray.BinarySearch(arr, target);
        int[] expected = {0,0};
        System.out.println(Arrays.toString(ans));
        assertEquals(0, ans[0]);
        assertEquals(0, ans[1]);
    }

    @Test
    // valid test case
    public void BinarySearch2dArrayTestLower() {
        int[][] arr = { {1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9,10,11,12}};
        int target = 10;

        int[] ans = BinarySearch2dArray.BinarySearch(arr, target);
        int[] expected = {2,1};
        System.out.println(Arrays.toString(ans));
        assertEquals(2, ans[0]);
        assertEquals(1, ans[1]);
    }

    @Test
    // valid test case
    public void BinarySearch2dArrayTestLowerSide() {
        int[][] arr = { {1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9,10,11,12}};
        int target = 11;

        int[] ans = BinarySearch2dArray.BinarySearch(arr, target);
        int[] expected = {2,2};
        System.out.println(Arrays.toString(ans));
        assertEquals(2, ans[0]);
        assertEquals(2, ans[1]);
    }

    @Test
    // valid test case
    public void BinarySearch2dArrayTestNotFound() {
        int[][] arr = { {1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9,10,11,12}};
        int target = 101;

        int[] ans = BinarySearch2dArray.BinarySearch(arr, target);
        int[] expected = {-1,-1};
        System.out.println(Arrays.toString(ans));
        assertEquals(-1, ans[0]);
        assertEquals(-1, ans[1]);
    }
    @Test
    // valid test case
    public void BinarySearch2dArrayTestNotFound() {
        int[][] arr = { {1, 2, 3, 4, 5},
                        {6, 7, 8, 9, 10},
                        {11, 12, 13, 14, 15},
                        {16, 17, 18, 19, 20}}};
        int target = 18;

        int[] ans = BinarySearch2dArray.BinarySearch(arr, target);
        int[] expected = {3,2};
        System.out.println(Arrays.toString(ans));
        assertEquals(-1, ans[0]);
        assertEquals(-1, ans[1]);
    }

}

