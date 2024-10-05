package com.thealgorithms.divideandconquer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class ClosestPairTest {

    @Test
    public void testBuildLocation() {
        ClosestPair cp = new ClosestPair(1);
        ClosestPair.Location point = cp.buildLocation(3.0, 4.0);
        assertNotNull(point);
        assertEquals(3.0, point.x);
        assertEquals(4.0, point.y);
    }

    @Test
    public void testCreateLocation() {
        ClosestPair cp = new ClosestPair(5);
        ClosestPair.Location[] locations = cp.createLocation(5);
        assertNotNull(locations);
        assertEquals(5, locations.length);
    }

    @Test
    public void testXPartition() {
        ClosestPair cp = new ClosestPair(5);
        ClosestPair.Location[] points = new ClosestPair.Location[5];
        points[0] = cp.buildLocation(2.0, 3.0);
        points[1] = cp.buildLocation(5.0, 1.0);
        points[2] = cp.buildLocation(1.0, 6.0);
        points[3] = cp.buildLocation(4.0, 7.0);
        points[4] = cp.buildLocation(3.0, 2.0);

        int pivotIndex = cp.xPartition(points, 0, 4);
        assertEquals(2, pivotIndex);
        assertEquals(2.0, points[0].x);
        assertEquals(1.0, points[1].x);
        assertEquals(3.0, points[2].x);
        assertEquals(4.0, points[3].x);
        assertEquals(5.0, points[4].x);
    }

    @Test
    public void testYPartition() {
        ClosestPair cp = new ClosestPair(5);
        ClosestPair.Location[] points = new ClosestPair.Location[5];
        points[0] = cp.buildLocation(2.0, 3.0);
        points[1] = cp.buildLocation(5.0, 1.0);
        points[2] = cp.buildLocation(1.0, 6.0);
        points[3] = cp.buildLocation(4.0, 7.0);
        points[4] = cp.buildLocation(3.0, 2.0);

        int pivotIndex = cp.yPartition(points, 0, 4);
        assertEquals(1, pivotIndex);
        assertEquals(2.0, points[1].y);
        assertEquals(3.0, points[4].y);
        assertEquals(1.0, points[0].y);
        assertEquals(6.0, points[2].y);
        assertEquals(7.0, points[3].y);
    }

    @Test
    public void testBruteForce() {
        ClosestPair cp = new ClosestPair(2);
        ClosestPair.Location loc1 = cp.buildLocation(1.0, 2.0);
        ClosestPair.Location loc2 = cp.buildLocation(4.0, 6.0);

        ClosestPair.Location[] locations = new ClosestPair.Location[] {loc1, loc2};
        double result = cp.bruteForce(locations);
        assertEquals(5.0, result, 0.01);
    }
}
