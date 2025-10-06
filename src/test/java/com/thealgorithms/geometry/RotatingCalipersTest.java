package com.thealgorithms.geometry;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class RotatingCalipersTest {

    @Test
    void testDiameterSimpleTriangle() {
        List<Point> convexHull = Arrays.asList(new Point(0, 0), new Point(4, 0), new Point(2, 3));
        RotatingCalipers.PointPair result = RotatingCalipers.diameter(convexHull);

        assertNotNull(result);
        assertEquals(4.0, result.distance(), 0.001);
    }

    @Test
    void testDiameterSquare() {
        List<Point> convexHull = Arrays.asList(new Point(0, 0), new Point(3, 0), new Point(3, 3), new Point(0, 3));
        RotatingCalipers.PointPair result = RotatingCalipers.diameter(convexHull);

        assertNotNull(result);
        assertEquals(Math.sqrt(18), result.distance(), 0.001);
    }

    @Test
    void testWidthSimpleTriangle() {
        List<Point> convexHull = Arrays.asList(new Point(0, 0), new Point(4, 0), new Point(2, 3));
        double result = RotatingCalipers.width(convexHull);

        // Updated expected width based on correct projection
        assertEquals(3, result, 0.1);
    }

    @Test
    void testMinAreaBoundingRectangleSquare() {
        List<Point> convexHull = Arrays.asList(new Point(0, 0), new Point(3, 0), new Point(3, 3), new Point(0, 3));
        RotatingCalipers.Rectangle result = RotatingCalipers.minAreaBoundingRectangle(convexHull);

        assertNotNull(result);
        assertEquals(9.0, result.area(), 0.1);
        assertEquals(3.0, result.width(), 0.1);
        assertEquals(3.0, result.height(), 0.1);

        // Check corners are PointD and not null
        for (RotatingCalipers.PointD corner : result.corners()) {
            assertNotNull(corner);
        }
    }

    @Test
    void testMinAreaBoundingRectangleTriangle() {
        List<Point> convexHull = Arrays.asList(new Point(0, 0), new Point(4, 0), new Point(2, 3));
        RotatingCalipers.Rectangle result = RotatingCalipers.minAreaBoundingRectangle(convexHull);

        assertNotNull(result);
        assertEquals(4, result.corners().length);
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
