package com.thealgorithms.datastructures.graphs;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Testcases for 2-SAT.
 * Please note thea while checking for boolean assignments always keep n + 1 elements and the first element should be always false.
 */
public class TwoSatTest {
    private TwoSat twoSat;

    /**
     * Case 1: Basic satisfiable case.
     * Simple 3 clauses with consistent assignments.
     */
    @Test
    public void testSatisfiableBasicCase() {
        twoSat = new TwoSat(5);

        twoSat.addClause(1, false, 2, false); // (x1 ∨ x2)
        twoSat.addClause(3, true, 2, false); // (¬x3 ∨ x2)
        twoSat.addClause(4, false, 5, true); // (x4 ∨ ¬x5)

        twoSat.solve();

        assertTrue(twoSat.isSolutionExists(), "Expected solution to exist");
        boolean[] expected = {false, true, true, true, true, true};
        assertArrayEquals(expected, twoSat.getSolutions());
    }

    /**
     * Case 2: Unsatisfiable due to direct contradiction.
     * (x1 ∨ x1) ∧ (¬x1 ∨ ¬x1) makes x1 and ¬x1 both required.
     */
    @Test
    public void testUnsatisfiableContradiction() {
        twoSat = new TwoSat(1);

        twoSat.addClause(1, false, 1, false); // (x1 ∨ x1)
        twoSat.addClause(1, true, 1, true); // (¬x1 ∨ ¬x1)

        twoSat.solve();

        assertFalse(twoSat.isSolutionExists(), "Expected no solution (contradiction)");
    }

    /**
     * Case 3: Single variable, trivially satisfiable.
     * Only (x1 ∨ x1) exists.
     */
    @Test
    public void testSingleVariableTrivialSatisfiable() {
        twoSat = new TwoSat(1);

        twoSat.addClause(1, false, 1, false); // (x1 ∨ x1)

        twoSat.solve();

        assertTrue(twoSat.isSolutionExists(), "Expected solution to exist");
        boolean[] expected = {false, true};
        assertArrayEquals(expected, twoSat.getSolutions());
    }

    /**
     * Case 4: Larger satisfiable system with dependencies.
     * (x1 ∨ x2), (¬x2 ∨ x3), (¬x3 ∨ x4), (¬x4 ∨ x5)
     */
    @Test
    public void testChainedDependenciesSatisfiable() {
        twoSat = new TwoSat(5);

        twoSat.addClause(1, false, 2, false);
        twoSat.addClause(2, true, 3, false);
        twoSat.addClause(3, true, 4, false);
        twoSat.addClause(4, true, 5, false);

        twoSat.solve();

        assertTrue(twoSat.isSolutionExists(), "Expected solution to exist");
        boolean[] solution = twoSat.getSolutions();
        for (int i = 1; i <= 5; i++) {
            assertTrue(solution[i], "Expected x" + i + " to be true");
        }
    }

    /**
     * Case 5: Contradiction due to dependency cycle.
     * (x1 ∨ x2), (¬x1 ∨ ¬x2), (x1 ∨ ¬x2), (¬x1 ∨ x2)
     * These clauses form a circular dependency making it impossible.
     */
    @Test
    public void testUnsatisfiableCycle() {
        twoSat = new TwoSat(2);

        twoSat.addClause(1, false, 2, false);
        twoSat.addClause(1, true, 2, true);
        twoSat.addClause(1, false, 2, true);
        twoSat.addClause(1, true, 2, false);

        twoSat.solve();

        assertFalse(twoSat.isSolutionExists(), "Expected no solution due to contradictory cycle");
    }

    /**
     * Testcase from CSES
     */
    @Test
    public void test6() {
        twoSat = new TwoSat(2);

        twoSat.addClause(1, true, 2, false);
        twoSat.addClause(2, true, 1, false);
        twoSat.addClause(1, true, 1, true);
        twoSat.addClause(2, false, 2, false);

        twoSat.solve();

        assertFalse(twoSat.isSolutionExists(), "Expected no solution.");
    }
}
