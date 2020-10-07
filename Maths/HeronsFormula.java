package Maths;

/**
 * Heron's formula gives the area of a triangle when the length of all three sides are known.
 * <a href="https://www.wikiwand.com/en/Heron%27s_formula">https://www.wikiwand.com/en/Heron%27s_formula</a>
 */

public class HeronsFormula {
    /**
     *
     * @param a a side of the triangle
     * @param b a side of the triangle
     * @param c a side of the triangle
     * @return the calculated area of the triangle using Heron's formula
     */
    public static double calculateArea(double a, double b, double c) {
        /* Checks if the three sides can really represent a triangle */
        if ((a + b < c) || (b + c < a) || (a + c < b)) {
            return 0.0;
        }

        double halfPerimeter = (a + b + c) / 2.0;
        double area = Math.sqrt(halfPerimeter * (halfPerimeter - a) * (halfPerimeter - b) * (halfPerimeter - c));

        return area;
    }
    /**
     * Driver code
     */
    public static void main(String[] args) {
        assert calculateArea(5, 5, 5) == 10.825317547305483;
        assert calculateArea(4, 4, 5) == 7.806247497997997;
        assert calculateArea(10, 2, 3) == 0.0;
    }
}
