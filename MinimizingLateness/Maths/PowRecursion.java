package Maths;

public class PowRecursion {
    public static void main(String[] args) {
        assert Double.compare(pow(2, 0), Math.pow(2, 0)) == 0;
        assert Double.compare(pow(0, 2), Math.pow(0, 2)) == 0;
        assert Double.compare(pow(2, 10), Math.pow(2, 10)) == 0;
        assert Double.compare(pow(10, 2), Math.pow(10, 2)) == 0;
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
        return b == 0 ? 1 : a * pow(a, b - 1);
    }
}