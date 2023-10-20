package com.thealgorithms.divideandconquer;

import java.util.Arrays;
import java.util.Comparator;

class Point {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
public class ClosestPairOfPoints {
    public static double findClosestPair(Point[] points, int left, int right) {
        if (right - left <= 3) {
            return bruteForce(points, left, right);
        }
        int mid = (left + right) / 2;
        Point midPoint = points[mid];

        double leftDistance = findClosestPair(points, left, mid);
        double rightDistance = findClosestPair(points, mid + 1, right);

        double minDistance = Math.min(leftDistance, rightDistance);

        Point[] strip = new Point[right - left + 1];
        int stripSize = 0;

        for (int i = left; i <= right; i++) {
            if (Math.abs(points[i].x - midPoint.x) < minDistance) {
                strip[stripSize] = points[i];
                stripSize++;
            }
        }

        Arrays.sort(strip, 0, stripSize, Comparator.comparingInt(p -> p.y));

        for (int i = 0; i < stripSize; i++) {
            for (int j = i + 1; j < stripSize && strip[j].y - strip[i].y < minDistance; j++) {
                minDistance = Math.min(minDistance, distance(strip[i], strip[j]));
            }
        }

        return minDistance;
    }

    public static double closestPair(Point[] points) {
        Arrays.sort(points, Comparator.comparingInt(p -> p.x));
        return findClosestPair(points, 0, points.length - 1);
    }

    public static double distance(Point a, Point b) {
        return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
    }

    public static double bruteForce(Point[] points, int left, int right) {
        double minDistance = Double.POSITIVE_INFINITY;
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                double dist = distance(points[i], points[j]);
                minDistance = Math.min(minDistance, dist);
            }
        }
        return minDistance;
    }
    public static void main(String[] args) {
        Point[] points = {
            new Point(1, 2),
            new Point(3, 4),
            new Point(5, 6),
            new Point(7, 8),
            new Point(9, 10),
            new Point(11, 12),
            new Point(13, 14)
        };

        double closestDistance = closestPair(points);
        System.out.println("Closest pair distance: " + closestDistance);
    }
}
