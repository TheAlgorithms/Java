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
    private static final String POSITIVE_SIDE_LENGTH = "Side length must be greater than 0";
    private static final String POSITIVE_LENGTH = "Length must be greater than 0";
    private static final String POSITIVE_WIDTH = "Width must be greater than 0";
    private static final String POSITIVE_SLANT_HEIGHT = "Slant height must be greater than 0";
    private static final String POSITIVE_BASE_1 = "First base must be greater than 0";
    private static final String POSITIVE_BASE_2 = "Second base must be greater than 0";

    /**
     * Calculate the surface area of a cube.
     *
     * @param sideLength side length of cube
     * @return surface area of given cube
     */
    public static double surfaceAreaCube(final double sideLength) {
        if (sideLength <= 0) {
            throw new IllegalArgumentException(POSITIVE_SIDE_LENGTH);
        }
        return 6 * sideLength * sideLength;
    }

    /**
     * Calculate the surface area of a cuboid.
     *
     * @param length length of the cuboid
     * @param width width of the cuboid
     * @param height height of the cuboid
     * @return surface area of given cuboid
     */
    public static double surfaceAreaCuboid(final double length, final double width,final double height) {
        if (length <= 0) {
            throw new IllegalArgumentException(POSITIVE_LENGTH);
        }
        if (width <= 0) {
            throw new IllegalArgumentException(POSITIVE_WIDTH);
        }
        if (height <= 0) {
            throw new IllegalArgumentException(POSITIVE_HEIGHT);
        }
        return 2 * (length * width + length * height + width * height);
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
            throw new IllegalArgumentException(POSITIVE_SIDE_LENGTH);
        }
        if (slantHeight <= 0) {
            throw new IllegalArgumentException(POSITIVE_SLANT_HEIGHT);
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
     * @return surface area of given rectangle
     */
    public static double surfaceAreaRectangle(final double length, final double width) {
        if (length <= 0) {
            throw new IllegalArgumentException(POSITIVE_LENGTH);
        }
        if (width <= 0) {
            throw new IllegalArgumentException(POSITIVE_WIDTH);
        }
        return length * width;
    }

    /**
     * Calculate surface area of a cylinder.
     *
     * @param radius radius of the base circle
     * @param height height of the cylinder.
     * @return surface area of given cylinder
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
     * @return surface area of given square
     */
    public static double surfaceAreaSquare(final double sideLength) {
        if (sideLength <= 0) {
            throw new IllegalArgumentException(POSITIVE_SIDE_LENGTH);
        }
        return sideLength * sideLength;
    }

    /**
     * Calculate the area of a triangle.
     *
     * @param baseLength base of triangle
     * @param height height of triangle
     * @return surface area of given triangle
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
     * @param baseLength   base of a parallelogram
     * @param height height of a parallelogram
     * @return surface area of given parallelogram
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
     * @param baseLength1  upper base of trapezium
     * @param baseLength2  bottom base of trapezium
     * @param height height of trapezium
     * @return surface area of given trapezium
     */
    public static double surfaceAreaTrapezium(final double baseLength1, final double baseLength2, final double height) {
        if (baseLength1 <= 0) {
            throw new IllegalArgumentException(POSITIVE_BASE_1 );
        }
        if (baseLength2 <= 0) {
            throw new IllegalArgumentException(POSITIVE_BASE_2 );
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
     * @return surface area of given circle
     */
    public static double surfaceAreaCircle(final double radius) {
        if (radius <= 0.0) {
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
        if (radius <= 0.0) {
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
        if (radius <= 0.0) {
            throw new IllegalArgumentException(POSITIVE_RADIUS);
        }
        if (height <= 0.0) {
            throw new IllegalArgumentException(POSITIVE_HEIGHT);
        }
        return Math.PI * radius * (radius + Math.pow(height * height + radius * radius, 0.5));
    }
}
