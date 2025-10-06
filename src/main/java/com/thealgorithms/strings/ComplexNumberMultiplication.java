package main.java.com.thealgorithms.strings;


public class ComplexNumberMultiplication {
    private ComplexNumberMultiplication() {
        // Prevent instantiation
    }
    /**
     * Multiplies two complex numbers given as strings.
     *
     * @param num1 the first complex number in "a+bi" format
     * @param num2 the second complex number in "c+di" format
     * @return the product of the two complex numbers, also in "x+yi" format
     */
    public static String multiply(String num1, String num2) {
        int[] complex1 = parseComplex(num1);
        int[] complex2 = parseComplex(num2);

        int realPart = complex1[0] * complex2[0] - complex1[1] * complex2[1];
        int imaginaryPart = complex1[0] * complex2[1] + complex1[1] * complex2[0];

        return realPart + "+" + imaginaryPart + "i";
    }

    /**
     * Parses a complex number string into its integer components.
     *
     * @param complexStr complex number string in "a+bi" format
     * @return an array where index 0 is the real part, and index 1 is the imaginary part
     */
    private static int[] parseComplex(String complexStr) {
        String[] parts = complexStr.split("\\+");
        int real = Integer.parseInt(parts[0]);
        int imaginary = Integer.parseInt(parts[1].replace("i", ""));
        return new int[]{real, imaginary};
    }
}