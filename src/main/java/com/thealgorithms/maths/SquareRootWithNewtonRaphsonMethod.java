package com.thealgorithms.maths;

import java.util.Scanner;

/*
 *To learn about the method, visit the link below :
 * https://en.wikipedia.org/wiki/Newton%27s_method
 *
 * To obtain the square root, no built-in functions should be used
 *
 * The formula to calculate the root is : root = 0.5(x + n/x),
 * here, n is the no. whose square root has to be calculated and
 * x has to be guessed such that, the calculation should result into
 * the square root of n.
 * And the root will be obtained when the error < 0.5 or the precision value can also
 * be changed according to the user preference.
 */

public class SquareRootWithNewtonRaphsonMethod {

    public static double squareRoot(int n) {
        double x = n; //initially taking a guess that x = n.
        double root = 0.5 * (x + n / x); //applying Newton-Raphson Method.

        while (Math.abs(root - x) > 0.0000001) { //root - x = error and error < 0.0000001, 0.0000001 is the precision value taken over here.
            x = root; //decreasing the value of x to root, i.e. decreasing the guess.
            root = 0.5 * (x + n / x);
        }

        return root;
    }
}
