package com.thealgorithms.maths;

import java.util.Objects;

/**
 * A solver for linear Diophantine equations of the form ax + by = c.
 * <p>
 * A linear Diophantine equation is an equation in which only integer solutions
 * are allowed.
 * This solver uses the Extended Euclidean Algorithm to find integer solutions
 * (x, y)
 * for equations of the form ax + by = c, where a, b, and c are integers.
 * </p>
 * <p>
 * The equation has solutions if and only if gcd(a, b) divides c.
 * If solutions exist, this solver finds one particular solution.
 * </p>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Diophantine_equation">Diophantine
 *      Equation</a>
 * @see <a href=
 *      "https://en.wikipedia.org/wiki/Extended_Euclidean_algorithm">Extended
 *      Euclidean Algorithm</a>
 */
public final class LinearDiophantineEquationsSolver {
    private LinearDiophantineEquationsSolver() {
    }

    /**
     * Demonstrates the solver with a sample equation: 3x + 4y = 7.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        // 3x + 4y = 7
        final var toSolve = new Equation(3, 4, 7);
        System.out.println(findAnySolution(toSolve));
    }

    /**
     * Finds any integer solution to the linear Diophantine equation ax + by = c.
     * <p>
     * The method returns one of three types of solutions:
     * <ul>
     * <li>A specific solution (x, y) if solutions exist</li>
     * <li>{@link Solution#NO_SOLUTION} if no integer solutions exist</li>
     * <li>{@link Solution#INFINITE_SOLUTIONS} if the equation is 0x + 0y = 0</li>
     * </ul>
     * </p>
     *
     * @param equation the linear Diophantine equation to solve
     * @return a Solution object containing the result
     * @throws NullPointerException if equation is null
     */
    public static Solution findAnySolution(final Equation equation) {
        if (equation.a() == 0 && equation.b() == 0 && equation.c() == 0) {
            return Solution.INFINITE_SOLUTIONS;
        }
        if (equation.a() == 0 && equation.b() == 0) {
            return Solution.NO_SOLUTION;
        }
        if (equation.a() == 0) {
            if (equation.c() % equation.b() == 0) {
                return new Solution(0, equation.c() / equation.b());
            } else {
                return Solution.NO_SOLUTION;
            }
        }
        if (equation.b() == 0) {
            if (equation.c() % equation.a() == 0) {
                return new Solution(equation.c() / equation.a(), 0);
            } else {
                return Solution.NO_SOLUTION;
            }
        }
        final var stub = new GcdSolutionWrapper(0, new Solution(0, 0));
        final var gcdSolution = gcd(equation.a(), equation.b(), stub);
        if (equation.c() % gcdSolution.getGcd() != 0) {
            return Solution.NO_SOLUTION;
        }
        final var toReturn = new Solution(0, 0);
        var xToSet = stub.getSolution().getX() * (equation.c() / stub.getGcd());
        var yToSet = stub.getSolution().getY() * (equation.c() / stub.getGcd());
        toReturn.setX(xToSet);
        toReturn.setY(yToSet);
        return toReturn;
    }

    /**
     * Computes the GCD of two integers using the Extended Euclidean Algorithm.
     * <p>
     * This method also finds coefficients x and y such that ax + by = gcd(a, b).
     * The coefficients are stored in the 'previous' wrapper object.
     * </p>
     *
     * @param a        the first integer
     * @param b        the second integer
     * @param previous a wrapper to store the solution coefficients
     * @return a GcdSolutionWrapper containing the GCD and coefficients
     */
    private static GcdSolutionWrapper gcd(final int a, final int b, final GcdSolutionWrapper previous) {
        if (b == 0) {
            return new GcdSolutionWrapper(a, new Solution(1, 0));
        }
        // stub wrapper becomes the `previous` of the next recursive call
        final var stubWrapper = new GcdSolutionWrapper(0, new Solution(0, 0));
        final var next = gcd(b, a % b, stubWrapper);
        previous.getSolution().setX(next.getSolution().getY());
        previous.getSolution().setY(next.getSolution().getX() - (a / b) * (next.getSolution().getY()));
        previous.setGcd(next.getGcd());
        return new GcdSolutionWrapper(next.getGcd(), previous.getSolution());
    }

