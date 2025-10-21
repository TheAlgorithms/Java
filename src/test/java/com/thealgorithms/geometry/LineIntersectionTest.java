package com.thealgorithms.geometry;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LineIntersectionTest {

    @Test
    void testIntersectingSegments() {
        LineIntersection.Point p1 = new LineIntersection.Point(1, 1);
        LineIntersection.Point q1 = new LineIntersection.Point(4, 4);
        LineIntersection.Point p2 = new LineIntersection.Point(1, 4);
        LineIntersection.Point q2 = new LineIntersection.Point(4, 1);
        assertTrue(LineIntersection.doIntersect(p1, q1, p2, q2));
    }

    @Test
    void testNonIntersectingSegments() {
        LineIntersection.Point p1 = new LineIntersection.Point(1, 1);
        LineIntersection.Point q1 = new LineIntersection.Point(2, 2);
        LineIntersection.Point p2 = new LineIntersection.Point(3, 3);
        LineIntersection.Point q2 = new LineIntersection.Point(4, 4);
        assertFalse(LineIntersection.doIntersect(p1, q1, p2, q2));
    }

    @Test
    void testCollinearOverlappingSegments() {
        LineIntersection.Point p1 = new LineIntersection.Point(1, 1);
        LineIntersection.Point q1 = new LineIntersection.Point(5, 5);
        LineIntersection.Point p2 = new LineIntersection.Point(2, 2);
        LineIntersection.Point q2 = new LineIntersection.Point(6, 6);
        assertTrue(LineIntersection.doIntersect(p1, q1, p2, q2));
    }
}
