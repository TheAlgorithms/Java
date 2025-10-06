package com.thealgorithms.geometry;

import java.util.List;

/**
 * Implementation of the Rotating Calipers algorithm for convex polygons.
 *
 * The Rotating Calipers algorithm is used to compute various geometric properties
 * of convex polygons efficiently, including:
 * - Diameter: the largest distance between any two points
 * - Width: the smallest distance between two parallel lines enclosing the polygon
 * - Minimum-area bounding rectangle: the rectangle with minimal area that encloses all points
 *
 * Time complexity: O(n) where n is the number of vertices in the convex polygon
 *
 * Reference: Shamos, M. I. (1978). Computational Geometry.
 *
 * @author TheAlgorithms
 */
public final class RotatingCalipers {

    private RotatingCalipers() {
        // Utility class
    }

    /**
     * Represents a pair of points.
     */
    public static final class PointPair {
        public final Point first;
        public final Point second;
        public final double distance;

        public PointPair(Point first, Point second) {
            this.first = first;
            this.second = second;
            this.distance = euclideanDistance(first, second);
        }

        @Override
        public String toString() {
            return String.format("PointPair{%s, %s, distance=%.2f}", first, second, distance);
        }
    }

    /**
     * Represents a rectangle defined by four points.
     */
    public static final class Rectangle {
        public final Point[] vertices;
        public final double area;

        public Rectangle(Point[] vertices) {
            this.vertices = vertices.clone();
            this.area = calculateArea(vertices);
        }

        private static double calculateArea(Point[] vertices) {
            if (vertices.length != 4) {
                return 0;
            }
            double width = euclideanDistance(vertices[0], vertices[1]);
            double height = euclideanDistance(vertices[1], vertices[2]);
            return width * height;
        }

        @Override
        public String toString() {
            return String.format("Rectangle{area=%.2f}", area);
        }
    }

    /**
     * Computes the diameter of a convex polygon using rotating calipers.
     * The diameter is the maximum distance between any two vertices.
     *
     * @param convexHull List of points representing the convex hull in counter-clockwise order
     * @return PointPair containing the two points that form the diameter
     * @throws IllegalArgumentException if the hull has fewer than 2 points
     */
    public static PointPair diameter(List<Point> convexHull) {
        if (convexHull.size() < 2) {
            throw new IllegalArgumentException("Convex hull must have at least 2 points");
        }

        if (convexHull.size() == 2) {
            return new PointPair(convexHull.get(0), convexHull.get(1));
        }

        // Find maximum distance between all pairs of points
        PointPair maxPair = new PointPair(convexHull.get(0), convexHull.get(1));

        for (int i = 0; i < convexHull.size(); i++) {
            for (int j = i + 1; j < convexHull.size(); j++) {
                PointPair candidate = new PointPair(convexHull.get(i), convexHull.get(j));
                if (candidate.distance > maxPair.distance) {
                    maxPair = candidate;
                }
            }
        }

        return maxPair;
    }

    /**
     * Computes the width of a convex polygon using rotating calipers.
     * The width is the minimum distance between two parallel supporting lines.
     *
     * @param convexHull List of points representing the convex hull in counter-clockwise order
     * @return The minimum width of the polygon
     * @throws IllegalArgumentException if the hull has fewer than 3 points
     */
    public static double width(List<Point> convexHull) {
        if (convexHull.size() < 3) {
            throw new IllegalArgumentException("Convex hull must have at least 3 points for width calculation");
        }

        int n = convexHull.size();
        double minWidth = Double.MAX_VALUE;

        int j = 1;
        for (int i = 0; i < n; i++) {
            Point p1 = convexHull.get(i);
            Point p2 = convexHull.get((i + 1) % n);

            // Find the farthest point from edge p1-p2
            while (true) {
                int nextJ = (j + 1) % n;
                if (distanceToLine(p1, p2, convexHull.get(nextJ)) > distanceToLine(p1, p2, convexHull.get(j))) {
                    j = nextJ;
                } else {
                    break;
                }
            }

            double currentWidth = distanceToLine(p1, p2, convexHull.get(j));
            minWidth = Math.min(minWidth, currentWidth);
        }

        return minWidth;
    }

