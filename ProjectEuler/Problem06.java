package ProjectEuler;

/**
 * The sum of the squares of the first ten natural numbers is,
 * 1^2 + 2^2 + ... + 10^2 = 385
 * The square of the sum of the first ten natural numbers is,
 * (1 + 2 + ... + 10)^2 = 552 = 3025
 * Hence the difference between the sum of the squares of the first ten natural
 * numbers and the square of the sum is 3025 − 385 = 2640.
 * Find the difference between the sum of the squares of the first N natural
 * numbers and the square of the sum.
 * <p>
 * link: https://projecteuler.net/problem=6
 */
public class Problem06 {
    public static void main(String[] args) {
        int[][] testNumbers = {
                {10, 2640},
                {15, 13160},
                {20, 41230},
                {50, 1582700}
        };

        for (int[] testNumber : testNumbers) {
            assert solution1(testNumber[0]) == testNumber[1]
                    && solutions2(testNumber[0]) == testNumber[1];
        }
    }

    private static int solution1(int n) {
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 1; i <= n; ++i) {
            sum1 += i * i;
            sum2 += i;
        }
        return sum2 * sum2 - sum1;
    }


    private static int solutions2(int n) {
        int sumOfSquares = n * (n + 1) * (2 * n + 1) / 6;
        int squareOfSum = (int) Math.pow((n * (n + 1) / 2.0), 2);
        return squareOfSum - sumOfSquares;
    }
}