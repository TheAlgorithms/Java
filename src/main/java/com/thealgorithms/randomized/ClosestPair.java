package com.thealgorithms.randomized;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

class Point {
    double x;
    double y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

public final class ClosestPair {
    private static final double INFINITY = Double.MAX_VALUE;

    private ClosestPair() {
    }

    public static double euclideanDistance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    /**
     * Algorithm Proof https://www.cs.toronto.edu/~anikolov/CSC473W20/kt-rabin.pdf
     * Additional information: https://en.wikipedia.org/wiki/Closest_pair_of_points_problem
     * This class uses Rabin's randomized approach to find the closest pair of points.
     * Rabin's approach randomly selects a sample of points to estimate an initial closest distance
     * (delta), then uses a grid for "probabilistic refinement". Finally, it updates the closest pair
     *  with the closest distance.
     */

    public static Object[] rabinRandomizedClosestPair(List<Point> points) {
        // Error handling, must have at least 2 points
        if (points == null || points.size() < 2) {
            return new Object[] {null, null, INFINITY};
        }

        Collections.shuffle(points, new Random()); // shuffle for required randomness

        double delta = INFINITY; // initialize distance
        Point closestA = null;
        Point closestB = null;

        // without exceeding number of points, work with some sample
        int sampleSize = Math.min(7, points.size());

        Random random = new Random(); // select randomly
        Set<Point> sampleSet = new HashSet<>(); // ensure unique pairs
        while (sampleSet.size() < sampleSize) {
            sampleSet.add(points.get(random.nextInt(points.size())));
        }
        List<Point> sample = new ArrayList<>(sampleSet);

        // initially the closest points are found via brute force
        for (int i = 0; i < sample.size(); i++) {
            for (int j = i + 1; j < sample.size(); j++) {
                double dist = euclideanDistance(sample.get(i), sample.get(j));
                if (dist < delta) {
                    closestA = sample.get(i);
                    closestB = sample.get(j);
                    delta = dist; // update distance
                }
            }
        }

        // Confirm neither closestA nor closestB are null
        if (closestA == null || closestB == null) {
            return new Object[] {points.get(0), points.get(1), euclideanDistance(points.get(0), points.get(1))};
        }

        // Create a grid, We will use "Probabilistic Filtering" by only checking
        //  neighboring grids to prevent bruteforce checking outside initialization
        Map<String, Point> grid = new HashMap<>();

        // coordinates computed based on delta, estimated closest distance
        for (Point p : points) {
            int gridX = (int) (p.x / delta);
            int gridY = (int) (p.y / delta);
            String key = gridX + "," + gridY; // string for indexing

            // check neighboring cells
            for (int dX = -1; dX <= 1; dX++) {
                for (int dY = -1; dY <= 1; dY++) {
                    String neighborKey = (gridX + dX) + "," + (gridY + dY);
                    Point neighborValue = grid.get(neighborKey);

                    // update points only if valid neighbor
                    if (neighborValue != null && p != neighborValue) {
                        double dist = euclideanDistance(p, neighborValue);
                        if (dist < delta) {
                            closestA = p;
                            closestB = neighborValue;
                            delta = dist;
                        }
                    }
                }
            }
            grid.put(key, p);
        }
        return new Object[] {closestA, closestB, delta};
    }
}
