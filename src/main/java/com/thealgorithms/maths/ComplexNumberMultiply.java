package com.thealgorithms.maths;

/**
 * Multiplies two complex numbers represented as strings in the form "a+bi".
 * Supports negative values and validates input format.
 */
public final class ComplexNumberMultiply {

    private ComplexNumberMultiply() {
    }

    private static int[] parse(String num) {
        if (num == null || !num.matches("-?\\d+\\+-?\\d+i")) {
            throw new IllegalArgumentException("Invalid complex number format: " + num);
        }

        String[] parts = num.split("\\+");
        int real = Integer.parseInt(parts[0]);
        int imaginary = Integer.parseInt(parts[1].replace("i", ""));
        return new int[] {real, imaginary};
    }

    public static String multiply(String num1, String num2) {
        int[] a = parse(num1);
        int[] b = parse(num2);

        int real = a[0] * b[0] - a[1] * b[1];
        int imaginary = a[0] * b[1] + a[1] * b[0];

        return real + "+" + imaginary + "i";
    }
}
