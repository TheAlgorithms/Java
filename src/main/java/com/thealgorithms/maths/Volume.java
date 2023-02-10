package com.thealgorithms.maths;

/* Find volume of various shapes.*/
public class Volume {

    /**
     * Calculate the volume of a cube.
     *
     * @param sideLength side length of cube
     * @return volume of given cube
     */
    public static double volumeCube(double sidelength) {
        return sidelength * sidelength * sidelength;
    }

    /**
     * Calculate the volume of a cuboid.
     *
     * @param width  of cuboid
     * @param height of cuboid
     * @param length of cuboid
     * @return volume of given cuboid
     */
    public static double volumeCuboid(double width, double height, double length) {
        return width * height * length;
    }

    /**
     * Calculate the volume of a sphere.
     *
     * @param radius radius of sphere
     * @return volume of given sphere
     */
    public static double volumeSphere(double radius) {
        return (4 * Math.PI * radius * radius * radius) / 3;
    }

    /**
     * Calculate volume of a cylinder
     *
     * @param radius radius of the floor
     * @param height height of the cylinder.
     * @return volume of given cylinder
     */
    public static double volumeCylinder(double radius, double height) {
        return Math.PI * radius * radius * height;
    }

    /**
     * Calculate the volume of a hemisphere.
     *
     * @param radius radius of hemisphere
     * @return volume of given hemisphere
     */
    public static double volumeHemisphere(double radius) {
        return (2 * Math.PI * radius * radius * radius) / 3;
    }

    /**
     * Calculate the volume of a cone.
     *
     * @param radius radius of cone.
     * @param height of cone.
     * @return volume of given cone.
     */
    public static double volumeCone(double radius, double height) {
        return (Math.PI * radius * radius * height) / 3;
    }

    /**
     * Calculate the volume of a prism.
     *
     * @param area   of the base.
     * @param height of prism.
     * @return volume of given prism.
     */
    public static double volumePrism(double basearea, double height) {
        return basearea * height;
    }

    /**
     * Calculate the volume of a pyramid.
     *
     * @param area   of the base.
     * @param height of pyramid.
     * @return volume of given pyramid.
     */
    public static double volumePyramid(double basearea, double height) {
        return (basearea * height) / 3;
    }
}
