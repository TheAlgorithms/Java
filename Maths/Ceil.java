package Maths;

public class Ceil {
    public static void main(String[] args) {
        assert ceil(10) == Math.ceil(10);
        assert ceil(-10) == Math.ceil(-10);
        assert ceil(10.0) == Math.ceil(10.0);
        assert ceil(-10.0) == Math.ceil(-10.0);
        assert ceil(10.1) == Math.ceil(10.1);
        assert ceil(-10.1) == Math.ceil(-10.1);
        assert ceil(0) == Math.ceil(0);
        assert ceil(-0) == Math.ceil(-0);
        assert ceil(0.0) == Math.ceil(0.0);
        assert ceil(-0.0) == Math.ceil(-0.0);
    }

    /**
     * Returns the smallest (closest to negative infinity)
     *
     * @param number the number
     * @return the smallest (closest to negative infinity) of given {@code number}
     */
    public static double ceil(double number) {
        if (number - (int) number == 0) {
            return number;
        } else if (number - (int) number > 0) {
            return (int) (number + 1);
        } else {
            return (int) number;
        }
    }
}