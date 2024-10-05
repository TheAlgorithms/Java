package com.thealgorithms.datastructures.trees;

import java.util.HashMap;

/**
 * TrieNode class holds the data for each node in the trie
 */
class TrieNode {
    char value;
    HashMap<Character, TrieNode> child;
    boolean end;

    TrieNode(char value) {
        this.value = value;
        this.child = new HashMap<>();
        this.end = false;
    }
}

/**
 * Trie Data structure implementation without any libraries
 *
 * @author <a href="https://github.com/dheeraj92">Dheeraj Kumar Barnwal</a>
 * @author <a href="https://github.com/sailok">Sailok Chinta</a>
 */
public class Trie {
    private static final char ROOT_NODE_VALUE = '*';

    private final TrieNode root;

    public Trie() {
        root = new TrieNode(ROOT_NODE_VALUE);
    }

    /**
     * Inserts a word into the trie
     *
     * @param word
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
     * Searches a word in the trie
     *
     * @param word
     * @return true / false
     */
    public boolean search(String word) {
        TrieNode currentNode = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            TrieNode node = currentNode.child.getOrDefault(ch, null);
            if (node == null) {
                return false;
            }

            currentNode = node;
        }

        return currentNode.end;
    }

    /**
     * Deletes a word in the trie
     *
     * @param word
     * @return true / false
     */
    public boolean delete(String word) {
        TrieNode currentNode = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            TrieNode node = currentNode.child.getOrDefault(ch, null);
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
     *
     * @return count
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
     * @param prefix
     * @return true / false
     */
    public boolean startsWithPrefix(String prefix) {
        TrieNode currentNode = root;

        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);

            TrieNode node = currentNode.child.getOrDefault(ch, null);
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
     * @param prefix
     * @return count
     */
    public int countWordsWithPrefix(String prefix) {
        TrieNode currentNode = root;

        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);

            TrieNode node = currentNode.child.getOrDefault(ch, null);
            if (node == null) {
                return 0;
            }

            currentNode = node;
        }

        return countWords(currentNode);
    }
}
