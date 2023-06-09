package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

public class BinarySearch2dArrayTest {

    @Test
    // valid test case
    public void BinarySearch2dArrayTestMiddle() {
        int[][] arr = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        int target = 6;

        int[] ans = BinarySearch2dArray.BinarySearch(arr, target);
        int[] expected = {1, 1};
        System.out.println(Arrays.toString(ans));
        assertEquals(1, ans[0]);
        assertEquals(1, ans[1]);
    }

    @Test
    // valid test case
    public void BinarySearch2dArrayTestMiddleSide() {
        int[][] arr = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        int target = 8;

        int[] ans = BinarySearch2dArray.BinarySearch(arr, target);
        int[] expected = {1, 3};
        System.out.println(Arrays.toString(ans));
        assertEquals(1, ans[0]);
        assertEquals(3, ans[1]);
    }

    @Test
    // valid test case
    public void BinarySearch2dArrayTestUpper() {
        int[][] arr = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        int target = 2;

        int[] ans = BinarySearch2dArray.BinarySearch(arr, target);
        int[] expected = {0, 1};
        System.out.println(Arrays.toString(ans));
        assertEquals(0, ans[0]);
        assertEquals(1, ans[1]);
    }

    @Test
    // valid test case
    public void BinarySearch2dArrayTestUpperSide() {
        int[][] arr = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        int target = 1;

        int[] ans = BinarySearch2dArray.BinarySearch(arr, target);
        int[] expected = {0, 0};
        System.out.println(Arrays.toString(ans));
        assertEquals(0, ans[0]);
        assertEquals(0, ans[1]);
    }

    @Test
    // valid test case
    public void BinarySearch2dArrayTestLower() {
        int[][] arr = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        int target = 10;

        int[] ans = BinarySearch2dArray.BinarySearch(arr, target);
        int[] expected = {2, 1};
        System.out.println(Arrays.toString(ans));
        assertEquals(2, ans[0]);
        assertEquals(1, ans[1]);
    }

    @Test
    // valid test case
    public void BinarySearch2dArrayTestLowerSide() {
        int[][] arr = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        int target = 11;

        int[] ans = BinarySearch2dArray.BinarySearch(arr, target);
        int[] expected = {2, 2};
        System.out.println(Arrays.toString(ans));
        assertEquals(2, ans[0]);
        assertEquals(2, ans[1]);
    }

    @Test
    // valid test case
    public void BinarySearch2dArrayTestNotFound() {
        int[][] arr = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        int target = 101;

        int[] ans = BinarySearch2dArray.BinarySearch(arr, target);
        int[] expected = {-1, -1};
        System.out.println(Arrays.toString(ans));
        assertEquals(-1, ans[0]);
        assertEquals(-1, ans[1]);
    }

    /**
     * Test if the method works with input arrays consisting only of one row.
     */
    @Test
    public void BinarySearch2dArrayTestOneRow() {
        int[][] arr = {{1, 2, 3, 4}};
        int target = 2;

        // Assert that the requirement, that the array only has one row, is fulfilled.
        assertEquals(arr.length, 1);
        int[] ans = BinarySearch2dArray.BinarySearch(arr, target);
        System.out.println(Arrays.toString(ans));
        assertEquals(0, ans[0]);
        assertEquals(1, ans[1]);
    }

    /**
     * Test if the method works with the target in the middle of the input.
     */
    @Test
    public void BinarySearch2dArrayTestTargetInMiddle() {
        int[][] arr = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}};
        int target = 8;
        // Assert that the requirement, that the target is in the middle row and middle column, is
        // fulfilled.
        assertEquals(arr[arr.length / 2][arr[0].length / 2], target);
        int[] ans = BinarySearch2dArray.BinarySearch(arr, target);
        System.out.println(Arrays.toString(ans));
        assertEquals(1, ans[0]);
        assertEquals(2, ans[1]);
    }

    /**
     * Test if the method works with the target in the middle column,
     * in the row above the middle row.
     */
    @Test
    public void BinarySearch2dArrayTestTargetAboveMiddleRowInMiddleColumn() {
        int[][] arr = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        int target = 3;

        // Assert that the requirement, that he target is in the middle column,
        // in an array with an even number of columns, and on the row "above" the middle row.
        assertEquals(arr[0].length % 2, 0);
        assertEquals(arr[arr.length / 2 - 1][arr[0].length / 2], target);
        int[] ans = BinarySearch2dArray.BinarySearch(arr, target);
        System.out.println(Arrays.toString(ans));
        assertEquals(0, ans[0]);
        assertEquals(2, ans[1]);
    }

    /**
     * Test if the method works with an empty array.
     */
    @Test
    public void BinarySearch2dArrayTestEmptyArray() {
        int[][] arr = {};
        int target = 5;

        // Assert that an empty array is not valid input for the method.
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> BinarySearch2dArray.BinarySearch(arr, target));
    }
}
