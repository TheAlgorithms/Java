package com.search;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IterativeBinarySearchTest {

    @Test
    void testIterativeBinarySearch() {
        Integer[] arr1 = {1, 2, 3, 5, 8, 13, 21, 34, 55};
        Assertions.assertEquals(2, IterativeBinarySearch.find(arr1, 3), "Incorrect index");
        Assertions.assertEquals(0, IterativeBinarySearch.find(arr1, 1), "Incorrect index");
        Assertions.assertEquals(8, IterativeBinarySearch.find(arr1, 55), "Incorrect index");
        Assertions.assertEquals(-1, IterativeBinarySearch.find(arr1, -2), "Incorrect index");
        Assertions.assertEquals(-1, IterativeBinarySearch.find(arr1, 4), "Incorrect index");

        String[] arr2 = {"A", "B", "C", "D"};
        Assertions.assertEquals(2, IterativeBinarySearch.find(arr2, "C"), "Incorrect index");
        Assertions.assertEquals(1, IterativeBinarySearch.find(arr2, "B"), "Incorrect index");
        Assertions.assertEquals(-1, IterativeBinarySearch.find(arr2, "F"), "Incorrect index");

        String[] arr3 = {};
        Assertions.assertEquals(-1, IterativeBinarySearch.find(arr3, ""), "Incorrect index");
    }

}
