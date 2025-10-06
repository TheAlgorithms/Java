package com.thealgorithms.geometry;

import java.util.Arrays;
import java.util.List;

/**
 * Demonstration class for the Rotating Calipers algorithm.
 * Shows how to use the algorithm with example polygons.
 */
public final class RotatingCalipersDemo {
    
    private RotatingCalipersDemo() {
        // Utility class
    }
    
    public static void main(String[] args) {
        System.out.println("Rotating Calipers Algorithm Demo");
        System.out.println("================================");
        
        // Example 1: Triangle
        System.out.println("\n1. Triangle Example:");
        List<Point> triangle = Arrays.asList(
            new Point(0, 0),
            new Point(4, 0),
            new Point(2, 3)
        );
        
        demonstrateAlgorithm(triangle, "Triangle");
        
        // Example 2: Square
        System.out.println("\n2. Square Example:");
        List<Point> square = Arrays.asList(
            new Point(0, 0),
            new Point(2, 0),
            new Point(2, 2),
            new Point(0, 2)
        );
        
        demonstrateAlgorithm(square, "Square");
        
        // Example 3: Hexagon
        System.out.println("\n3. Hexagon Example:");
        List<Point> hexagon = Arrays.asList(
            new Point(2, 0),
            new Point(4, 1),
            new Point(4, 3),
            new Point(2, 4),
            new Point(0, 3),
            new Point(0, 1)
        );
        
        demonstrateAlgorithm(hexagon, "Hexagon");
    }
    
    private static void demonstrateAlgorithm(List<Point> polygon, String name) {
        System.out.println(name + " vertices: " + polygon);
        
        try {
            // Calculate diameter
            RotatingCalipers.PointPair diameter = RotatingCalipers.diameter(polygon);
            System.out.println("Diameter: " + diameter);
            
            // Calculate width (only for polygons with 3+ vertices)
            if (polygon.size() >= 3) {
                double width = RotatingCalipers.width(polygon);
                System.out.println("Width: " + String.format("%.2f", width));
                
                // Calculate minimum bounding rectangle
                RotatingCalipers.Rectangle rect = RotatingCalipers.minimumBoundingRectangle(polygon);
                System.out.println("Minimum bounding rectangle: " + rect);
            }
            
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}