package com.thealgorithms.geometry;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GrahamScanTest {
    @Test
    void testGrahamScan() {
        GrahamScan.Point[] points = {new GrahamScan.Point(0, 3), new GrahamScan.Point(1, 1), new GrahamScan.Point(2, 2), new GrahamScan.Point(4, 4), new GrahamScan.Point(0, 0), new GrahamScan.Point(1, 2), new GrahamScan.Point(3, 1), new GrahamScan.Point(3, 3)};
        String expectedResult = "[(0, 0), (3, 1), (4, 4), (0, 3)]";

        GrahamScan graham = new GrahamScan(points);
        assertEquals(expectedResult, graham.hull().toString());
    }
}