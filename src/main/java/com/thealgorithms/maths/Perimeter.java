package com.thealgorithms.maths;

// Perimeter of different 2D geometrical shapes
public class Perimeter {

    /**
     * Calculate the Perimeter of polygon
     * 
     * @parameter number of sides.
     * @parameter length of side.
     * @return Perimeter of given polygon
     */
    public static float perimeterPolygon(int n, float side) {
        return n * side;
    }

    /**
     * Calculate the Perimeter of rectangle.
     * 
     * @parameter length of rectangle
     * @parameter breadth of rectangle
     * @return Perimeter of given rectangle
     */
    public static float perimeterRectangle(float length, float breadth) {
        return 2 * (length + breadth);
    }

    /**
     * Calculate the Perimeter or Circumference of circle.
     * 
     * @parameter radius of circle.
     * @return circumference of given circle.
     */
    public static double perimeterCircle(float r) {
        return 2 * Math.PI * r;
    }

    /**
     * Calculate the Perimeter of triangle.
     * 
     * @parameter length of side 1
     * @parameter length of side 2
     * @parameter length of side 3
     * @return Perimeter of given triangle.
     */
    public static float perimeterTriangle(float side1, float side2, float side3) {
        return side1 + side2 + side3;
    }

    /**
     * Calculate the Perimeter of trapezoid.
     * 
     * @parameter length of side 1
     * @parameter length of side 2
     * @parameter length of side 3
     * @parameter length of side 4
     * @return Perimeter of given trapezoid.
     */
    public static float perimeterTrapezoid(float side1, float side2, float side3, float side4) {
        return side1 + side2 + side3 + side4;
    }
}
