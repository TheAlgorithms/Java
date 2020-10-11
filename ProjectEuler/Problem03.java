package ProjectEuler;
import java.util.ArrayList;

/**
 * The prime factors of 13195 are 5, 7, 13 and 29.
 * What is the largest prime factor of the number 600851475143 ?
 * Link: https://projecteuler.net/problem=3
 */
public class Problem03 {
    public static void main(String[] args) {
        /* Remember to put L after the number to declare it as a long, otherwise it will error! */
        long testNumber = 600851475143L;

        System.out.println(solution1(testNumber));
    }

    private static long solution1(long n) {
        ArrayList<Long> factors = new ArrayList<Long>();
        for (long i = 2; i <= n; i++) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }
        return factors.get(factors.size() - 1);
    }
}