package com.thealgorithms.geometry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * A class providing algorithms to compute the convex hull of a set of points
 * using brute-force and recursive methods.
 *
 * Convex hull: The smallest convex polygon that contains all the given points.
 *
 * Algorithms provided:
 * 1. Brute-Force Method
 * 2. Recursive (Divide-and-Conquer) Method
 *
 * @author Hardvan
 */
public final class ConvexHull {
    private ConvexHull() {
    }

    private static boolean checkPointOrientation(Point i, Point j, Point k) {
        int detK = Point.orientation(i, j, k);
        if (detK > 0) {
            return true; // pointsLeftOfIJ
        } else if (detK < 0) {
            return false; // pointsRightOfIJ
        } else {
            return k.compareTo(i) >= 0 && k.compareTo(j) <= 0;
        }
    }

    public static List<Point> convexHullBruteForce(List<Point> points) {
        Set<Point> convexSet = new TreeSet<>(Comparator.naturalOrder());

        for (int i = 0; i < points.size() - 1; i++) {
            for (int j = i + 1; j < points.size(); j++) {
                boolean allPointsOnOneSide = true;
                boolean leftSide = checkPointOrientation(points.get(i), points.get(j), points.get((i + 1) % points.size()));

                for (int k = 0; k < points.size(); k++) {
                    if (k != i && k != j && checkPointOrientation(points.get(i), points.get(j), points.get(k)) != leftSide) {
                        allPointsOnOneSide = false;
                        break;
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

    /**
     * Computes the convex hull using a recursive divide-and-conquer approach.
     * Returns points in counter-clockwise order starting from the bottom-most, left-most point.
     *
     * @param points the input points
     * @return the convex hull points in counter-clockwise order
     */
    public static List<Point> convexHullRecursive(List<Point> points) {
        if (points.size() < 3) {
            List<Point> result = new ArrayList<>(points);
            Collections.sort(result);
            return result;
        }

        Collections.sort(points);
        Set<Point> convexSet = new HashSet<>();
        Point leftMostPoint = points.getFirst();
        Point rightMostPoint = points.getLast();

        convexSet.add(leftMostPoint);
        convexSet.add(rightMostPoint);

        List<Point> upperHull = new ArrayList<>();
        List<Point> lowerHull = new ArrayList<>();

        for (int i = 1; i < points.size() - 1; i++) {
            int det = Point.orientation(leftMostPoint, rightMostPoint, points.get(i));
            if (det > 0) {
                upperHull.add(points.get(i));
            } else if (det < 0) {
                lowerHull.add(points.get(i));
            }
        }

        constructHull(upperHull, leftMostPoint, rightMostPoint, convexSet);
        constructHull(lowerHull, rightMostPoint, leftMostPoint, convexSet);

        // Convert to list and sort in counter-clockwise order
        return sortCounterClockwise(new ArrayList<>(convexSet));
    }

    private static void constructHull(Collection<Point> points, Point left, Point right, Set<Point> convexSet) {
        if (!points.isEmpty()) {
            Point extremePoint = null;
            int extremePointDistance = Integer.MIN_VALUE;
            List<Point> candidatePoints = new ArrayList<>();

            for (Point p : points) {
                int det = Point.orientation(left, right, p);
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

    /**
     * Sorts convex hull points in counter-clockwise order starting from
     * the bottom-most, left-most point.
     *
     * @param hullPoints the unsorted convex hull points
     * @return the points sorted in counter-clockwise order
     */
    private static List<Point> sortCounterClockwise(List<Point> hullPoints) {
        if (hullPoints.size() <= 2) {
            Collections.sort(hullPoints);
            return hullPoints;
        }

        // Find the bottom-most, left-most point (pivot)
        Point pivot = hullPoints.getFirst();
        for (Point p : hullPoints) {
            if (p.y() < pivot.y() || (p.y() == pivot.y() && p.x() < pivot.x())) {
                pivot = p;
            }
        }

        // Sort other points by polar angle with respect to pivot
        final Point finalPivot = pivot;
        List<Point> sorted = new ArrayList<>(hullPoints);
        sorted.remove(finalPivot);

        sorted.sort((p1, p2) -> {
            int crossProduct = Point.orientation(finalPivot, p1, p2);

            if (crossProduct == 0) {
                // Collinear points: sort by distance from pivot (closer first for convex hull)
                long dist1 = distanceSquared(finalPivot, p1);
                long dist2 = distanceSquared(finalPivot, p2);
                return Long.compare(dist1, dist2);
            }

            // Positive cross product means p2 is counter-clockwise from p1
            // We want counter-clockwise order, so if p2 is CCW from p1, p1 should come first
            return -crossProduct;
        });

        // Build result with pivot first, filtering out intermediate collinear points
        List<Point> result = new ArrayList<>();
        result.add(finalPivot);

        if (!sorted.isEmpty()) {
            // This loop iterates through the points sorted by angle.
            // For points that are collinear with the pivot, we only want the one that is farthest away.
            // The sort places closer points first.
            for (int i = 0; i < sorted.size() - 1; i++) {
                // Check the orientation of the pivot, the current point, and the next point.
                int orientation = Point.orientation(finalPivot, sorted.get(i), sorted.get(i + 1));

                // If the orientation is not 0, it means the next point (i+1) is at a new angle.
                // Therefore, the current point (i) must be the farthest point at its angle. We keep it.
                if (orientation != 0) {
                    result.add(sorted.get(i));
                }
                // If the orientation is 0, the points are collinear. We discard the current point (i)
                // because it is closer to the pivot than the next point (i+1).
            }
            // Always add the very last point from the sorted list. It is either the only point
            // at its angle, or it's the farthest among a set of collinear points.
            result.add(sorted.getLast());
        }

        return result;
    }

    /**
     * Computes the squared distance between two points to avoid floating point operations.
     */
    private static long distanceSquared(Point p1, Point p2) {
        long dx = (long) p1.x() - p2.x();
        long dy = (long) p1.y() - p2.y();
        return dx * dx + dy * dy;
    }
}
