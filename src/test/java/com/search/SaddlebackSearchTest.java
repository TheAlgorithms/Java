package com.search;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SaddlebackSearchTest {

    @Test
    void testBinarySearch() {
        Integer[][] arr1 = {
            {1, 2, 3, 4, 5},
            {6, 7, 8, 9, 10},
            {11, 12, 13, 14, 15},
            {16, 17, 18, 19, 20},
        };

        Assertions.assertArrayEquals(new int[] {1, 0}, SaddlebackSearch.find(arr1, 6), "Incorrect index");
        Assertions.assertArrayEquals(new int[] {1, 4}, SaddlebackSearch.find(arr1, 10), "Incorrect index");
        Assertions.assertArrayEquals(new int[] {3, 4}, SaddlebackSearch.find(arr1, 20), "Incorrect index");
        Assertions.assertArrayEquals(new int[] {0, 0}, SaddlebackSearch.find(arr1, 1), "Incorrect index");
        Assertions.assertArrayEquals(new int[] {-1, -1}, SaddlebackSearch.find(arr1, -10), "Incorrect index");

        String[][] arr2 = {
                {"A", "B", "C", "D", "E"},
                {"F", "G", "H", "I", "J"},
                {"K", "L", "M", "N", "O"},
        };

        Assertions.assertArrayEquals(new int[] {1, 0}, SaddlebackSearch.find(arr2, "F"), "Incorrect index");
        Assertions.assertArrayEquals(new int[] {1, 4}, SaddlebackSearch.find(arr2, "J"), "Incorrect index");
        Assertions.assertArrayEquals(new int[] {2, 4}, SaddlebackSearch.find(arr2, "O"), "Incorrect index");
        Assertions.assertArrayEquals(new int[] {0, 0}, SaddlebackSearch.find(arr2, "A"), "Incorrect index");
        Assertions.assertArrayEquals(new int[] {-1, -1}, SaddlebackSearch.find(arr2, "X"), "Incorrect index");
    }

}
