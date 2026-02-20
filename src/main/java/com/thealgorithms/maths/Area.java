package com.thealgorithms.maths;

/**
 * Find the area of various geometric shapes
 */
public final class Area {
    private Area() {
    }

    /**
     * String of IllegalArgumentException for radius
     */
    private static final String POSITIVE_RADIUS = "Radius must be greater than 0";

    /**
     * String of IllegalArgumentException for height
     */
    private static final String POSITIVE_HEIGHT = "Height must be greater than 0";

    /**
     * String of IllegalArgumentException for base
     */
    private static final String POSITIVE_BASE = "Base must be greater than 0";

    /**
     * Calculate the surface area of a cube.
     *
     * @param sideLength side length of cube
     * @return surface area of given cube
     */
    public static double surfaceAreaCube(final double sideLength) {
        if (sideLength <= 0) {
            throw new IllegalArgumentException("Side length must be greater than 0");
        }
        return 6 * sideLength * sideLength;
    }

    /**
     * Calculate the surface area of a sphere.
     *
     * @param radius radius of sphere
     * @return surface area of given sphere
     */
    public static double surfaceAreaSphere(final double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException(POSITIVE_RADIUS);
        }
        return 4 * Math.PI * radius * radius;
    }

    /**
     * Calculate the surface area of a pyramid with a square base.
     *
     * @param sideLength side length of the square base
     * @param slantHeight slant height of the pyramid
     * @return surface area of the given pyramid
     */
    public static double surfaceAreaPyramid(final double sideLength, final double slantHeight) {
        if (sideLength <= 0) {
            throw new IllegalArgumentException("");
        }
        if (slantHeight <= 0) {
            throw new IllegalArgumentException("slant height must be greater than 0");
        }
        double baseArea = sideLength * sideLength;
        double lateralSurfaceArea = 2 * sideLength * slantHeight;
        return baseArea + lateralSurfaceArea;
    }

    /**
     * Calculate the area of a rectangle.
     *
     * @param length length of a rectangle
     * @param width  width of a rectangle
     * @return area of given rectangle
     */
    public static double surfaceAreaRectangle(final double length, final double width) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0");
        }
        if (width <= 0) {
            throw new IllegalArgumentException("Width must be greater than 0");
        }
        return length * width;
    }

    /**
     * Calculate surface area of a cylinder.
     *
     * @param radius radius of the floor
     * @param height height of the cylinder.
     * @return volume of given cylinder
     */
    public static double surfaceAreaCylinder(final double radius, final double height) {
        if (radius <= 0) {
            throw new IllegalArgumentException(POSITIVE_RADIUS);
        }
        if (height <= 0) {
            throw new IllegalArgumentException(POSITIVE_HEIGHT);
        }
        return 2 * (Math.PI * radius * radius + Math.PI * radius * height);
    }

    /**
     * Calculate the area of a square.
     *
     * @param sideLength side length of square
     * @return area of given square
     */
    public static double surfaceAreaSquare(final double sideLength) {
        if (sideLength <= 0) {
            throw new IllegalArgumentException("Side Length must be greater than 0");
        }
        return sideLength * sideLength;
    }

    /**
     * Calculate the area of a triangle.
     *
     * @param base   base of triangle
     * @param height height of triangle
     * @return area of given triangle
     */
    public static double surfaceAreaTriangle(final double baseLength, final double height) {
        if (baseLength <= 0) {
            throw new IllegalArgumentException(POSITIVE_BASE);
        }
        if (height <= 0) {
            throw new IllegalArgumentException(POSITIVE_HEIGHT);
        }
        return baseLength * height / 2;
    }

    /**
     * Calculate the area of a parallelogram.
     *
     * @param base   base of a parallelogram
     * @param height height of a parallelogram
     * @return area of given parallelogram
     */
    public static double surfaceAreaParallelogram(final double baseLength, final double height) {
        if (baseLength <= 0) {
            throw new IllegalArgumentException(POSITIVE_BASE);
        }
        if (height <= 0) {
            throw new IllegalArgumentException(POSITIVE_HEIGHT);
        }
        return baseLength * height;
    }

    /**
     * Calculate the area of a trapezium.
     *
     * @param base1  upper base of trapezium
     * @param base2  bottom base of trapezium
     * @param height height of trapezium
     * @return area of given trapezium
     */
    public static double surfaceAreaTrapezium(final double baseLength1, final double baseLength2, final double height) {
        if (baseLength1 <= 0) {
            throw new IllegalArgumentException(POSITIVE_BASE + 1);
        }
        if (baseLength2 <= 0) {
            throw new IllegalArgumentException(POSITIVE_BASE + 2);
        }
        if (height <= 0) {
            throw new IllegalArgumentException(POSITIVE_HEIGHT);
        }
        return (baseLength1 + baseLength2) * height / 2;
    }

    /**
     * Calculate the area of a circle.
     *
     * @param radius radius of circle
     * @return area of given circle
     */
    public static double surfaceAreaCircle(final double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException(POSITIVE_RADIUS);
        }
        return Math.PI * radius * radius;
    }

    /**
     * Calculate the surface area of a hemisphere.
     *
     * @param radius radius of hemisphere
     * @return surface area of given hemisphere
     */
    public static double surfaceAreaHemisphere(final double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException(POSITIVE_RADIUS);
        }
        return 3 * Math.PI * radius * radius;
    }

    /**
     * Calculate the surface area of a cone.
     *
     * @param radius radius of cone.
     * @param height of cone.
     * @return surface area of given cone.
     */
    public static double surfaceAreaCone(final double radius, final double height) {
        if (radius <= 0) {
            throw new IllegalArgumentException(POSITIVE_RADIUS);
        }
        if (height <= 0) {
            throw new IllegalArgumentException(POSITIVE_HEIGHT);
        }
        return Math.PI * radius * (radius + Math.pow(height * height + radius * radius, 0.5));
    }
}
