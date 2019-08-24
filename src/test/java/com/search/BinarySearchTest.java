package com.search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinarySearchTest {

    @Test
    public void testBinarySearch() {
        Integer[] arr1 = {1,2,3,4,5};
        assertEquals(2, BinarySearch.findIndex(arr1,3));
        assertEquals(0, BinarySearch.findIndex(arr1,1));
        assertEquals(-1, BinarySearch.findIndex(arr1,8));
        assertEquals(-1, BinarySearch.findIndex(arr1,-2));

        String[] arr2 = {"A", "B", "C", "D"};
        assertEquals(2, BinarySearch.findIndex(arr2,"C"));
        assertEquals(1, BinarySearch.findIndex(arr2,"B"));
        assertEquals(-1, BinarySearch.findIndex(arr2,"F"));

        String[] arr3 = {};
        assertEquals(-1, BinarySearch.findIndex(arr3, ""));
    }
}
