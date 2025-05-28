package com.thealgorithms.randomized;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.thealgorithms.randomized.RandomizedClosestPair.Point;
import org.junit.jupiter.api.Test;

public class RandomizedClosestPairTest {

    @Test
    public void testClosestPairBasic() {
        Point[] points = new Point[] {
            new Point(2, 3),
            new Point(12, 30),
            new Point(40, 50),
            new Point(5, 1),
            new Point(12, 10),
            new Point(3, 4)
        };
        double result = RandomizedClosestPair.findClosestPairDistance(points);
        assertEquals(Math.hypot(1, 1), result, 0.01); // Closest pair: (2,3) and (3,4)
    }

    @Test
    public void testIdenticalPoints() {
        Point[] points = new Point[] {
            new Point(0, 0),
            new Point(0, 0),
            new Point(1, 1),
        };
        double result = RandomizedClosestPair.findClosestPairDistance(points);
        assertEquals(0.0, result, 0.00001);
    }

    @Test
    public void testMinimalCase() {
        Point[] points = new Point[] {
            new Point(0, 0),
            new Point(3, 4)
        };
        double result = RandomizedClosestPair.findClosestPairDistance(points);
        assertEquals(5.0, result, 0.00001);
    }
} 

