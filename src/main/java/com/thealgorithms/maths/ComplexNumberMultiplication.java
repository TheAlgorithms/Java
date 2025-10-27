package com.thealgorithms.maths;
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
        int plusIndex1 = num1.indexOf('+');
        int plusIndex2 = num2.indexOf('+');

        String realPart1 = num1.substring(0, plusIndex1);
        String imagPart1 = num1.substring(plusIndex1 + 1, num1.length() - 1);

        int re1 = Integer.parseInt(realPart1);
        int im1 = Integer.parseInt(imagPart1);

        String realPart2 = num2.substring(0, plusIndex2);
        String imagPart2 = num2.substring(plusIndex2 + 1, num2.length() - 1);

        int re2 = Integer.parseInt(realPart2);
        int im2 = Integer.parseInt(imagPart2);

        int reResult = re1 * re2 - im1 * im2;
        int imResult = re1 * im2 + im1 * re2;

        return reResult + "+" + imResult + "i";
    }
}
