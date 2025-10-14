package com.thealgorithms.datastructures.tries;

/**
 * A Trie (prefix tree) data structure implementation for storing strings.
 *
 * <p>
 * The Trie allows for efficient insertion, search, and prefix-matching
 * operations.
 * It is commonly used for tasks such as autocomplete, spell checking, and
 * dictionary storage.
 *
 * <p>
 * This implementation supports lowercase English letters ('a'–'z').
 * Each node stores an array of 26 children references for each alphabet letter.
 *
 * <p>
 * Trie is not thread-safe and should not be accessed by multiple threads
 * concurrently.
 */
public class Trie {

    /** The root node of the Trie. */
    private final TrieNode root;

    /** Constructs an empty Trie with an empty root node. */
    public Trie() {
        root = new TrieNode();
    }

    /**
     * Inserts a word into the Trie.
     *
     * @param word the word to insert; must not be {@code null} or empty
     */
    public void insert(String word) {
        TrieNode currentNode = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (currentNode.children[index] == null) {
                currentNode.children[index] = new TrieNode();
            }
            currentNode = currentNode.children[index];
        }
        currentNode.isEndOfWord = true;
    }

    /**
     * Checks whether the Trie contains a specific word.
     *
     * @param word the word to check for; must not be {@code null}
     * @return {@code true} if the Trie contains the specified word; {@code false} otherwise
     */
    public boolean search(String word) {
        TrieNode currentNode = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (currentNode.children[index] == null) {
                return false;
            }
            currentNode = currentNode.children[index];
        }
        return currentNode.isEndOfWord;
    }

    /**
     * Checks whether there is any word in the Trie that starts with the given prefix.
     *
     * @param prefix the prefix to check for; must not be {@code null}
     * @return {@code true} if there is at least one word starting with the prefix;
     *         {@code false} otherwise
     */
    public boolean startsWith(String prefix) {
        TrieNode currentNode = root;
        for (char ch : prefix.toCharArray()) {
            int index = ch - 'a';
            if (currentNode.children[index] == null) {
                return false;
            }
            currentNode = currentNode.children[index];
        }
        return true;
    }

    /**
     * Represents a single node in the Trie.
     */
    private static class TrieNode {
        private final TrieNode[] children;
        private boolean isEndOfWord;

        /** Constructs an empty TrieNode with 26 possible children. */
        TrieNode() {
            children = new TrieNode[26];
            isEndOfWord = false;
        }
    }
}
