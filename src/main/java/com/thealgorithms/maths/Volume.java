package com.thealgorithms.maths;

/* Calculate the volume of various shapes.*/
public class Volume {

    /**
     * Calculate the volume of a cube.
     *
     * @param sideLength length of the given cube's sides
     * @return volume of the given cube
     */
    public static double volumeCube(double sideLength) {
        return sideLength * sideLength * sideLength;
    }

    /**
     * Calculate the volume of a cuboid.
     *
     * @param width  width of given cuboid
     * @param height height of given cuboid
     * @param length length of given cuboid
     * @return volume of given cuboid
     */
    public static double volumeCuboid(double width, double height, double length) {
        return width * height * length;
    }

    /**
     * Calculate the volume of a sphere.
     *
     * @param radius radius of given sphere
     * @return volume of given sphere
     */
    public static double volumeSphere(double radius) {
        return (4 * Math.PI * radius * radius * radius) / 3;
    }

    /**
     * Calculate volume of a cylinder
     *
     * @param radius radius of the given cylinder's floor
     * @param height height of the given cylinder
     * @return volume of given cylinder
     */
    public static double volumeCylinder(double radius, double height) {
        return Math.PI * radius * radius * height;
    }

    /**
     * Calculate the volume of a hemisphere.
     *
     * @param radius radius of given hemisphere
     * @return volume of given hemisphere
     */
    public static double volumeHemisphere(double radius) {
        return (2 * Math.PI * radius * radius * radius) / 3;
    }

    /**
     * Calculate the volume of a cone.
     *
     * @param radius radius of given cone
     * @param height of given cone
     * @return volume of given cone
     */
    public static double volumeCone(double radius, double height) {
        return (Math.PI * radius * radius * height) / 3;
    }

    /**
     * Calculate the volume of a prism.
     *
     * @param baseArea  area of the given prism's base
     * @param height of given prism
     * @return volume of given prism
     */
    public static double volumePrism(double baseArea, double height) {
        return baseArea * height;
    }

    /**
     * Calculate the volume of a pyramid.
     *
     * @param baseArea   of the given pyramid's base
     * @param height of given pyramid
     * @return volume of given pyramid
     */
    public static double volumePyramid(double baseArea, double height) {
        return (baseArea * height) / 3;
    }
}
