package com.thealgorithms.geometry;

import java.util.*;

/**
 * Delaunay Triangulation using the Bowyer–Watson algorithm.
 * 
 * Delaunay triangulation ensures that no point lies inside the circumcircle
 * of any triangle in the triangulation.
 *
 * Time Complexity: O(n^2) average
 * 
 * Reference: https://en.wikipedia.org/wiki/Bowyer%E2%80%93Watson_algorithm
 */
public final class DelaunayTriangulation {

    private DelaunayTriangulation() {}

    public static List<Triangle> triangulate(List<Point> points) {
        List<Triangle> triangles = new ArrayList<>();

        // 1. Create super triangle large enough to encompass all points
        double minX = points.stream().mapToDouble(p -> p.x).min().orElse(0);
        double minY = points.stream().mapToDouble(p -> p.y).min().orElse(0);
        double maxX = points.stream().mapToDouble(p -> p.x).max().orElse(0);
        double maxY = points.stream().mapToDouble(p -> p.y).max().orElse(0);

        double dx = maxX - minX;
        double dy = maxY - minY;
        double deltaMax = Math.max(dx, dy);
        double midx = (minX + maxX) / 2;
        double midy = (minY + maxY) / 2;

        Point p1 = new Point(midx - 20 * deltaMax, midy - deltaMax);
        Point p2 = new Point(midx, midy + 20 * deltaMax);
        Point p3 = new Point(midx + 20 * deltaMax, midy - deltaMax);
        Triangle superTriangle = new Triangle(p1, p2, p3);
        triangles.add(superTriangle);

        // 2. Add points one by one
        for (Point p : points) {
            List<Triangle> badTriangles = new ArrayList<>();

            for (Triangle t : triangles) {
                if (t.isPointInsideCircumcircle(p)) {
                    badTriangles.add(t);
                }
            }

            List<Edge> polygon = new ArrayList<>();
            for (Triangle t : badTriangles) {
                for (Edge e : t.getEdges()) {
                    boolean shared = false;
                    for (Triangle t2 : badTriangles) {
                        if (t2 != t && t2.hasEdge(e)) {
                            shared = true;
                            break;
                        }
                    }
                    if (!shared) polygon.add(e);
                }
            }

            triangles.removeAll(badTriangles);
            for (Edge e : polygon) {
                triangles.add(new Triangle(e.p1, e.p2, p));
            }
        }

        // 3. Remove triangles that share vertices with super triangle
        triangles.removeIf(t -> t.hasVertex(p1) || t.hasVertex(p2) || t.hasVertex(p3));
        return triangles;
    }

    /** Helper record for representing an Edge. */
    public record Edge(Point p1, Point p2) {}

    /** Helper class for representing a Triangle. */
    public static class Triangle {
        final Point a, b, c;

        public Triangle(Point a, Point b, Point c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public boolean hasVertex(Point p) {
            return p.equals(a) || p.equals(b) || p.equals(c);
        }

        public boolean hasEdge(Edge e) {
            return hasVertex(e.p1) && hasVertex(e.p2);
        }

        public List<Edge> getEdges() {
            return List.of(new Edge(a, b), new Edge(b, c), new Edge(c, a));
        }

        public boolean isPointInsideCircumcircle(Point p) {
            double ax = a.x - p.x, ay = a.y - p.y;
            double bx = b.x - p.x, by = b.y - p.y;
            double cx = c.x - p.x, cy = c.y - p.y;
            double det = (ax * ax + ay * ay) * (bx * cy - by * cx)
                       - (bx * bx + by * by) * (ax * cy - ay * cx)
                       + (cx * cx + cy * cy) * (ax * by - ay * bx);
            return det > 0;
        }
    }
}
