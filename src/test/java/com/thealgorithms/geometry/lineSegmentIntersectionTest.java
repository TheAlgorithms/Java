package com.thealgorithms.geometry;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class LineSegmentIntersectionTest {
    @Test
    void testIntersectingSegments() {
        Point p1 = new Point(1, 1), q1 = new Point(4, 4);
        Point p2 = new Point(1, 4), q2 = new Point(4, 1);
        assertTrue(LineSegmentIntersection.doIntersect(p1, q1, p2, q2));
    }

    @Test
    void testNonIntersectingSegments() {
        Point p1 = new Point(1, 1), q1 = new Point(2, 2);
        Point p2 = new Point(3, 3), q2 = new Point(4, 4);
        assertFalse(LineSegmentIntersection.doIntersect(p1, q1, p2, q2));
    }
}