    /**
     * Represents a solution (x, y) to a linear Diophantine equation.
     * <p>
     * Special instances:
     * <ul>
     * <li>{@link #NO_SOLUTION} - indicates no integer solutions exist</li>
     * <li>{@link #INFINITE_SOLUTIONS} - indicates infinitely many solutions
     * exist</li>
     * </ul>
     * </p>
     */
    public static final class Solution {

        /**
         * Singleton instance representing the case where no solution exists.
         */
        public static final Solution NO_SOLUTION = new Solution(Integer.MAX_VALUE, Integer.MAX_VALUE);

        /**
         * Singleton instance representing the case where infinite solutions exist.
         */
        public static final Solution INFINITE_SOLUTIONS = new Solution(Integer.MIN_VALUE, Integer.MIN_VALUE);

        private int x;
        private int y;

        /**
         * Constructs a solution with the given x and y values.
         *
         * @param x the x coordinate of the solution
         * @param y the y coordinate of the solution
         */
        public Solution(int x, int y) {
            this.x = x;
            this.y = y;
        }

        /**
         * Gets the x value of this solution.
         *
         * @return the x value
         */
        public int getX() {
            return x;
        }

        /**
         * Gets the y value of this solution.
         *
         * @return the y value
         */
        public int getY() {
            return y;
        }

        /**
         * Sets the x value of this solution.
         *
         * @param x the new x value
         */
        public void setX(int x) {
            this.x = x;
        }

        /**
         * Sets the y value of this solution.
         *
         * @param y the new y value
         */
        public void setY(int y) {
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null || obj.getClass() != this.getClass()) {
                return false;
            }
            var that = (Solution) obj;
            return this.x == that.x && this.y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Solution["
                + "x=" + x + ", "
                + "y=" + y + ']';
        }
    }

    /**
     * Represents a linear Diophantine equation of the form ax + by = c.
     *
     * @param a the coefficient of x
     * @param b the coefficient of y
     * @param c the constant term
     */
    public record Equation(int a, int b, int c) {
    }

    /**
     * A wrapper class that holds both the GCD and the solution coefficients
     * from the Extended Euclidean Algorithm.
     * <p>
     * This class is used internally to pass results between recursive calls
     * of the GCD computation.
     * </p>
     */
    public static final class GcdSolutionWrapper {

        private int gcd;
        private Solution solution;

        /**
         * Constructs a GcdSolutionWrapper with the given GCD and solution.
         *
         * @param gcd      the greatest common divisor
         * @param solution the solution coefficients
         */
        public GcdSolutionWrapper(int gcd, Solution solution) {
            this.gcd = gcd;
            this.solution = solution;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null || obj.getClass() != this.getClass()) {
                return false;
            }
            var that = (GcdSolutionWrapper) obj;
            return (this.gcd == that.gcd && Objects.equals(this.solution, that.solution));
        }

        /**
         * Gets the GCD value.
         *
         * @return the GCD
         */
        public int getGcd() {
            return gcd;
        }

        /**
         * Sets the GCD value.
         *
         * @param gcd the new GCD value
         */
        public void setGcd(int gcd) {
            this.gcd = gcd;
        }

        /**
         * Gets the solution coefficients.
         *
         * @return the solution
         */
        public Solution getSolution() {
            return solution;
        }

        /**
         * Sets the solution coefficients.
         *
         * @param solution the new solution
         */
        public void setSolution(Solution solution) {
            this.solution = solution;
        }

        @Override
        public int hashCode() {
            return Objects.hash(gcd, solution);
        }

        @Override
        public String toString() {
            return ("GcdSolutionWrapper["
                + "gcd=" + gcd + ", "
                + "solution=" + solution + ']');
        }
    }
}
