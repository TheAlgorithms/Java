package com.thealgorithms.maths;

/**
 * Find the area of various geometric shapes
 */
public class Area {

    public static void main(String[] args) {

        /* test cube */
        assert Double.compare(surfaceAreaCube(1), 6.0) == 0;

        /* test sphere */
        assert Double.compare(surfaceAreaSphere(5), 314.1592653589793) == 0;
        assert Double.compare(surfaceAreaSphere(1), 12.566370614359172) == 0;

        /* test rectangle */
        assert Double.compare(surfaceAreaRectangle(10, 20), 200.0) == 0;

        /* test square */
        assert Double.compare(surfaceAreaSquare(10), 100.0) == 0;

        /* test triangle */
        assert Double.compare(surfaceAreaTriangle(10, 10), 50.0) == 0;

        /* test parallelogram */
        assert Double.compare(surfaceAreaParallelogram(10, 20), 200.0) == 0;

        /* test trapezium */
        assert Double.compare(surfaceAreaTrapezium(10, 20, 30), 450.0) == 0;

        /* test circle */
        assert Double.compare(surfaceAreaCircle(20), 1256.6370614359173) == 0;

        /* test cylinder */
        assert Double.compare(surfaceAreaCylinder(1, 2), 18.84955592153876) == 0;

        /* test hemisphere */
        assert Double.compare(surfaceAreaHemisphere(5), 235.61944901923448) == 0;
        assert Double.compare(surfaceAreaHemisphere(1), 9.42477796076938) == 0;

        /* test cone */
        assert Double.compare(surfaceAreaCone(6, 8), 301.59289474462014) == 0;
        assert Double.compare(surfaceAreaCone(10, 24), 1130.9733552923256) == 0;

    }

    /**
     * Calculate the surface area of a cube.
     *
     * @param sideLength side length of cube
     * @return surface area of given cube
     */
    private static double surfaceAreaCube(double sideLength) {
        return 6 * sideLength * sideLength;
    }

    /**
     * Calculate the surface area of a sphere.
     *
     * @param radius radius of sphere
     * @return surface area of given sphere
     */
    private static double surfaceAreaSphere(double radius) {
        return 4 * Math.PI * radius * radius;
    }

    /**
     * Calculate the area of a rectangle
     *
     * @param length length of rectangle
     * @param width width of rectangle
     * @return area of given rectangle
     */
    private static double surfaceAreaRectangle(double length, double width) {
        return length * width;
    }

    /**
     * Calculate surface area of a cylinder
     *
     * @param radius radius of the floor
     * @param height height of the cylinder.
     * @return volume of given cylinder
     */
    private static double surfaceAreaCylinder(double radius, double height) {
        return 2 * (Math.PI * radius * radius + Math.PI * radius * height);
    }

    /**
     * Calculate the area of a square
     *
     * @param sideLength side length of square
     * @return area of given square
     */
    private static double surfaceAreaSquare(double sideLength) {
        return sideLength * sideLength;
    }

    /**
     * Calculate the area of a triangle
     *
     * @param base base of triangle
     * @param height height of triangle
     * @return area of given triangle
     */
    private static double surfaceAreaTriangle(double base, double height) {
        return base * height / 2;
    }

    /**
     * Calculate the area of a parallelogram
     *
     * @param base base of parallelogram
     * @param height height of parallelogram
     * @return area of given parallelogram
     */
    private static double surfaceAreaParallelogram(double base, double height) {
        return base * height;
    }

    /**
     * Calculate the area of a trapezium
     *
     * @param base1 upper base of trapezium
     * @param base2 bottom base of trapezium
     * @param height height of trapezium
     * @return area of given trapezium
     */
    private static double surfaceAreaTrapezium(double base1, double base2, double height) {
        return (base1 + base2) * height / 2;
    }

    /**
     * Calculate the area of a circle
     *
     * @param radius radius of circle
     * @return area of given circle
     */
    private static double surfaceAreaCircle(double radius) {
        return Math.PI * radius * radius;
    }

    /**
     * Calculate the surface area of a hemisphere.
     *
     * @param radius radius of hemisphere
     * @return surface area of given hemisphere
     */
    private static double surfaceAreaHemisphere(double radius) {
        return 3 * Math.PI * radius * radius;
    }

    /**
     * Calculate the surface area of a cone.
     *
     * @param radius radius of cone.
     * @param height of cone.
     * @return surface area of given cone.
     */
    private static double surfaceAreaCone(double radius, double height) {
        return Math.PI * radius * (radius + Math.pow((height * height + radius * radius), 0.5));
    }
}
