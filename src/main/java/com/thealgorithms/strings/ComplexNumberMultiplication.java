package com.thealgorithms.strings;

import java.util.Scanner;

/**
 * Implementation of complex number multiplication.
 * Given two strings representing complex numbers in the form "a+bi" and "c+di",
 * this class provides a method to multiply them and return the result
 * as a string in the same format.
 *
 * Example:
 * Input: "1+1i", "1+1i"
 * Output: "0+2i"
 *
 * Formula used:
 * (a + bi) × (c + di) = (ac - bd) + (ad + bc)i
 **/

public final class ComplexNumberMultiplication {

    // Private constructor to prevent instantiation
    private ComplexNumberMultiplication() {
    }

    /**
     * Multiplies two complex numbers represented as strings.
     *
     * @param num1 The first complex number in the form "a+bi".
     * @param num2 The second complex number in the form "c+di".
     * @return The product of the two complex numbers as a string in the form "x+yi".
     */
    public static String complexNumberMultiply(String num1, String num2) {
        // Split real and imaginary parts
        String[] parts1 = num1.split("\\+");
        String[] parts2 = num2.split("\\+");

        int a = Integer.parseInt(parts1[0]);
        int b = Integer.parseInt(parts1[1].replace("i", ""));
        int c = Integer.parseInt(parts2[0]);
        int d = Integer.parseInt(parts2[1].replace("i", ""));

        // Apply the formula: (a+bi)*(c+di) = (ac - bd) + (ad + bc)i
        int real = a * c - b * d;
        int imag = a * d + b * c;

        return real + "+" + imag + "i";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first complex number (a+bi): ");
        String num1 = sc.nextLine();

        System.out.print("Enter second complex number (c+di): ");
        String num2 = sc.nextLine();

        String result = complexNumberMultiply(num1, num2);
        System.out.println("Result: " + result);

        sc.close();
    }
}
