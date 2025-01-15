package com.thealgorithms.matrix.matrixexponentiation;

import java.math.BigDecimal;
import java.util.Scanner;

import com.thealgorithms.matrix.utils.MatrixUtil;

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
    // First 2 fibonacci numbers
    private static final BigDecimal[][] BASE_FIB_NUMBERS = {{ONE}, {ZERO}};

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

    public static void main(String[] args) {
        // Returns [0, 1, 1, 2, 3, 5 ..] for n = [0, 1, 2, 3, 4, 5.. ]
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        BigDecimal[][] result = MatrixUtil.multiply(fib(n), BASE_FIB_NUMBERS).get();
        System.out.println("Fib(" + n + ") = " + result[1][0]);
        sc.close();
    }
}
