package com.thealgorithms.maths;

/**
 * Calculate the volume of various shapes.
 */
public final class Volume {
    private Volume() {
    }

    private static final String POSITIVE_RADIUS = "Radius must be greater than 0";
    private static final String POSITIVE_HEIGHT = "Height must be greater than 0";
    private static final String POSITIVE_SIDE = "Side length must be greater than 0";
    private static final String POSITIVE_BASE_AREA = "Base area must be greater than 0";
    private static final String POSITIVE_LENGTH = "Length must be greater than 0";
    private static final String POSITIVE_WIDTH = "Width must be greater than 0";
    private static final String POSITIVE_AXIS = "Semi-axis must be greater than 0";

    /**
     * Calculate the volume of a cube.
     *
     * @param sideLength length of the given cube's sides
     * @return volume of the given cube
     * @throws IllegalArgumentException if sideLength is less than or equal to 0
     */
    public static double volumeCube(double sideLength) {
        if (sideLength <= 0) {
            throw new IllegalArgumentException(POSITIVE_SIDE);
        }
        return sideLength * sideLength * sideLength;
    }

    /**
     * Calculate the volume of a cuboid.
     *
     * @param width  width of given cuboid
     * @param height height of given cuboid
     * @param length length of given cuboid
     * @return volume of given cuboid
     * @throws IllegalArgumentException if any dimension is less than or equal to 0
     */
    public static double volumeCuboid(double width, double height, double length) {
        if (width <= 0) {
            throw new IllegalArgumentException(POSITIVE_WIDTH);
        }
        if (height <= 0) {
            throw new IllegalArgumentException(POSITIVE_HEIGHT);
        }
        if (length <= 0) {
            throw new IllegalArgumentException(POSITIVE_LENGTH);
        }
        return width * height * length;
    }

    /**
     * Calculate the volume of a sphere.
     *
     * @param radius radius of given sphere
     * @return volume of given sphere
     * @throws IllegalArgumentException if radius is less than or equal to 0
     */
    public static double volumeSphere(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException(POSITIVE_RADIUS);
        }
        return (4 * Math.PI * radius * radius * radius) / 3;
    }

    /**
     * Calculate volume of a cylinder.
     *
     * @param radius radius of the given cylinder's floor
     * @param height height of the given cylinder
     * @return volume of given cylinder
     * @throws IllegalArgumentException if radius or height is less than or equal to 0
     */
    public static double volumeCylinder(double radius, double height) {
        if (radius <= 0) {
            throw new IllegalArgumentException(POSITIVE_RADIUS);
        }
        if (height <= 0) {
            throw new IllegalArgumentException(POSITIVE_HEIGHT);
        }
        return Math.PI * radius * radius * height;
    }

    /**
     * Calculate the volume of a hemisphere.
     *
     * @param radius radius of given hemisphere
     * @return volume of given hemisphere
     * @throws IllegalArgumentException if radius is less than or equal to 0
     */
    public static double volumeHemisphere(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException(POSITIVE_RADIUS);
        }
        return (2 * Math.PI * radius * radius * radius) / 3;
    }

    /**
     * Calculate the volume of a cone.
     *
     * @param radius radius of given cone
     * @param height height of given cone
     * @return volume of given cone
     * @throws IllegalArgumentException if radius or height is less than or equal to 0
     */
    public static double volumeCone(double radius, double height) {
        if (radius <= 0) {
            throw new IllegalArgumentException(POSITIVE_RADIUS);
        }
        if (height <= 0) {
            throw new IllegalArgumentException(POSITIVE_HEIGHT);
        }
        return (Math.PI * radius * radius * height) / 3;
    }

    /**
     * Calculate the volume of a prism.
     *
     * @param baseArea area of the given prism's base
     * @param height   height of given prism
     * @return volume of given prism
     * @throws IllegalArgumentException if baseArea or height is less than or equal to 0
     */
    public static double volumePrism(double baseArea, double height) {
        if (baseArea <= 0) {
            throw new IllegalArgumentException(POSITIVE_BASE_AREA);
        }
        if (height <= 0) {
            throw new IllegalArgumentException(POSITIVE_HEIGHT);
        }
        return baseArea * height;
    }

    /**
     * Calculate the volume of a pyramid.
     *
     * @param baseArea area of the given pyramid's base
     * @param height   height of given pyramid
     * @return volume of given pyramid
     * @throws IllegalArgumentException if baseArea or height is less than or equal to 0
     */
    public static double volumePyramid(double baseArea, double height) {
        if (baseArea <= 0) {
            throw new IllegalArgumentException(POSITIVE_BASE_AREA);
        }
        if (height <= 0) {
            throw new IllegalArgumentException(POSITIVE_HEIGHT);
        }
        return (baseArea * height) / 3;
    }

    /**
     * Calculate the volume of a frustum of a cone.
     *
     * @param r1     radius of the top of the frustum
     * @param r2     radius of the bottom of the frustum
     * @param height height of the frustum
     * @return volume of the frustum
     * @throws IllegalArgumentException if radii or height are less than or equal to 0
     */
    public static double volumeFrustumOfCone(double r1, double r2, double height) {
        if (r1 <= 0 || r2 <= 0) {
            throw new IllegalArgumentException(POSITIVE_RADIUS);
        }
        if (height <= 0) {
            throw new IllegalArgumentException(POSITIVE_HEIGHT);
        }
        return (Math.PI * height / 3) * (r1 * r1 + r2 * r2 + r1 * r2);
    }

    /**
     * Calculate the volume of a frustum of a pyramid.
     *
     * @param upperBaseArea area of the upper base
     * @param lowerBaseArea area of the lower base
     * @param height        height of the frustum
     * @return volume of the frustum
     * @throws IllegalArgumentException if base areas or height are less than or equal to 0
     */
    public static double volumeFrustumOfPyramid(double upperBaseArea, double lowerBaseArea, double height) {
        if (upperBaseArea <= 0 || lowerBaseArea <= 0) {
            throw new IllegalArgumentException(POSITIVE_BASE_AREA);
        }
        if (height <= 0) {
            throw new IllegalArgumentException(POSITIVE_HEIGHT);
        }
        return (upperBaseArea + lowerBaseArea + Math.sqrt(upperBaseArea * lowerBaseArea)) * height / 3;
    }

    /**
     * Calculate the volume of a torus.
     *
     * @param majorRadius major radius of a torus
     * @param minorRadius minor radius of a torus
     * @return volume of the torus
     * @throws IllegalArgumentException if radii are non-positive or minor radius exceeds major radius
     */
    public static double volumeTorus(double majorRadius, double minorRadius) {
        if (majorRadius <= 0) {
            throw new IllegalArgumentException("Major radius must be greater than 0");
        }
        if (minorRadius <= 0) {
            throw new IllegalArgumentException("Minor radius must be greater than 0");
        }
        if (minorRadius > majorRadius) {
            throw new IllegalArgumentException("Major radius must be greater than or equal to minor radius");
        }
        return 2 * Math.PI * Math.PI * majorRadius * minorRadius * minorRadius;
    }

    /**
     * Calculate the volume of an ellipsoid.
     *
     * @param a first semi-axis of an ellipsoid
     * @param b second semi-axis of an ellipsoid
     * @param c third semi-axis of an ellipsoid
     * @return volume of the ellipsoid
     * @throws IllegalArgumentException if any semi-axis is less than or equal to 0
     */
    public static double volumeEllipsoid(double a, double b, double c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            throw new IllegalArgumentException(POSITIVE_AXIS);
        }
        return (4 * Math.PI * a * b * c) / 3;
    }
}
