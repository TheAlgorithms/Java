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
}
