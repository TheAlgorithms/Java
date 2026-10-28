package com.thealgorithms.geometry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.geom.Point2D;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class LineIntersectionTest {

    @Test
    void testCrossingSegments() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(4, 4);
        Point q1 = new Point(0, 4);
        Point q2 = new Point(4, 0);

        assertTrue(LineIntersection.intersects(p1, p2, q1, q2));
        Optional<Point2D.Double> intersection = LineIntersection.intersectionPoint(p1, p2, q1, q2);
        assertTrue(intersection.isPresent());
        assertEquals(2.0, intersection.orElseThrow().getX(), 1e-9);
        assertEquals(2.0, intersection.orElseThrow().getY(), 1e-9);
    }

    @Test
    void testParallelSegments() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(3, 3);
        Point q1 = new Point(0, 1);
        Point q2 = new Point(3, 4);

        assertFalse(LineIntersection.intersects(p1, p2, q1, q2));
        assertTrue(LineIntersection.intersectionPoint(p1, p2, q1, q2).isEmpty());
    }

    @Test
    void testTouchingAtEndpoint() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(2, 2);
        Point q1 = new Point(2, 2);
        Point q2 = new Point(4, 0);

        assertTrue(LineIntersection.intersects(p1, p2, q1, q2));
        Optional<Point2D.Double> intersection = LineIntersection.intersectionPoint(p1, p2, q1, q2);
        assertTrue(intersection.isPresent());
        assertEquals(2.0, intersection.orElseThrow().getX(), 1e-9);
        assertEquals(2.0, intersection.orElseThrow().getY(), 1e-9);
    }

    @Test
    void testCollinearOverlapHasNoUniquePoint() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(4, 4);
        Point q1 = new Point(2, 2);
        Point q2 = new Point(6, 6);

        assertTrue(LineIntersection.intersects(p1, p2, q1, q2));
        assertTrue(LineIntersection.intersectionPoint(p1, p2, q1, q2).isEmpty());
    }

    @Test
    void testCollinearDisjointSegments() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(2, 2);
        Point q1 = new Point(3, 3);
        Point q2 = new Point(5, 5);

        assertFalse(LineIntersection.intersects(p1, p2, q1, q2));
        assertTrue(LineIntersection.intersectionPoint(p1, p2, q1, q2).isEmpty());
    }

    @Test
    void testCollinearSegmentsTouchingAtEndpointHaveUniquePoint() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(2, 2);
        Point q1 = new Point(2, 2);
        Point q2 = new Point(4, 4);

        assertTrue(LineIntersection.intersects(p1, p2, q1, q2));
        Optional<Point2D.Double> intersection = LineIntersection.intersectionPoint(p1, p2, q1, q2);
        assertTrue(intersection.isPresent());
        assertEquals(2.0, intersection.orElseThrow().getX(), 1e-9);
        assertEquals(2.0, intersection.orElseThrow().getY(), 1e-9);
    }

    @Test
    void testVerticalAndHorizontalCrossingSegments() {
        Point p1 = new Point(2, 0);
        Point p2 = new Point(2, 5);
        Point q1 = new Point(0, 3);
        Point q2 = new Point(4, 3);

        assertTrue(LineIntersection.intersects(p1, p2, q1, q2));
        Optional<Point2D.Double> intersection = LineIntersection.intersectionPoint(p1, p2, q1, q2);
        assertTrue(intersection.isPresent());
        assertEquals(2.0, intersection.orElseThrow().getX(), 1e-9);
        assertEquals(3.0, intersection.orElseThrow().getY(), 1e-9);
    }
}
