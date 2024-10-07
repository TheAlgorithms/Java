package com.thealgorithms.maths;

/**
 * This class represents a complex number which has real and imaginary part
 */
class ComplexNumber {
    Double real;
    Double imaginary;

    ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    ComplexNumber(double real) {
        this.real = real;
        this.imaginary = null;
    }
}

/**
 * Quadratic Equation Formula is used to find
 * the roots of a quadratic equation of the form ax^2 + bx + c = 0
 *
 * @see <a href="https://en.wikipedia.org/wiki/Quadratic_equation">Quadratic Equation</a>
 */
public class QuadraticEquationSolver {
    /**
     * Function takes in the coefficients of the quadratic equation
     *
     * @param a is the coefficient of x^2
     * @param b is the coefficient of x
     * @param c is the constant
     * @return roots of the equation which are ComplexNumber type
     */
    public ComplexNumber[] solveEquation(double a, double b, double c) {
        double discriminant = b * b - 4 * a * c;

        // if discriminant is positive, roots will be different
        if (discriminant > 0) {
            return new ComplexNumber[] {new ComplexNumber((-b + Math.sqrt(discriminant)) / (2 * a)), new ComplexNumber((-b - Math.sqrt(discriminant)) / (2 * a))};
        }

        // if discriminant is zero, roots will be same
        if (discriminant == 0) {
            return new ComplexNumber[] {new ComplexNumber((-b) / (2 * a))};
        }

        // if discriminant is negative, roots will have imaginary parts
        if (discriminant < 0) {
            double realPart = -b / (2 * a);
            double imaginaryPart = Math.sqrt(-discriminant) / (2 * a);

            return new ComplexNumber[] {new ComplexNumber(realPart, imaginaryPart), new ComplexNumber(realPart, -imaginaryPart)};
        }

        // return no roots
        return new ComplexNumber[] {};
    }
}
