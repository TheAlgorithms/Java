package com.thealgorithms.datastructures.trees;

/**
 * Trie Data structure implementation without any libraries.
 * <p>
 * The Trie (also known as a prefix tree) is a special tree-like data structure
 * that is used to store a dynamic set or associative array where the keys are
 * usually strings. It is highly efficient for prefix-based searches.
 * <p>
 * This implementation supports basic Trie operations like insertion, search,
 * and deletion.
 * <p>
 * Each node of the Trie represents a character and has child nodes for each
 * possible character.
 *
 * @author <a href="https://github.com/dheeraj92">Dheeraj Kumar Barnwal</a>
 */
public class TrieImp {

    /**
     * Represents a Trie Node that stores a character and pointers to its children.
     * Each node has an array of 26 children (one for each letter from 'a' to 'z').
     */
    public class TrieNode {

        TrieNode[] child;
        boolean end;

        /**
         * Constructor to initialize a TrieNode with an empty child array
         * and set end to false.
         */
        public TrieNode() {
            child = new TrieNode[26];
            end = false;
        }
    }

    private final TrieNode root;

    /**
     * Constructor to initialize the Trie.
     * The root node is created but doesn't represent any character.
     */
    public TrieImp() {
        root = new TrieNode();
    }

    /**
     * Inserts a word into the Trie.
     * <p>
     * The method traverses the Trie from the root, character by character, and adds
     * nodes if necessary. It marks the last node of the word as an end node.
     *
     * @param word The word to be inserted into the Trie.
     */
    public void insert(String word) {
        TrieNode currentNode = root;
        for (int i = 0; i < word.length(); i++) {
            TrieNode node = currentNode.child[word.charAt(i) - 'a'];
            if (node == null) {
                node = new TrieNode();
                currentNode.child[word.charAt(i) - 'a'] = node;
            }
            currentNode = node;
        }
        currentNode.end = true;
    }

    /**
     * Searches for a word in the Trie.
     * <p>
     * This method traverses the Trie based on the input word and checks whether
     * the word exists. It returns true if the word is found and its end flag is
     * true.
     *
     * @param word The word to search in the Trie.
     * @return true if the word exists in the Trie, false otherwise.
     */
    public boolean search(String word) {
        TrieNode currentNode = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = currentNode.child[ch - 'a'];
            if (node == null) {
                return false;
            }
            currentNode = node;
        }
        return currentNode.end;
    }

    /**
     * Deletes a word from the Trie.
     * <p>
     * The method traverses the Trie to find the word and marks its end flag as
     * false.
     * It returns true if the word was successfully deleted, false if the word
     * wasn't found.
     *
     * @param word The word to be deleted from the Trie.
     * @return true if the word was found and deleted, false if it was not found.
     */
    public boolean delete(String word) {
        TrieNode currentNode = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = currentNode.child[ch - 'a'];
            if (node == null) {
                return false;
            }
            currentNode = node;
        }
        if (currentNode.end) {
            currentNode.end = false;
            return true;
        }
        return false;
    }

    /**
     * Helper method to print a string to the console.
     *
     * @param print The string to be printed.
     */
    public static void sop(String print) {
        System.out.println(print);
    }

    /**
     * Validates if a given word contains only lowercase alphabetic characters
     * (a-z).
     * <p>
     * The method uses a regular expression to check if the word matches the pattern
     * of only lowercase letters.
     *
     * @param word The word to be validated.
     * @return true if the word is valid (only a-z), false otherwise.
     */
    public static boolean isValid(String word) {
        return word.matches("^[a-z]+$");
    }
}
