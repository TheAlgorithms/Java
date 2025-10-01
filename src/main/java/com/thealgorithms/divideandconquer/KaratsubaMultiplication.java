// Karatsuba Multiplication Algorithm
//
// -----References-----
// https://en.wikipedia.org/wiki/Karatsuba_algorithm
// https://www.geeksforgeeks.org/dsa/karatsuba-algorithm-for-fast-multiplication-using-divide-and-conquer-algorithm/

import java.util.Scanner;

public final class KaratsubaMultiplication {

    // Private constructor to prevent instantiation
    private KaratsubaMultiplication() {
        throw new UnsupportedOperationException("Utility class");
    }

    // Karatsuba multiplication function
    public static long karatsuba(long x, long y) {
        if (x < 10 || y < 10) {
            return x * y;
        }

        int n = Math.max(Long.toString(x).length(), Long.toString(y).length());
        int m = n / 2;

        long high1 = x / (long) Math.pow(10, m);
        long low1 = x % (long) Math.pow(10, m);
        long high2 = y / (long) Math.pow(10, m);
        long low2 = y % (long) Math.pow(10, m);

        long z0 = karatsuba(low1, low2);
        long z1 = karatsuba(low1 + high1, low2 + high2);
        long z2 = karatsuba(high1, high2);

        return (z2 * (long) Math.pow(10, 2 * m)) + ((z1 - z2 - z0) * (long) Math.pow(10, m)) + z0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first number: ");
        long num1 = sc.nextLong();

        System.out.print("Enter second number: ");
        long num2 = sc.nextLong();

        long result = karatsuba(num1, num2);

        System.out.println("Product: " + result);

        sc.close();
    }
}
