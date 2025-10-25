package com.thealgorithms.geometry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * A class implementing the Rotating Calipers algorithm for geometric computations on convex polygons.
 *
 * The Rotating Calipers algorithm is an efficient technique for solving various geometric problems
 * on convex polygons, including:
 * - Computing the diameter (maximum distance between any two points)
 * - Computing the width (minimum distance between parallel supporting lines)
 * - Finding the minimum-area bounding rectangle
 *
 * Algorithm Description:
 * 1. Compute the convex hull of the given points
 * 2. Use rotating calipers (parallel lines) that rotate around the convex hull
 * 3. For each rotation, compute the desired geometric property
 * 4. Return the optimal result
 *
 * Time Complexity: O(n) where n is the number of points in the convex hull
 * Space Complexity: O(n) for storing the convex hull
 *
 * Reference:
 * Shamos, M. I. (1978). Computational Geometry.
 *
 * @author TheAlgorithms
 */
public final class RotatingCalipers {

    private RotatingCalipers() {
    }

    /**
     * Represents a pair of points with their distance.
     */
    public record PointPair(Point p1, Point p2, double distance) {
        @Override
        public String toString() {
            return String.format("PointPair(%s, %s, distance=%.2f)", p1, p2, distance);
        }
    }

    /**
     * Represents a rectangle with its area.
     */
    public record Rectangle(Point bottomLeft, Point topRight, double area) {
        @Override
        public String toString() {
            return String.format("Rectangle(%s, %s, area=%.2f)", bottomLeft, topRight, area);
        }
    }

    /**
     * Computes the diameter of a convex polygon using rotating calipers.
     * The diameter is the maximum distance between any two points of the polygon.
     *
     * @param points List of points representing a convex polygon
     * @return PointPair containing the two points with maximum distance and the distance
     * @throws IllegalArgumentException if points is null or has less than 2 points
     */
    public static PointPair computeDiameter(Collection<Point> points) {
        if (points == null || points.size() < 2) {
            throw new IllegalArgumentException("Points list must contain at least 2 points");
        }

        List<Point> hull = ConvexHull.convexHullRecursive(new ArrayList<>(points));
        if (hull.size() < 2) {
            throw new IllegalArgumentException("Convex hull must contain at least 2 points");
        }

        hull = ensureCounterClockwiseOrder(hull);

        if (hull.size() == 2) {
            Point p1 = hull.get(0);
            Point p2 = hull.get(1);
            return new PointPair(p1, p2, distance(p1, p2));
        }

        int n = hull.size();
        PointPair maxPair = null;
        double maxDistance = 0.0;

        int j = 1;
        // Rotating calipers algorithm requires indexed access for antipodal point tracking
        for (int i = 0; i < n; i++) {
            Point p1 = hull.get(i);

            // Find antipodal point for current vertex
            while (true) {
                Point next = hull.get((j + 1) % n);
                double dist1 = distance(p1, hull.get(j));
                double dist2 = distance(p1, next);

                if (dist2 > dist1) {
                    j = (j + 1) % n;
                } else {
                    break;
                }
            }

            double dist = distance(p1, hull.get(j));
            if (dist > maxDistance) {
                maxDistance = dist;
                maxPair = new PointPair(p1, hull.get(j), dist);
            }
        }

        return maxPair;
    }

    /**
     * Computes the width of a convex polygon using rotating calipers.
     * The width is the minimum distance between two parallel supporting lines.
     *
     * @param points List of points representing a convex polygon
     * @return The minimum width of the polygon
     * @throws IllegalArgumentException if points is null or has less than 2 points
     */
    public static double computeWidth(Collection<Point> points) {
        if (points == null || points.size() < 2) {
            throw new IllegalArgumentException("Points list must contain at least 2 points");
        }

        List<Point> hull = ConvexHull.convexHullRecursive(new ArrayList<>(points));
        if (hull.size() < 2) {
            throw new IllegalArgumentException("Convex hull must contain at least 2 points");
        }

        hull = ensureCounterClockwiseOrder(hull);

        if (hull.size() == 2) {
            return 0.0;
        }

        int n = hull.size();
        double minWidth = Double.MAX_VALUE;

        // Use rotating calipers to find minimum width
        for (int i = 0; i < n; i++) {
            Point p1 = hull.get(i);
            Point p2 = hull.get((i + 1) % n);

            // Find the antipodal point for this edge
            int j = findAntipodalPoint(hull, i);

            // Compute width as distance between parallel lines
            double width = distanceToLine(p1, p2, hull.get(j));
            minWidth = Math.min(minWidth, width);
        }

        return minWidth;
    }

