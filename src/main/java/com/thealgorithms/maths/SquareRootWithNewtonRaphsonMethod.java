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

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Enter n to find it's square root: ");
        int n = in.nextInt();

        System.out.println("Square root of " + n + " is " + sqRoot(n));
    }

    public static double sqRoot (int n) {

        double x = n;                         //initially taking a guess that x = n.
        double root;

        while (true) {

            root = 0.5 * (x + n/x);           //applying Newton-Raphson Method.

            if (Math.abs(root - x) < 0.1){    //root - x = error and error < 0.1, 0.1 is the precision value taken over here.
                break;                        //if error is below the stated precision value, break out of the loop.
            }

            x = root;                         //decreasing the value of x to root, i.e. decreasing the guess.
        }

        return root;
    }
}
