package com.thealgorithms.geometry;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Comprehensive unit tests for {@link BentleyOttmann}.
 *
 * <p>This test suite validates the correctness of the Bentley–Ottmann algorithm
 * implementation by checking intersection points between multiple line segment configurations.</p>
 *
 * <p>Test cases include typical, edge, degenerate geometrical setups, and performance tests.</p>
 */
public class BentleyOttmannTest {

    private static final double EPS = 1e-6;

    @Test
    void testSingleIntersection() {
        List<BentleyOttmann.Segment> segments = List.of(newSegment(1, 1, 5, 5), newSegment(1, 5, 5, 1));

        Set<Point2D.Double> intersections = BentleyOttmann.findIntersections(segments);
        Assertions.assertEquals(1, intersections.size());
        Assertions.assertTrue(containsPoint(intersections, 3.0, 3.0));
    }

    @Test
    void testVerticalIntersection() {
        List<BentleyOttmann.Segment> segments = List.of(newSegment(3, 0, 3, 6), newSegment(1, 1, 5, 5));

        Set<Point2D.Double> intersections = BentleyOttmann.findIntersections(segments);
        Assertions.assertEquals(1, intersections.size());
        Assertions.assertTrue(containsPoint(intersections, 3.0, 3.0));
    }

    @Test
    void testNoIntersection() {
        List<BentleyOttmann.Segment> segments = List.of(newSegment(0, 0, 1, 1), newSegment(2, 2, 3, 3));

        Set<Point2D.Double> intersections = BentleyOttmann.findIntersections(segments);
        Assertions.assertTrue(intersections.isEmpty());
    }

    @Test
    void testCoincidentSegments() {
        List<BentleyOttmann.Segment> segments = List.of(newSegment(1, 1, 5, 5), newSegment(1, 1, 5, 5));

        Set<Point2D.Double> intersections = BentleyOttmann.findIntersections(segments);

        Assertions.assertEquals(2, intersections.size(), "Two identical segments should report 2 intersection points (both endpoints)");
        Assertions.assertTrue(containsPoint(intersections, 1.0, 1.0));
        Assertions.assertTrue(containsPoint(intersections, 5.0, 5.0));
    }

    @Test
    void testHorizontalIntersection() {
        List<BentleyOttmann.Segment> segments = List.of(newSegment(0, 2, 4, 2), newSegment(2, 0, 2, 4));

        Set<Point2D.Double> intersections = BentleyOttmann.findIntersections(segments);
        Assertions.assertTrue(containsPoint(intersections, 2.0, 2.0));
    }

    @Test
    void testEmptyList() {
        List<BentleyOttmann.Segment> segments = List.of();
        Set<Point2D.Double> intersections = BentleyOttmann.findIntersections(segments);
        Assertions.assertTrue(intersections.isEmpty());
    }

    @Test
    void testSingleSegment() {
        List<BentleyOttmann.Segment> segments = List.of(newSegment(0, 0, 5, 5));
        Set<Point2D.Double> intersections = BentleyOttmann.findIntersections(segments);
        Assertions.assertTrue(intersections.isEmpty());
    }

