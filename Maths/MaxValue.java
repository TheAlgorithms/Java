package Maths;

public class MaxValue {

    /**
     * Returns the greater of two {@code int} values. That is, the
     * result is the argument closer to the value of
     * {@link Integer#MAX_VALUE}. If the arguments have the same value,
     * the result is that same value.
     *
     * @param a an argument.
     * @param b another argument.
     * @return the larger of {@code a} and {@code b}.
     */
    public static int max(int a, int b) {
        return a >= b ? a : b;
    }

    public static void main(String[] args) {
        int a = 3;
        int b = 4;
        System.out.format("max:%d between %d and %d", max(a, b), a, b);
    }
}
