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
    public void rowColumnSorted2dArrayBinarySearchTestUpper() {
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
    public void rowColumnSorted2dArrayBinarySearchTestUpperSide() {
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
    public void rowColumnSorted2dArrayBinarySearchTestLower() {
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
    public void rowColumnSorted2dArrayBinarySearchTestLowerSide() {
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
    public void rowColumnSorted2dArrayBinarySearchTestNotFound() {
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

    /**
     * Tests for a WIDE rectangular matrix (3 rows, 4 columns)
     */
    private static final Integer[][] WIDE_RECTANGULAR_MATRIX = {
        {10, 20, 30, 40},
        {15, 25, 35, 45},
        {18, 28, 38, 48},
    };

    @Test
    public void rowColumnSorted2dArrayBinarySearchTestWideMatrixMiddle() {
        Integer target = 25; // A value in the middle
        int[] ans = RowColumnWiseSorted2dArrayBinarySearch.search(WIDE_RECTANGULAR_MATRIX, target);
        int[] expected = {1, 1};
        assertEquals(expected[0], ans[0]);
        assertEquals(expected[1], ans[1]);
    }

    @Test
    public void rowColumnSorted2dArrayBinarySearchTestWideMatrixTopRightCorner() {
        Integer target = 40; // The top-right corner element
        int[] ans = RowColumnWiseSorted2dArrayBinarySearch.search(WIDE_RECTANGULAR_MATRIX, target);
        int[] expected = {0, 3};
        assertEquals(expected[0], ans[0]);
        assertEquals(expected[1], ans[1]);
    }

    @Test
    public void rowColumnSorted2dArrayBinarySearchTestWideMatrixBottomLeftCorner() {
        Integer target = 18; // The bottom-left corner element
        int[] ans = RowColumnWiseSorted2dArrayBinarySearch.search(WIDE_RECTANGULAR_MATRIX, target);
        int[] expected = {2, 0};
        assertEquals(expected[0], ans[0]);
        assertEquals(expected[1], ans[1]);
    }

    @Test
    public void rowColumnSorted2dArrayBinarySearchTestWideMatrixTopLeftCorner() {
        Integer target = 10; // The top-left corner element
        int[] ans = RowColumnWiseSorted2dArrayBinarySearch.search(WIDE_RECTANGULAR_MATRIX, target);
        int[] expected = {0, 0};
        assertEquals(expected[0], ans[0]);
        assertEquals(expected[1], ans[1]);
    }

    @Test
    public void rowColumnSorted2dArrayBinarySearchTestWideMatrixBottomRightCorner() {
        Integer target = 48; // The bottom-right corner element
        int[] ans = RowColumnWiseSorted2dArrayBinarySearch.search(WIDE_RECTANGULAR_MATRIX, target);
        int[] expected = {2, 3};
        assertEquals(expected[0], ans[0]);
        assertEquals(expected[1], ans[1]);
    }

    @Test
    public void rowColumnSorted2dArrayBinarySearchTestWideMatrixNotFound() {
        Integer target = 99; // A value that does not exist
        int[] ans = RowColumnWiseSorted2dArrayBinarySearch.search(WIDE_RECTANGULAR_MATRIX, target);
        int[] expected = {-1, -1};
        assertEquals(expected[0], ans[0]);
        assertEquals(expected[1], ans[1]);
    }

    /**
     * Tests for a TALL rectangular matrix (4 rows, 3 columns)
     */
    private static final Integer[][] TALL_RECTANGULAR_MATRIX = {
        {10, 20, 30},
        {15, 25, 35},
        {18, 28, 38},
        {21, 31, 41},
    };

    @Test
    public void rowColumnSorted2dArrayBinarySearchTestTallMatrixMiddle() {
        Integer target = 28; // A value in the middle
        int[] ans = RowColumnWiseSorted2dArrayBinarySearch.search(TALL_RECTANGULAR_MATRIX, target);
        int[] expected = {2, 1};
        assertEquals(expected[0], ans[0]);
        assertEquals(expected[1], ans[1]);
    }

    @Test
    public void rowColumnSorted2dArrayBinarySearchTestTallMatrixTopRightCorner() {
        Integer target = 30; // The top-right corner element
        int[] ans = RowColumnWiseSorted2dArrayBinarySearch.search(TALL_RECTANGULAR_MATRIX, target);
        int[] expected = {0, 2};
        assertEquals(expected[0], ans[0]);
        assertEquals(expected[1], ans[1]);
    }

    @Test
    public void rowColumnSorted2dArrayBinarySearchTestTallMatrixBottomLeftCorner() {
        Integer target = 21; // The bottom-left corner element
        int[] ans = RowColumnWiseSorted2dArrayBinarySearch.search(TALL_RECTANGULAR_MATRIX, target);
        int[] expected = {3, 0};
        assertEquals(expected[0], ans[0]);
        assertEquals(expected[1], ans[1]);
    }

    @Test
    public void rowColumnSorted2dArrayBinarySearchTestTallMatrixTopLeftCorner() {
        Integer target = 10; // The top-left corner element
        int[] ans = RowColumnWiseSorted2dArrayBinarySearch.search(TALL_RECTANGULAR_MATRIX, target);
        int[] expected = {0, 0};
        assertEquals(expected[0], ans[0]);
        assertEquals(expected[1], ans[1]);
    }

    @Test
    public void rowColumnSorted2dArrayBinarySearchTestTallMatrixBottomRightCorner() {
        Integer target = 41; // The bottom-right corner element
        int[] ans = RowColumnWiseSorted2dArrayBinarySearch.search(TALL_RECTANGULAR_MATRIX, target);
        int[] expected = {3, 2};
        assertEquals(expected[0], ans[0]);
        assertEquals(expected[1], ans[1]);
    }

    @Test
    public void rowColumnSorted2dArrayBinarySearchTestTallMatrixNotFound() {
        Integer target = 5; // A value that does not exist
        int[] ans = RowColumnWiseSorted2dArrayBinarySearch.search(TALL_RECTANGULAR_MATRIX, target);
        int[] expected = {-1, -1};
        assertEquals(expected[0], ans[0]);
        assertEquals(expected[1], ans[1]);
    }
}
