package com.thealgorithms.strings;
/**
 * @author Swarit Srivastava (https://github.com/SwarritSrivastava)
 */
public final class ComplexNumberMultiplication {
    private ComplexNumberMultiplication() {

    }
    /**
     * Multiplies two complex numbers given as strings.
     * <p>
     * Each complex number is represented in the form "real+imaginaryi" where:
     * <ul>
     *   <li>real is the real part and is an integer in the range [-100, 100]</li>
     *   <li>imaginary is the imaginary part and is an integer in the range [-100, 100]</li>
     *   <li>i * i = -1</li>
     * </ul>
     *
     * Example: {@code multiplyComplexNumbers("1+1i", "1+1i") -> "0+2i"}
     *
     * @param num1 the first complex number
     * @param num2 the second complex number
     * @return the resulting complex number after multiplication
     */
    public static String multiplyComplexNumbers(String num1, String num2) {
        int i = num1.indexOf('+');
        int j = num2.indexOf('+');

        String r1 = num1.substring(0, i);
        String i1 = num1.substring(i + 1, num1.length() - 1);

        int re1 = Integer.parseInt(r1);
        int im1 = Integer.parseInt(i1);

        String r2 = num2.substring(0, j);
        String i2 = num2.substring(j + 1, num2.length() - 1);

        int re2 = Integer.parseInt(r2);
        int im2 = Integer.parseInt(i2);

        int reAns = re1 * re2 - im1 * im2;
        int imAns = re1 * im2 + im1 * re2;

        return reAns + "+" + imAns + "i";
    }
}
