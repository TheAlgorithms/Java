package com.thealgorithms.geometry;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class PolygonIntersection {
    public static void main(String[] args) {
        // Define two example polygons as lists of points.
        List<Point2D.Double> polygon1 = new ArrayList<>();
        polygon1.add(new Point2D.Double(0, 0));
        polygon1.add(new Point2D.Double(5, 0));
        polygon1.add(new Point2D.Double(5, 5));
        polygon1.add(new Point2D.Double(0, 5));

        List<Point2D.Double> polygon2 = new ArrayList<>();
        polygon2.add(new Point2D.Double(2, 2));
        polygon2.add(new Point2D.Double(7, 2));
        polygon2.add(new Point2D.Double(7, 7));
        polygon2.add(new Point2D.Double(2, 7));

        // Find the intersection of the two polygons.
        List<Point2D.Double> intersection = findPolygonIntersection(polygon1, polygon2);

        // Print the intersection points.
        System.out.println("Intersection Points:");
        for (Point2D.Double point : intersection) {
            System.out.println("(" + point.getX() + ", " + point.getY() + ")");
        }
    }

    // Function to find the intersection of two polygons.
    public static List<Point2D.Double> findPolygonIntersection(List<Point2D.Double> polygon1, List<Point2D.Double> polygon2) {
        Polygon poly1 = new Polygon();
        Polygon poly2 = new Polygon();

        // Convert the input lists of points into Polygon objects.
        for (Point2D.Double point : polygon1) {
            poly1.addPoint((int) point.getX(), (int) point.getY());
        }

        for (Point2D.Double point : polygon2) {
            poly2.addPoint((int) point.getX(), (int) point.getY());
        }

        // Perform the intersection using the Polygon class's methods.
        Polygon resultPolygon = new Polygon();
        for (int i = 0; i < poly1.npoints; i++) {
            if (poly2.contains(poly1.xpoints[i], poly1.ypoints[i])) {
                resultPolygon.addPoint(poly1.xpoints[i], poly1.ypoints[i]);
            }
        }

        // Convert the result Polygon back to a list of points.
        List<Point2D.Double> intersection = new ArrayList<>();
        for (int i = 0; i < resultPolygon.npoints; i++) {
            intersection.add(new Point2D.Double(resultPolygon.xpoints[i], resultPolygon.ypoints[i]));
        }

        return intersection;
    }
}
