package com.thealgorithms.maths;


/* Find volume of various shapes.*/
public class Volume {

    public static void main(String[] args) {

        /* test cube */
        assert Double.compare(volumeCube(7), 343.0) == 0;

        /* test cuboid */
        assert Double.compare(volumeCuboid(2, 5, 7), 70.0) == 0;

        /* test sphere */
        assert Double.compare(volumeSphere(5), 523.5987755982989) == 0;

        /* test cylinder */
        assert Double.compare(volumeCylinder(1, 2), 12.566370614359172) == 0;

        /* test hemisphere */
        assert Double.compare(volumeHemisphere(5), 261.79938779914943) == 0;

        /* test cone */
        assert Double.compare(volumeCone(5, 7), 916.297857297023) == 0;
        
        /*test prism*/
        assert Double.compare(volumePrism(10, 2), 20.0) == 0;
        
        /*test pyramid*/
        assert Double.compare(volumePyramid(10, 3), 10.0) == 0;

    }

    /**
     * Calculate the volume of a cube.
     *
     * @param sideLength side length of cube
     * @return volume of given cube
     */
    private static double volumeCube(double sidelength) {
        return sidelength * sidelength * sidelength;
    }

    /**
     * Calculate the volume of a cuboid.
     *
     * @param width of cuboid
     * @param height of cuboid
     * @param length of cuboid
     * @return volume of given cuboid
     */
    private static double volumeCuboid(double width, double height, double length) {
        return width * height * length;
    }

    /**
     * Calculate the volume of a sphere.
     *
     * @param radius radius of sphere
     * @return volume of given sphere
     */
    private static double volumeSphere(double radius) {
        return 4 / 3 * Math.PI * radius * radius * radius;
    }

    /**
     * Calculate volume of a cylinder
     *
     * @param radius radius of the floor
     * @param height height of the cylinder.
     * @return volume of given cylinder
     */
    private static double volumeCylinder(double radius, double height) {
        return Math.PI * radius * radius * height;
    }

    /**
     * Calculate the volume of a hemisphere.
     *
     * @param radius radius of hemisphere
     * @return volume of given hemisphere
     */
    private static double volumeHemisphere(double radius) {
        return 2 / 3 * Math.PI * radius * radius * radius;
    }

    /**
     * Calculate the volume of a cone.
     *
     * @param radius radius of cone.
     * @param height of cone.
     * @return volume of given cone.
     */
    private static double volumeCone(double radius, double height) {
        return Math.PI * radius * radius * height / 3;
    }
    
    /**
     * Calculate the volume of a prism.
     *
     * @param area of the base.
     * @param height of prism.
     * @return volume of given prism.
     */
    private static double volumePrism(double basearea, double height) {
        return basearea * height;
    }
    
    /**
     * Calculate the volume of a pyramid.
     *
     * @param area of the base.
     * @param height of pyramid.
     * @return volume of given pyramid.
     */
    private static double volumePyramid(double basearea, double height) {
        return basearea * height / 3;
    }
}
