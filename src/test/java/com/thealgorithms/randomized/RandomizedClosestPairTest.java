package com.thealgorithms.randomized;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.thealgorithms.randomized.RandomizedClosestPair.Point;
import org.junit.jupiter.api.Test;

public class RandomizedClosestPairTest {

    @Test
    public void testFindClosestPairDistance() {
        Point[] points = {new Point(1, 1), new Point(2, 2), new Point(3, 3), new Point(8, 8), new Point(13, 13)};
        double result = RandomizedClosestPair.findClosestPairDistance(points);
        assertEquals(Math.sqrt(2), result, 0.00001);
    }

    @Test
    public void testWithIdenticalPoints() {
        Point[] points = {new Point(0, 0), new Point(0, 0), new Point(1, 1)};
        double result = RandomizedClosestPair.findClosestPairDistance(points);
        assertEquals(0.0, result, 0.00001);
    }

    @Test
    public void testWithDistantPoints() {
        Point[] points = {new Point(0, 0), new Point(5, 0), new Point(10, 0)};
        double result = RandomizedClosestPair.findClosestPairDistance(points);
        assertEquals(5.0, result, 0.00001);
    }
}
