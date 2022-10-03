package com.thealgorithms.maths;

import java.util.Objects;

public final class LinearDiophantineEquationsSolver {

    public static void main(String[] args) {
        // 3x + 4y = 7
        final var toSolve = new Equation(3, 4, 7);
        System.out.println(findAnySolution(toSolve));
    }

    public static Solution findAnySolution(final Equation equation) {
        if (equation.a() == 0 && equation.b() == 0 && equation.c() == 0) {
            return Solution.INFINITE_SOLUTIONS;
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

    private static GcdSolutionWrapper gcd(
        final int a,
        final int b,
        final GcdSolutionWrapper previous
    ) {
        if (b == 0) {
            return new GcdSolutionWrapper(a, new Solution(1, 0));
        }
        // stub wrapper becomes the `previous` of the next recursive call
        final var stubWrapper = new GcdSolutionWrapper(0, new Solution(0, 0));
        final var next = /* recursive call */gcd(b, a % b, stubWrapper);
        previous.getSolution().setX(next.getSolution().getY());
        previous
            .getSolution()
            .setY(
                next.getSolution().getX() -
                (a / b) *
                (next.getSolution().getY())
            );
        previous.setGcd(next.getGcd());
        return new GcdSolutionWrapper(next.getGcd(), previous.getSolution());
    }

    public static final class Solution {

        public static final Solution NO_SOLUTION = new Solution(
            Integer.MAX_VALUE,
            Integer.MAX_VALUE
        );
        public static final Solution INFINITE_SOLUTIONS = new Solution(
            Integer.MIN_VALUE,
            Integer.MIN_VALUE
        );
        private int x;
        private int y;

        public Solution(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void setX(int x) {
            this.x = x;
        }

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
            return "Solution[" + "x=" + x + ", " + "y=" + y + ']';
        }
    }

    public record Equation(int a, int b, int c) {}

    public static final class GcdSolutionWrapper {

        private int gcd;
        private Solution solution;

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
            return (
                this.gcd == that.gcd &&
                Objects.equals(this.solution, that.solution)
            );
        }

        public int getGcd() {
            return gcd;
        }

        public void setGcd(int gcd) {
            this.gcd = gcd;
        }

        public Solution getSolution() {
            return solution;
        }

        public void setSolution(Solution solution) {
            this.solution = solution;
        }

        @Override
        public int hashCode() {
            return Objects.hash(gcd, solution);
        }

        @Override
        public String toString() {
            return (
                "GcdSolutionWrapper[" +
                "gcd=" +
                gcd +
                ", " +
                "solution=" +
                solution +
                ']'
            );
        }
    }
}
