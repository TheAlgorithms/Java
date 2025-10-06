package com.thealgorithms.geometry;

import java.util.Collections;
import java.util.List;

/**
 * Rotating Calipers algorithm to compute:
 * - Diameter of a convex polygon
 * - Width of a convex polygon
 * - Minimum-area bounding rectangle
 */
public final class RotatingCalipers {

    private RotatingCalipers() {
    }

    // -------------------- Inner Classes --------------------
    public static record PointPair(Point p1, Point p2, double distance) {
    }

    public static record Rectangle(Point[] corners, double width, double height, double area) {
    }

    // -------------------- Diameter --------------------
    public static PointPair diameter(List<Point> points) {
        if (points == null || points.size() < 2) {
            throw new IllegalArgumentException("At least two points required for diameter");
        }

        List<Point> hull = ConvexHull.convexHullRecursive(points);
        orderCounterClockwise(hull);
        int n = hull.size();

        double maxDist = 0;
        Point bestA = hull.get(0);
        Point bestB = hull.get(0);

        int j = 1;
        for (int i = 0; i < n; i++) {
            Point a = hull.get(i);
            while (true) {
                Point b1 = hull.get(j);
                Point b2 = hull.get((j + 1) % n);
                double d1 = distanceSquared(a, b1);
                double d2 = distanceSquared(a, b2);
                if (d2 > d1) {
                    j = (j + 1) % n;
                } else {
                    break;
                }
            }
            double d = distanceSquared(a, hull.get(j));
            if (d > maxDist) {
                maxDist = d;
                bestA = a;
                bestB = hull.get(j);
            }
        }
        return new PointPair(bestA, bestB, Math.sqrt(maxDist));
    }

    // -------------------- Width --------------------
    public static double width(List<Point> points) {
        if (points == null || points.size() < 3) {
            throw new IllegalArgumentException("At least three points required for width");
        }

        List<Point> hull = ConvexHull.convexHullRecursive(points);
        orderCounterClockwise(hull);
        int n = hull.size();

        double minWidth = Double.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            Point a = hull.get(i);
            Point b = hull.get((i + 1) % n);

            double ux = b.x() - a.x();
            double uy = b.y() - a.y();
            double len = Math.hypot(ux, uy);
            ux /= len;
            uy /= len;

            double vx = -uy;
            double vy = ux;

            double minProjV = Double.MAX_VALUE;
            double maxProjV = -Double.MAX_VALUE;

            for (Point p : hull) {
                double projV = p.x() * vx + p.y() * vy;
                minProjV = Math.min(minProjV, projV);
                maxProjV = Math.max(maxProjV, projV);
            }
            minWidth = Math.min(minWidth, maxProjV - minProjV);
        }
        return minWidth;
    }

    // -------------------- Minimum-Area Bounding Rectangle --------------------
    public static Rectangle minAreaBoundingRectangle(List<Point> points) {
        if (points == null || points.size() < 3) {
            throw new IllegalArgumentException("At least three points required");
        }

        List<Point> hull = ConvexHull.convexHullRecursive(points);
        orderCounterClockwise(hull);
        int n = hull.size();

        double minArea = Double.MAX_VALUE;
        Point[] bestCorners = null;
        double bestWidth = 0;
        double bestHeight = 0;

        for (int i = 0; i < n; i++) {
            Point a = hull.get(i);
            Point b = hull.get((i + 1) % n);

            double edgeDx = b.x() - a.x();
            double edgeDy = b.y() - a.y();
            double edgeLen = Math.hypot(edgeDx, edgeDy);
            double ux = edgeDx / edgeLen;
            double uy = edgeDy / edgeLen;
            double vx = -uy;
            double vy = ux;

            double minU = Double.MAX_VALUE;
            double maxU = -Double.MAX_VALUE;
            double minV = Double.MAX_VALUE;
            double maxV = -Double.MAX_VALUE;

            for (Point p : hull) {
                double projU = p.x() * ux + p.y() * uy;
                double projV = p.x() * vx + p.y() * vy;
                minU = Math.min(minU, projU);
                maxU = Math.max(maxU, projU);
                minV = Math.min(minV, projV);
                maxV = Math.max(maxV, projV);
            }

            double width = maxU - minU;
            double height = maxV - minV;
            double area = width * height;

            if (area < minArea) {
                minArea = area;
                bestWidth = width;
                bestHeight = height;
                bestCorners = new Point[] {new Point((int) (ux * minU + vx * minV), (int) (uy * minU + vy * minV)), new Point((int) (ux * maxU + vx * minV), (int) (uy * maxU + vy * minV)), new Point((int) (ux * maxU + vx * maxV), (int) (uy * maxU + vy * maxV)),
                    new Point((int) (ux * minU + vx * maxV), (int) (uy * minU + vy * maxV))};
            }
        }

        return new Rectangle(bestCorners, bestWidth, bestHeight, minArea);
    }

    // -------------------- Helper Methods --------------------
    private static void orderCounterClockwise(List<Point> points) {
        double area = 0.0;
        for (int i = 0; i < points.size(); i++) {
            Point a = points.get(i);
            Point b = points.get((i + 1) % points.size());
            area += (a.x() * b.y()) - (b.x() * a.y());
        }
        if (area < 0) {
            Collections.reverse(points);
        }
    }

    private static double distanceSquared(Point a, Point b) {
        double dx = a.x() - b.x();
        double dy = a.y() - b.y();
        return dx * dx + dy * dy;
    }
}
