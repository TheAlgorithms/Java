package com.thealgorithms.strings;

/**
 * Algorithm to multiply two complex numbers represented as strings.
 *
 * A complex number can be represented as a string on the form "real+imaginaryi" where:
 * - real is the real part and is an integer in the range [-100, 100]
 * - imaginary is the imaginary part and is an integer in the range [-100, 100]
 * - i² == -1
 *
 * Given two complex numbers num1 and num2 as strings, this returns a string
 * of the complex number that represents their multiplication.
 *
 * Mathematical formula:
 * (a + bi) * (c + di) = (ac - bd) + (ad + bc)i
 *
 * Example:
 * Input: num1 = "1+1i", num2 = "1+1i"
 * Output: "0+2i"
 * Explanation: (1 + i) * (1 + i) = 1 + i + i + i² = 1 + 2i - 1 = 0 + 2i
 *
 * @see <a href="https://leetcode.com/problems/complex-number-multiplication/">LeetCode Problem</a>
 */
public final class ComplexNumberMultiplication {
    private ComplexNumberMultiplication() {
    }

    /**
     * Multiplies two complex numbers represented as strings.
     *
     * @param num1 The first complex number in the format "a+bi"
     * @param num2 The second complex number in the format "c+di"
     * @return The product of the two complex numbers in the format "real+imaginaryi"
     */
    public static String complexNumberMultiply(String num1, String num2) {
        if (num1 == null || num2 == null || num1.isEmpty() || num2.isEmpty()) {
            return "0+0i";
        }

        // Parse the first complex number
        int[] complex1 = parseComplexNumber(num1);
        int a = complex1[0]; // Real part of num1
        int b = complex1[1]; // Imaginary part of num1

        // Parse the second complex number
        int[] complex2 = parseComplexNumber(num2);
        int c = complex2[0]; // Real part of num2
        int d = complex2[1]; // Imaginary part of num2

        // Calculate the multiplication using the formula: (a + bi) * (c + di) = (ac - bd) + (ad + bc)i
        int realPart = a * c - b * d;
        int imaginaryPart = a * d + b * c;

        return realPart + "+" + imaginaryPart + "i";
    }

    /**
     * Parses a complex number string into its real and imaginary components.
     *
     * @param num The complex number string in the format "a+bi" or "a-bi"
     * @return An array where index 0 is the real part and index 1 is the imaginary part
     */
    private static int[] parseComplexNumber(String num) {
        // Find the position of '+' or '-' after the first character (to handle negative real parts)
        int plusIndex = num.indexOf('+', 1);
        int minusIndex = num.indexOf('-', 1);

        // Determine the separator position
        int separatorIndex = (plusIndex != -1) ? plusIndex : minusIndex;

        // Extract real and imaginary parts
        int real = Integer.parseInt(num.substring(0, separatorIndex));
        // Remove the 'i' at the end and parse the imaginary part
        int imaginary = Integer.parseInt(num.substring(separatorIndex + 1, num.length() - 1));

        return new int[] {real, imaginary};
    }
}
