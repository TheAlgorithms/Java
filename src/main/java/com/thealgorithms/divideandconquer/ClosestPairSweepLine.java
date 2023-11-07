package com.thealgorithms.divideandconquer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * The ClosestPairSweepLine class provides a solution to the closest pair of points problem
 * using the Sweep Line Algorithm. This geometric problem asks to find the smallest
 * distance between any two points in a set. The algorithm works by sweeping a line
 * across the plane and maintaining a set of candidate points that could potentially
 * be the closest pair.
 *
 * References:
 * - https://en.wikipedia.org/wiki/Closest_pair_of_points_problem
 * - https://en.wikipedia.org/wiki/Sweep_line_algorithm
 */
public class ClosestPairSweepLine {

    /**
     * The Point class represents a point in two-dimensional space.
     */
    public static class Point implements Comparable<Point> {
        double x, y;

        /**
         * Constructs a new point with the specified coordinates.
         *
         * @param x the x-coordinate of the point
         * @param y the y-coordinate of the point
         */
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        /**
         * Compares this point with another point for order by x-coordinate
         * and then y-coordinate if x is the same.
         *
         * @param other the other point to compare to
         * @return a negative integer, zero, or a positive integer as this point
         * is less than, equal to, or greater than the specified point
         */
        @Override
        public int compareTo(Point other) {
            if (this.x != other.x) {
                return Double.compare(this.x, other.x);
            } else {
                return Double.compare(this.y, other.y);
            }
        }

        /**
         * Calculates the Euclidean distance between this point and another point.
         *
         * @param other the other point
         * @return the distance between this point and the other point
         */
        public double distance(Point other) {
            return Math.hypot(this.x - other.x, this.y - other.y);
        }
    }

    /**
     * Finds the distance between the closest pair of points in a set using
     * the Sweep Line Algorithm.
     *
     * @param points the list of points in the set
     * @return the distance between the closest pair of points
     */
    public static double findClosestPair(List<Point> points) {
        points.sort(Comparator.comparingDouble(p -> p.x));

        TreeSet<Point> set = new TreeSet<>(Comparator.comparingDouble(p -> p.y));

        double bestDistance = Double.POSITIVE_INFINITY;
        int left = 0;

        for (Point point : points) {
            while (point.x - points.get(left).x > bestDistance) {
                set.remove(points.get(left));
                left++;
            }

            // Define the vertical strip a bit larger to include the edge
            Point from = new Point(Double.NEGATIVE_INFINITY, point.y - bestDistance);
            Point to = new Point(Double.POSITIVE_INFINITY, point.y + bestDistance + 0.000001);

            for (Point pointInStrip : set.subSet(from, true, to, true)) {
                double distance = point.distance(pointInStrip);
                bestDistance = Math.min(bestDistance, distance);
            }

            set.add(point);
        }

        return bestDistance;
    }

    /**
     * Main method to execute the ClosestPairSweepLine algorithm.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        List<Point> points = new ArrayList<>();
        points.add(new Point(1.0, 1.0));
        points.add(new Point(2.0, 3.0));
        points.add(new Point(3.0, 4.0));
        points.add(new Point(4.0, 2.0));
        points.add(new Point(5.0, 1.0));

        double closestDistance = ClosestPairSweepLine.findClosestPair(points);
        System.out.println("The closest pair of points are " + closestDistance + " units apart.");
    }
}
