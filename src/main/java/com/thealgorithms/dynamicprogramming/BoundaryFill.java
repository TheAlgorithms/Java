package com.thealgorithms.dynamicprogramming;

/**
 * Java program for Boundary fill algorithm.
 * @author Akshay Dubey (https://github.com/itsAkshayDubey)
 */
public final class BoundaryFill {
    private BoundaryFill() {
    }

    /**
     * Get the color at the given co-odrinates of a 2D image
     *
     * @param image The image to be filled
     * @param xCoordinate The x co-ordinate of which color is to be obtained
     * @param yCoordinate The y co-ordinate of which color is to be obtained
     */
    public static int getPixel(int[][] image, int xCoordinate, int yCoordinate) {
        return image[xCoordinate][yCoordinate];
    }

    /**
     * Put the color at the given co-odrinates of a 2D image
     *
     * @param image The image to be filed
     * @param xCoordinate The x co-ordinate at which color is to be filled
     * @param yCoordinate The y co-ordinate at which color is to be filled
     */
    public static void putPixel(int[][] image, int xCoordinate, int yCoordinate, int newColor) {
        image[xCoordinate][yCoordinate] = newColor;
    }

    /**
     * Fill the 2D image with new color
     *
     * @param image The image to be filed
     * @param xCoordinate The x co-ordinate at which color is to be filled
     * @param yCoordinate The y co-ordinate at which color is to be filled
     * @param newColor The new color which to be filled in the image
     * @param boundaryColor The old color which is to be replaced in the image
     */
    public static void boundaryFill(int[][] image, int xCoordinate, int yCoordinate, int newColor, int boundaryColor) {
        if (xCoordinate >= 0 && yCoordinate >= 0 && getPixel(image, xCoordinate, yCoordinate) != newColor && getPixel(image, xCoordinate, yCoordinate) != boundaryColor) {
            putPixel(image, xCoordinate, yCoordinate, newColor);
            boundaryFill(image, xCoordinate + 1, yCoordinate, newColor, boundaryColor);
            boundaryFill(image, xCoordinate - 1, yCoordinate, newColor, boundaryColor);
            boundaryFill(image, xCoordinate, yCoordinate + 1, newColor, boundaryColor);
            boundaryFill(image, xCoordinate, yCoordinate - 1, newColor, boundaryColor);
            boundaryFill(image, xCoordinate + 1, yCoordinate - 1, newColor, boundaryColor);
            boundaryFill(image, xCoordinate - 1, yCoordinate + 1, newColor, boundaryColor);
            boundaryFill(image, xCoordinate + 1, yCoordinate + 1, newColor, boundaryColor);
            boundaryFill(image, xCoordinate - 1, yCoordinate - 1, newColor, boundaryColor);
        }
    }
}
