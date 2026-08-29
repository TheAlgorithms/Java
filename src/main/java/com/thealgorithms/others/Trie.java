package com.thealgorithms.others;

import java.util.ArrayList;
import java.util.List;

public class Trie {

    private static final int ALPHABET_SIZE = 26;

    // ---------------- NODE ----------------

    static class TrieNode {

        TrieNode[] children = new TrieNode[ALPHABET_SIZE];

        boolean isWordEnd;
    }

    // ---------------- ROOT ----------------

    private final TrieNode root = new TrieNode();

    // ---------------- INSERT ----------------

    public void insert(String word) {

        word = normalize(word);

        TrieNode current = root;

        for (char c : word.toCharArray()) {

            int index = c - 'a';

            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }

            current = current.children[index];
        }

        current.isWordEnd = true;
    }

    // ---------------- SEARCH ----------------

    public boolean search(String word) {

        word = normalize(word);

        TrieNode current = root;

        for (char c : word.toCharArray()) {

            int index = c - 'a';

            if (current.children[index] == null) {
                return false;
            }

            current = current.children[index];
        }

        return current.isWordEnd;
    }

    // ---------------- PREFIX ----------------

    public boolean startsWith(String prefix) {

        prefix = normalize(prefix);

        return findNode(prefix) != null;
    }

    // ---------------- AUTOCOMPLETE ----------------

    public List<String> suggestions(String prefix) {

        prefix = normalize(prefix);

        List<String> result = new ArrayList<>();

        TrieNode node = findNode(prefix);

        // Prefix doesn't exist
        if (node == null) {
            return result;
        }

        StringBuilder currentWord = new StringBuilder(prefix);

        collectSuggestions(node, currentWord, result);

        return result;
    }

    // Recursive autocomplete
    private void collectSuggestions(TrieNode node, StringBuilder currentWord, List<String> result) {

        // Current word is complete
        if (node.isWordEnd) {
            result.add(currentWord.toString());
        }

        // Check all 26 possible characters
        for (int i = 0; i < ALPHABET_SIZE; i++) {

            if (node.children[i] != null) {

                // Add character
                currentWord.append((char) ('a' + i));

                // Go to next node
                collectSuggestions(node.children[i], currentWord, result);

                // Backtrack
                currentWord.deleteCharAt(currentWord.length() - 1);
            }
        }
    }

    // ---------------- FIND NODE ----------------

    private TrieNode findNode(String text) {

        TrieNode current = root;

        for (char c : text.toCharArray()) {

            int index = c - 'a';

            if (current.children[index] == null) {
                return null;
            }

            current = current.children[index];
        }

        return current;
    }

    // ---------------- NORMALIZE ----------------

    private String normalize(String text) {

        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("Word cannot be empty");
        }

        text = text.toLowerCase().trim();

        for (char c : text.toCharArray()) {

            if (c < 'a' || c > 'z') {
                throw new IllegalArgumentException("Only a-z characters are allowed");
            }
        }

        return text;
    }

    // ---------------- MAIN ----------------

    public static void main(String[] args) {

        Trie trie = new Trie();

        /*
         * Add as many words as you want.
         * No fixed 10-20 word limitation.
         */

        String[] words = {
                "hello", "hell", "hel", "help", "helps", "helping", "helicopter", "car", "career", "dog"
        };

        // Dynamically insert all words
        for (String word : words) {
            trie.insert(word);
        }

        // Search
        System.out.println("Search 'hello': " + trie.search("hello"));

        System.out.println("Search 'xyz': " + trie.search("xyz"));

        // Prefix
        System.out.println("Starts with 'hel': " + trie.startsWith("hel"));

        // Autocomplete
        System.out.println("\nSuggestions for 'hel':");

        List<String> suggestions = trie.suggestions("hel");

        for (String word : suggestions) {
            System.out.println(word);
        }
    }
}
