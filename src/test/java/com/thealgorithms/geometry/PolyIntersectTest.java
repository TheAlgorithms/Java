package com.thealgorithms.geometry;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Point;
import java.awt.Polygon;
import org.junit.jupiter.api.Test;

public class PolyIntersectTest {

    @Test
    public void testGetIntersections() {
        // Create a Polygon object (for example, a triangle)
        Polygon polygon = new Polygon();
        polygon.addPoint(0, 0);
        polygon.addPoint(5, 0);
        polygon.addPoint(0, 5);

        // Define the line segment coordinates
        int l1x1 = 2;
        int l1y1 = 2;
        int l1x2 = 6;
        int l1y2 = 6;

        // Find intersections between the line segment and the polygon
        Point[] intersections = PolyIntersect.getIntersections(polygon, l1x1, l1y1, l1x2, l1y2);

        // Create an array of expected intersection points
        Point[] expectedIntersections = { new Point(2, 2), new Point(4, 4)};

        // Compare the expected and actual intersection points
        assertEquals(expectedIntersections.length, intersections.length);
        for (int i = 0; i < expectedIntersections.length; i++) {
            assertEquals(expectedIntersections[i].x, intersections[i].x);
            assertEquals(expectedIntersections[i].y, intersections[i].y);
        }
    }

    @Test
    public void testGetIntersectionsNoIntersections() {
        // Create a Polygon object (for example, a triangle)
        Polygon polygon = new Polygon();
        polygon.addPoint(0, 0);
        polygon.addPoint(5, 0);
        polygon.addPoint(0, 5);

        // Define a line segment that does not intersect with the polygon
        int l1x1 = 1;
        int l1y1 = 1;
        int l1x2 = 1;
        int l1y2 = 6;

        // Find intersections between the line segment and the polygon
        Point[] intersections = PolyIntersect.getIntersections(polygon, l1x1, l1y1, l1x2, l1y2);

        // There should be no intersections
        assertEquals(0, intersections.length);
    }
}
