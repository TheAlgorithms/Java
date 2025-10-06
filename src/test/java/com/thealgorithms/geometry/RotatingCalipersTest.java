package com.thealgorithms.geometry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class RotatingCalipersTest {

    @Test
    void testDiameterSimpleTriangle() {
        List<Point> convexHull = Arrays.asList(new Point(0, 0), new Point(4, 0), new Point(2, 3));
        RotatingCalipers.PointPair result = RotatingCalipers.diameter(convexHull);

        assertNotNull(result);
        assertEquals(5.0, result.distance(), 0.001);
    }

    @Test
    void testDiameterSquare() {
        List<Point> convexHull = Arrays.asList(new Point(0, 0), new Point(3, 0), new Point(3, 3), new Point(0, 3));
        RotatingCalipers.PointPair result = RotatingCalipers.diameter(convexHull);

        assertNotNull(result);
        assertEquals(Math.sqrt(18), result.distance(), 0.001);
    }

    @Test
    void testDiameterComplexPolygon() {
        List<Point> convexHull = Arrays.asList(new Point(0, 0), new Point(3, 0), new Point(3, 3), new Point(0, 3));
        RotatingCalipers.PointPair result = RotatingCalipers.diameter(convexHull);

        assertNotNull(result);
        assertEquals(Math.sqrt(18), result.distance(), 0.001);
    }

    @Test
    void testDiameterTwoPoints() {
        List<Point> convexHull = Arrays.asList(new Point(0, 0), new Point(5, 0));
        RotatingCalipers.PointPair result = RotatingCalipers.diameter(convexHull);

        assertNotNull(result);
        assertEquals(5.0, result.distance(), 0.001);
        assertEquals(new Point(0, 0), result.p1());
        assertEquals(new Point(5, 0), result.p2());
    }

    @Test
    void testDiameterInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> RotatingCalipers.diameter(null));
        assertThrows(IllegalArgumentException.class, () -> RotatingCalipers.diameter(Arrays.asList()));
        assertThrows(IllegalArgumentException.class, () -> RotatingCalipers.diameter(Arrays.asList(new Point(0, 0))));
    }

    @Test
    void testWidthSimpleTriangle() {
        List<Point> convexHull = Arrays.asList(new Point(0, 0), new Point(4, 0), new Point(2, 3));
        double result = RotatingCalipers.width(convexHull);

        assertEquals(2.4, result, 0.1);
    }

    @Test
    void testWidthSquare() {
        List<Point> convexHull = Arrays.asList(new Point(0, 0), new Point(3, 0), new Point(3, 3), new Point(0, 3));
        double result = RotatingCalipers.width(convexHull);

        assertEquals(3.0, result, 0.001);
    }

    @Test
    void testWidthRectangle() {
        List<Point> convexHull = Arrays.asList(new Point(0, 0), new Point(5, 0), new Point(5, 2), new Point(0, 2));
        double result = RotatingCalipers.width(convexHull);

        assertEquals(2.0, result, 0.001);
    }

    @Test
    void testWidthInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> RotatingCalipers.width(null));
        assertThrows(IllegalArgumentException.class, () -> RotatingCalipers.width(Arrays.asList()));
        assertThrows(IllegalArgumentException.class, () -> RotatingCalipers.width(Arrays.asList(new Point(0, 0), new Point(1, 1))));
    }

    @Test
    void testMinAreaBoundingRectangleSquare() {
        List<Point> convexHull = Arrays.asList(new Point(0, 0), new Point(3, 0), new Point(3, 3), new Point(0, 3));
        RotatingCalipers.Rectangle result = RotatingCalipers.minAreaBoundingRectangle(convexHull);

        assertNotNull(result);
        assertEquals(9.0, result.area(), 0.1);
        assertEquals(3.0, result.width(), 0.1);
        assertEquals(3.0, result.height(), 0.1);
    }

    @Test
    void testMinAreaBoundingRectangleTriangle() {
        List<Point> convexHull = Arrays.asList(new Point(0, 0), new Point(4, 0), new Point(2, 3));
        RotatingCalipers.Rectangle result = RotatingCalipers.minAreaBoundingRectangle(convexHull);

        assertNotNull(result);
        assertNotNull(result.corners());
        assertEquals(4, result.corners().length);
    }

    @Test
    void testMinAreaBoundingRectangleRectangle() {
        List<Point> convexHull = Arrays.asList(new Point(0, 0), new Point(5, 0), new Point(5, 2), new Point(0, 2));
        RotatingCalipers.Rectangle result = RotatingCalipers.minAreaBoundingRectangle(convexHull);

        assertNotNull(result);
        assertEquals(10.0, result.area(), 0.1);
    }

    @Test
    void testMinAreaBoundingRectangleInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> RotatingCalipers.minAreaBoundingRectangle(null));
        assertThrows(IllegalArgumentException.class, () -> RotatingCalipers.minAreaBoundingRectangle(Arrays.asList()));
        assertThrows(IllegalArgumentException.class, () -> RotatingCalipers.minAreaBoundingRectangle(Arrays.asList(new Point(0, 0), new Point(1, 1))));
    }

    @Test
    void testDiameterWithLargeConvexHull() {
        List<Point> convexHull = Arrays.asList(new Point(0, 0), new Point(3, 0), new Point(3, 3), new Point(0, 3), new Point(2, -4), new Point(1, -3));
        RotatingCalipers.PointPair result = RotatingCalipers.diameter(convexHull);

        assertNotNull(result);
    }

    @Test
    void testWidthWithLargeConvexHull() {
        List<Point> convexHull = Arrays.asList(new Point(0, 0), new Point(3, 0), new Point(3, 3), new Point(0, 3));
        double result = RotatingCalipers.width(convexHull);

        assertEquals(3.0, result, 0.001);
    }

    @Test
    void testMinAreaBoundingRectangleWithLargeConvexHull() {
        List<Point> convexHull = Arrays.asList(new Point(0, 0), new Point(10, 0), new Point(10, 5), new Point(0, 5));
        RotatingCalipers.Rectangle result = RotatingCalipers.minAreaBoundingRectangle(convexHull);

        assertNotNull(result);
        assertEquals(50.0, result.area(), 0.1);
    }
}
