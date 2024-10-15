package com.thealgorithms.backtracking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A class to solve a crossword puzzle using backtracking.
 * Example:
 * Input:
 *  puzzle = {
 *      {' ', ' ', ' '},
 *      {' ', ' ', ' '},
 *      {' ', ' ', ' '}
 *  }
 *  words = List.of("cat", "dog")
 *
 * Output:
 *  {
 *      {'c', 'a', 't'},
 *      {' ', ' ', ' '},
 *      {'d', 'o', 'g'}
 *  }
 */
public final class CrosswordSolver {
    private CrosswordSolver() {
    }

    /**
     * Checks if a word can be placed at the specified position in the crossword.
     *
     * @param puzzle   The crossword puzzle represented as a 2D char array.
     * @param word     The word to be placed.
     * @param row      The row index where the word might be placed.
     * @param col      The column index where the word might be placed.
     * @param vertical If true, the word is placed vertically; otherwise, horizontally.
     * @return true if the word can be placed, false otherwise.
     */
    public static boolean isValid(char[][] puzzle, String word, int row, int col, boolean vertical) {
        for (int i = 0; i < word.length(); i++) {
            if (vertical) {
                if (row + i >= puzzle.length || puzzle[row + i][col] != ' ') {
                    return false;
                }
            } else {
                if (col + i >= puzzle[0].length || puzzle[row][col + i] != ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Places a word at the specified position in the crossword.
     *
     * @param puzzle   The crossword puzzle represented as a 2D char array.
     * @param word     The word to be placed.
     * @param row      The row index where the word will be placed.
     * @param col      The column index where the word will be placed.
     * @param vertical If true, the word is placed vertically; otherwise, horizontally.
     */
    public static void placeWord(char[][] puzzle, String word, int row, int col, boolean vertical) {
        for (int i = 0; i < word.length(); i++) {
            if (vertical) {
                puzzle[row + i][col] = word.charAt(i);
            } else {
                puzzle[row][col + i] = word.charAt(i);
            }
        }
    }

    /**
     * Removes a word from the specified position in the crossword.
     *
     * @param puzzle   The crossword puzzle represented as a 2D char array.
     * @param word     The word to be removed.
     * @param row      The row index where the word is placed.
     * @param col      The column index where the word is placed.
     * @param vertical If true, the word was placed vertically; otherwise, horizontally.
     */
    public static void removeWord(char[][] puzzle, String word, int row, int col, boolean vertical) {
        for (int i = 0; i < word.length(); i++) {
            if (vertical) {
                puzzle[row + i][col] = ' ';
            } else {
                puzzle[row][col + i] = ' ';
            }
        }
    }

    /**
     * Solves the crossword puzzle using backtracking.
     *
     * @param puzzle The crossword puzzle represented as a 2D char array.
     * @param words  The list of words to be placed.
     * @return true if the crossword is solved, false otherwise.
     */
    public static boolean solveCrossword(char[][] puzzle, Collection<String> words) {
        // Create a mutable copy of the words list
        List<String> remainingWords = new ArrayList<>(words);

        for (int row = 0; row < puzzle.length; row++) {
            for (int col = 0; col < puzzle[0].length; col++) {
                if (puzzle[row][col] == ' ') {
                    for (String word : new ArrayList<>(remainingWords)) {
                        for (boolean vertical : new boolean[] {true, false}) {
                            if (isValid(puzzle, word, row, col, vertical)) {
                                placeWord(puzzle, word, row, col, vertical);
                                remainingWords.remove(word);
                                if (solveCrossword(puzzle, remainingWords)) {
                                    return true;
                                }
                                remainingWords.add(word);
                                removeWord(puzzle, word, row, col, vertical);
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
