package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test class for LinearDiophantineEquationsSolver.
 * Tests various cases including:
 * - Equations with solutions
 * - Equations with no solutions
 * - Special cases (zero coefficients, infinite solutions)
 * - Edge cases (negative coefficients, large numbers)
 */
class LinearDiophantineEquationsSolverTest {

    /**
     * Tests the example equation 3x + 4y = 7.
     * Expected solution: x = -9, y = 8 (or other valid solutions).
     */
    @Test
    void testBasicEquation() {
        final var equation = new LinearDiophantineEquationsSolver.Equation(3, 4, 7);
        final var solution = LinearDiophantineEquationsSolver.findAnySolution(equation);

        assertNotNull(solution);
        // Verify that the solution satisfies the equation
        int result = equation.a() * solution.getX() + equation.b() * solution.getY();
        assertEquals(equation.c(), result);
    }

    /**
     * Tests an equation with no solution: 2x + 4y = 5.
     * Since gcd(2, 4) = 2 and 2 does not divide 5, no solution exists.
     */
    @Test
    void testNoSolution() {
        final var equation = new LinearDiophantineEquationsSolver.Equation(2, 4, 5);
        final var solution = LinearDiophantineEquationsSolver.findAnySolution(equation);

        assertEquals(LinearDiophantineEquationsSolver.Solution.NO_SOLUTION, solution);
    }

    /**
     * Tests the trivial equation 0x + 0y = 0.
     * This has infinite solutions.
     */
    @Test
    void testInfiniteSolutions() {
        final var equation = new LinearDiophantineEquationsSolver.Equation(0, 0, 0);
        final var solution = LinearDiophantineEquationsSolver.findAnySolution(equation);

        assertEquals(LinearDiophantineEquationsSolver.Solution.INFINITE_SOLUTIONS, solution);
    }

    /**
     * Tests an equation where a = 0: 0x + 5y = 10.
     * Expected solution: x = 0, y = 2.
     */
    @Test
    void testZeroCoefficient() {
        final var equation = new LinearDiophantineEquationsSolver.Equation(0, 5, 10);
        final var solution = LinearDiophantineEquationsSolver.findAnySolution(equation);

        assertNotNull(solution);
        int result = equation.a() * solution.getX() + equation.b() * solution.getY();
        assertEquals(equation.c(), result);
    }

    /**
     * Tests an equation where b = 0: 3x + 0y = 9.
     * Expected solution: x = 3, y can be anything (solver will return y = 0).
     */
    @Test
    void testZeroCoefficientB() {
        final var equation = new LinearDiophantineEquationsSolver.Equation(3, 0, 9);
        final var solution = LinearDiophantineEquationsSolver.findAnySolution(equation);

        assertNotNull(solution);
        int result = equation.a() * solution.getX() + equation.b() * solution.getY();
        assertEquals(equation.c(), result);
    }

    /**
     * Tests an equation with negative coefficients: -3x + 4y = 7.
     */
    @Test
    void testNegativeCoefficients() {
        final var equation = new LinearDiophantineEquationsSolver.Equation(-3, 4, 7);
        final var solution = LinearDiophantineEquationsSolver.findAnySolution(equation);

        assertNotNull(solution);
        int result = equation.a() * solution.getX() + equation.b() * solution.getY();
        assertEquals(equation.c(), result);
    }

    /**
     * Tests an equation with negative result: 3x + 4y = -7.
     */
    @Test
    void testNegativeResult() {
        final var equation = new LinearDiophantineEquationsSolver.Equation(3, 4, -7);
        final var solution = LinearDiophantineEquationsSolver.findAnySolution(equation);

        assertNotNull(solution);
        int result = equation.a() * solution.getX() + equation.b() * solution.getY();
        assertEquals(equation.c(), result);
    }

    /**
     * Tests an equation with coprime coefficients: 7x + 11y = 1.
     * Since gcd(7, 11) = 1, a solution exists.
     */
    @Test
    void testCoprimeCoefficients() {
        final var equation = new LinearDiophantineEquationsSolver.Equation(7, 11, 1);
        final var solution = LinearDiophantineEquationsSolver.findAnySolution(equation);

        assertNotNull(solution);
        int result = equation.a() * solution.getX() + equation.b() * solution.getY();
        assertEquals(equation.c(), result);
    }

    /**
     * Tests an equation with larger coefficients: 12x + 18y = 30.
     * Since gcd(12, 18) = 6 and 6 divides 30, a solution exists.
     */
    @Test
    void testLargerCoefficients() {
        final var equation = new LinearDiophantineEquationsSolver.Equation(12, 18, 30);
        final var solution = LinearDiophantineEquationsSolver.findAnySolution(equation);

        assertNotNull(solution);
        int result = equation.a() * solution.getX() + equation.b() * solution.getY();
        assertEquals(equation.c(), result);
    }

    /**
     * Tests an equation that has no solution due to GCD: 6x + 9y = 5.
     * Since gcd(6, 9) = 3 and 3 does not divide 5, no solution exists.
     */
    @Test
    void testNoSolutionGcdCheck() {
        final var equation = new LinearDiophantineEquationsSolver.Equation(6, 9, 5);
        final var solution = LinearDiophantineEquationsSolver.findAnySolution(equation);

        assertEquals(LinearDiophantineEquationsSolver.Solution.NO_SOLUTION, solution);
    }

    /**
     * Tests the equation x + y = 1.
     * Simple case where gcd(1, 1) = 1.
     */
    @Test
    void testSimpleCase() {
        final var equation = new LinearDiophantineEquationsSolver.Equation(1, 1, 1);
        final var solution = LinearDiophantineEquationsSolver.findAnySolution(equation);

        assertNotNull(solution);
        int result = equation.a() * solution.getX() + equation.b() * solution.getY();
        assertEquals(equation.c(), result);
    }