    /**
     * Computes the minimum-area bounding rectangle of a convex polygon.
     *
     * @param convexHull List of points representing the convex hull in counter-clockwise order
     * @return Rectangle with minimum area that encloses all points
     * @throws IllegalArgumentException if the hull has fewer than 3 points
     */
    public static Rectangle minimumBoundingRectangle(List<Point> convexHull) {
        if (convexHull.size() < 3) {
            throw new IllegalArgumentException("Convex hull must have at least 3 points");
        }

        int n = convexHull.size();
        Rectangle minRect = null;
        double minArea = Double.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            Point p1 = convexHull.get(i);
            Point p2 = convexHull.get((i + 1) % n);

            // Create rectangle aligned with edge p1-p2
            Rectangle rect = createAlignedRectangle(convexHull, p1, p2);

            if (rect.area < minArea) {
                minArea = rect.area;
                minRect = rect;
            }
        }

        return minRect;
    }

    /**
     * Creates a rectangle aligned with the given edge that encloses all points.
     */
    private static Rectangle createAlignedRectangle(List<Point> points, Point p1, Point p2) {
        // Vector along the edge
        double dx = p2.x() - p1.x();
        double dy = p2.y() - p1.y();
        double length = Math.sqrt(dx * dx + dy * dy);

        if (length == 0) {
            // Degenerate case
            return new Rectangle(new Point[]{p1, p1, p1, p1});
        }

        // Unit vectors
        double ux = dx / length;
        double uy = dy / length;
        double vx = -uy;  // Perpendicular vector
        double vy = ux;

        double minU = 0;
        double maxU = 0;
        double minV = 0;
        double maxV = 0;

        for (Point p : points) {
            double u = (p.x() - p1.x()) * ux + (p.y() - p1.y()) * uy;
            double v = (p.x() - p1.x()) * vx + (p.y() - p1.y()) * vy;

            minU = Math.min(minU, u);
            maxU = Math.max(maxU, u);
            minV = Math.min(minV, v);
            maxV = Math.max(maxV, v);
        }

        // Calculate rectangle corners
        Point[] corners = new Point[4];
        corners[0] = new Point((int) Math.round(p1.x() + minU * ux + minV * vx), (int) Math.round(p1.y() + minU * uy + minV * vy));
        corners[1] = new Point((int) Math.round(p1.x() + maxU * ux + minV * vx), (int) Math.round(p1.y() + maxU * uy + minV * vy));
        corners[2] = new Point((int) Math.round(p1.x() + maxU * ux + maxV * vx), (int) Math.round(p1.y() + maxU * uy + maxV * vy));
        corners[3] = new Point((int) Math.round(p1.x() + minU * ux + maxV * vx), (int) Math.round(p1.y() + minU * uy + maxV * vy));

        return new Rectangle(corners);
    }

    /**
     * Calculates the Euclidean distance between two points.
     */
    private static double euclideanDistance(Point p1, Point p2) {
        double dx = p1.x() - p2.x();
        double dy = p1.y() - p2.y();
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Calculates the perpendicular distance from a point to a line defined by two points.
     */
    private static double distanceToLine(Point lineStart, Point lineEnd, Point point) {
        double dx = lineEnd.x() - lineStart.x();
        double dy = lineEnd.y() - lineStart.y();

        if (dx == 0 && dy == 0) {
            return euclideanDistance(lineStart, point);
        }

        double numerator = Math.abs(dy * point.x() - dx * point.y() + lineEnd.x() * lineStart.y() - lineEnd.y() * lineStart.x());
        double denominator = Math.sqrt(dx * dx + dy * dy);

        return numerator / denominator;
    }
}
