package com.thealgorithms.randomized;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ClosestPairTest {

    // Tests sorting of an array with multiple elements, including duplicates.
    @Test
    public void testMultiplePairs() {
        List<Point> points = Arrays.asList(new Point(1, 2), new Point(3, 4), new Point(5, 1), new Point(7, 8), new Point(2, 3), new Point(6, 2));
        double expected = 1.41;
        assertEquals(expected, ClosestPair.closest(points));
    }

    // Test if there are no pairs.
    @Test
    public void testNoPoints() {
        List<Point> points = new ArrayList<>();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> { ClosestPair.closest(points); });
        assertEquals("There are no pairs to compare.", exception.getMessage());
    }

    // Test if there is one point, no pairs.
    @Test
    public void testOnePoint() {
        List<Point> points = Arrays.asList(new Point(1, 2));
        Exception exception = assertThrows(IllegalArgumentException.class, () -> { ClosestPair.closest(points); });
        assertEquals("There is only one pair.", exception.getMessage());
    }

    // Test if there is a duplicate points as a pair
    @Test
    public void testPoints() {
        List<Point> points = Arrays.asList(new Point(1, 2), new Point(5, 1), new Point(5, 1), new Point(7, 8), new Point(2, 3), new Point(6, 2));
        double expected = 0.00;
        assertEquals(expected, ClosestPair.closest(points));
    }
}
