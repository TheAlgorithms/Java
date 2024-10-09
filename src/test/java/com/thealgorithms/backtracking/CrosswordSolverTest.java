package com.thealgorithms.backtracking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class CrosswordSolverTest {

    @Test
    public void testValidPlacement() {
        char[][] puzzle = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
        assertTrue(CrosswordSolver.isValid(puzzle, "cat", 0, 0, true));
        assertTrue(CrosswordSolver.isValid(puzzle, "dog", 0, 0, false));
        assertFalse(CrosswordSolver.isValid(puzzle, "cat", 1, 2, false));
    }

    @Test
    public void testPlaceAndRemoveWord() {
        char[][] puzzle = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
        CrosswordSolver.placeWord(puzzle, "cat", 0, 0, true);
        assertEquals('c', puzzle[0][0]);
        assertEquals('a', puzzle[1][0]);
        assertEquals('t', puzzle[2][0]);

        CrosswordSolver.removeWord(puzzle, "cat", 0, 0, true);
        assertEquals(' ', puzzle[0][0]);
        assertEquals(' ', puzzle[1][0]);
        assertEquals(' ', puzzle[2][0]);
    }

    @Test
    public void testSolveCrossword() {
        char[][] puzzle = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
        List<String> words = Arrays.asList("cat", "dog", "car");
        assertTrue(CrosswordSolver.solveCrossword(puzzle, words));

        /* Solved crossword:
         * c d c
         * a o a
         * t g r
         */

        assertEquals('c', puzzle[0][0]);
        assertEquals('a', puzzle[1][0]);
        assertEquals('t', puzzle[2][0]);

        assertEquals('d', puzzle[0][1]);
        assertEquals('o', puzzle[1][1]);
        assertEquals('g', puzzle[2][1]);

        assertEquals('c', puzzle[0][2]);
        assertEquals('a', puzzle[1][2]);
        assertEquals('r', puzzle[2][2]);
    }

    @Test
    public void testNoSolution() {
        char[][] puzzle = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
        List<String> words = Arrays.asList("cat", "dog", "elephant"); // 'elephant' is too long for the grid
        assertFalse(CrosswordSolver.solveCrossword(puzzle, words));
    }
}