    /**
     * Tests Solution equality.
     */
    @Test
    void testSolutionEquality() {
        final var solution1 = new LinearDiophantineEquationsSolver.Solution(3, 5);
        final var solution2 = new LinearDiophantineEquationsSolver.Solution(3, 5);
        final var solution3 = new LinearDiophantineEquationsSolver.Solution(3, 6);

        assertEquals(solution1, solution2);
        assertNotEquals(solution3, solution1);
        assertEquals(solution1, solution1);
        assertNotNull(solution1);
        assertNotEquals("string", solution1);
    }

    /**
     * Tests Solution hashCode.
     */
    @Test
    void testSolutionHashCode() {
        final var solution1 = new LinearDiophantineEquationsSolver.Solution(3, 5);
        final var solution2 = new LinearDiophantineEquationsSolver.Solution(3, 5);

        assertEquals(solution1.hashCode(), solution2.hashCode());
    }

    /**
     * Tests Solution toString.
     */
    @Test
    void testSolutionToString() {
        final var solution = new LinearDiophantineEquationsSolver.Solution(3, 5);
        final var str = solution.toString();

        assertTrue(str.contains("3"));
        assertTrue(str.contains("5"));
        assertTrue(str.contains("Solution"));
    }

    /**
     * Tests GcdSolutionWrapper equality.
     */
    @Test
    void testGcdSolutionWrapperEquality() {
        final var solution = new LinearDiophantineEquationsSolver.Solution(1, 2);
        final var wrapper1 = new LinearDiophantineEquationsSolver.GcdSolutionWrapper(5, solution);
        final var wrapper2 = new LinearDiophantineEquationsSolver.GcdSolutionWrapper(5, solution);
        final var wrapper3 = new LinearDiophantineEquationsSolver.GcdSolutionWrapper(6, solution);

        assertEquals(wrapper1, wrapper2);
        assertNotEquals(wrapper3, wrapper1);
        assertEquals(wrapper1, wrapper1);
        assertNotNull(wrapper1);
        assertNotEquals("string", wrapper1);
    }

    /**
     * Tests GcdSolutionWrapper hashCode.
     */
    @Test
    void testGcdSolutionWrapperHashCode() {
        final var solution = new LinearDiophantineEquationsSolver.Solution(1, 2);
        final var wrapper1 = new LinearDiophantineEquationsSolver.GcdSolutionWrapper(5, solution);
        final var wrapper2 = new LinearDiophantineEquationsSolver.GcdSolutionWrapper(5, solution);

        assertEquals(wrapper1.hashCode(), wrapper2.hashCode());
    }

    /**
     * Tests GcdSolutionWrapper toString.
     */
    @Test
    void testGcdSolutionWrapperToString() {
        final var solution = new LinearDiophantineEquationsSolver.Solution(1, 2);
        final var wrapper = new LinearDiophantineEquationsSolver.GcdSolutionWrapper(5, solution);
        final var str = wrapper.toString();

        assertTrue(str.contains("5"));
        assertTrue(str.contains("GcdSolutionWrapper"));
    }

    /**
     * Tests Equation record functionality.
     */
    @Test
    void testEquationRecord() {
        final var equation = new LinearDiophantineEquationsSolver.Equation(3, 4, 7);

        assertEquals(3, equation.a());
        assertEquals(4, equation.b());
        assertEquals(7, equation.c());
    }

    /**
     * Tests an equation with c = 0: 3x + 4y = 0.
     * Expected solution: x = 0, y = 0.
     */
    @Test
    void testZeroResult() {
        final var equation = new LinearDiophantineEquationsSolver.Equation(3, 4, 0);
        final var solution = LinearDiophantineEquationsSolver.findAnySolution(equation);

        assertNotNull(solution);
        int result = equation.a() * solution.getX() + equation.b() * solution.getY();
        assertEquals(equation.c(), result);
    }

    /**
     * Tests Solution setters.
     */
    @Test
    void testSolutionSetters() {
        final var solution = new LinearDiophantineEquationsSolver.Solution(1, 2);

        solution.setX(10);
        solution.setY(20);

        assertEquals(10, solution.getX());
        assertEquals(20, solution.getY());
    }

    /**
     * Tests GcdSolutionWrapper setters.
     */
    @Test
    void testGcdSolutionWrapperSetters() {
        final var solution = new LinearDiophantineEquationsSolver.Solution(1, 2);
        final var wrapper = new LinearDiophantineEquationsSolver.GcdSolutionWrapper(5, solution);

        final var newSolution = new LinearDiophantineEquationsSolver.Solution(3, 4);
        wrapper.setGcd(10);
        wrapper.setSolution(newSolution);

        assertEquals(10, wrapper.getGcd());
        assertEquals(newSolution, wrapper.getSolution());
    }

    /**
     * Tests an equation with both coefficients negative: -3x - 4y = -7.
     */
    @Test
    void testBothCoefficientsNegative() {
        final var equation = new LinearDiophantineEquationsSolver.Equation(-3, -4, -7);
        final var solution = LinearDiophantineEquationsSolver.findAnySolution(equation);

        assertNotNull(solution);
        int result = equation.a() * solution.getX() + equation.b() * solution.getY();
        assertEquals(equation.c(), result);
    }

    /**
     * Tests an equation with large prime coefficients: 97x + 101y = 198.
     */
    @Test
    void testLargePrimeCoefficients() {
        final var equation = new LinearDiophantineEquationsSolver.Equation(97, 101, 198);
        final var solution = LinearDiophantineEquationsSolver.findAnySolution(equation);

        assertNotNull(solution);
        int result = equation.a() * solution.getX() + equation.b() * solution.getY();
        assertEquals(equation.c(), result);
    }
}
