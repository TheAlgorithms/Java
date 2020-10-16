package Maths;

//POWER (exponentials) Examples (a^b)
public class Pow {
    public static void main(String[] args) {
        assert pow(2, 0) == Math.pow(2, 0); // == 1
        assert pow(0, 2) == Math.pow(0, 2); // == 0
        assert pow(2, 10) == Math.pow(2, 10); // == 1024
        assert pow(10, 2) == Math.pow(10, 2); // == 100
    }

    /**
     * Returns the value of the first argument raised to the power of the
     * second argument
     *
     * @param a the base.
     * @param b the exponent.
     * @return the value {@code a}<sup>{@code b}</sup>.
     */
    class Solution {
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            return 1 / x * myPow(1 / x, -(n + 1));
        }
        return (n % 2 == 0) ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
    }
}
