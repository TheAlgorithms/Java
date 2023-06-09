package com.thealgorithms.backtracking;

/**
 * Java program for Flood fill algorithm.
 * @author Akshay Dubey (https://github.com/itsAkshayDubey)
 */
public class FloodFill {

    /**
     * Get the color at the given coordinates of a 2D image
     *
     * @param image The image to be filled
     * @param x The x co-ordinate of which color is to be obtained
     * @param y The y co-ordinate of which color is to be obtained
     */

    public static int getPixel(int[][] image, int x, int y) {
        return image[x][y];
    }

    /**
     * Put the color at the given coordinates of a 2D image
     *
     * @param image The image to be filled
     * @param x The x co-ordinate at which color is to be filled
     * @param y The y co-ordinate at which color is to be filled
     */
    public static void putPixel(int[][] image, int x, int y, int newColor) {
        image[x][y] = newColor;
    }

    /**
     * Fill the 2D image with new color
     *
     * @param image The image to be filled
     * @param x The x co-ordinate at which color is to be filled
     * @param y The y co-ordinate at which color is to be filled
     * @param newColor The new color which to be filled in the image
     * @param oldColor The old color which is to be replaced in the image
     */
    public static void floodFill(int[][] image, int x, int y, int newColor, int oldColor) {
        if (x < 0 || x >= image.length) return;
        if (y < 0 || y >= image[x].length) return;
        if (getPixel(image, x, y) != oldColor) return;

        putPixel(image, x, y, newColor);

        /* Recursively check for horizontally & vertically adjacent coordinates */
        floodFill(image, x + 1, y, newColor, oldColor);
        floodFill(image, x - 1, y, newColor, oldColor);
        floodFill(image, x, y + 1, newColor, oldColor);
        floodFill(image, x, y - 1, newColor, oldColor);

        /* Recursively check for diagonally adjacent coordinates  */
        floodFill(image, x + 1, y - 1, newColor, oldColor);
        floodFill(image, x - 1, y + 1, newColor, oldColor);
        floodFill(image, x + 1, y + 1, newColor, oldColor);
        floodFill(image, x - 1, y - 1, newColor, oldColor);
    }
}
