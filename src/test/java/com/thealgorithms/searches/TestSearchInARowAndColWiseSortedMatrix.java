package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestSearchInARowAndColWiseSortedMatrix {
    @Test
    public void searchItem() {
        int[][] matrix = {{3, 4, 5, 6, 7}, {8, 9, 10, 11, 12}, {14, 15, 16, 17, 18}, {23, 24, 25, 26, 27}, {30, 31, 32, 33, 34}};

        var test = new SearchInARowAndColWiseSortedMatrix();
        int[] res = test.search(matrix, 16);
        int[] expectedResult = {2, 2};
        assertArrayEquals(expectedResult, res);
    }

    @Test
    public void notFound() {
        int[][] matrix = {{3, 4, 5, 6, 7}, {8, 9, 10, 11, 12}, {14, 15, 16, 17, 18}, {23, 24, 25, 26, 27}, {30, 31, 32, 33, 34}};

        var test = new SearchInARowAndColWiseSortedMatrix();
        int[] res = test.search(matrix, 96);
        int[] expectedResult = {-1, -1};
        assertArrayEquals(expectedResult, res);
    }
}
