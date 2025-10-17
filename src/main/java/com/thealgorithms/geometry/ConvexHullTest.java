package com.thealgorithms.geometry;

import java.util.*;

public class ConvexHullTest {
    public static void main(String[] args) {
        List<Point> points = Arrays.asList(
            new Point(0, 3),
            new Point(2, 2),
            new Point(1, 1),
            new Point(2, 1),
            new Point(3, 0),
            new Point(0, 0),
            new Point(3, 3)
        );

        System.out.println("Input Points:");
        for (Point p : points) System.out.print(p + " ");
        System.out.println();

        List<Point> hullBruteForce = ConvexHull.convexHullBruteForce(points);
        System.out.println("\nConvex Hull (Brute Force):");
        for (Point p : hullBruteForce) System.out.print(p + " ");
        System.out.println();

        List<Point> hullRecursive = ConvexHull.convexHullRecursive(points);
        System.out.println("\nConvex Hull (Recursive):");
        for (Point p : hullRecursive) System.out.print(p + " ");
        System.out.println();
    }
}
