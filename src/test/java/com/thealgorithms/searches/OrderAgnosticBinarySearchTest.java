package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.thealgorithms.searches.OrderAgnosticBinarySearch;
import java.util.*;
import org.junit.jupiter.api.Test;

public class OrderAgnosticBinarySearchTest {
    @Test
    // valid Test Case
    public void ElementInMiddle() {
        int[] arr = {10, 20, 30, 40, 50};
        int answer = OrderAgnosticBinarySearch.BinSearchAlgo(arr, 0, arr.length - 1, 30);
        System.out.println(answer);
        int expected = 2;
        assertEquals(expected, answer);
    }

    @Test
    // valid Test Case
    public void RightHalfDescOrder() {
        int[] arr = {50, 40, 30, 20, 10};
        int answer = OrderAgnosticBinarySearch.BinSearchAlgo(arr, 0, arr.length - 1, 10);
        System.out.println(answer);
        int expected = 4;
        assertEquals(expected, answer);
    }

    @Test
    // valid test case
    public void LeftHalfDescOrder() {
        int[] arr = {50, 40, 30, 20, 10};
        int answer = OrderAgnosticBinarySearch.BinSearchAlgo(arr, 0, arr.length - 1, 50);
        System.out.println(answer);
        int expected = 0;
        assertEquals(expected, answer);
    }

    @Test
    // valid test case
    public void RightHalfAscOrder() {
        int[] arr = {10, 20, 30, 40, 50};
        int answer = OrderAgnosticBinarySearch.BinSearchAlgo(arr, 0, arr.length - 1, 50);
        System.out.println(answer);
        int expected = 4;
        assertEquals(expected, answer);
    }

    @Test
    // valid test case
    public void LeftHalfAscOrder() {
        int[] arr = {10, 20, 30, 40, 50};
        int answer = OrderAgnosticBinarySearch.BinSearchAlgo(arr, 0, arr.length - 1, 10);
        System.out.println(answer);
        int expected = 0;
        assertEquals(expected, answer);
    }

    @Test
    // valid test case
    public void ElementNotFound() {
        int[] arr = {10, 20, 30, 40, 50};
        int answer = OrderAgnosticBinarySearch.BinSearchAlgo(arr, 0, arr.length - 1, 100);
        System.out.println(answer);
        int expected = -1;
        assertEquals(expected, answer);
    }
}
