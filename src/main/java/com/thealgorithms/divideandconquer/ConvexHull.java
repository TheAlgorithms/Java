package com.thealgorithms.divideandconquer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

/**
 * A class representing points on a 2D plane and providing algorithms to compute
 * the convex hull of a set of points using brute-force and recursive methods.
 *
 * Convex hull: The smallest convex polygon that contains all the given points.
 *
 * Algorithms provided:
 * 1. Brute-Force Method
 * 2. Recursive (Divide-and-Conquer) Method
 * 3. Graham Scan method
 *
 * @author Hardvan
 */
public final class ConvexHull {
    private ConvexHull() {
    }

    public static class Point implements Comparable<Point> {
        final int x;
        final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point other) {
            int cmpY = Integer.compare(this.y, other.y);
            return cmpY != 0 ? cmpY : Integer.compare(this.x, other.x);
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Point)) {
                return false;
            }
            Point other = (Point) obj;
            return this.x == other.x && this.y == other.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return String.format("(%d, %d)", x, y);
        }

        public Comparator<Point> polarOrder() {
            return new PolarOrder();
        }

        private final class PolarOrder implements Comparator<Point> {
            @Override
            public int compare(Point p1, Point p2) {
                int dx1 = p1.x - x;
                int dy1 = p1.y - y;
                int dx2 = p2.x - x;
                int dy2 = p2.y - y;

                if (dy1 >= 0 && dy2 < 0) {
                    return -1; // p1 above p2
                } else if (dy2 >= 0 && dy1 < 0) {
                    return 1; // p1 below p2
                } else if (dy1 == 0 && dy2 == 0) { // Collinear and horizontal
                    return Integer.compare(dx2, dx1);
                } else {
                    return -orientation(Point.this, p1, p2); // Compare orientation
                }
            }
        }
    }

    private static int orientation(Point a, Point b, Point c) {
        return Integer.compare((b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x), 0);
    }

    private static boolean checkPointOrientation(Point i, Point j, Point k) {
        int detK = orientation(i, j, k);
        if (detK > 0) {
            return true; // pointsLeftOfIJ
        } else if (detK < 0) {
            return false; // pointsRightOfIJ
        } else {
            return k.compareTo(i) >= 0 && k.compareTo(j) <= 0;
        }
    }

    public static List<Point> convexHullBruteForce(List<Point> points) {
        TreeSet<Point> convexSet = new TreeSet<>();

        for (int i = 0; i < points.size() - 1; i++) {
            for (int j = i + 1; j < points.size(); j++) {
                boolean allPointsOnOneSide = true;
                boolean leftSide = checkPointOrientation(points.get(i), points.get(j), points.get((i + 1) % points.size()));

                for (int k = 0; k < points.size(); k++) {
                    if (k != i && k != j) {
                        if (checkPointOrientation(points.get(i), points.get(j), points.get(k)) != leftSide) {
                            allPointsOnOneSide = false;
                            break;
                        }
                    }
                }

                if (allPointsOnOneSide) {
                    convexSet.add(points.get(i));
                    convexSet.add(points.get(j));
                }
            }
        }

        return new ArrayList<>(convexSet);
    }

    public static List<Point> convexHullRecursive(List<Point> points) {
        Collections.sort(points);
        Set<Point> convexSet = new HashSet<>();
        Point leftMostPoint = points.get(0);
        Point rightMostPoint = points.get(points.size() - 1);

        convexSet.add(leftMostPoint);
        convexSet.add(rightMostPoint);

        List<Point> upperHull = new ArrayList<>();
        List<Point> lowerHull = new ArrayList<>();

        for (int i = 1; i < points.size() - 1; i++) {
            int det = orientation(leftMostPoint, rightMostPoint, points.get(i));
            if (det > 0) {
                upperHull.add(points.get(i));
            } else if (det < 0) {
                lowerHull.add(points.get(i));
            }
        }

        constructHull(upperHull, leftMostPoint, rightMostPoint, convexSet);
        constructHull(lowerHull, rightMostPoint, leftMostPoint, convexSet);

        List<Point> result = new ArrayList<>(convexSet);
        Collections.sort(result);
        return result;
    }

    private static void constructHull(List<Point> points, Point left, Point right, Set<Point> convexSet) {
        if (!points.isEmpty()) {
            Point extremePoint = null;
            int extremePointDistance = Integer.MIN_VALUE;
            List<Point> candidatePoints = new ArrayList<>();

            for (Point p : points) {
                int det = orientation(left, right, p);
                if (det > 0) {
                    candidatePoints.add(p);
                    if (det > extremePointDistance) {
                        extremePointDistance = det;
                        extremePoint = p;
                    }
                }
            }

            if (extremePoint != null) {
                constructHull(candidatePoints, left, extremePoint, convexSet);
                convexSet.add(extremePoint);
                constructHull(candidatePoints, extremePoint, right, convexSet);
            }
        }
    }

    public static List<Point> grahamScan(List<Point> points) {
        if (points.size() <= 3) {
            return new ArrayList<>(points);
        }

        // Find the point with the lowest y-coordinate
        Point lowest = Collections.min(points);

        // Sort points by polar angle with respect to the lowest point
        points.sort(lowest.polarOrder());

        Stack<Point> hull = new Stack<>();
        hull.push(lowest);

        // Find the first point not equal to lowest
        int k1;
        for (k1 = 1; k1 < points.size(); k1++) {
            if (!lowest.equals(points.get(k1))) {
                break;
            }
        }
        if (k1 == points.size()) {
            return new ArrayList<>(hull);
        }

        // Find first point not collinear with lowest and points[k1]
        int k2;
        for (k2 = k1 + 1; k2 < points.size(); k2++) {
            if (orientation(lowest, points.get(k1), points.get(k2)) != 0) {
                break;
            }
        }
        hull.push(points.get(k2 - 1));

        // Process remaining points
        for (int i = k2; i < points.size(); i++) {
            Point top = hull.pop();
            while (orientation(hull.peek(), top, points.get(i)) <= 0) {
                top = hull.pop();
            }
            hull.push(top);
            hull.push(points.get(i));
        }

        return new ArrayList<>(hull);
    }
}
