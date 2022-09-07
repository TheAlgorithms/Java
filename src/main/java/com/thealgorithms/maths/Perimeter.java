package com.thealgorithms.maths;

public class Perimeter {
    public static void main(String[] args) {
        System.out.println(perimeter_polygon(5,4));
        System.out.println(perimeter_rectangle(3,4));
        System.out.printf("%,3f",circumference(5));
    }
    // Perimeter of different 2D geometrical shapes
    /**
     *Calculate the Perimeter of polygon.
     * @parameter length of side.
     * @parameter number of sides.
     * @return Perimeter of given polygon
     */
    public static float perimeter_polygon( int n, float side){
        float perimeter = n*side;
        return perimeter;
    }
    /**
     *Calculate the Perimeter of rectangle.
     * @parameter length and breadth.
     * @return Perimeter of given rectangle
     */
    public static float perimeter_rectangle( float length, float breadth){
        float perimeter = 2*(length + breadth);
        return perimeter;
    }
    /**
     *Calculate the circumference of circle.
     * @parameter radius of circle.
     * @return circumference of given circle.
     */
    public static double circumference( float r){
        double circumference = 2*Math.PI*r;
        return circumference;
    }
}
