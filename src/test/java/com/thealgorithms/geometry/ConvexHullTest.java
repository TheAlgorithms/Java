package com.thealgorithms.geometry;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConvexHullTest {

    @Test
    public void testConvexHull() {
        List<Point> points =
            Arrays.asList(new Point(0, 3), new Point(2, 2), new Point(1, 1), new Point(2, 1), new Point(3, 0),
                          new Point(0, 0), new Point(3, 3), new Point(2, -1), new Point(2, -4), new Point(1, -3));

        Set<Point> expected = new HashSet<>(
            Arrays.asList(new Point(2, -4), new Point(1, -3), new Point(0, 0), new Point(3, 0), new Point(0, 3),
                          new Point(3, 3))
        );

        List<Point> hull = ConvexHull.convexHullRecursive(points);
        Set<Point> actual = new HashSet<>(hull);

        assertEquals(expected, actual);
    }
}