    /**
     * Computes the minimum-area bounding rectangle of a convex polygon using rotating calipers.
     *
     * @param points List of points representing a convex polygon
     * @return Rectangle containing the minimum-area bounding rectangle
     * @throws IllegalArgumentException if points is null or has less than 2 points
     */
    public static Rectangle computeMinimumAreaBoundingRectangle(Collection<Point> points) {
        if (points == null || points.size() < 2) {
            throw new IllegalArgumentException("Points list must contain at least 2 points");
        }

        List<Point> hull = ConvexHull.convexHullRecursive(new ArrayList<>(points));
        if (hull.size() < 2) {
            throw new IllegalArgumentException("Convex hull must contain at least 2 points");
        }

        hull = ensureCounterClockwiseOrder(hull);

        if (hull.size() == 2) {
            Point p1 = hull.get(0);
            Point p2 = hull.get(1);
            return new Rectangle(p1, p2, 0.0);
        }

        int n = hull.size();
        double minArea = Double.MAX_VALUE;
        Rectangle bestRectangle = null;

        for (int i = 0; i < n; i++) {
            Point p1 = hull.get(i);
            Point p2 = hull.get((i + 1) % n);

            int j = findAntipodalPoint(hull, i);

            double edgeLength = distance(p1, p2);
            double height = distanceToLine(p1, p2, hull.get(j));

            double area = edgeLength * height;

            if (area < minArea) {
                minArea = area;
                Point bottomLeft = computeRectangleCorner(p1, p2, hull.get(j), true);
                Point topRight = computeRectangleCorner(p1, p2, hull.get(j), false);
                bestRectangle = new Rectangle(bottomLeft, topRight, area);
            }
        }

        return bestRectangle;
    }

    /**
     * Finds the antipodal point for a given edge using rotating calipers.
     */
    private static int findAntipodalPoint(List<Point> hull, int edgeStart) {
        int n = hull.size();
        int j = (edgeStart + 1) % n;

        Point p1 = hull.get(edgeStart);
        Point p2 = hull.get((edgeStart + 1) % n);

        while (true) {
            Point next = hull.get((j + 1) % n);
            double dist1 = distanceToLine(p1, p2, hull.get(j));
            double dist2 = distanceToLine(p1, p2, next);

            if (dist2 > dist1) {
                j = (j + 1) % n;
            } else {
                break;
            }
        }

        return j;
    }

    /**
     * Computes a corner of the bounding rectangle.
     */
    private static Point computeRectangleCorner(Point p1, Point p2, Point antipodal, boolean isBottomLeft) {
        int minX = Math.min(Math.min(p1.x(), p2.x()), antipodal.x());
        int maxX = Math.max(Math.max(p1.x(), p2.x()), antipodal.x());
        int minY = Math.min(Math.min(p1.y(), p2.y()), antipodal.y());
        int maxY = Math.max(Math.max(p1.y(), p2.y()), antipodal.y());

        if (isBottomLeft) {
            return new Point(minX, minY);
        } else {
            return new Point(maxX, maxY);
        }
    }

    /**
     * Computes the Euclidean distance between two points.
     */
    private static double distance(Point p1, Point p2) {
        int dx = p2.x() - p1.x();
        int dy = p2.y() - p1.y();
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Computes the perpendicular distance from a point to a line defined by two points.
     */
    private static double distanceToLine(Point lineStart, Point lineEnd, Point point) {
        int dx = lineEnd.x() - lineStart.x();
        int dy = lineEnd.y() - lineStart.y();

        if (dx == 0 && dy == 0) {
            return distance(lineStart, point);
        }

        int px = point.x() - lineStart.x();
        int py = point.y() - lineStart.y();

        double crossProduct = Math.abs(px * dy - py * dx);
        double lineLength = Math.sqrt(dx * dx + dy * dy);

        return crossProduct / lineLength;
    }

    /**
     * Ensures the hull points are in counter-clockwise order for rotating calipers.
     * The convex hull algorithm returns points sorted by natural order, but rotating calipers
     * requires counter-clockwise ordering.
     */
    private static List<Point> ensureCounterClockwiseOrder(List<Point> hull) {
        if (hull.size() <= 2) {
            return hull;
        }

        // Find bottommost point (lowest y, then leftmost x)
        Point bottomMost = hull.get(0);
        int bottomIndex = 0;

        for (int i = 1; i < hull.size(); i++) {
            Point current = hull.get(i);
            // Check if current point is better than current best
            boolean isBetter = current.y() < bottomMost.y()
                || (current.y() == bottomMost.y() && current.x() < bottomMost.x());

            if (isBetter) {
                bottomMost = current;
                bottomIndex = i;
            }
        }

        List<Point> orderedHull = new ArrayList<>();
        for (int i = 0; i < hull.size(); i++) {
            orderedHull.add(hull.get((bottomIndex + i) % hull.size()));
        }

        if (orderedHull.size() >= 3) {
            Point p1 = orderedHull.get(0);
            Point p2 = orderedHull.get(1);
            Point p3 = orderedHull.get(2);

            if (Point.orientation(p1, p2, p3) < 0) {
                Collections.reverse(orderedHull);
                Collections.rotate(orderedHull, 1);
            }
        }

        return orderedHull;
    }
}
