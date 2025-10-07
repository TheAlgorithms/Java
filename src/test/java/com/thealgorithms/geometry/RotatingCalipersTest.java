package com.thealgorithms.geometry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the RotatingCalipers class.
 * Tests cover various scenarios including simple cases, edge cases, and complex polygons.
 */
public class RotatingCalipersTest {

    @Test
    void testComputeDiameterWithTwoPoints() {
        List<Point> points = Arrays.asList(new Point(0, 0), new Point(3, 4));
        RotatingCalipers.PointPair result = RotatingCalipers.computeDiameter(points);
        
        assertNotNull(result);
        assertEquals(5.0, result.distance(), 1e-9);
        assertTrue((result.p1().equals(new Point(0, 0)) && result.p2().equals(new Point(3, 4))) ||
                   (result.p1().equals(new Point(3, 4)) && result.p2().equals(new Point(0, 0))));
    }

    @Test
    void testComputeDiameterWithTriangle() {
        List<Point> points = Arrays.asList(
            new Point(0, 0),
            new Point(3, 0),
            new Point(1, 2)
        );
        RotatingCalipers.PointPair result = RotatingCalipers.computeDiameter(points);
        
        assertNotNull(result);
        assertEquals(3.0, result.distance(), 1e-9);
        assertTrue((result.p1().equals(new Point(0, 0)) && result.p2().equals(new Point(3, 0))) ||
                   (result.p1().equals(new Point(3, 0)) && result.p2().equals(new Point(0, 0))));
    }

    @Test
    void testComputeDiameterWithSquare() {
        List<Point> points = Arrays.asList(
            new Point(0, 0),
            new Point(2, 0),
            new Point(2, 2),
            new Point(0, 2)
        );
        RotatingCalipers.PointPair result = RotatingCalipers.computeDiameter(points);
        
        assertNotNull(result);
        assertEquals(Math.sqrt(8), result.distance(), 1e-9); // Diagonal of square
    }

    @Test
    void testComputeDiameterWithRectangle() {
        List<Point> points = Arrays.asList(
            new Point(0, 0),
            new Point(4, 0),
            new Point(4, 2),
            new Point(0, 2)
        );
        RotatingCalipers.PointPair result = RotatingCalipers.computeDiameter(points);
        
        assertNotNull(result);
        assertEquals(Math.sqrt(20), result.distance(), 1e-9); // Diagonal of rectangle
    }

    @Test
    void testComputeDiameterWithPentagon() {
        List<Point> points = Arrays.asList(
            new Point(0, 0),
            new Point(2, 0),
            new Point(3, 1),
            new Point(1, 2),
            new Point(-1, 1)
        );
        RotatingCalipers.PointPair result = RotatingCalipers.computeDiameter(points);
        
        assertNotNull(result);
        assertTrue(result.distance() > 0);
    }

    @Test
    void testComputeDiameterWithNullPoints() {
        assertThrows(IllegalArgumentException.class, () -> {
            RotatingCalipers.computeDiameter(null);
        });
    }

    @Test
    void testComputeDiameterWithEmptyPoints() {
        assertThrows(IllegalArgumentException.class, () -> {
            RotatingCalipers.computeDiameter(new ArrayList<>());
        });
    }

    @Test
    void testComputeDiameterWithSinglePoint() {
        assertThrows(IllegalArgumentException.class, () -> {
            RotatingCalipers.computeDiameter(Arrays.asList(new Point(0, 0)));
        });
    }

    @Test
    void testComputeWidthWithTwoPoints() {
        List<Point> points = Arrays.asList(new Point(0, 0), new Point(3, 4));
        double width = RotatingCalipers.computeWidth(points);
        
        assertEquals(0.0, width, 1e-9);
    }

    @Test
    void testComputeWidthWithTriangle() {
        List<Point> points = Arrays.asList(
            new Point(0, 0),
            new Point(3, 0),
            new Point(1, 2)
        );
        double width = RotatingCalipers.computeWidth(points);
        
        assertTrue(width > 0);
        assertTrue(width < 3.0); // Should be less than the base width
    }

    @Test
    void testComputeWidthWithSquare() {
        List<Point> points = Arrays.asList(
            new Point(0, 0),
            new Point(2, 0),
            new Point(2, 2),
            new Point(0, 2)
        );
        double width = RotatingCalipers.computeWidth(points);
        
        assertEquals(Math.sqrt(2), width, 1e-9); // Width of square
    }

    @Test
    void testComputeWidthWithRectangle() {
        List<Point> points = Arrays.asList(
            new Point(0, 0),
            new Point(4, 0),
            new Point(4, 2),
            new Point(0, 2)
        );
        double width = RotatingCalipers.computeWidth(points);
        
        assertEquals(2.0, width, 1e-9); // Width of rectangle (height)
    }

    @Test
    void testComputeWidthWithNullPoints() {
        assertThrows(IllegalArgumentException.class, () -> {
            RotatingCalipers.computeWidth(null);
        });
    }

    @Test
    void testComputeWidthWithEmptyPoints() {
        assertThrows(IllegalArgumentException.class, () -> {
            RotatingCalipers.computeWidth(new ArrayList<>());
        });
    }

    @Test
    void testComputeWidthWithSinglePoint() {
        assertThrows(IllegalArgumentException.class, () -> {
            RotatingCalipers.computeWidth(Arrays.asList(new Point(0, 0)));
        });
    }

    @Test
    void testComputeMinimumAreaBoundingRectangleWithTwoPoints() {
        List<Point> points = Arrays.asList(new Point(0, 0), new Point(3, 4));
        RotatingCalipers.Rectangle result = RotatingCalipers.computeMinimumAreaBoundingRectangle(points);
        
        assertNotNull(result);
        assertEquals(0.0, result.area(), 1e-9);
    }

