package com.thealgorithms.lineclipping;

import com.thealgorithms.lineclipping.utils.Line;
import com.thealgorithms.lineclipping.utils.Point;

/**
 * @author shikarisohan
 * @since 10/5/24
 *
 *  * The Liang-Barsky line clipping algorithm is an efficient algorithm for
 *  * line clipping against a rectangular window. It is based on the parametric
 *  * equation of a line and checks the intersections of the line with the
 *  * window boundaries. This algorithm calculates the intersection points,
 *  * if any, and returns the clipped line that lies inside the window.
 *  *
 *  * Reference:
 *  * https://en.wikipedia.org/wiki/Liang%E2%80%93Barsky_algorithm
 *
 * Clipping window boundaries are defined as (xMin, yMin) and (xMax, yMax).
 * The algorithm computes the clipped line segment if it's partially or
 * fully inside the clipping window.
 */
public class LiangBarsky {

    // Define the clipping window
    double xMin;
    double xMax;
    double yMin;
    double yMax;

    public LiangBarsky(double xMin, double yMin, double xMax, double yMax) {
        this.xMin = xMin;
        this.yMin = yMin;
        this.xMax = xMax;
        this.yMax = yMax;
    }

    // Liang-Barsky algorithm to return the clipped line
    public Line liangBarskyClip(Line line) {
        double dx = line.end.x - line.start.x;
        double dy = line.end.y - line.start.y;

        double[] p = {-dx, dx, -dy, dy};
        double[] q = {line.start.x - xMin, xMax - line.start.x, line.start.y - yMin, yMax - line.start.y};

        double[] resultT = clipLine(p, q);

        if (resultT == null) {
            return null; // Line is outside the clipping window
        }

        return calculateClippedLine(line, resultT[0], resultT[1], dx, dy);
    }

    // clip the line by adjusting t0 and t1 for each edge
    private double[] clipLine(double[] p, double[] q) {
        double t0 = 0.0;
        double t1 = 1.0;

        for (int i = 0; i < 4; i++) {
            double t = q[i] / p[i];
            if (p[i] == 0 && q[i] < 0) {
                return null; // Line is outside the boundary
            } else if (p[i] < 0) {
                if (t > t1) {
                    return null;
                } // Line is outside
                if (t > t0) {
                    t0 = t;
                } // Update t0
            } else if (p[i] > 0) {
                if (t < t0) {
                    return null;
                } // Line is outside
                if (t < t1) {
                    t1 = t;
                } // Update t1
            }
        }

        return new double[] {t0, t1}; // Return valid t0 and t1
    }

    // calculate the clipped line based on t0 and t1
    private Line calculateClippedLine(Line line, double t0, double t1, double dx, double dy) {
        double clippedX1 = line.start.x + t0 * dx;
        double clippedY1 = line.start.y + t0 * dy;
        double clippedX2 = line.start.x + t1 * dx;
        double clippedY2 = line.start.y + t1 * dy;

        return new Line(new Point(clippedX1, clippedY1), new Point(clippedX2, clippedY2));
    }
}
