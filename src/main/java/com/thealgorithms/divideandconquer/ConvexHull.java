package com.thealgorithms.divideandconquer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * A class representing points on a 2D plane and providing algorithms to compute
 * the convex hull of a set of points using brute-force and recursive methods.
 *
 * Convex hull: The smallest convex polygon that contains all the given points.
 *
 * Algorithms provided:
 * 1. Brute-Force Method
 * 2. Recursive (Divide-and-Conquer) Method
 *
 * @author Hardvan
 */
class Point implements Comparable<Point> {
    double x;
    double y;

    /**
     * Constructor to initialize a point with x and y coordinates.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     */
    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Compares this point to another point based on x and y coordinates.
     *
     * @param other The other point to compare with.
     * @return A negative integer, zero, or a positive integer as this point is
     *         less than, equal to, or greater than the specified point.
     */
    @Override
    public int compareTo(Point other) {
        if (this.x != other.x) {
            return Double.compare(this.x, other.x);
        }
        return Double.compare(this.y, other.y);
    }

    /**
     * Checks if this point is equal to another object based on x and y coordinates.
     *
     * @param obj The object to compare with.
     * @return true if the other object is a Point with the same coordinates;
     *         false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point)) {
            return false;
        }
        Point other = (Point) obj;
        return this.x == other.x && this.y == other.y;
    }

    /**
     * Generates the hash code for the point based on its coordinates.
     *
     * @return The hash code of the point.
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * Returns a string representation of the point in (x, y) format.
     *
     * @return A string representing the point.
     */
    @Override
    public String toString() {
        return String.format("(%.1f, %.1f)", x, y);
    }
}

/**
 * A class that provides two algorithms to find the convex hull of a set of points:
 * 1. Brute-force method
 * 2. Recursive (divide-and-conquer) method
 */
public final class ConvexHull {
    private ConvexHull() {
    }

    /**
     * Computes the determinant of three points to determine their orientation.
     *
     * @param a The first point.
     * @param b The second point.
     * @param c The third point.
     * @return A positive value if points a, b, c are in counter-clockwise order;
     *         negative if in clockwise order; and 0 if they are collinear.
     */
    private static double det(Point a, Point b, Point c) {
        return (a.x * b.y + b.x * c.y + c.x * a.y) - (a.y * b.x + b.y * c.x + c.y * a.x);
    }

    /**
     * Brute-force algorithm to find the convex hull of a set of points.
     * This algorithm checks every pair of points and determines if all other
     * points lie to one side of the line formed by these two points.
     *
     * @param points A list of points for which to compute the convex hull.
     * @return A sorted list of points forming the convex hull.
     */
    public static List<Point> convexHullBruteForce(List<Point> points) {
        Collections.sort(points);
        Set<Point> convexSet = new HashSet<>();

        for (int i = 0; i < points.size() - 1; i++) {
            for (int j = i + 1; j < points.size(); j++) {
                boolean pointsLeftOfIJ = false;
                boolean pointsRightOfIJ = false;
                boolean ijPartOfConvexHull = true;

                for (int k = 0; k < points.size(); k++) {
                    if (k != i && k != j) {
                        double detK = det(points.get(i), points.get(j), points.get(k));

                        if (detK > 0) {
                            pointsLeftOfIJ = true;
                        } else if (detK < 0) {
                            pointsRightOfIJ = true;
                        } else if (points.get(k).compareTo(points.get(i)) < 0 || points.get(k).compareTo(points.get(j)) > 0) {
                            ijPartOfConvexHull = false;
                            break;
                        }
                    }

                    if (pointsLeftOfIJ && pointsRightOfIJ) {
                        ijPartOfConvexHull = false;
                        break;
                    }
                }

                if (ijPartOfConvexHull) {
                    convexSet.add(points.get(i));
                    convexSet.add(points.get(j));
                }
            }
        }

        List<Point> result = new ArrayList<>(convexSet);
        Collections.sort(result);
        return result;
    }

    /**
     * Recursive (divide-and-conquer) algorithm to find the convex hull of a set of points.
     *
     * @param points A list of points for which to compute the convex hull.
     * @return A sorted list of points forming the convex hull.
     */
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
            double det = det(leftMostPoint, rightMostPoint, points.get(i));
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

    /**
     * Helper function to construct the convex hull recursively.
     *
     * @param points The list of candidate points for the hull.
     * @param left The left boundary point.
     * @param right The right boundary point.
     * @param convexSet The set to store points forming the convex hull.
     */
    private static void constructHull(List<Point> points, Point left, Point right, Set<Point> convexSet) {
        if (!points.isEmpty()) {
            Point extremePoint = null;
            double extremePointDistance = Double.NEGATIVE_INFINITY;
            List<Point> candidatePoints = new ArrayList<>();

            for (Point p : points) {
                double det = det(left, right, p);
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
}
