package com.thealgorithms.lineclipping;

import com.thealgorithms.lineclipping.utils.Line;
import com.thealgorithms.lineclipping.utils.Point;

/**
 * @author shikarisohan
 * @since 10/4/24
 * Cohen-Sutherland Line Clipping Algorithm
 *
 * This algorithm is used to clip a line segment to a rectangular window.
 * It assigns a region code to each endpoint of the line segment, and
 * then efficiently determines whether the line segment is fully inside,
 * fully outside, or partially inside the window.
 *
 * Reference:
 * https://en.wikipedia.org/wiki/Cohen%E2%80%93Sutherland_algorithm
 *
 * Clipping window boundaries are defined as (xMin, yMin) and (xMax, yMax).
 * The algorithm computes the clipped line segment if it's partially or
 * fully inside the clipping window.
 */
public class CohenSutherland {

    // Region codes for the 9 regions
    private static final int INSIDE = 0; // 0000
    private static final int LEFT = 1; // 0001
    private static final int RIGHT = 2; // 0010
    private static final int BOTTOM = 4; // 0100
    private static final int TOP = 8; // 1000

    // Define the clipping window
    double xMin;
    double yMin;
    double xMax;
    double yMax;

    public CohenSutherland(double xMin, double yMin, double xMax, double yMax) {
        this.xMin = xMin;
        this.yMin = yMin;
        this.xMax = xMax;
        this.yMax = yMax;
    }

    // Compute the region code for a point (x, y)
    private int computeCode(double x, double y) {
        int code = INSIDE;

        if (x < xMin) // to the left of rectangle
        {
            code |= LEFT;
        } else if (x > xMax) // to the right of rectangle
        {
            code |= RIGHT;
        }
        if (y < yMin) // below the rectangle
        {
            code |= BOTTOM;
        } else if (y > yMax) // above the rectangle
        {
            code |= TOP;
        }

        return code;
    }

    // Cohen-Sutherland algorithm to return the clipped line
    public Line cohenSutherlandClip(Line line) {
        double x1 = line.start.x;
        double y1 = line.start.y;
        double x2 = line.end.x;
        double y2 = line.end.y;

        int code1 = computeCode(x1, y1);
        int code2 = computeCode(x2, y2);
        boolean accept = false;

        while (true) {
            if ((code1 == 0) && (code2 == 0)) {
                // Both points are inside the rectangle
                accept = true;
                break;
            } else if ((code1 & code2) != 0) {
                // Both points are outside the rectangle in the same region
                break;
            } else {
                // Some segment of the line is inside the rectangle
                double x = 0;
                double y = 0;

                // Pick an endpoint that is outside the rectangle
                int codeOut = (code1 != 0) ? code1 : code2;

                // Find the intersection point using the line equation
                if ((codeOut & TOP) != 0) {
                    // Point is above the rectangle
                    x = x1 + (x2 - x1) * (yMax - y1) / (y2 - y1);
                    y = yMax;
                } else if ((codeOut & BOTTOM) != 0) {
                    // Point is below the rectangle
                    x = x1 + (x2 - x1) * (yMin - y1) / (y2 - y1);
                    y = yMin;
                } else if ((codeOut & RIGHT) != 0) {
                    // Point is to the right of the rectangle
                    y = y1 + (y2 - y1) * (xMax - x1) / (x2 - x1);
                    x = xMax;
                } else if ((codeOut & LEFT) != 0) {
                    // Point is to the left of the rectangle
                    y = y1 + (y2 - y1) * (xMin - x1) / (x2 - x1);
                    x = xMin;
                }

                // Replace the point outside the rectangle with the intersection point
                if (codeOut == code1) {
                    x1 = x;
                    y1 = y;
                    code1 = computeCode(x1, y1);
                } else {
                    x2 = x;
                    y2 = y;
                    code2 = computeCode(x2, y2);
                }
            }
        }

        if (accept) {
            return new Line(new Point(x1, y1), new Point(x2, y2));
        } else {

            return null; // The line is fully rejected
        }
    }
}
