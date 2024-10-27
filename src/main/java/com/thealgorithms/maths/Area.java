package com.thealgorithms.maths;

/**
 * Utility class to find the surface area of various geometric shapes.
 */
public final class Area {
    // Prevent instantiation
    private Area() {
    }

    // Error messages for input validation
    
    /**
     * String of IllegalArgumentException for radius
     */
    private static final String POSITIVE_RADIUS = "Must be a positive radius";

    /**
     * String of IllegalArgumentException for height
     */
    private static final String POSITIVE_HEIGHT = "Must be a positive height";
    
    /**
     * String of IllegalArgumentException for base
     */
    private static final String POSITIVE_BASE = "Must be a positive base";

    /**
     * String of IllegalArgumentException for length
     */
    private static final String POSITIVE_LENGTH = "Must be a positive length";
    
    /**
     * String of IllegalArgumentException for width
     */
    private static final String POSITIVE_WIDTH = "Must be a positive width";
    /**
     * Validates that a given value is positive.
     *
     * @param value  The value to check.
     * @param message The message to display if validation fails.
     * @throws IllegalArgumentException if value is not positive.
     */
    private static void validatePositive(double value, String message) {
        if (value <= 0) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Calculate the surface area of a cube.
     *
     * @param sideLength Length of one side of the cube.
     * @return Surface area of the cube.
     * @throws IllegalArgumentException if sideLength is not positive.
     */
    public static double cubeSurfaceArea(final double sideLength) {
        validatePositive(sideLength, "Must be a positive side length");
        return 6 * sideLength * sideLength;
    }

    /**
     * Calculate the surface area of a sphere.
     *
     * @param radius Radius of the sphere.
     * @return Surface area of the sphere.
     * @throws IllegalArgumentException if radius is not positive.
     */
    public static double sphereSurfaceArea(final double radius) {
        validatePositive(radius, POSITIVE_RADIUS);
        return 4 * Math.PI * radius * radius;
    }

    /**
     * Calculate the area of a rectangle.
     *
     * @param length Length of the rectangle.
     * @param width  Width of the rectangle.
     * @return Area of the rectangle.
     * @throws IllegalArgumentException if length or width is not positive.
     */
    public static double rectangleArea(final double length, final double width) {
        validatePositive(length, POSITIVE_LENGTH);
        validatePositive(width, POSITIVE_WIDTH);
        return length * width;
    }

    /**
     * Calculate the surface area of a cylinder.
     *
     * @param radius Radius of the base of the cylinder.
     * @param height Height of the cylinder.
     * @return Surface area of the cylinder.
     * @throws IllegalArgumentException if radius or height is not positive.
     */
    public static double cylinderSurfaceArea(final double radius, final double height) {
        validatePositive(radius, POSITIVE_RADIUS);
        validatePositive(height, POSITIVE_HEIGHT);
        return 2 * (Math.PI * radius * radius + Math.PI * radius * height);
    }

    /**
     * Calculate the area of a square.
     *
     * @param sideLength Length of one side of the square.
     * @return Area of the square.
     * @throws IllegalArgumentException if sideLength is not positive.
     */
    public static double squareArea(final double sideLength) {
        validatePositive(sideLength, "Must be a positive side length");
        return sideLength * sideLength;
    }

    /**
     * Calculate the area of a triangle.
     *
     * @param base   Base of the triangle.
     * @param height Height of the triangle.
     * @return Area of the triangle.
     * @throws IllegalArgumentException if base or height is not positive.
     */
    public static double triangleArea(final double base, final double height) {
        validatePositive(base, POSITIVE_BASE);
        validatePositive(height, POSITIVE_HEIGHT);
        return (base * height) / 2;
    }

    /**
     * Calculate the area of a parallelogram.
     *
     * @param base   Base of the parallelogram.
     * @param height Height of the parallelogram.
     * @return Area of the parallelogram.
     * @throws IllegalArgumentException if base or height is not positive.
     */
    public static double parallelogramArea(final double base, final double height) {
        validatePositive(base, POSITIVE_BASE);
        validatePositive(height, POSITIVE_HEIGHT);
        return base * height;
    }

    /**
     * Calculate the area of a trapezium.
     *
     * @param base1  Length of the upper base of the trapezium.
     * @param base2  Length of the lower base of the trapezium.
     * @param height Height of the trapezium.
     * @return Area of the trapezium.
     * @throws IllegalArgumentException if base1, base2, or height is not positive.
     */
    public static double trapeziumArea(final double base1, final double base2, final double height) {
        validatePositive(base1, POSITIVE_BASE);
        validatePositive(base2, POSITIVE_BASE);
        validatePositive(height, POSITIVE_HEIGHT);
        return (base1 + base2) * height / 2;
    }

    /**
     * Calculate the area of a circle.
     *
     * @param radius Radius of the circle.
     * @return Area of the circle.
     * @throws IllegalArgumentException if radius is not positive.
     */
    public static double circleArea(final double radius) {
        validatePositive(radius, POSITIVE_RADIUS);
        return Math.PI * radius * radius;
    }

    /**
     * Calculate the surface area of a hemisphere.
     *
     * @param radius Radius of the hemisphere.
     * @return Surface area of the hemisphere.
     * @throws IllegalArgumentException if radius is not positive.
     */
    public static double hemisphereSurfaceArea(final double radius) {
        validatePositive(radius, POSITIVE_RADIUS);
        return 3 * Math.PI * radius * radius;
    }

    /**
     * Calculate the surface area of a cone.
     *
     * @param radius Radius of the base of the cone.
     * @param height Height of the cone.
     * @return Surface area of the cone.
     * @throws IllegalArgumentException if radius or height is not positive.
     */
    public static double coneSurfaceArea(final double radius, final double height) {
        validatePositive(radius, POSITIVE_RADIUS);
        validatePositive(height, POSITIVE_HEIGHT);
        return Math.PI * radius * (radius + Math.sqrt(height * height + radius * radius));
    }
}
