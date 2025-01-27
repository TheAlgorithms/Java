package com.thealgorithms.matrix.matrixexponentiation;

import com.thealgorithms.matrix.utils.MatrixUtil;
import java.math.BigDecimal;

/**
 * @author Anirudh Buvanesh (https://github.com/anirudhb11) For more information
 * see https://www.geeksforgeeks.org/matrix-exponentiation/
 *
 */
public final class Fibonacci {
    private Fibonacci() {
    }

    // Exponentiation matrix for Fibonacci sequence
    private static final BigDecimal ONE = BigDecimal.valueOf(1);
    private static final BigDecimal ZERO = BigDecimal.valueOf(0);

    private static final BigDecimal[][] FIB_MATRIX = {{ONE, ONE}, {ONE, ZERO}};
    private static final BigDecimal[][] IDENTITY_MATRIX = {{ONE, ZERO}, {ZERO, ONE}};

    /**
     * Calculates the fibonacci number using matrix exponentiaition technique
     *
     * @param n The input n for which we have to determine the fibonacci number
     * Outputs the nth * fibonacci number
     * @return a 2 X 1 array as { {F_n+1}, {F_n} }
     */
    public static BigDecimal[][] fib(int n) {
        if (n == 0) {
            return IDENTITY_MATRIX;
        } else {
            BigDecimal[][] cachedResult = fib(n / 2);
            BigDecimal[][] matrixExpResult = MatrixUtil.multiply(cachedResult, cachedResult).get();
            if (n % 2 == 0) {
                return matrixExpResult;
            } else {
                return MatrixUtil.multiply(FIB_MATRIX, matrixExpResult).get();
            }
        }
    }
}
