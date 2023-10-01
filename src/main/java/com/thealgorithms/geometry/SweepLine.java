package com.thealgorithms.geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A Java program that calculates the closest pair of points in a set using the Sweep Line Algorithm.
 * This algorithm efficiently solves geometric problems, such as finding the closest pair of points in a plane.
 *
 * Algorithm Characteristics:
 * - The Closest Pair of Points algorithm employs the Sweep Line Algorithm to efficiently find the pair of points with the smallest Euclidean distance.
 * - It uses a "sweep line," which is an imaginary horizontal line moving through a set of points in the plane.
 * - The algorithm maintains a dynamic window of points as the sweep line progresses, reducing the search space.
 *
 * Time Complexity:
 * - The time complexity of the algorithm is O(n log n), where n is the number of input points.
 * - Sorting the points by their x-coordinates dominates the time complexity.
 *
 * Space Complexity:
 * - The space complexity of the algorithm is O(n), as it stores points and an active set during execution.
 *
 * References:
 * - https://en.wikipedia.org/wiki/Sweep_line_algorithm
 */
public class SweepLine {

    // Structure to represent a point with x and y coordinates
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // Function to calculate the distance between two points
    static long distance(Point p1, Point p2) {
        long dx = p1.x - p2.x;
        long dy = p1.y - p2.y;
        return dx * dx + dy * dy;
    }

    // Function to find the closest pair of points
    static long closestPair(Point[] points) {
        // Sort the points by their x-coordinates
        Arrays.sort(points, new Comparator<Point>() {
            public int compare(Point a, Point b) {
                return a.x - b.x;
            }
        });

        // Initialize the minimum distance as a large value
        long minDistance = (long) Math.pow(10, 18);

        // Store the points in a set, ordered by y-coordinates
        Set<Point> activeSet = new HashSet<>();

        // Initialize the active set with the first point
        activeSet.add(points[0]);

        int left = 0;

        // Iterate through each point
        for (int right = 1; right < points.length; ++right) {
            Point current = points[right];

            // Remove points from the active set if they are outside the current window
            while (current.x - points[left].x > minDistance) {
                activeSet.remove(points[left]);
                ++left;
            }

            // Calculate the y-coordinate range within the window
            int minY = current.y - (int) minDistance;
            int maxY = current.y + (int) minDistance;

            // Find potential candidates in the active set
            List<Point> candidates = new ArrayList<>();
            for (Point p : activeSet) {
                if (p.y >= minY && p.y <= maxY) {
                    candidates.add(p);
                }
            }

            // Calculate the distance between the current point and potential candidates
            for (Point candidate : candidates) {
                long dist = distance(current, candidate);
                minDistance = Math.min(minDistance, dist);
            }

            // Insert the current point into the active set
            activeSet.add(current);
        }

        return minDistance;
    }

    public static void main(String[] args) {
        // Points on a plane P[i] = (x, y)
        Point[] points = {new Point(1, 2), new Point(2, 3), new Point(3, 4), new Point(5, 6), new Point(2, 1)};

        // Find the closest pair of points
        long closestDistance = closestPair(points);

        System.out.println("The smallest distance is " + closestDistance);
    }
}
