package com.thealgorithms.others;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implementation of the Flood Fill algorithm using an iterative BFS (Breadth-First Search) approach.
 *
 * <p>The Flood Fill algorithm is used to fill connected areas in an image with a new color, starting from a specified point.
 * This implementation uses an iterative BFS approach with a queue
 * instead of recursion to avoid stack overflow issues with large images.</p>
 *
 * <p><b>Implementation Features:</b></p>
 * <ul>
 *   <li>Supports 8-connected filling (horizontal, vertical, and diagonal directions)</li>
 *   <li>Uses BFS traversal through {@link java.util.Queue}</li>
 *   <li>Includes nested {@code Point} class to represent pixel coordinates</li>
 *   <li>Iterative approach avoids stack overflow for large images</li>
 * </ul>
 *
 * <p><b>Time Complexity:</b> O(M × N) where M and N are the dimensions of the image</p>
 * <p><b>Space Complexity:</b> O(M × N) in the worst case the queue stores every pixel</p>
 *
 * @see <a href="https://www.geeksforgeeks.org/dsa/flood-fill-algorithm">Flood Fill Algorithm - GeeksforGeeks</a>
 * @see <a href="https://en.wikipedia.org/wiki/Flood_fill">Flood Fill Algorithm - Wikipedia</a>
 */
public final class IterativeFloodFill {
    private IterativeFloodFill() {
    }

    /**
     * Iteratively fill the 2D image with new color
     *
     * @param image    The image to be filled
     * @param x        The x coordinate at which color is to be filled
     * @param y        The y coordinate at which color is to be filled
     * @param newColor The new color which to be filled in the image
     * @param oldColor The old color which is to be replaced in the image
     * @see <a href=https://www.geeksforgeeks.org/dsa/flood-fill-algorithm>FloodFill BFS<a/>
     */
    public static void floodFill(final int[][] image, final int x, final int y, final int newColor, final int oldColor) {
        if (image.length == 0 || image[0].length == 0 || newColor == oldColor || shouldSkipPixel(image, x, y, oldColor)) {
            return;
        }

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));

        int[] dx = {0, 0, -1, 1, 1, -1, 1, -1};
        int[] dy = {-1, 1, 0, 0, -1, 1, 1, -1};

        while (!queue.isEmpty()) {
            Point currPoint = queue.poll();

            if (shouldSkipPixel(image, currPoint.x, currPoint.y, oldColor)) {
                continue;
            }

            image[currPoint.x][currPoint.y] = newColor;

            for (int i = 0; i < 8; i++) {
                int curX = currPoint.x + dx[i];
                int curY = currPoint.y + dy[i];

                if (!shouldSkipPixel(image, curX, curY, oldColor)) {
                    queue.add(new Point(curX, curY));
                }
            }
        }
    }

    /**
     * Represents a point in 2D space with integer coordinates.
     */
    private static class Point {
        final int x;
        final int y;

        Point(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * Checks if a pixel should be skipped during flood fill operation.
     *
     * @param image    The image to get boundaries
     * @param x        The x coordinate of pixel to check
     * @param y        The y coordinate of pixel to check
     * @param oldColor The old color which is to be replaced in the image
     * @return {@code true} if pixel should be skipped, else {@code false}
     */
    private static boolean shouldSkipPixel(final int[][] image, final int x, final int y, final int oldColor) {

        if (x < 0 || x >= image.length || y < 0 || y >= image[0].length || image[x][y] != oldColor) {
            return true;
        }

        return false;
    }
}
