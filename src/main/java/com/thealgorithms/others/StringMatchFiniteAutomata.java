package com.thealgorithms.others;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * A class to perform string matching using <a href="https://en.wikipedia.org/wiki/Finite-state_machine">finite automata</a>.
 *
 * @author <a href="https://github.com/prateekKrOraon">Prateek Kumar Oraon</a>
 */
public final class StringMatchFiniteAutomata {

    // Constants
    private static final int CHARS = 256; // Total number of characters in the input alphabet

    // Finite automata table
    private static int[][] finiteAutomata;

    // Private constructor to prevent instantiation
    private StringMatchFiniteAutomata() {
    }

    public static void main(String[] args) {
        // Scanner instance for user input
        try (Scanner scanner = new Scanner(System.in);) {

            System.out.println("Enter text:");
            String text = scanner.nextLine();

            System.out.println("Enter pattern:");
            String pattern = scanner.nextLine();

            Set<Integer> indexFound = searchPattern(text, pattern);
            indexFound.forEach(System.out::println);
        }
    }

    /**
     * Searches for the pattern in the given text using finite automata.
     *
     * @param text    The text to search within.
     * @param pattern The pattern to search for.
     */
    public static Set<Integer> searchPattern(String text, String pattern) {
        Set<Integer> indexFound = new TreeSet<>();
        int patternLength = pattern.length();
        int textLength = text.length();

        // Initialize finite automata table
        finiteAutomata = new int[patternLength + 1][CHARS];

        // Preprocess the pattern to create the finite automata table
        computeFiniteAutomata(pattern, patternLength);

        int state = 0; // Initial state

        // Process the text over the finite automata
        for (int i = 0; i < textLength; i++) {
            state = finiteAutomata[state][text.charAt(i)];

            if (state == patternLength) {
                indexFound.add(i - patternLength + 1);
            }
        }
        return indexFound;
    }

    /**
     * Computes the finite automata table for the given pattern.
     *
     * @param pattern       The pattern to preprocess.
     * @param patternLength The length of the pattern.
     */
    private static void computeFiniteAutomata(String pattern, int patternLength) {
        for (int state = 0; state <= patternLength; ++state) {
            for (int x = 0; x < CHARS; ++x) {
                finiteAutomata[state][x] = getNextState(pattern, patternLength, state, x);
            }
        }
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
    private static int getNextState(String pattern, int patternLength, int state, int x) {
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
}
