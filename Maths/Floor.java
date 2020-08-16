package Maths;

public class Floor {
    public static void main(String[] args) {
        assert floor(10) == Math.floor(10);
        assert floor(-10) == Math.floor(-10);
        assert floor(10.0) == Math.floor(10.0);
        assert floor(-10.0) == Math.floor(-10.0);
        assert floor(10.1) == Math.floor(10.1);
        assert floor(-10.1) == Math.floor(-10.1);
        assert floor(0) == Math.floor(0);
        assert floor(-0) == Math.floor(-0);
        assert floor(0.0) == Math.floor(0.0);
        assert floor(-0.0) == Math.floor(-0.0);
    }

    /**
     * Returns the largest (closest to positive infinity)
     *
     * @param number the number
     * @return the largest (closest to positive infinity)  of given {@code number}
     */
    public static double floor(double number) {
        if (number - (int) number == 0) {
            return number;
        } else if (number - (int) number > 0) {
            return (int) number;
        } else {
            return (int) number - 1;
        }
    }
}