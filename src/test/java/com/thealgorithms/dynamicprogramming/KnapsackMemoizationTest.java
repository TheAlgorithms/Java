package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class KnapsackMemoizationTest {

    KnapsackMemoization knapsackMemoization = new KnapsackMemoization();

    @Test
    void Test1() {
        int[] weight = {1, 3, 4, 5};
        int[] value = {1, 4, 5, 7};
        int capacity = 10;
        assertEquals(13, knapsackMemoization.knapSack(capacity, weight, value, weight.length));
    }

    @Test
    void Test2() {
        int[] weight = {95, 4, 60, 32, 23, 72, 80, 62, 65, 46};
        int[] value = {55, 10, 47, 5, 4, 50, 8, 61, 85, 87};
        int capacity = 269;
        assertEquals(295, knapsackMemoization.knapSack(capacity, weight, value, weight.length));
    }

    @Test
    void Test3() {
        int[] weight = {10, 20, 30};
        int[] value = {60, 100, 120};
        int capacity = 50;
        assertEquals(220, knapsackMemoization.knapSack(capacity, weight, value, weight.length));
    }
}