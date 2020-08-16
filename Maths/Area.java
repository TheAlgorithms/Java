package Maths;

/**
 * Find the area of various geometric shapes
 */
public class Area {
    public static void main(String[] args) {
        /* test cube */
        assert surfaceAreaCube(1) == 6;

        /* test sphere */
        assert surfaceAreaSphere(5) == 314.1592653589793;
        assert surfaceAreaSphere(1) == 12.566370614359172;

        /* test rectangle */
        assert surfaceAreaRectangle(10, 20) == 200;

        /* test square */
        assert surfaceAreaSquare(10) == 100;

        /* test triangle */
        assert surfaceAreaTriangle(10, 10) == 50;

        /* test parallelogram */
        assert surfaceAreaParallelogram(10, 20) == 200;

        /* test trapezium */
        assert surfaceAreaTrapezium(10, 20, 30) == 450;

        /* test circle */
        assert surfaceAreaCircle(20) == 1256.6370614359173;

    }

    /**
     * Calculate the surface area of a cube.
     *
     * @param sideLength side length of cube
     * @return surface area of given cube
     */
    public static double surfaceAreaCube(double sideLength) {
        return 6 * sideLength * sideLength;
    }

    /**
     * Calculate the surface area of a sphere.
     *
     * @param radius radius of sphere
     * @return surface area of given sphere
     */
    public static double surfaceAreaSphere(double radius) {
        return 4 * Math.PI * radius * radius;
    }

    /**
     * Calculate the area of a rectangle
     *
     * @param length length of rectangle
     * @param width  width of rectangle
     * @return area of given rectangle
     */
    public static double surfaceAreaRectangle(double length, double width) {
        return length * width;
    }

    /**
     * Calculate the area of a square
     *
     * @param sideLength side length of square
     * @return area of given square
     */
    public static double surfaceAreaSquare(double sideLength) {
        return sideLength * sideLength;
    }

    /**
     * Calculate the area of a triangle
     *
     * @param base   base of triangle
     * @param height height of triangle
     * @return area of given triangle
     */
    public static double surfaceAreaTriangle(double base, double height) {
        return base * height / 2;
    }

    /**
     * Calculate the area of a parallelogram
     *
     * @param base   base of parallelogram
     * @param height height of parallelogram
     * @return area of given parallelogram
     */
    public static double surfaceAreaParallelogram(double base, double height) {
        return base * height;
    }

    /**
     * Calculate the area of a trapezium
     *
     * @param base1  upper base of trapezium
     * @param base2  bottom base of trapezium
     * @param height height of trapezium
     * @return area of given trapezium
     */
    public static double surfaceAreaTrapezium(double base1, double base2, double height) {
        return (base1 + base2) * height / 2;
    }

    /**
     * Calculate the area of a circle
     *
     * @param radius radius of circle
     * @return area of given circle
     */
    public static double surfaceAreaCircle(double radius) {
        return Math.PI * radius * radius;
    }
}
