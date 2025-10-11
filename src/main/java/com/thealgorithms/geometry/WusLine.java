package com.thealgorithms.geometry;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code WusLine} class implements Xiaolin Wu's line drawing algorithm,
 * which produces anti-aliased lines by varying pixel brightness
 * according to the line's proximity to pixel centers.
 *
 * This implementation returns the pixel coordinates along with
 * their associated intensity values (in range [0.0, 1.0]), allowing
 * rendering systems to blend accordingly.
 *
 * The algorithm works by:
 * - Computing a line's intersection with pixel boundaries
 * - Assigning intensity values based on distance from pixel centers
 * - Drawing pairs of pixels perpendicular to the line's direction
 *
 * Reference: Xiaolin Wu, "An Efficient Antialiasing Technique",
 * Computer Graphics (SIGGRAPH '91 Proceedings).
 *
 */
public final class WusLine {

    private WusLine() {
        // Utility class; prevent instantiation.
    }

    /**
     * Represents a pixel and its intensity for anti-aliased rendering.
     *
     * The intensity value determines how bright the pixel should be drawn,
     * with 1.0 being fully opaque and 0.0 being fully transparent.
     */
    public static class Pixel {
        /** The pixel's coordinate on the screen. */
        public final Point point;

        /** The pixel's intensity value, clamped to the range [0.0, 1.0]. */
        public final double intensity;

        /**
         * Constructs a new Pixel with the given coordinates and intensity.
         *
         * @param x the x-coordinate of the pixel
         * @param y the y-coordinate of the pixel
         * @param intensity the brightness/opacity of the pixel, will be clamped to [0.0, 1.0]
         */
        public Pixel(int x, int y, double intensity) {
            this.point = new Point(x, y);
            this.intensity = Math.clamp(intensity, 0.0, 1.0);
        }
    }

    /**
     * Internal class to hold processed endpoint data.
     */
    private static class EndpointData {
        final int xPixel;
        final int yPixel;
        final double yEnd;
        final double xGap;

        EndpointData(int xPixel, int yPixel, double yEnd, double xGap) {
            this.xPixel = xPixel;
            this.yPixel = yPixel;
            this.yEnd = yEnd;
            this.xGap = xGap;
        }
    }

    /**
     * Draws an anti-aliased line using Wu's algorithm.
     *
     * The algorithm produces smooth lines by drawing pairs of pixels at each
     * x-coordinate (or y-coordinate for steep lines), with intensities based on
     * the line's distance from pixel centers.
     *
     * @param x0 the x-coordinate of the line's start point
     * @param y0 the y-coordinate of the line's start point
     * @param x1 the x-coordinate of the line's end point
     * @param y1 the y-coordinate of the line's end point
     * @return a list of {@link Pixel} objects representing the anti-aliased line,
     *         ordered from start to end
     */
    public static List<Pixel> drawLine(int x0, int y0, int x1, int y1) {
        List<Pixel> pixels = new ArrayList<>();

        // Determine if the line is steep (more vertical than horizontal)
        boolean steep = Math.abs(y1 - y0) > Math.abs(x1 - x0);

        if (steep) {
            // For steep lines, swap x and y coordinates to iterate along y-axis
            int temp = x0;
            x0 = y0;
            y0 = temp;

            temp = x1;
            x1 = y1;
            y1 = temp;
        }

        if (x0 > x1) {
            // Ensure we always draw from left to right
            int temp = x0;
            x0 = x1;
            x1 = temp;

            temp = y0;
            y0 = y1;
            y1 = temp;
        }

        // Calculate the line's slope
        double deltaX = x1 - (double) x0;
        double deltaY = y1 - (double) y0;
        double gradient = (deltaX == 0) ? 1.0 : deltaY / deltaX;

        // Process the first endpoint
        EndpointData firstEndpoint = processEndpoint(x0, y0, gradient, true);
        addEndpointPixels(pixels, firstEndpoint, steep);

        // Process the second endpoint
        EndpointData secondEndpoint = processEndpoint(x1, y1, gradient, false);
        addEndpointPixels(pixels, secondEndpoint, steep);

        // Draw the main line between endpoints
        drawMainLine(pixels, firstEndpoint, secondEndpoint, gradient, steep);

        return pixels;
    }

    /**
     * Processes a line endpoint to determine its pixel coordinates and intensities.
     *
     * @param x the x-coordinate of the endpoint
     * @param y the y-coordinate of the endpoint
     * @param gradient the slope of the line
     * @param isStart true if this is the start endpoint, false if it's the end
     * @return an {@link EndpointData} object containing processed endpoint information
     */
    private static EndpointData processEndpoint(double x, double y, double gradient, boolean isStart) {
        double xEnd = round(x);
        double yEnd = y + gradient * (xEnd - x);
        double xGap = isStart ? rfpart(x + 0.5) : fpart(x + 0.5);

        int xPixel = (int) xEnd;
        int yPixel = (int) Math.floor(yEnd);

        return new EndpointData(xPixel, yPixel, yEnd, xGap);
    }

    /**
     * Adds the two endpoint pixels (one above, one below the line) to the pixel list.
     *
     * @param pixels the list to add pixels to
     * @param endpoint the endpoint data containing coordinates and gaps
     * @param steep true if the line is steep (coordinates should be swapped)
     */
    private static void addEndpointPixels(List<Pixel> pixels, EndpointData endpoint, boolean steep) {
        double fractionalY = fpart(endpoint.yEnd);
        double complementFractionalY = rfpart(endpoint.yEnd);

        if (steep) {
            pixels.add(new Pixel(endpoint.yPixel, endpoint.xPixel, complementFractionalY * endpoint.xGap));
            pixels.add(new Pixel(endpoint.yPixel + 1, endpoint.xPixel, fractionalY * endpoint.xGap));
        } else {
            pixels.add(new Pixel(endpoint.xPixel, endpoint.yPixel, complementFractionalY * endpoint.xGap));
            pixels.add(new Pixel(endpoint.xPixel, endpoint.yPixel + 1, fractionalY * endpoint.xGap));
        }
    }

    /**
     * Draws the main portion of the line between the two endpoints.
     *
     * @param pixels the list to add pixels to
     * @param firstEndpoint the processed start endpoint
     * @param secondEndpoint the processed end endpoint
     * @param gradient the slope of the line
     * @param steep true if the line is steep (coordinates should be swapped)
     */
    private static void drawMainLine(List<Pixel> pixels, EndpointData firstEndpoint, EndpointData secondEndpoint, double gradient, boolean steep) {
        // Start y-intersection after the first endpoint
        double intersectionY = firstEndpoint.yEnd + gradient;

        // Iterate through x-coordinates between the endpoints
        for (int x = firstEndpoint.xPixel + 1; x < secondEndpoint.xPixel; x++) {
            int yFloor = (int) Math.floor(intersectionY);
            double fractionalPart = fpart(intersectionY);
            double complementFractionalPart = rfpart(intersectionY);

            if (steep) {
                pixels.add(new Pixel(yFloor, x, complementFractionalPart));
                pixels.add(new Pixel(yFloor + 1, x, fractionalPart));
            } else {
                pixels.add(new Pixel(x, yFloor, complementFractionalPart));
                pixels.add(new Pixel(x, yFloor + 1, fractionalPart));
            }

            intersectionY += gradient;
        }
    }

    /**
     * Returns the fractional part of a number.
     *
     * @param x the input number
     * @return the fractional part (always in range [0.0, 1.0))
     */
    private static double fpart(double x) {
        return x - Math.floor(x);
    }

    /**
     * Returns the reverse fractional part of a number (1 - fractional part).
     *
     * @param x the input number
     * @return 1.0 minus the fractional part (always in range (0.0, 1.0])
     */
    private static double rfpart(double x) {
        return 1.0 - fpart(x);
    }

    /**
     * Rounds a number to the nearest integer.
     *
     * @param x the input number
     * @return the nearest integer value as a double
     */
    private static double round(double x) {
        return Math.floor(x + 0.5);
    }
}
