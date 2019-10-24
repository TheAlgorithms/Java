package maths;

public class Pow {
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
        long result = 1;
        for (int i = 1; i <= b; i++) {
            result *= a;
        }
        return result;
    }
}
