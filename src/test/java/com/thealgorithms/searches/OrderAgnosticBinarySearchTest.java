package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class OrderAgnosticBinarySearchTest {
    @Test
    // valid Test Case
    public void elementInMiddle() {
        int[] arr = {10, 20, 30, 40, 50};
        int answer = OrderAgnosticBinarySearch.binSearchAlgo(arr, 0, arr.length - 1, 30);
        System.out.println(answer);
        int expected = 2;
        assertEquals(expected, answer);
    }

    @Test
    // valid Test Case
    public void rightHalfDescOrder() {
        int[] arr = {50, 40, 30, 20, 10};
        int answer = OrderAgnosticBinarySearch.binSearchAlgo(arr, 0, arr.length - 1, 10);
        System.out.println(answer);
        int expected = 4;
        assertEquals(expected, answer);
    }

    @Test
    // valid test case
    public void leftHalfDescOrder() {
        int[] arr = {50, 40, 30, 20, 10};
        int answer = OrderAgnosticBinarySearch.binSearchAlgo(arr, 0, arr.length - 1, 50);
        System.out.println(answer);
        int expected = 0;
        assertEquals(expected, answer);
    }

    @Test
    // valid test case
    public void rightHalfAscOrder() {
        int[] arr = {10, 20, 30, 40, 50};
        int answer = OrderAgnosticBinarySearch.binSearchAlgo(arr, 0, arr.length - 1, 50);
        System.out.println(answer);
        int expected = 4;
        assertEquals(expected, answer);
    }

    @Test
    // valid test case
    public void leftHalfAscOrder() {
        int[] arr = {10, 20, 30, 40, 50};
        int answer = OrderAgnosticBinarySearch.binSearchAlgo(arr, 0, arr.length - 1, 10);
        System.out.println(answer);
        int expected = 0;
        assertEquals(expected, answer);
    }

    @Test
    // valid test case
    public void elementNotFound() {
        int[] arr = {10, 20, 30, 40, 50};
        int answer = OrderAgnosticBinarySearch.binSearchAlgo(arr, 0, arr.length - 1, 100);
        System.out.println(answer);
        int expected = -1;
        assertEquals(expected, answer);
    }
}
