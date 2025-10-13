/**
 * This class provides a method to calculate the area of a simple polygon
 * using the Shoelace formula (Gauss's area formula).
 *
 * The vertices should be ordered either clockwise or counter-clockwise.
 */
public class AreaOfPolygon {

    /**
     * Calculates the area of a polygon using x and y coordinates.
     * Uses the Shoelace formula:
     * Area = (1/2) * |x[i]*y[i+1] - x[i+1]*y[i]|
     *
     * @param x x-coordinates of the polygon's vertices
     * @param y y-coordinates of the polygon's vertices
     * @return area of the polygon
     * @throws IllegalArgumentException if x and y are different lengths
     */
    public static double area(double[] x, double[] y) {
        if (x.length != y.length) {
            throw new IllegalArgumentException("Arrays must be same length");
        }

        double sum = 0.0;
        int n = x.length;

        for (int i = 0; i < n; i++) {
            sum += (x[i] * y[(i + 1) % n]) - (x[(i + 1) % n] * y[i]);
        }

        return Math.abs(sum) / 2.0;
    }

    // Sample usage
    public static void main(String[] args) {
        double[] x = {0, 4, 4, 0};
        double[] y = {0, 0, 4, 4};
        System.out.println("Area: " + area(x, y)); // Should print 16.0
    }
}