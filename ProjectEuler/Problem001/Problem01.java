package ProjectEuler;

/**
 * If we list all the natural numbers below 10 that are multiples of 3 or 5,
 * we get 3, 5, 6 and 9. The sum of these multiples is 23.
 * <p>
 * Find the sum of all the multiples of 3 or 5 below 1000.
 * <p>
 * Link: https://projecteuler.net/problem=1
 */
public class Problem01 {
    public static void main(String[] args) {
        int[][] testNumber = {
                {3, 0},
                {4, 3},
                {10, 23},
                {1000, 233168},
                {-1, 0}
        };

        for (int[] ints : testNumber) {
            assert solution1(ints[0]) == ints[1];
            assert solution2(ints[0]) == ints[1];
        }
    }

    private static int solution1(int n) {
        int sum = 0;
        for (int i = 3; i < n; ++i) {
            if (i % 3 == 0 || i % 5 == 0) {
                sum += i;
            }
        }
        return sum;
    }

    private static int solution2(int n) {
        int sum = 0;

        int terms = (n - 1) / 3;
        sum += terms * (6 + (terms - 1) * 3) / 2;

        terms = (n - 1) / 5;
        sum += terms * (10 + (terms - 1) * 5) / 2;

        terms = (n - 1) / 15;
        sum -= terms * (30 + (terms - 1) * 15) / 2;

        return sum;
    }
}