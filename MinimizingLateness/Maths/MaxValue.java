package Maths;

import java.util.Random;

public class MaxValue {

    /**
     * Driver Code
     */
    public static void main(String[] args) {
        Random rand = new Random();

        /* test 100 times using rand numbers */
        for (int i = 1; i <= 100; ++i) {
            /* generate number from -50 to 49 */
            int a = rand.nextInt(100) - 50;
            int b = rand.nextInt(100) - 50;
            assert max(a, b) == Math.max(a, b);
        }
    }

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
}
