package com.thealgorithms.geometry;

import java.awt.geom.Line2D;
import java.util.*;

/**
 * A simplified Voronoi Diagram generator using perpendicular bisectors.
 * 
 * This implementation computes the Voronoi edges between each pair of sites
 * by finding their perpendicular bisectors (not a full Fortune’s algorithm).
 * 
 * Time Complexity: O(n^2)
 */
public final class VoronoiDiagram {

    private VoronoiDiagram() {}

    public static List<Line2D.Double> computeVoronoiEdges(List<Point> points) {
        List<Line2D.Double> edges = new ArrayList<>();

        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                Point p1 = points.get(i);
                Point p2 = points.get(j);
                Point mid = new Point((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);

                double dx = p2.x - p1.x;
                double dy = p2.y - p1.y;

                // Perpendicular slope
                double length = Math.sqrt(dx * dx + dy * dy);
                double ux = -dy / length;
                double uy = dx / length;

                // Extend line in both directions
                double scale = 1000;
                Point start = new Point(mid.x + ux * scale, mid.y + uy * scale);
                Point end = new Point(mid.x - ux * scale, mid.y - uy * scale);
                edges.add(new Line2D.Double(start.x, start.y, end.x, end.y));
            }
        }
        return edges;
    }
}
