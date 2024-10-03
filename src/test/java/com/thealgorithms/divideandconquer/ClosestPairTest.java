package com.thealgorithms.divideandconquer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ClosestPairTest {

    @Test
    public void testBuildLocation() {
        // Test creating a point using the buildLocation method
        ClosestPair cp = new ClosestPair(1);
        ClosestPair.Location point = cp.buildLocation(3.0, 4.0);
        assertNotNull(point);
        assertEquals(3.0, point.x);
        assertEquals(4.0, point.y);
    }

    @Test
    public void testCreateLocation() {
        // Test creating an array of locations using the createLocation method
        ClosestPair cp = new ClosestPair(5);
        ClosestPair.Location[] locations = cp.createLocation(5);
        assertNotNull(locations);
        assertEquals(5, locations.length);
    }

    @Test
    public void testXPartition() {
        // Test xPartition method: Sorting points by x-coordinate
        ClosestPair cp = new ClosestPair(5);
        ClosestPair.Location[] points = new ClosestPair.Location[5];
        points[0] = cp.buildLocation(2.0, 3.0);
        points[1] = cp.buildLocation(5.0, 1.0);
        points[2] = cp.buildLocation(1.0, 6.0);
        points[3] = cp.buildLocation(4.0, 7.0);
        points[4] = cp.buildLocation(3.0, 2.0);

        int pivotIndex = cp.xPartition(points, 0, 4);
        assertEquals(2, pivotIndex); // After partition, pivot should be at index 2
        assertEquals(2.0, points[0].x);
        assertEquals(2.0, points[1].x);
        assertEquals(3.0, points[2].x);
        assertEquals(4.0, points[3].x);
        assertEquals(5.0, points[4].x);
    }

    @Test
    public void testYPartition() {
        // Test yPartition method: Sorting points by y-coordinate
        ClosestPair cp = new ClosestPair(5);
        ClosestPair.Location[] points = new ClosestPair.Location[5];
        points[0] = cp.buildLocation(2.0, 3.0);
        points[1] = cp.buildLocation(5.0, 1.0);
        points[2] = cp.buildLocation(1.0, 6.0);
        points[3] = cp.buildLocation(4.0, 7.0);
        points[4] = cp.buildLocation(3.0, 2.0);

        int pivotIndex = cp.yPartition(points, 0, 4);
        assertEquals(1, pivotIndex); // After partition, pivot should be at index 1
        assertEquals(1.0, points[1].y);
        assertEquals(2.0, points[4].y);
        assertEquals(3.0, points[0].y);
        assertEquals(6.0, points[2].y);
        assertEquals(7.0, points[3].y);
    }

    @Test
    public void testBruteForce() {
        // Test bruteForce method to handle 2 points
        ClosestPair cp = new ClosestPair(2);
        ClosestPair.Location loc1 = cp.buildLocation(1.0, 2.0);
        ClosestPair.Location loc2 = cp.buildLocation(4.0, 6.0);

        // Use the bruteForce method and pass the locations as an array
        ClosestPair.Location[] locations = new ClosestPair.Location[] {loc1, loc2};
        double result = cp.bruteForce(locations);
        assertEquals(5.0, result, 0.01); // Distance between (1, 2) and (4, 6)
    }

    @Test
    public void testClosestPair() {
        // Test closestPair method for more than 2 points
        ClosestPair cp = new ClosestPair(5);
        cp.buildLocation(2.0, 3.0);
        cp.buildLocation(5.0, 1.0);
        cp.buildLocation(1.0, 6.0);
        cp.buildLocation(4.0, 7.0);
        cp.buildLocation(3.0, 2.0);

        cp.xQuickSort(cp.array, 0, cp.array.length - 1); // Sorting by x-coordinate
        double result = cp.closestPair(cp.array, cp.array.length);
        assertEquals(2.8284, result, 0.0001); // Closest pair is (2, 3) and (3, 2), distance ≈ 2.8284
    }

    @Test
    public void testMainFunction() {
        // Test the main function with the given input
        ClosestPair cp = new ClosestPair(12);
        cp.buildLocation(2, 3);
        cp.buildLocation(2, 16);
        cp.buildLocation(3, 9);
        cp.buildLocation(6, 3);
        cp.buildLocation(7, 7);
        cp.buildLocation(19, 4);
        cp.buildLocation(10, 11);
        cp.buildLocation(15, 2);
        cp.buildLocation(15, 19);
        cp.buildLocation(16, 11);
        cp.buildLocation(17, 13);
        cp.buildLocation(9, 12);

        cp.xQuickSort(cp.array, 0, cp.array.length - 1); // Sorting by x value
        double result = cp.closestPair(cp.array, cp.array.length);

        assertNotNull(cp.point1);
        assertNotNull(cp.point2);
        assertTrue(result > 0); // The minimum distance should be positive
    }
}
