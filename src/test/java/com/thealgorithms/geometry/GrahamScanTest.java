package com.thealgorithms.geometry;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GrahamScanTest {
    @Test
    void testGrahamScan() {
        Point[] points = {new Point(0, 3), new Point(1, 1), new Point(2, 2), new Point(4, 4), new Point(0, 0), new Point(1, 2), new Point(3, 1), new Point(3, 3)};
        String expectedResult = "[(0, 0), (3, 1), (4, 4), (0, 3)]";

        GrahamScan graham = new GrahamScan(points);
        assertEquals(expectedResult, graham.hull().toString());
    }
    @Test
    void testGrahamScanAllCollinearHorizontal() {
        // All points on a horizontal line: the hull is just the two extremes.
        Point[] points = {new Point(1, 3), new Point(6, 3), new Point(8, 3), new Point(5, 3)};
        String expectedResult = "[(1, 3), (8, 3)]";
        GrahamScan graham = new GrahamScan(points);
        assertEquals(expectedResult, graham.hull().toString());
    }

    @Test
    void testGrahamScanAllCollinearHorizontalWithDuplicates() {
        Point[] points = {new Point(3, 3), new Point(11, 3), new Point(5, 3), new Point(0, 3), new Point(2, 3), new Point(2, 3), new Point(4, 3), new Point(0, 3), new Point(5, 3)};
        String expectedResult = "[(0, 3), (11, 3)]";
        GrahamScan graham = new GrahamScan(points);
        assertEquals(expectedResult, graham.hull().toString());
    }

    @Test
    void testGrahamScanKeepsFarthestPointOnHorizontalRay() {
        // (6,0) and (9,0) are collinear with the anchor (1,0) on a horizontal ray;
        // (9,0) is a hull vertex and must not be dropped.
        Point[] points = {new Point(10, 6), new Point(10, 8), new Point(4, 1), new Point(0, 9), new Point(8, 1), new Point(1, 0), new Point(7, 1), new Point(9, 0), new Point(4, 11), new Point(2, 5), new Point(7, 9), new Point(6, 0)};
        String expectedResult = "[(1, 0), (9, 0), (10, 6), (10, 8), (4, 11), (0, 9)]";
        GrahamScan graham = new GrahamScan(points);
        assertEquals(expectedResult, graham.hull().toString());
    }
}
