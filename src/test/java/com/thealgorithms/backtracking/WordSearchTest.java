package com.thealgorithms.backtracking;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WordSearchTest {
    @Test
    void test1() {
        WordSearch ws = new WordSearch();
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";
        assertTrue(ws.exist(board, word));
    }

    @Test
    void test2() {
        WordSearch ws = new WordSearch();
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "SEE";
        assertTrue(ws.exist(board, word));
    }

    @Test
    void test3() {
        WordSearch ws = new WordSearch();
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCB";
        Assertions.assertFalse(ws.exist(board, word));
    }
}