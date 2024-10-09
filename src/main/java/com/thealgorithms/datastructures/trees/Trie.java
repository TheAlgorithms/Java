package com.thealgorithms.datastructures.trees;

import java.util.HashMap;

/**
 * Represents a Trie Node that stores a character and pointers to its children.
 * Each node has a hashmap which can point to all possible characters.
 * Each node also has a boolean value to indicate if it is the end of a word.
 */
class TrieNode {
    char value;
    HashMap<Character, TrieNode> child;
    boolean end;

    /**
     * Constructor to initialize a TrieNode with an empty hashmap
     * and set end to false.
     */
    TrieNode(char value) {
        this.value = value;
        this.child = new HashMap<>();
        this.end = false;
    }
}

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
 * @author <a href="https://github.com/sailok">Sailok Chinta</a>
 */

public class Trie {
    private static final char ROOT_NODE_VALUE = '*';

    private final TrieNode root;

    /**
     * Constructor to initialize the Trie.
     * The root node is created but doesn't represent any character.
     */
    public Trie() {
        root = new TrieNode(ROOT_NODE_VALUE);
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
            TrieNode node = currentNode.child.getOrDefault(word.charAt(i), null);

            if (node == null) {
                node = new TrieNode(word.charAt(i));
                currentNode.child.put(word.charAt(i), node);
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
            TrieNode node = currentNode.child.getOrDefault(word.charAt(i), null);

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
            TrieNode node = currentNode.child.getOrDefault(word.charAt(i), null);
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
     * Counts the number of words in the trie
     *<p>
     * The method traverses the Trie and counts the number of words.
     *
     * @return count of words
     */
    public int countWords() {
        return countWords(root);
    }

    private int countWords(TrieNode node) {
        if (node == null) {
            return 0;
        }

        int count = 0;
        if (node.end) {
            count++;
        }

        for (TrieNode child : node.child.values()) {
            count += countWords(child);
        }

        return count;
    }

    /**
     * Check if the prefix exists in the trie
     *
     * @param prefix the prefix to be checked in the Trie
     * @return true / false depending on the prefix if exists in the Trie
     */
    public boolean startsWithPrefix(String prefix) {
        TrieNode currentNode = root;

        for (int i = 0; i < prefix.length(); i++) {
            TrieNode node = currentNode.child.getOrDefault(prefix.charAt(i), null);
            if (node == null) {
                return false;
            }

            currentNode = node;
        }

        return true;
    }

    /**
     * Count the number of words starting with the given prefix in the trie
     *
     * @param prefix the prefix to be checked in the Trie
     * @return count of words
     */
    public int countWordsWithPrefix(String prefix) {
        TrieNode currentNode = root;

        for (int i = 0; i < prefix.length(); i++) {
            TrieNode node = currentNode.child.getOrDefault(prefix.charAt(i), null);
            if (node == null) {
                return 0;
            }

            currentNode = node;
        }

        return countWords(currentNode);
    }
}
