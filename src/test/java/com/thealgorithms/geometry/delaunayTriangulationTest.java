package com.thealgorithms.geometry;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class DelaunayTriangulationTest {

    @Test
    void testBasicTriangulation() {
        List<Point> points = Arrays.asList(
            new Point(0, 0), new Point(1, 0),
            new Point(0, 1), new Point(1, 1)
        );

        List<DelaunayTriangulation.Triangle> triangles = DelaunayTriangulation.triangulate(points);
        assertFalse(triangles.isEmpty());
        assertTrue(triangles.size() >= 2); // At least 2 triangles for a square
    }
}
