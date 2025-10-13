package com.thealgorithms.strings;

/**
 * Multiplies two complex numbers given as strings in "a+bi" format.
 * Parses the strings manually, performs multiplication, and returns the product.
 */
public class ComplexMultiply {

    public static String complexNumberMultiply(String num1, String num2) {
        // Extract real and imaginary parts without using parseInt
        int plusIndex1 = num1.indexOf('+');
        int real1 = Integer.valueOf(num1.substring(0, plusIndex1));
        int imag1 = Integer.valueOf(num1.substring(plusIndex1 + 1, num1.length() - 1));

        int plusIndex2 = num2.indexOf('+');
        int real2 = Integer.valueOf(num2.substring(0, plusIndex2));
        int imag2 = Integer.valueOf(num2.substring(plusIndex2 + 1, num2.length() - 1));

        // Multiply using complex number formula:
        // (a+bi)(c+di) = (ac - bd) + (ad + bc)i
        int real = real1 * real2 - imag1 * imag2;
        int imaginary = real1 * imag2 + imag1 * real2;

        return real + "+" + imaginary + "i";
    }
}
