package com.thealgorithms.randomized;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// As required by Repository, new algorithms have URL in comments with explanation
// https://www.geeksforgeeks.org/closest-pair-of-points-using-divide-and-conquer-algorithm
// Given 2 or more points on a 2-dimensional plane, find the closest 2 points in Euclidean distance
// This class uses the divide and conquer technique with recursion

final class Point implements Comparable<Point> {
    double x;
    double y;

    // Constructor to initialize a point with x and y coordinates
    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public int compareTo(Point other) {
        return Double.compare(this.x, other.x);
    }

    static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }
}

public final class ClosestPair {

    public static double closest(List<Point> points) {
        if (points == null || points.isEmpty()) {
            throw new IllegalArgumentException("There are no pairs to compare.");
        }

        if (points.size() == 1) {
            throw new IllegalArgumentException("There is only one pair.");
        }

        Collections.sort(points);
        double result = closestRecursiveHelper(points, 0, points.size() - 1);

        // Return distance of closest pair rounded to 2 decimal places
        return new BigDecimal(result).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private static double closestRecursiveHelper(List<Point> points, int left, int right) {
        // Base Case occurs with 3 or fewer points
        if (right - left <= 2) {
            return baseCase(points, left, right);
        }

        // Divide and conquer
        int mid = (left + right) / 2;
        double midX = points.get(mid).x;

        double leftDist = closestRecursiveHelper(points, left, mid);
        double rightDist = closestRecursiveHelper(points, mid + 1, right);

        double minDist = Math.min(leftDist, rightDist);

        return checkBoundary(points, left, right, midX, minDist);
    }

    private static double baseCase(List<Point> points, int left, int right) {
        // Sub-problems fitting the base case can use brute force
        double minDist = Double.MAX_VALUE;
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                minDist = Math.min(minDist, Point.distance(points.get(i), points.get(j)));
            }
        }
        return minDist;
    }

    private static double checkBoundary(List<Point> points, int left, int right, double midX, double minDist) {
        // Consider a boundary by the dividing line
        List<Point> boundary = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (Math.abs(points.get(i).x - midX) < minDist) {
                boundary.add(points.get(i));
            }
        }

        // sort by y coordinate within the boundary and check for closer points
        boundary.sort(Comparator.comparingDouble(p -> p.y));
        for (int i = 0; i < boundary.size(); i++) {
            for (int j = i + 1; j < boundary.size() && (boundary.get(j).y - boundary.get(i).y) < minDist; j++) {
                minDist = Math.min(minDist, Point.distance(boundary.get(i), boundary.get(j)));
            }
        }
        return minDist;
    }
}
