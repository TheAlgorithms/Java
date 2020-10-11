package ProjectEuler;

/**
 * <p>
 * 2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
 * What is the sum of the digits of the number 2^1000?
 * </p>
 * link: https://projecteuler.net/problem=16
 */

import java.math.BigDecimal;
import java.math.BigInteger;

public class Problem16 {

    public static void main(String[] args) {
        assert solution1(1000) == 1366;
    }

    private static int solution1(int power) {
        BigInteger n = BigInteger.valueOf(2);
        n = n.pow(1000);

        BigInteger sum = BigInteger.ZERO;
        final BigInteger ten = BigInteger.valueOf(10);
        while (n.compareTo(BigInteger.ZERO) == 1) {
            sum = sum.add(n.remainder(ten));
            n = n.divide(ten);
        }
        return sum.intValue();
    }
}
