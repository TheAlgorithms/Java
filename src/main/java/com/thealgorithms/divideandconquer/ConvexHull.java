package com.thealgorithms.divideandconquer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

class Point implements Comparable<Point> {
    double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point other) {
        if (this.x != other.x) {
            return Double.compare(this.x, other.x);
        }
        return Double.compare(this.y, other.y);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point)) return false;
        Point other = (Point) obj;
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return String.format("(%.1f, %.1f)", x, y);
    }
}

public class ConvexHull {
    private static double det(Point a, Point b, Point c) {
        return (a.x * b.y + b.x * c.y + c.x * a.y) - (a.y * b.x + b.y * c.x + c.y * a.x);
    }

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
