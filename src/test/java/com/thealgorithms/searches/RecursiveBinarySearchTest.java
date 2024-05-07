// Created by Pronay Debnath
// Date:- 1/10/2023
// Test file updated with JUnit tests
package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test; // Import the JUnit 5 Test annotation

public class RecursiveBinarySearchTest {

    @Test
    public void testBinarySearch() {
        // Create an instance of GenericBinarySearch
        RecursiveBinarySearch<Integer> searcher = new RecursiveBinarySearch<>();

        // Test case 1: Element found in the array
        Integer[] arr1 = {1, 2, 3, 4, 5};
        int target1 = 3;
        int result1 = searcher.binsear(arr1, 0, arr1.length - 1, target1);
        assertEquals(2, result1);

        // Test case 2: Element not found in the array
        Integer[] arr2 = {1, 2, 3, 4, 5};
        int target2 = 6;
        int result2 = searcher.binsear(arr2, 0, arr2.length - 1, target2);
        assertEquals(-1, result2);

        // Test case 3: Element found at the beginning of the array
        Integer[] arr3 = {10, 20, 30, 40, 50};
        int target3 = 10;
        int result3 = searcher.binsear(arr3, 0, arr3.length - 1, target3);
        assertEquals(0, result3);

        // Test case 4: Element found at the end of the array
        Integer[] arr4 = {10, 20, 30, 40, 50};
        int target4 = 50;
        int result4 = searcher.binsear(arr4, 0, arr4.length - 1, target4);
        assertEquals(4, result4);
    }
}
