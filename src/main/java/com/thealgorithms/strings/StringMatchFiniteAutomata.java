package com.thealgorithms.strings;

import java.util.Set;
import java.util.TreeSet;

/**
 * A class to perform string matching using <a href="https://en.wikipedia.org/wiki/Finite-state_machine">finite automata</a>.
 *
 * @author <a href="https://github.com/prateekKrOraon">Prateek Kumar Oraon</a>
 */
public final class StringMatchFiniteAutomata {

    // Constants
    private static final int CHARS = Character.MAX_VALUE + 1; // Total number of characters in the input alphabet

    // Private constructor to prevent instantiation
    private StringMatchFiniteAutomata() {
    }

    /**
     * Searches for the pattern in the given text using finite automata.
     *
     * @param text    The text to search within.
     * @param pattern The pattern to search for.
     */
    public static Set<Integer> searchPattern(final String text, final String pattern) {
        final var stateTransitionTable = computeStateTransitionTable(pattern);
        FiniteAutomata finiteAutomata = new FiniteAutomata(stateTransitionTable);

        Set<Integer> indexFound = new TreeSet<>();
        for (int i = 0; i < text.length(); i++) {
            finiteAutomata.consume(text.charAt(i));

            if (finiteAutomata.getState() == pattern.length()) {
                indexFound.add(i - pattern.length() + 1);
            }
        }
        return indexFound;
    }

    /**
     * Computes the finite automata table for the given pattern.
     *
     * @param pattern The pattern to preprocess.
     * @return The state transition table.
     */
    private static int[][] computeStateTransitionTable(final String pattern) {
        final int patternLength = pattern.length();
        int[][] stateTransitionTable = new int[patternLength + 1][CHARS];

        for (int state = 0; state <= patternLength; ++state) {
            for (int x = 0; x < CHARS; ++x) {
                stateTransitionTable[state][x] = getNextState(pattern, patternLength, state, x);
            }
        }

        return stateTransitionTable;
    }

    /**
     * Gets the next state for the finite automata.
     *
     * @param pattern       The pattern being matched.
     * @param patternLength The length of the pattern.
     * @param state         The current state.
     * @param x             The current character from the input alphabet.
     * @return The next state.
     */
    private static int getNextState(final String pattern, final int patternLength, final int state, final int x) {
        // If the current state is less than the length of the pattern
        // and the character matches the pattern character, go to the next state
        if (state < patternLength && x == pattern.charAt(state)) {
            return state + 1;
        }

        // Check for the highest prefix which is also a suffix
        for (int ns = state; ns > 0; ns--) {
            if (pattern.charAt(ns - 1) == x) {
                boolean match = true;
                for (int i = 0; i < ns - 1; i++) {
                    if (pattern.charAt(i) != pattern.charAt(state - ns + i + 1)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    return ns;
                }
            }
        }

        // If no prefix which is also a suffix is found, return 0
        return 0;
    }

    /**
     * A class representing the finite automata for pattern matching.
     */
    private static final class FiniteAutomata {
        private int state = 0;
        private final int[][] stateTransitionTable;

        private FiniteAutomata(int[][] stateTransitionTable) {
            this.stateTransitionTable = stateTransitionTable;
        }

        /**
         * Consumes an input character and transitions to the next state.
         *
         * @param input The input character.
         */
        private void consume(final char input) {
            state = stateTransitionTable[state][input];
        }

        /**
         * Gets the current state of the finite automata.
         *
         * @return The current state.
         */
        private int getState() {
            return state;
        }
    }
}
