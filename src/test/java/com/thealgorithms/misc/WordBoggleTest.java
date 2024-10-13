package com.thealgorithms.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WordBoggleTest {

    private char[][] board;
    private String[] words;

    @BeforeEach
    public void setup() {
        board = new char[][] {
            {'t', 'h', 'i', 's', 'i', 's', 'a'},
            {'s', 'i', 'm', 'p', 'l', 'e', 'x'},
            {'b', 'x', 'x', 'x', 'x', 'e', 'b'},
            {'x', 'o', 'g', 'g', 'l', 'x', 'o'},
            {'x', 'x', 'x', 'D', 'T', 'r', 'a'},
            {'R', 'E', 'P', 'E', 'A', 'd', 'x'},
            {'x', 'x', 'x', 'x', 'x', 'x', 'x'},
            {'N', 'O', 'T', 'R', 'E', '_', 'P'},
            {'x', 'x', 'D', 'E', 'T', 'A', 'E'},
        };

        words = new String[] {"this", "is", "not", "a", "simple", "test", "boggle", "board", "REPEATED", "NOTRE_PEATED"};
    }

    @Test
    public void testBoggleBoardFindsAllWords() {
        List<String> expected = Arrays.asList("this", "is", "a", "simple", "board", "boggle", "NOTRE_PEATED");
        List<String> result = WordBoggle.boggleBoard(board, words);
        assertEquals(expected.size(), result.size());
        assertTrue(expected.containsAll(result));
    }

    @Test
    public void testBoggleBoardNoMatchingWords() {
        // Test with words that don't exist on the board
        String[] nonMatchingWords = {"xyz", "hello", "world"};
        List<String> result = WordBoggle.boggleBoard(board, nonMatchingWords);
        assertEquals(0, result.size());
    }

    @Test
    public void testBoggleBoardEmptyBoard() {
        // Test with an empty board
        char[][] emptyBoard = new char[0][0];
        List<String> result = WordBoggle.boggleBoard(emptyBoard, words);
        assertEquals(0, result.size());
    }

    @Test
    public void testBoggleBoardEmptyWordsArray() {
        // Test with an empty words array
        String[] emptyWords = {};
        List<String> result = WordBoggle.boggleBoard(board, emptyWords);
        assertEquals(0, result.size());
    }

    @Test
    public void testBoggleBoardSingleCharacterWords() {
        // Test with single-character words
        String[] singleCharWords = {"a", "x", "o"};
        List<String> expected = Arrays.asList("a", "o");
        List<String> result = WordBoggle.boggleBoard(board, singleCharWords);
        assertEquals(expected.size() + 1, result.size());
    }

    @Test
    public void testBoggleBoardDuplicateWordsInInput() {
        // Test with duplicate words in the input array
        String[] duplicateWords = {"this", "this", "board", "board"};
        List<String> expected = Arrays.asList("this", "board");
        List<String> result = WordBoggle.boggleBoard(board, duplicateWords);
        assertEquals(expected.size(), result.size());
        assertTrue(expected.containsAll(result));
    }
}