    @Test
    void testNullListThrowsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> BentleyOttmann.findIntersections(null));
    }

    @Test
    void testParallelSegments() {
        // Test 1: Parallel diagonal segments
        List<BentleyOttmann.Segment> diagonalSegments = List.of(newSegment(0, 0, 4, 4), newSegment(1, 0, 5, 4), newSegment(2, 0, 6, 4));
        Assertions.assertTrue(BentleyOttmann.findIntersections(diagonalSegments).isEmpty());

        // Test 2: Parallel vertical segments
        List<BentleyOttmann.Segment> verticalSegments = List.of(newSegment(1, 0, 1, 5), newSegment(2, 0, 2, 5), newSegment(3, 0, 3, 5));
        Assertions.assertTrue(BentleyOttmann.findIntersections(verticalSegments).isEmpty());

        // Test 3: Parallel horizontal segments
        List<BentleyOttmann.Segment> horizontalSegments = List.of(newSegment(0, 1, 5, 1), newSegment(0, 2, 5, 2), newSegment(0, 3, 5, 3));
        Assertions.assertTrue(BentleyOttmann.findIntersections(horizontalSegments).isEmpty());
    }

    @Test
    void testTouchingEndpoints() {
        List<BentleyOttmann.Segment> segments = List.of(newSegment(0, 0, 2, 2), newSegment(2, 2, 4, 0));

        Set<Point2D.Double> intersections = BentleyOttmann.findIntersections(segments);
        Assertions.assertEquals(1, intersections.size());
        Assertions.assertTrue(containsPoint(intersections, 2.0, 2.0));
    }

    @Test
    void testOverlappingCollinearSegments() {
        List<BentleyOttmann.Segment> segments = List.of(newSegment(0, 0, 4, 4), newSegment(2, 2, 6, 6));

        Set<Point2D.Double> intersections = BentleyOttmann.findIntersections(segments);
        // Overlapping collinear segments share the point (2,2) where second starts
        // and (4,4) where first ends - at least one should be detected
        Assertions.assertFalse(intersections.isEmpty(), "Should find at least one overlap point");
        Assertions.assertTrue(containsPoint(intersections, 2.0, 2.0) || containsPoint(intersections, 4.0, 4.0), "Should contain either (2,2) or (4,4)");
    }

    @Test
    void testMultipleSegmentsAtOnePoint() {
        // Star pattern: 4 segments meeting at (2, 2)
        List<BentleyOttmann.Segment> segments = List.of(newSegment(0, 2, 4, 2), // horizontal
            newSegment(2, 0, 2, 4), // vertical
            newSegment(0, 0, 4, 4), // diagonal /
            newSegment(0, 4, 4, 0) // diagonal \
        );

        Set<Point2D.Double> intersections = BentleyOttmann.findIntersections(segments);
        Assertions.assertTrue(containsPoint(intersections, 2.0, 2.0));
        // All segments meet at (2, 2), so should be reported once
        Assertions.assertEquals(1, intersections.size());
    }

    @Test
    void testGridPattern() {
        // 3x3 grid: should have 9 intersection points
        List<BentleyOttmann.Segment> segments = new ArrayList<>();

        // Vertical lines at x = 0, 1, 2
        for (int i = 0; i <= 2; i++) {
            segments.add(newSegment(i, 0, i, 2));
        }

        // Horizontal lines at y = 0, 1, 2
        for (int i = 0; i <= 2; i++) {
            segments.add(newSegment(0, i, 2, i));
        }

        Set<Point2D.Double> intersections = BentleyOttmann.findIntersections(segments);

        // Each vertical line crosses each horizontal line
        // 3 vertical × 3 horizontal = 9 intersections
        Assertions.assertEquals(9, intersections.size(), "3x3 grid should have 9 intersections");

        // Verify all grid points are present
        for (int x = 0; x <= 2; x++) {
            for (int y = 0; y <= 2; y++) {
                Assertions.assertTrue(containsPoint(intersections, x, y), String.format("Grid point (%d, %d) should be present", x, y));
            }
        }
    }

    @Test
    void testTriangleIntersections() {
        // Three segments forming a triangle
        List<BentleyOttmann.Segment> segments = List.of(newSegment(0, 0, 4, 0), // base
            newSegment(0, 0, 2, 3), // left side
            newSegment(4, 0, 2, 3) // right side
        );

        Set<Point2D.Double> intersections = BentleyOttmann.findIntersections(segments);
        // Triangle vertices are intersections
        Assertions.assertTrue(containsPoint(intersections, 0.0, 0.0));
        Assertions.assertTrue(containsPoint(intersections, 4.0, 0.0));
        Assertions.assertTrue(containsPoint(intersections, 2.0, 3.0));
        Assertions.assertEquals(3, intersections.size());
    }

    @Test
    void testCrossingDiagonals() {
        // X pattern with multiple crossings
        List<BentleyOttmann.Segment> segments = List.of(newSegment(0, 0, 10, 10), newSegment(0, 10, 10, 0), newSegment(5, 0, 5, 10), newSegment(0, 5, 10, 5));

        Set<Point2D.Double> intersections = BentleyOttmann.findIntersections(segments);
        Assertions.assertTrue(containsPoint(intersections, 5.0, 5.0), "Center point should be present");
        Assertions.assertEquals(1, intersections.size());
    }

    @Test
    void testVerySmallSegments() {
        List<BentleyOttmann.Segment> segments = List.of(newSegment(0.001, 0.001, 0.002, 0.002), newSegment(0.001, 0.002, 0.002, 0.001));

        Set<Point2D.Double> intersections = BentleyOttmann.findIntersections(segments);
        Assertions.assertEquals(1, intersections.size());
        Assertions.assertTrue(containsPoint(intersections, 0.0015, 0.0015));
    }

    @Test
    void testSegmentsShareCommonPoint() {
        List<BentleyOttmann.Segment> segmentsSameStart = List.of(newSegment(0, 0, 4, 4), newSegment(0, 0, 4, -4), newSegment(0, 0, -4, 4));

        Set<Point2D.Double> intersectionsSameStart = BentleyOttmann.findIntersections(segmentsSameStart);
        Assertions.assertTrue(containsPoint(intersectionsSameStart, 0.0, 0.0));
        List<BentleyOttmann.Segment> segmentsSameEnd = List.of(newSegment(0, 0, 4, 4), newSegment(8, 4, 4, 4), newSegment(4, 8, 4, 4));

        Set<Point2D.Double> intersectionsSameEnd = BentleyOttmann.findIntersections(segmentsSameEnd);
        Assertions.assertTrue(containsPoint(intersectionsSameEnd, 4.0, 4.0));
    }

    @Test
    void testSegmentsAtAngles() {
        // Segments at 45, 90, 135 degrees
        List<BentleyOttmann.Segment> segments = List.of(newSegment(0, 2, 4, 2), // horizontal
            newSegment(2, 0, 2, 4), // vertical
            newSegment(0, 0, 4, 4), // 45 degrees
            newSegment(0, 4, 4, 0) // 135 degrees
        );

        Set<Point2D.Double> intersections = BentleyOttmann.findIntersections(segments);
        Assertions.assertTrue(containsPoint(intersections, 2.0, 2.0));
    }

    @Test
    void testPerformanceWithManySegments() {
        // Generate 100 random segments
        Random random = new Random(42); // Fixed seed for reproducibility
        List<BentleyOttmann.Segment> segments = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            double x1 = random.nextDouble() * 100;
            double y1 = random.nextDouble() * 100;
            double x2 = random.nextDouble() * 100;
            double y2 = random.nextDouble() * 100;
            segments.add(newSegment(x1, y1, x2, y2));
        }

        long startTime = System.currentTimeMillis();
        Set<Point2D.Double> intersections = BentleyOttmann.findIntersections(segments);
        long endTime = System.currentTimeMillis();

        long duration = endTime - startTime;

        // Should complete in reasonable time (< 1 second for 100 segments)
        Assertions.assertTrue(duration < 1000, "Algorithm should complete in less than 1 second for 100 segments. Took: " + duration + "ms");

        // Just verify it returns a valid result
        Assertions.assertNotNull(intersections);
        System.out.println("Performance test: 100 segments processed in " + duration + "ms, found " + intersections.size() + " intersections");
    }

    @Test
    void testIssueExample() {
        // Example from the GitHub issue
        List<BentleyOttmann.Segment> segments = List.of(newSegment(1, 1, 5, 5), // Segment A
            newSegment(1, 5, 5, 1), // Segment B
            newSegment(3, 0, 3, 6) // Segment C
        );

        Set<Point2D.Double> intersections = BentleyOttmann.findIntersections(segments);

        // Expected output: [(3, 3)]
        Assertions.assertEquals(1, intersections.size(), "Should find exactly one intersection");
        Assertions.assertTrue(containsPoint(intersections, 3.0, 3.0), "Intersection should be at (3, 3)");
    }

    @Test
    void testEventTypeOrdering() {
        // Multiple events at the same point with different types
        List<BentleyOttmann.Segment> segments = List.of(newSegment(2, 2, 6, 2), // ends at (2,2)
            newSegment(0, 2, 2, 2), // ends at (2,2)
            newSegment(2, 2, 2, 6), // starts at (2,2)
            newSegment(2, 0, 2, 2) // ends at (2,2)
        );

        Set<Point2D.Double> intersections = BentleyOttmann.findIntersections(segments);
        Assertions.assertTrue(containsPoint(intersections, 2.0, 2.0));
    }

    @Test
    void testCollinearOverlapWithInteriorPoint() {
        // Test collinear segments where one segment's interior overlaps another
        List<BentleyOttmann.Segment> segments = List.of(newSegment(0, 0, 6, 6), newSegment(2, 2, 4, 4));
        Set<Point2D.Double> intersections = BentleyOttmann.findIntersections(segments);

        // Should find at least one overlap point (where segments touch/overlap)
        Assertions.assertFalse(intersections.isEmpty(), "Should find overlap points for collinear segments");
        Assertions.assertTrue(containsPoint(intersections, 2.0, 2.0) || containsPoint(intersections, 4.0, 4.0), "Should contain overlap boundary point");
    }

    @Test
    void testCollinearTouchingAtBothEndpoints() {
        // Test collinear segments that touch at both endpoints
        // This triggers the "endpoint of both" logic (line 354-355)
        List<BentleyOttmann.Segment> segments = List.of(newSegment(0, 0, 4, 4), newSegment(4, 4, 8, 8));

        Set<Point2D.Double> intersections = BentleyOttmann.findIntersections(segments);
        Assertions.assertEquals(1, intersections.size());
        Assertions.assertTrue(containsPoint(intersections, 4.0, 4.0), "Should find touching point");
    }

    @Test
    void testCollinearOverlapPartialInterior() {
        // Test case where segments overlap but one point is inside, one is endpoint
        List<BentleyOttmann.Segment> segments = List.of(newSegment(0, 0, 5, 5), newSegment(3, 3, 7, 7));

        Set<Point2D.Double> intersections = BentleyOttmann.findIntersections(segments);

        // Should detect the overlap region
        Assertions.assertFalse(intersections.isEmpty());
        // The algorithm should return at least one of the boundary points
        Assertions.assertTrue(containsPoint(intersections, 3.0, 3.0) || containsPoint(intersections, 5.0, 5.0));
    }

    private static BentleyOttmann.Segment newSegment(double x1, double y1, double x2, double y2) {
        return new BentleyOttmann.Segment(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2));
    }

    private static boolean containsPoint(Set<Point2D.Double> points, double x, double y) {
        return points.stream().anyMatch(p -> Math.abs(p.x - x) < EPS && Math.abs(p.y - y) < EPS);
    }
}
