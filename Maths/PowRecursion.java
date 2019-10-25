package Maths;

public class PowRecursion {
    public static void main(String[] args) {
        assert pow(2, 0) == Math.pow(2, 0);
        assert pow(0, 2) == Math.pow(0, 2);
        assert pow(2, 10) == Math.pow(2, 10);
        assert pow(10, 2) == Math.pow(10, 2);
    }

    /**
     * Returns the value of the first argument raised to the power of the
     * second argument
     *
     * @param a the base.
     * @param b the exponent.
     * @return the value {@code a}<sup>{@code b}</sup>.
     */
    public static long pow(int a, int b) {
        if (b == 0) {
            return 1;
        } else {
            return a * pow(a, b - 1);
        }
    }
}
