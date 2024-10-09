package com.thealgorithms.maths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QuadraticEquationSolverTest {
    private final QuadraticEquationSolver quadraticEquationSolver = new QuadraticEquationSolver();

    @Test
    public void testSolveEquationRealRoots() {
        // 4.2x^2 + 8x + 1.9 = 0
        double a = 4.2;
        double b = 8;
        double c = 1.9;

        ComplexNumber[] roots = quadraticEquationSolver.solveEquation(a, b, c);
        Assertions.assertEquals(roots.length, 2);
        Assertions.assertEquals(roots[0].real, -0.27810465435684306);
        Assertions.assertNull(roots[0].imaginary);
        Assertions.assertEquals(roots[1].real, -1.6266572504050616);
        Assertions.assertNull(roots[1].imaginary);
    }

    @Test
    public void testSolveEquationEqualRoots() {
        // x^2 + 2x + 1 = 0
        double a = 1;
        double b = 2;
        double c = 1;

        ComplexNumber[] roots = quadraticEquationSolver.solveEquation(a, b, c);
        Assertions.assertEquals(roots.length, 1);
        Assertions.assertEquals(roots[0].real, -1);
    }

    @Test
    public void testSolveEquationComplexRoots() {
        // 2.3x^2 + 4x + 5.6 = 0
        double a = 2.3;
        double b = 4;
        double c = 5.6;

        ComplexNumber[] roots = quadraticEquationSolver.solveEquation(a, b, c);
        Assertions.assertEquals(roots.length, 2);
        Assertions.assertEquals(roots[0].real, -0.8695652173913044);
        Assertions.assertEquals(roots[0].imaginary, 1.2956229935435948);
        Assertions.assertEquals(roots[1].real, -0.8695652173913044);
        Assertions.assertEquals(roots[1].imaginary, -1.2956229935435948);
    }
}