    @Test
    void testComputeMinimumAreaBoundingRectangleWithTriangle() {
        List<Point> points = Arrays.asList(
            new Point(0, 0),
            new Point(3, 0),
            new Point(1, 2)
        );
        RotatingCalipers.Rectangle result = RotatingCalipers.computeMinimumAreaBoundingRectangle(points);
        
        assertNotNull(result);
        assertTrue(result.area() > 0);
        assertTrue(result.area() <= 6.0); // Should be less than or equal to axis-aligned bounding box
    }

    @Test
    void testComputeMinimumAreaBoundingRectangleWithSquare() {
        List<Point> points = Arrays.asList(
            new Point(0, 0),
            new Point(2, 0),
            new Point(2, 2),
            new Point(0, 2)
        );
        RotatingCalipers.Rectangle result = RotatingCalipers.computeMinimumAreaBoundingRectangle(points);
        
        assertNotNull(result);
        assertEquals(4.0, result.area(), 1e-9); // Area of square
    }

    @Test
    void testComputeMinimumAreaBoundingRectangleWithRectangle() {
        List<Point> points = Arrays.asList(
            new Point(0, 0),
            new Point(4, 0),
            new Point(4, 2),
            new Point(0, 2)
        );
        RotatingCalipers.Rectangle result = RotatingCalipers.computeMinimumAreaBoundingRectangle(points);
        
        assertNotNull(result);
        assertEquals(8.0, result.area(), 1e-9); // Area of rectangle
    }

    @Test
    void testComputeMinimumAreaBoundingRectangleWithPentagon() {
        List<Point> points = Arrays.asList(
            new Point(0, 0),
            new Point(2, 0),
            new Point(3, 1),
            new Point(1, 2),
            new Point(-1, 1)
        );
        RotatingCalipers.Rectangle result = RotatingCalipers.computeMinimumAreaBoundingRectangle(points);
        
        assertNotNull(result);
        assertTrue(result.area() > 0);
        assertTrue(result.area() <= 8.0); // Should be less than or equal to axis-aligned bounding box
    }

    @Test
    void testComputeMinimumAreaBoundingRectangleWithNullPoints() {
        assertThrows(IllegalArgumentException.class, () -> {
            RotatingCalipers.computeMinimumAreaBoundingRectangle(null);
        });
    }

    @Test
    void testComputeMinimumAreaBoundingRectangleWithEmptyPoints() {
        assertThrows(IllegalArgumentException.class, () -> {
            RotatingCalipers.computeMinimumAreaBoundingRectangle(new ArrayList<>());
        });
    }

    @Test
    void testComputeMinimumAreaBoundingRectangleWithSinglePoint() {
        assertThrows(IllegalArgumentException.class, () -> {
            RotatingCalipers.computeMinimumAreaBoundingRectangle(Arrays.asList(new Point(0, 0)));
        });
    }

    @Test
    void testPointPairToString() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(3, 4);
        RotatingCalipers.PointPair pair = new RotatingCalipers.PointPair(p1, p2, 5.0);
        
        String str = pair.toString();
        assertTrue(str.contains("PointPair"));
        assertTrue(str.contains("distance=5.00"));
    }

    @Test
    void testRectangleToString() {
        Point bottomLeft = new Point(0, 0);
        Point topRight = new Point(2, 2);
        RotatingCalipers.Rectangle rect = new RotatingCalipers.Rectangle(bottomLeft, topRight, 4.0);
        
        String str = rect.toString();
        assertTrue(str.contains("Rectangle"));
        assertTrue(str.contains("area=4.00"));
    }

    @Test
    void testComplexPolygon() {
        // Test with a more complex polygon (hexagon)
        List<Point> points = Arrays.asList(
            new Point(0, 0),
            new Point(2, 0),
            new Point(3, 1),
            new Point(2, 2),
            new Point(0, 2),
            new Point(-1, 1)
        );
        
        RotatingCalipers.PointPair diameter = RotatingCalipers.computeDiameter(points);
        double width = RotatingCalipers.computeWidth(points);
        RotatingCalipers.Rectangle rectangle = RotatingCalipers.computeMinimumAreaBoundingRectangle(points);
        
        assertNotNull(diameter);
        assertNotNull(rectangle);
        assertTrue(diameter.distance() > 0);
        assertTrue(width > 0);
        assertTrue(rectangle.area() > 0);
    }

    @Test
    void testCollinearPoints() {
        // Test with collinear points
        List<Point> points = Arrays.asList(
            new Point(0, 0),
            new Point(1, 0),
            new Point(2, 0),
            new Point(3, 0)
        );
        
        RotatingCalipers.PointPair diameter = RotatingCalipers.computeDiameter(points);
        double width = RotatingCalipers.computeWidth(points);
        RotatingCalipers.Rectangle rectangle = RotatingCalipers.computeMinimumAreaBoundingRectangle(points);
        
        assertNotNull(diameter);
        assertNotNull(rectangle);
        assertEquals(3.0, diameter.distance(), 1e-9);
        assertEquals(0.0, width, 1e-9);
        assertEquals(0.0, rectangle.area(), 1e-9);
    }

    @Test
    void testSinglePointConvexHull() {
        // Test edge case where convex hull reduces to a single point
        List<Point> points = Arrays.asList(
            new Point(0, 0),
            new Point(0, 0),
            new Point(0, 0)
        );
        
        assertThrows(IllegalArgumentException.class, () -> {
            RotatingCalipers.computeDiameter(points);
        });
    }
}
