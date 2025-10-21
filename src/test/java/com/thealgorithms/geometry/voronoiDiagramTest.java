package com.thealgorithms.geometry;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.geom.Line2D;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class VoronoiDiagramTest {

    @Test
    void testVoronoiEdges() {
        List<Point> points = Arrays.asList(
            new Point(0, 0), new Point(1, 0),
            new Point(0, 1)
        );

        List<Line2D.Double> edges = VoronoiDiagram.computeVoronoiEdges(points);
        assertFalse(edges.isEmpty());
        assertTrue(edges.size() > 0);
    }
}
