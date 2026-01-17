/**
 * Distance Between Two Points in 2D Space
 *
 * Reference:
 * https://en.wikipedia.org/wiki/Euclidean_distance
 */
public class DistanceBetweenTwoPoints {

    /**
     * Calculate the Euclidean distance between two points.
     *
     * @param x1 x-coordinate of first point
     * @param y1 y-coordinate of first point
     * @param x2 x-coordinate of second point
     * @param y2 y-coordinate of second point
     * @return Euclidean distance
     */
    public static double calculate(
            double x1, double y1, double x2, double y2) {

        return Math.sqrt(
                Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)
        );
    }

    public static void main(String[] args) {
        System.out.println(calculate(0, 0, 3, 4)); // 5.0
    }
}
