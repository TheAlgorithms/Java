package com.search;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LinearSearchTest {
    @Test
    void testLinearSearch() {
        Integer[] arr1 = {1, 2, 3, 4, 5};
        Assertions.assertEquals(2, LinearSearch.findIndex(arr1, 3), "Incorrect index");
        Assertions.assertEquals(0, LinearSearch.findIndex(arr1, 1), "Incorrect index");
        Assertions.assertEquals(-1, LinearSearch.findIndex(arr1, 8), "Incorrect index");
        Assertions.assertEquals(-1, LinearSearch.findIndex(arr1, -2), "Incorrect index");

        String[] arr2 = {"A", "B", "C", "D"};
        Assertions.assertEquals(2, LinearSearch.findIndex(arr2, "C"), "Incorrect index");
        Assertions.assertEquals(1, LinearSearch.findIndex(arr2, "B"), "Incorrect index");
        Assertions.assertEquals(-1, LinearSearch.findIndex(arr2, "F"), "Incorrect index");

        String[] arr3 = {};
        Assertions.assertEquals(-1, LinearSearch.findIndex(arr3, ""), "Incorrect index");

    }
}
