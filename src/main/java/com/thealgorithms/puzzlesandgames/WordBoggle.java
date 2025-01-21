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
     * O(nm * 8^s + ws) time where n = width of boggle board, m = height of
     * boggle board, s = length of longest word in string array, w = length of
     * string array, 8 is due to 8 explorable neighbours O(nm + ws) space.
     */
    public static List<String> boggleBoard(char[][] board, String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.add(word);
        }
        Set<String> finalWords = new HashSet<>();
        boolean[][] visited = new boolean[board.length][board.length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                explore(i, j, board, trie.root, visited, finalWords);
            }
        }
        return new ArrayList<>(finalWords);
    }

    public static void explore(int i, int j, char[][] board, TrieNode trieNode, boolean[][] visited, Set<String> finalWords) {
        if (visited[i][j]) {
            return;
        }

        char letter = board[i][j];
        if (!trieNode.children.containsKey(letter)) {
            return;
        }
        visited[i][j] = true;
        trieNode = trieNode.children.get(letter);
        if (trieNode.children.containsKey('*')) {
            finalWords.add(trieNode.word);
        }

        List<Integer[]> neighbors = getNeighbors(i, j, board);
        for (Integer[] neighbor : neighbors) {
            explore(neighbor[0], neighbor[1], board, trieNode, visited, finalWords);
        }

        visited[i][j] = false;
    }

    public static List<Integer[]> getNeighbors(int i, int j, char[][] board) {
        List<Integer[]> neighbors = new ArrayList<>();
        if (i > 0 && j > 0) {
            neighbors.add(new Integer[] {i - 1, j - 1});
        }

        if (i > 0 && j < board[0].length - 1) {
            neighbors.add(new Integer[] {i - 1, j + 1});
        }

        if (i < board.length - 1 && j < board[0].length - 1) {
            neighbors.add(new Integer[] {i + 1, j + 1});
        }

        if (i < board.length - 1 && j > 0) {
            neighbors.add(new Integer[] {i + 1, j - 1});
        }

        if (i > 0) {
            neighbors.add(new Integer[] {i - 1, j});
        }

        if (i < board.length - 1) {
            neighbors.add(new Integer[] {i + 1, j});
        }

        if (j > 0) {
            neighbors.add(new Integer[] {i, j - 1});
        }

        if (j < board[0].length - 1) {
            neighbors.add(new Integer[] {i, j + 1});
        }

        return neighbors;
    }
}

// Trie used to optimize string search
class TrieNode {

    Map<Character, TrieNode> children = new HashMap<>();
    String word = "";
}

class Trie {

    TrieNode root;
    char endSymbol;

    Trie() {
        this.root = new TrieNode();
        this.endSymbol = '*';
    }

    public void add(String str) {
        TrieNode node = this.root;
        for (int i = 0; i < str.length(); i++) {
            char letter = str.charAt(i);
            if (!node.children.containsKey(letter)) {
                TrieNode newNode = new TrieNode();
                node.children.put(letter, newNode);
            }
            node = node.children.get(letter);
        }
        node.children.put(this.endSymbol, null);
        node.word = str;
    }
}
