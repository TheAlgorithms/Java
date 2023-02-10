package com.thealgorithms.maths;

// Perimeter of different 2D geometrical shapes
public class Perimeter {

    /**
     * Calculate the Perimeter of regular polygon (equals sides)
     * Examples of regular polygon are Equilateral Triangle, Square, Regular Pentagon, Regular Hexagon. 
     * 
     * @param n for number of sides.
     * @param side for length of each side.
     * @return Perimeter of given polygon
     */
    public static float perimeterRegularPolygon(int n, float side) {
        return n * side;
    }

    /**
     * Calculate the Perimeter of irregular polygon (unequals sides)
     * Examples of irregular polygon are scalent triangle, irregular quadrilateral, irregular Pentagon, irregular Hexagon. 
     * 
     * @param side1 for length of side 1
     * @param side2 for length of side 2
     * @param side3 for length of side 3
     * @param sides for length of remaining sides
     * @return Perimeter of given trapezoid.
     */
    public static float perimeterIrregularPolygon(float side1, float side2, float side3, float... sides) {
        float perimeter = side1 + side2 + side3;
        for (float side : sides) {
            perimeter += side;
        }
        return perimeter;
    }

    /**
     * Calculate the Perimeter of rectangle
     * 
     * @param length for length of rectangle
     * @param breadth for breadth of rectangle
     * @return Perimeter of given rectangle
     */
    public static float perimeterRectangle(float length, float breadth) {
        return 2 * (length + breadth);
    }

    /**
     * Calculate the Perimeter or Circumference of circle.
     * 
     * @param r for radius of circle.
     * @return circumference of given circle.
     */
    public static double perimeterCircle(float r) {
        return 2 * Math.PI * r;
    }
}
