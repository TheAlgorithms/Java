package com.thealgorithms.conversions;

import java.util.Scanner;

/**
 * This class converts a Decimal number to a Binary number
 */
final class DecimalToBinary {
    private DecimalToBinary() {
    }

    /**
     * Main Method
     *
     * @param args Command Line Arguments
     */
    public static void main(String[] args) {
        conventionalConversion();
        bitwiseConversion();
    }

    /**
     * This method converts a decimal number to a binary number using a
     * conventional algorithm.
     */
    public static void conventionalConversion() {
        int n;
        int b = 0;
        int c = 0;
        int d;
        Scanner input = new Scanner(System.in);
        System.out.printf("Conventional conversion.%n Enter the decimal number: ");
        n = input.nextInt();
        while (n != 0) {
            d = n % 2;
            b = b + d * (int) Math.pow(10, c++);
            n /= 2;
        } // converting decimal to binary
        System.out.println("\tBinary number: " + b);
        input.close();
    }

    /**
     * This method converts a decimal number to a binary number using a bitwise
     * algorithm
     */
    public static void bitwiseConversion() {
        int n;
        int b = 0;
        int c = 0;
        int d;
        Scanner input = new Scanner(System.in);
        System.out.printf("Bitwise conversion.%n Enter the decimal number: ");
        n = input.nextInt();
        while (n != 0) {
            d = (n & 1);
            b += d * (int) Math.pow(10, c++);
            n >>= 1;
        }
        System.out.println("\tBinary number: " + b);
        input.close();
    }
}
