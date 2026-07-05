package com.thealgorithms.maths;

    public final class SquareRootWithNewtonRaphsonMethod {
        private SquareRootWithNewtonRaphsonMethod() {
        }
        public static double squareRoot(int n) {
            double x = n; // initially taking a guess that x = n.
            double root = 0.5 * (x + n / x); // applying Newton-Raphson Method.
            while (Math.abs(root - x) > 0.0000001) { // root - x = error and error < 0.0000001, 0.0000001
                // is the precision value taken over here.
                x = root; // decreasing the value of x to root, i.e. decreasing the guess.
                root = 0.5 * (x + n / x);
            }
            return root;
        }
    }

