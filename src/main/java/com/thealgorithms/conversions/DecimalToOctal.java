package com.thealgorithms.conversions;

import java.util.Scanner;

/**
 * This class converts Decimal numbers to Octal Numbers
 */
public final class DecimalToOctal {
    private DecimalToOctal() {
    }

    /**
     * Main Method
     *
     * @param args Command line Arguments
     */

    // enter in a decimal value to get Octal output
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        int k;
        int d;
        int s = 0;
        int c = 0;
        System.out.print("Decimal number: ");
        n = sc.nextInt();
        k = n;
        while (k != 0) {
            d = k % 8;
            s += d * (int) Math.pow(10, c++);
            k /= 8;
        }

        System.out.println("Octal equivalent:" + s);
        sc.close();
    }
}
