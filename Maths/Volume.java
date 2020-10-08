/**
 * contains functions to compute the volumes of some geometrical objects
 * See https://en.wikipedia.org/wiki/Volume
 */
public class Volume {

    public static void main(String[] args) {
        // Test formulas
        assert Double.compare(getCubeVolume(2), 8.0) == 0;
        assert Double.compare(getCuboidVolume(1, 2, 3), 6.0) == 0;
        assert Double.compare(getPrismVolume(4, 2), 8.0) == 0;
        assert Double.compare(getPyramidVolume(4, 3), 4.0) == 0;
        assert Double.compare(getRegularTetrahedronVolume(2), 0.9428090415820635) == 0;
        assert Double.compare(getSphereVolume(2), 33.510321638291124) == 0;
        assert Double.compare(getCircularCylinderVolume(2, 3), 37.69911184307752) == 0;
        assert Double.compare(getConeVolume(2, 3), 12.566370614359172) == 0;
    }

    /**
     * Calculates the volume of a cube
     *
     * @param length the side length of the cube
     * @return the volume of the cube
     */
    private static double getCubeVolume(double length) {
        return Math.pow(length, 3);
    }

    /**
     * Calculates the volume of a cuboid
     *
     * @param a the first side length
     * @param b the second side length
     * @param c the third side length
     * @return the volume of the cuboid
     */
    private static double getCuboidVolume(double a, double b, double c) {
        return a * b * c;
    }

    /**
     * Calculates the volume of a prism
     *
     * @param base the area of the base of the prism
     * @param height height of the prism
     * @return the volume of the prism
     */
    private static double getPrismVolume(double base, double height) {
        return base * height;
    }

    /**
     * Calculates the volume of a pyramid
     *
     * @param base the area of the base of the pyramid
     * @param height the height of the pyramid
     * @return the volume of the pyramid
     */
    private static double getPyramidVolume(double base, double height) {
        return base * height / 3.0;
    }

    /**
     * Calculates the volume of a regular tetrahedron
     *
     * @param length the side length of the tetrahedron
     * @return the volume of the tetrahedron
     */
    private static double getRegularTetrahedronVolume(double length) {
        return Math.sqrt(2) / 12 * Math.pow(length, 3);
    }

    /**
     * Calculates the volume of a sphere
     *
     * @param radius the radius of the sphere
     * @return the volume of the sphere
     */
    private static double getSphereVolume(double radius) {
        return 4.0 / 3.0 * Math.PI * Math.pow(radius, 3);
    }

    /**
     * Calculates the volume of a circular cylinder
     *
     * @param radius the radius of the cylinder
     * @param height the height of the cylinder
     * @return the volume of the cylinder
     */
    private static double getCircularCylinderVolume(double radius, double height) {
        return Math.PI * Math.pow(radius, 2) * height;
    }

    /**
     * Calculates the volume of a cone
     *
     * @param radius the radius of the cone
     * @param height the height of the cone
     * @return the volume of the cone
     */
    private static double getConeVolume(double radius, double height) {
        return Math.PI * Math.pow(radius, 2) * height / 3;
    }
}
