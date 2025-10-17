package com.thealgorithms.geometry;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * RotatingCalipers - utility class for convex polygon computations:
 * - diameter (farthest pair)
 * - width (minimum distance between two parallel supporting lines)
 * - minimum-area bounding rectangle (simple implementation)
 */
public final class RotatingCalipers {

    private RotatingCalipers() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static final class Point {
        public final double x;
        public final double y;
        public Point(double x, double y) { this.x = x; this.y = y; }
    }

    public static final class PointPair {
        public final Point a;
        public final Point b;
        public PointPair(Point a, Point b) { this.a = a; this.b = b; }
        public double distance() {
            double dx = a.x - b.x, dy = a.y - b.y;
            return Math.hypot(dx, dy);
        }
    }

    public static final class Rectangle {
        public final Point center;
        public final double width;
        public final double height;
        public final double angle;
        public Rectangle(Point center, double width, double height, double angle) {
            this.center = center; this.width = width; this.height = height; this.angle = angle;
        }
    }

    private static double cross(Point o, Point a, Point b) {
        return (a.x - o.x) * (b.y - o.y) - (a.y - o.y) * (b.x - o.x);
    }
    private static double dist2(Point a, Point b) {
        double dx = a.x - b.x, dy = a.y - b.y; return dx*dx + dy*dy;
    }

    public static List<Point> convexHull(List<Point> pts) {
        List<Point> p = new ArrayList<>(pts);
        p.sort(Comparator.comparingDouble((Point q) -> q.x).thenComparingDouble(q -> q.y));
        int n = p.size();
        if (n <= 1) return new ArrayList<>(p);
        List<Point> lower = new ArrayList<>();
        for (Point pt : p) {
            while (lower.size() >= 2 && cross(lower.get(lower.size()-2), lower.get(lower.size()-1), pt) <= 0) {
                lower.remove(lower.size()-1);
            }
            lower.add(pt);
        }
        List<Point> upper = new ArrayList<>();
        for (int i = n-1; i >= 0; --i) {
            Point pt = p.get(i);
            while (upper.size() >= 2 && cross(upper.get(upper.size()-2), upper.get(upper.size()-1), pt) <= 0) {
                upper.remove(upper.size()-1);
            }
            upper.add(pt);
        }
        lower.remove(lower.size()-1);
        upper.remove(upper.size()-1);
        lower.addAll(upper);
        return lower;
    }

    public static PointPair diameter(List<Point> points) {
        List<Point> ch = convexHull(points);
        int n = ch.size();
        if (n == 0) return new PointPair(null, null);
        if (n == 1) return new PointPair(ch.get(0), ch.get(0));
        if (n == 2) return new PointPair(ch.get(0), ch.get(1));

        int j = 1;
        double best = 0;
        Point bestA = ch.get(0), bestB = ch.get(0);
        for (int i = 0; i < n; ++i) {
            int ni = (i + 1) % n;
            while (Math.abs(cross(ch.get(i), ch.get(ni), ch.get((j + 1) % n)))
                    > Math.abs(cross(ch.get(i), ch.get(ni), ch.get(j)))) {
                j = (j + 1) % n;
            }
            double d2 = dist2(ch.get(i), ch.get(j));
            if (d2 > best) { best = d2; bestA = ch.get(i); bestB = ch.get(j); }
            d2 = dist2(ch.get(ni), ch.get(j));
            if (d2 > best) { best = d2; bestA = ch.get(ni); bestB = ch.get(j); }
        }
        return new PointPair(bestA, bestB);
    }

    public static double width(List<Point> points) {
        List<Point> ch = convexHull(points);
        int n = ch.size();
        if (n <= 1) return 0.0;
        if (n == 2) return Math.hypot(ch.get(1).x - ch.get(0).x, ch.get(1).y - ch.get(0).y);

        int j = 1;
        double minWidth = Double.POSITIVE_INFINITY;
        for (int i = 0; i < n; ++i) {
            int ni = (i + 1) % n;
            while (Math.abs(cross(ch.get(i), ch.get(ni), ch.get((j + 1) % n)))
                    > Math.abs(cross(ch.get(i), ch.get(ni), ch.get(j)))) {
                j = (j + 1) % n;
            }
            double distance = Math.abs(cross(ch.get(i), ch.get(ni), ch.get(j)))
                    / Math.hypot(ch.get(ni).x - ch.get(i).x, ch.get(ni).y - ch.get(i).y);
            minWidth = Math.min(minWidth, distance);
        }
        return minWidth;
    }

    public static Rectangle minAreaBoundingRectangle(List<Point> points) {
        List<Point> ch = convexHull(points);
        int n = ch.size();
        if (n == 0) return null;
        if (n == 1) return new Rectangle(ch.get(0), 0.0, 0.0, 0.0);
        if (n == 2) {
            Point a = ch.get(0), b = ch.get(1);
            double w = Math.hypot(b.x - a.x, b.y - a.y);
            Point center = new Point((a.x + b.x) / 2.0, (a.y + b.y) / 2.0);
            double angle = Math.atan2(b.y - a.y, b.x - a.x);
            return new Rectangle(center, w, 0.0, angle);
        }

        double bestArea = Double.POSITIVE_INFINITY;
        Rectangle bestRect = null;
        for (int i = 0; i < n; ++i) {
            Point a = ch.get(i);
            Point b = ch.get((i + 1) % n);
            double dx = b.x - a.x, dy = b.y - a.y;
            double len = Math.hypot(dx, dy);
            double ux = dx / len, uy = dy / len;
            double vx = -uy, vy = ux;
            double minU = Double.POSITIVE_INFINITY, maxU = -Double.POSITIVE_INFINITY;
            double minV = Double.POSITIVE_INFINITY, maxV = -Double.POSITIVE_INFINITY;
            for (Point p : ch) {
                double u = (p.x - a.x) * ux + (p.y - a.y) * uy;
                double v = (p.x - a.x) * vx + (p.y - a.y) * vy;
                minU = Math.min(minU, u); maxU = Math.max(maxU, u);
                minV = Math.min(minV, v); maxV = Math.max(maxV, v);
            }
            double width = maxU - minU;
            double height = maxV - minV;
            double area = width * height;
            if (area < bestArea) {
                bestArea = area;
                double centerU = (minU + maxU) / 2.0;
                double centerV = (minV + maxV) / 2.0;
                Point center = new Point(a.x + centerU * ux + centerV * vx,
                        a.y + centerU * uy + centerV * vy);
                double angle = Math.atan2(uy, ux);
                bestRect = new Rectangle(center, width, height, angle);
            }
        }
        return bestRect;
    }
}