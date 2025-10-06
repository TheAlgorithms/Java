package com.thealgorithms.geometry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class RotatingCalipersTest {

    private static final double EPSILON = 1e-6;

    @Test
    void testDiameterSimpleTriangle() {
        List<Point> triangle = Arrays.asList(
            new Point(0, 0),
            new Point(3, 4),
            new Point(0, 4)
        );
        
        RotatingCalipers.PointPair diameter = RotatingCalipers.diameter(triangle);
        assertEquals(5.0, diameter.distance, EPSILON);
    }

    @Test
    void testDiameterSquare() {
        List<Point> square = Arrays.asList(
            new Point(0, 0),
            new Point(2, 0),
            new Point(2, 2),
            new Point(0, 2)
        );
        
        RotatingCalipers.PointPair diameter = RotatingCalipers.diameter(square);
        assertEquals(Math.sqrt(8), diameter.distance, EPSILON);
    }

    @Test
    void testDiameterTwoPoints() {
        List<Point> twoPoints = Arrays.asList(
            new Point(0, 0),
            new Point(3, 4)
        );
        
        RotatingCalipers.PointPair diameter = RotatingCalipers.diameter(twoPoints);
        assertEquals(5.0, diameter.distance, EPSILON);
    }

    @Test
    void testDiameterInvalidInput() {
        List<Point> onePoint = Arrays.asList(new Point(0, 0));
        assertThrows(IllegalArgumentException.class, () -> RotatingCalipers.diameter(onePoint));
    }

    @Test
    void testWidthTriangle() {
        List<Point> triangle = Arrays.asList(
            new Point(0, 0),
            new Point(4, 0),
            new Point(2, 3)
        );
        
        double width = RotatingCalipers.width(triangle);
        assertTrue(width > 0);
        assertTrue(width <= 4.0); // Width should be less than or equal to base
    }

    @Test
    void testWidthSquare() {
        List<Point> square = Arrays.asList(
            new Point(0, 0),
            new Point(2, 0),
            new Point(2, 2),
            new Point(0, 2)
        );
        
        double width = RotatingCalipers.width(square);
        assertEquals(2.0, width, EPSILON);
    }

    @Test
    void testWidthInvalidInput() {
        List<Point> twoPoints = Arrays.asList(
            new Point(0, 0),
            new Point(1, 1)
        );
        assertThrows(IllegalArgumentException.class, () -> RotatingCalipers.width(twoPoints));
    }

    @Test
    void testMinimumBoundingRectangleTriangle() {
        List<Point> triangle = Arrays.asList(
            new Point(0, 0),
            new Point(4, 0),
            new Point(2, 3)
        );
        
        RotatingCalipers.Rectangle rect = RotatingCalipers.minimumBoundingRectangle(triangle);
        assertTrue(rect.area > 0);
        assertTrue(rect.area <= 12.0); // Should be less than axis-aligned bounding box
    }

    @Test
    void testMinimumBoundingRectangleSquare() {
        List<Point> square = Arrays.asList(
            new Point(0, 0),
            new Point(2, 0),
            new Point(2, 2),
            new Point(0, 2)
        );
        
        RotatingCalipers.Rectangle rect = RotatingCalipers.minimumBoundingRectangle(square);
        assertEquals(4.0, rect.area, EPSILON);
    }

    @Test
    void testMinimumBoundingRectangleInvalidInput() {
        List<Point> twoPoints = Arrays.asList(
            new Point(0, 0),
            new Point(1, 1)
        );
        assertThrows(IllegalArgumentException.class, () -> RotatingCalipers.minimumBoundingRectangle(twoPoints));
    }

    @Test
    void testComplexPolygon() {
        // Hexagon
        List<Point> hexagon = Arrays.asList(
            new Point(2, 0),
            new Point(4, 1),
            new Point(4, 3),
            new Point(2, 4),
            new Point(0, 3),
            new Point(0, 1)
        );
        
        RotatingCalipers.PointPair diameter = RotatingCalipers.diameter(hexagon);
        assertTrue(diameter.distance > 0);
        
        double width = RotatingCalipers.width(hexagon);
        assertTrue(width > 0);
        
        RotatingCalipers.Rectangle rect = RotatingCalipers.minimumBoundingRectangle(hexagon);
        assertTrue(rect.area > 0);
    }

    @Test
    void testPointPairToString() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(3, 4);
        RotatingCalipers.PointPair pair = new RotatingCalipers.PointPair(p1, p2);
        
        String expected = "PointPair{(0, 0), (3, 4), distance=5.00}";
        assertEquals(expected, pair.toString());
    }

    @Test
    void testRectangleToString() {
        Point[] vertices = {
            new Point(0, 0),
            new Point(2, 0),
            new Point(2, 2),
            new Point(0, 2)
        };
        RotatingCalipers.Rectangle rect = new RotatingCalipers.Rectangle(vertices);
        
        assertTrue(rect.toString().contains("Rectangle{area="));
    }

    @Test
    void testRectangleAreaCalculation() {
        Point[] vertices = {new Point(0, 0), new Point(1, 0), new Point(1, 1)};
        RotatingCalipers.Rectangle rect = new RotatingCalipers.Rectangle(vertices);
        assertEquals(0.0, rect.area, EPSILON);
    }

    @Test
    void testDegenerateCase() {
        List<Point> samePoints = Arrays.asList(
            new Point(1, 1),
            new Point(1, 1),
            new Point(1, 1)
        );
        
        RotatingCalipers.Rectangle rect = RotatingCalipers.minimumBoundingRectangle(samePoints);
        assertEquals(0.0, rect.area, EPSILON);
    }
}