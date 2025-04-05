package com.thealgorithms.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TravelingSalesmanTest {

    // Test Case 1: A simple distance matrix with 4 cities
    @Test
    public void testBruteForceSimple() {
        int[][] distanceMatrix = {{0, 10, 15, 20}, {10, 0, 35, 25}, {15, 35, 0, 30}, {20, 25, 30, 0}};
        int expectedMinDistance = 80;
        int result = TravelingSalesman.bruteForce(distanceMatrix);
        assertEquals(expectedMinDistance, result);
    }

    @Test
    public void testDynamicProgrammingSimple() {
        int[][] distanceMatrix = {{0, 10, 15, 20}, {10, 0, 35, 25}, {15, 35, 0, 30}, {20, 25, 30, 0}};
        int expectedMinDistance = 80;
        int result = TravelingSalesman.dynamicProgramming(distanceMatrix);
        assertEquals(expectedMinDistance, result);
    }

    // Test Case 2: A distance matrix with 3 cities
    @Test
    public void testBruteForceThreeCities() {
        int[][] distanceMatrix = {{0, 10, 15}, {10, 0, 35}, {15, 35, 0}};
        int expectedMinDistance = 60;
        int result = TravelingSalesman.bruteForce(distanceMatrix);
        assertEquals(expectedMinDistance, result);
    }

    @Test
    public void testDynamicProgrammingThreeCities() {
        int[][] distanceMatrix = {{0, 10, 15}, {10, 0, 35}, {15, 35, 0}};
        int expectedMinDistance = 60;
        int result = TravelingSalesman.dynamicProgramming(distanceMatrix);
        assertEquals(expectedMinDistance, result);
    }

    // Test Case 3: A distance matrix with 5 cities (larger input)
    @Test
    public void testBruteForceFiveCities() {
        int[][] distanceMatrix = {{0, 2, 9, 10, 1}, {2, 0, 6, 5, 8}, {9, 6, 0, 4, 3}, {10, 5, 4, 0, 7}, {1, 8, 3, 7, 0}};
        int expectedMinDistance = 15;
        int result = TravelingSalesman.bruteForce(distanceMatrix);
        assertEquals(expectedMinDistance, result);
    }

    @Test
    public void testDynamicProgrammingFiveCities() {
        int[][] distanceMatrix = {{0, 2, 9, 10, 1}, {2, 0, 6, 5, 8}, {9, 6, 0, 4, 3}, {10, 5, 4, 0, 7}, {1, 8, 3, 7, 0}};
        int expectedMinDistance = 15;
        int result = TravelingSalesman.dynamicProgramming(distanceMatrix);
        assertEquals(expectedMinDistance, result);
    }

    // Test Case 4: A distance matrix with 2 cities (simple case)
    @Test
    public void testBruteForceTwoCities() {
        int[][] distanceMatrix = {{0, 1}, {1, 0}};
        int expectedMinDistance = 2;
        int result = TravelingSalesman.bruteForce(distanceMatrix);
        assertEquals(expectedMinDistance, result);
    }

    @Test
    public void testDynamicProgrammingTwoCities() {
        int[][] distanceMatrix = {{0, 1}, {1, 0}};
        int expectedMinDistance = 2;
        int result = TravelingSalesman.dynamicProgramming(distanceMatrix);
        assertEquals(expectedMinDistance, result);
    }

    // Test Case 5: A distance matrix with identical distances
    @Test
    public void testBruteForceEqualDistances() {
        int[][] distanceMatrix = {{0, 10, 10, 10}, {10, 0, 10, 10}, {10, 10, 0, 10}, {10, 10, 10, 0}};
        int expectedMinDistance = 40;
        int result = TravelingSalesman.bruteForce(distanceMatrix);
        assertEquals(expectedMinDistance, result);
    }

    @Test
    public void testDynamicProgrammingEqualDistances() {
        int[][] distanceMatrix = {{0, 10, 10, 10}, {10, 0, 10, 10}, {10, 10, 0, 10}, {10, 10, 10, 0}};
        int expectedMinDistance = 40;
        int result = TravelingSalesman.dynamicProgramming(distanceMatrix);
        assertEquals(expectedMinDistance, result);
    }

    // Test Case 6: A distance matrix with only one city
    @Test
    public void testBruteForceOneCity() {
        int[][] distanceMatrix = {{0}};
        int expectedMinDistance = 0;
        int result = TravelingSalesman.bruteForce(distanceMatrix);
        assertEquals(expectedMinDistance, result);
    }

    @Test
    public void testDynamicProgrammingOneCity() {
        int[][] distanceMatrix = {{0}};
        int expectedMinDistance = 0;
        int result = TravelingSalesman.dynamicProgramming(distanceMatrix);
        assertEquals(expectedMinDistance, result);
    }

    // Test Case 7: Distance matrix with large numbers
    @Test
    public void testBruteForceLargeNumbers() {
        int[][] distanceMatrix = {{0, 1000000, 2000000}, {1000000, 0, 1500000}, {2000000, 1500000, 0}};
        int expectedMinDistance = 4500000;
        int result = TravelingSalesman.bruteForce(distanceMatrix);
        assertEquals(expectedMinDistance, result);
    }

    @Test
    public void testDynamicProgrammingLargeNumbers() {
        int[][] distanceMatrix = {{0, 1000000, 2000000}, {1000000, 0, 1500000}, {2000000, 1500000, 0}};
        int expectedMinDistance = 4500000;
        int result = TravelingSalesman.dynamicProgramming(distanceMatrix);
        assertEquals(expectedMinDistance, result);
    }
}
