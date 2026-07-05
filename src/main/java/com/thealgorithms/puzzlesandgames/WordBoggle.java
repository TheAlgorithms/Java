package com.thealgorithms.puzzlesandgames;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class WordBoggle {

    private WordBoggle() {
    }

    /**
     * O(nm * 8^s + ws) time, where n = width of boggle board, m = height of
     * boggle board, s = length of longest word in string array, w = length of
     * string array, 8 is due to 8 explorable neighbours O(nm + ws) space.
     */
    public static List<String> boggleBoard(char[][] board, String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        Set<String> foundWords = new HashSet<>();
        // Bug fix: columns are board[0].length, not board.length (board need not be square).
        boolean[][] visited = new boolean[board.length][board[0].length];

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                explore(row, col, board, trie.getRoot(), visited, foundWords);
            }
        }

        return new ArrayList<>(foundWords);
    }

    // Private: this is an implementation detail of boggleBoard, not part of the public API.
    private static void explore(int row, int col, char[][] board, TrieNode node, boolean[][] visited, Set<String> foundWords) {
        if (visited[row][col]) {
            return;
        }

        char letter = board[row][col];
        TrieNode nextNode = node.getChild(letter);
        if (nextNode == null) {
            return;
        }

        visited[row][col] = true;

        if (nextNode.isEndOfWord()) {
            foundWords.add(nextNode.getWord());
        }

        for (Position neighbor : getNeighbors(row, col, board)) {
            explore(neighbor.row(), neighbor.col(), board, nextNode, visited, foundWords);
        }

        visited[row][col] = false;
    }

    // Private: neighbor geometry is an internal concern of the search, not a public utility.
    private static List<Position> getNeighbors(int row, int col, char[][] board) {
        List<Position> neighbors = new ArrayList<>();
        int rows = board.length;
        int cols = board[0].length;

        for (int deltaRow = -1; deltaRow <= 1; deltaRow++) {
            for (int deltaCol = -1; deltaCol <= 1; deltaCol++) {
                if (deltaRow == 0 && deltaCol == 0) {
                    continue;
                }
                int newRow = row + deltaRow;
                int newCol = col + deltaCol;
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                    neighbors.add(new Position(newRow, newCol));
                }
            }
        }

        return neighbors;
    }

    // Named coordinate pair instead of Integer[]: self-documenting, no autoboxing ambiguity.
    private record Position(int row, int col) {
    }
}

/**
 * A trie node. All state is private; callers interact only through the methods below,
 * so the "end of word" marker is an explicit flag rather than a magic sentinel key.
 */
final class TrieNode {

    private final Map<Character, TrieNode> children = new HashMap<>();
    private boolean endOfWord;
    private String word = "";

    TrieNode getChild(char letter) {
        return children.get(letter);
    }

    void addChild(char letter, TrieNode node) {
        children.put(letter, node);
    }

    boolean isEndOfWord() {
        return endOfWord;
    }

    void markEndOfWord(String word) {
        this.endOfWord = true;
        this.word = word;
    }

    String getWord() {
        return word;
    }
}

/**
 * Trie used to optimize string search. Owns its root and construction logic
 * so callers (WordBoggle) never need to know how nodes are wired together.
 */
final class Trie {

    private final TrieNode root = new TrieNode();

    TrieNode getRoot() {
        return root;
    }

    void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            TrieNode child = node.getChild(letter);
            if (child == null) {
                child = new TrieNode();
                node.addChild(letter, child);
            }
            node = child;
        }
        node.markEndOfWord(word);
    }
}