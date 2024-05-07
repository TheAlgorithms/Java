package com.thealgorithms.maths;

public class ComplexNumberUtil {

    public static class ComplexNumber {
        public final double REAL;
        public final double IMAGINARY;

        public ComplexNumber(double real, double imaginary) {
            REAL = real;
            IMAGINARY = imaginary;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof ComplexNumber num) {
                return this.REAL == num.REAL && this.IMAGINARY == num.IMAGINARY;
            }

            return super.equals(obj);
        }

        @Override
        public String toString() {
            return REAL + ((IMAGINARY < 0) ? (" - " + Math.abs(IMAGINARY)) : (" + " + IMAGINARY)) + "i";
        }
    }

    public static final ComplexNumber ZERO = new ComplexNumber(0, 0);
    public static final ComplexNumber ONE = new ComplexNumber(1, 0);
    public static final ComplexNumber TWO = new ComplexNumber(2, 0);
    public static final ComplexNumber PLUS_I = new ComplexNumber(0, 1);
    public static final ComplexNumber MINUS_I = new ComplexNumber(0, -1);

    /**
     * add two complex numbers
     * @param num1 the first complex number
     * @param num2 the second complex number
     * @return the sum of num1 and num2
     */
    public static ComplexNumber add(ComplexNumber num1, ComplexNumber num2) {
        return new ComplexNumber(num1.REAL + num2.REAL, num1.IMAGINARY + num2.IMAGINARY);
    }

    /**
     * Subtracts the second complex number from the first complex number.
     *
     * @param num1 the first complex number
     * @param num2 the second complex number
     * @return the result of subtracting num2 from num1
     */
    public static ComplexNumber subtract(ComplexNumber num1, ComplexNumber num2) {
        return new ComplexNumber(num1.REAL - num2.REAL, num1.IMAGINARY - num2.IMAGINARY);
    }

    /**
     * Multiplies two complex numbers.
     *
     * @param num1 the first complex number
     * @param num2 the second complex number
     * @return the product of num1 and num2
     * @link <a href="https://en.wikipedia.org/wiki/Complex_number#Multiplication">...</a>
     */
    public static ComplexNumber multiply(ComplexNumber num1, ComplexNumber num2) {
        return new ComplexNumber(num1.REAL * num2.REAL - num1.IMAGINARY * num2.IMAGINARY, num1.REAL * num2.IMAGINARY + num1.IMAGINARY * num2.REAL);
    }

    /**
     * Divides the first complex number by the second complex number.
     *
     * @param num1 the numerator complex number
     * @param num2 the denominator complex number
     * @return the result of dividing num1 by num2
     * @throws RuntimeException if the divisor (num2) is zero
     * @link <a href="https://en.wikipedia.org/wiki/Complex_number#Complex_conjugate,_absolute_value_and_argument">...</a>
     */
    public static ComplexNumber divide(ComplexNumber num1, ComplexNumber num2) {
        final double divisor = num2.REAL * num2.REAL + num2.IMAGINARY * num2.IMAGINARY;
        if (divisor == 0) {
            throw new RuntimeException("Cannot divide by zero");
        }

        return new ComplexNumber((num1.REAL * num2.REAL + num1.IMAGINARY * num2.IMAGINARY) / divisor, (num1.IMAGINARY * num2.REAL - num1.REAL * num2.IMAGINARY) / divisor);
    }

    /**
     * Computes the absolute value (magnitude) of a complex number.
     *
     * @param num the complex number
     * @return the absolute value of num
     * @link <a href="https://en.wikipedia.org/wiki/Complex_number#Complex_conjugate,_absolute_value_and_argument">...</a>
     */
    public static double abs(ComplexNumber num) {
        return Math.sqrt(num.REAL * num.REAL + num.IMAGINARY * num.IMAGINARY);
    }

    /**
     * Computes the exponential function of a complex number.
     *
     * @param num the complex number
     * @return e raised to the power of num
     * @link <a href="https://en.wikipedia.org/wiki/Exponential_function#Continued_fractions_for_ex">...</a>
     */
    public static ComplexNumber exp(ComplexNumber num) {
        final double coefficient = Math.exp(num.REAL);
        return new ComplexNumber(coefficient * Math.cos(num.IMAGINARY), coefficient * Math.sin(num.IMAGINARY));
    }

    /**
     * Computes the principal value of natural logarithm of a complex number.
     *
     * @param num the complex number
     * @return the natural logarithm of num
     * @throws RuntimeException if num is zero
     * @link <a href="https://en.wikipedia.org/wiki/Complex_logarithm#Calculating_the_principal_value">...</a>
     */
    public static ComplexNumber log(ComplexNumber num) {
        if (num.equals(ZERO)) {
            throw new RuntimeException("Cannot take the logarithm of zero");
        }

        return new ComplexNumber(Math.log(abs(num)), Math.atan2(num.IMAGINARY, num.REAL));
    }

    /**
     * Computes the power of a complex number raised to another complex number.
     *
     * @param num1 the base complex number
     * @param num2 the exponent complex number
     * @return num1 raised to the power of num2
     * link <a href="https://en.wikipedia.org/wiki/Exponentiation#Complex_exponents_with_a_positive_real_base">...</a>
     */
    public static ComplexNumber pow(ComplexNumber num1, ComplexNumber num2) {
        if (num1.equals(ZERO)) {
            return num2.equals(ZERO) ? ONE : ZERO;
        }

        return exp(multiply(ln(num1), num2));
    }

    /**
     * Computes the principal square root of a complex number.
     *
     * @param num the complex number
     * @return the square root of num
     */
    public static ComplexNumber sqrt(ComplexNumber num) {
        return pow(num, new ComplexNumber(0.5, 0));
    }

    /**
     * Computes the sine of a complex number.
     *
     * @param num the complex number
     * @return the sine of num
     * @link <a href="https://en.wikipedia.org/wiki/Trigonometric_functions#Relationship_to_exponential_function_">...</a>(Euler's_formula)
     */
    public static ComplexNumber sin(ComplexNumber num) {
        ComplexNumber exp1 = exp(multiply(num, PLUS_I));
        ComplexNumber exp2 = exp(multiply(num, MINUS_I));
        return divide(subtract(exp1, exp2), multiply(new ComplexNumber(2, 0), PLUS_I));
    }

    /**
     * Computes the cosine of a complex number.
     *
     * @param num the complex number
     * @return the cosine of num
     * @link <a href="https://en.wikipedia.org/wiki/Trigonometric_functions#Relationship_to_exponential_function_">...</a>(Euler's_formula)
     */
    public static ComplexNumber cos(ComplexNumber num) {
        ComplexNumber exp1 = exp(multiply(num, PLUS_I));
        ComplexNumber exp2 = exp(multiply(num, MINUS_I));
        return divide(add(exp1, exp2), TWO);
    }

    /**
     * Computes the tangent of a complex number.
     *
     * @param num the complex number
     * @return the tangent of num
     * @throws RuntimeException if <code>num.real = pi*(n+0.5)</code>
     * @link <a href="https://en.wikipedia.org/wiki/Trigonometric_functions#Right-angled_triangle_definitions">...</a>
     */
    public static ComplexNumber tan(ComplexNumber num) {
        if (num.REAL % Math.PI == Math.PI / 2) {
            throw new RuntimeException("Cannot take the tan of a number where the real part can be expressed as pi*(n+0.5)");
        }

        return divide(sin(num), cos(num));
    }

    /**
     * Computes the cotangent of a complex number.
     *
     * @param num the complex number
     * @return the cotangent of num
     * @throws RuntimeException if <code>num.real = pi*n</code>
     * @link <a href="https://en.wikipedia.org/wiki/Trigonometric_functions#Right-angled_triangle_definitions">...</a>
     */
    public static ComplexNumber cot(ComplexNumber num) {
        if (num.REAL % Math.PI == 0) {
            throw new RuntimeException("Cannot take the cot of number with real part dividable by pi");
        }

        return divide(cos(num), sin(num));
    }

    /**
     * Computes the arcsine of a complex number.
     *
     * @param num the complex number
     * @return the arcsine of num
     * @link <a href="https://en.wikipedia.org/wiki/Inverse_trigonometric_functions#Extension_to_the_complex_plane">...</a>
     */
    public static ComplexNumber arcsin(ComplexNumber num) {
        ComplexNumber temp = sqrt(subtract(ONE, multiply(num, num)));
        return multiply(MINUS_I, ln(add(multiply(PLUS_I, num), temp)));
    }

    /**
     * Computes the arccosine of a complex number.
     *
     * @param num the complex number
     * @return the arccosine of num
     * @link <a href="https://en.wikipedia.org/wiki/Inverse_trigonometric_functions#Extension_to_the_complex_plane">...</a>
     */
    public static ComplexNumber arccos(ComplexNumber num) {
        ComplexNumber temp = sqrt(subtract(ONE, multiply(num, num)));
        return multiply(MINUS_I, ln(add(num, multiply(temp, PLUS_I))));
    }

    /**
     * Computes the arctangent of a complex number.
     *
     * @param num the complex number
     * @return the arctangent of num
     * @throws RuntimeException if <code>num=i</code> or <code>num=-i</code>
     * @link <a href="https://en.wikipedia.org/wiki/Inverse_trigonometric_functions#Extension_to_the_complex_plane">...</a>
     */
    public static ComplexNumber arctan(ComplexNumber num) {
        if (num.equals(PLUS_I) || num.equals(MINUS_I)) {
            throw new RuntimeException("Cannot take the arctan of " + num);
        }

        return multiply(divide(MINUS_I, TWO), ln(divide(subtract(PLUS_I, num), add(PLUS_I, num))));
    }

    /**
     * Computes the arccotangent of a complex number.
     *
     * @param num the complex number
     * @return the arccotangent of num
     * @throws RuntimeException if <code>num=i</code> or <code>num=-i</code>
     * @link <a href="https://en.wikipedia.org/wiki/Inverse_trigonometric_functions#Extension_to_the_complex_plane">...</a>
     */
    public static ComplexNumber arccot(ComplexNumber num) {
        if (num.equals(PLUS_I) || num.equals(MINUS_I)) {
            throw new RuntimeException("Cannot take the arccot of " + num);
        }

        return multiply(divide(MINUS_I, TWO), ln(divide(add(num, PLUS_I), subtract(num, PLUS_I))));
    }
}
