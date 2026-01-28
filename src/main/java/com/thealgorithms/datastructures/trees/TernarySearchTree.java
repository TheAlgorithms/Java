package com.thealgorithms.datastructures.trees;

/**
 * Ternary Search Tree implementation for efficient string storage and retrieval.
 * 
 * A Ternary Search Tree (TST) is a data structure that combines the time efficiency
 * of digital tries with the space efficiency of binary search trees.
 * 
 * Time Complexity:
 * - Insert: O(log n) average, O(n) worst case
 * - Search: O(log n) average, O(n) worst case  
 * - Delete: O(log n) average, O(n) worst case
 * 
 * Space Complexity: O(n) where n is the number of characters
 * 
 * @see <a href="https://en.wikipedia.org/wiki/Ternary_search_tree">Ternary Search Tree</a>
 * @author JeevanYewale
 */
public final class TernarySearchTree {

    private Node root;

    private static class Node {
        char data;
        boolean isEnd;
        Node left, middle, right;

        Node(char data) {
            this.data = data;
            this.isEnd = false;
        }
    }

    /**
     * Inserts a word into the ternary search tree.
     * 
     * @param word the word to insert
     * @throws IllegalArgumentException if word is null or empty
     */
    public void insert(String word) {
        if (word == null || word.isEmpty()) {
            throw new IllegalArgumentException("Word cannot be null or empty");
        }
        root = insert(root, word, 0);
    }

    private Node insert(Node node, String word, int index) {
        char c = word.charAt(index);
        
        if (node == null) {
            node = new Node(c);
        }
        
        if (c < node.data) {
            node.left = insert(node.left, word, index);
        } else if (c > node.data) {
            node.right = insert(node.right, word, index);
        } else {
            if (index < word.length() - 1) {
                node.middle = insert(node.middle, word, index + 1);
            } else {
                node.isEnd = true;
            }
        }
        
        return node;
    }

    /**
     * Searches for a word in the ternary search tree.
     * 
     * @param word the word to search for
     * @return true if the word exists, false otherwise
     * @throws IllegalArgumentException if word is null or empty
     */
    public boolean search(String word) {
        if (word == null || word.isEmpty()) {
            throw new IllegalArgumentException("Word cannot be null or empty");
        }
        return search(root, word, 0);
    }

    private boolean search(Node node, String word, int index) {
        if (node == null) {
            return false;
        }
        
        char c = word.charAt(index);
        
        if (c < node.data) {
            return search(node.left, word, index);
        } else if (c > node.data) {
            return search(node.right, word, index);
        } else {
            if (index == word.length() - 1) {
                return node.isEnd;
            }
            return search(node.middle, word, index + 1);
        }
    }

    /**
     * Checks if any word in the tree starts with the given prefix.
     * 
     * @param prefix the prefix to search for
     * @return true if any word starts with the prefix, false otherwise
     * @throws IllegalArgumentException if prefix is null or empty
     */
    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.isEmpty()) {
            throw new IllegalArgumentException("Prefix cannot be null or empty");
        }
        return startsWith(root, prefix, 0);
    }

    private boolean startsWith(Node node, String prefix, int index) {
        if (node == null) {
            return false;
        }
        
        if (index == prefix.length()) {
            return true;
        }
        
        char c = prefix.charAt(index);
        
        if (c < node.data) {
            return startsWith(node.left, prefix, index);
        } else if (c > node.data) {
            return startsWith(node.right, prefix, index);
        } else {
            return startsWith(node.middle, prefix, index + 1);
        }
    }

    /**
     * Deletes a word from the ternary search tree.
     * 
     * @param word the word to delete
     * @return true if the word was deleted, false if it didn't exist
     * @throws IllegalArgumentException if word is null or empty
     */
    public boolean delete(String word) {
        if (word == null || word.isEmpty()) {
            throw new IllegalArgumentException("Word cannot be null or empty");
        }
        
        if (!search(word)) {
            return false;
        }
        
        root = delete(root, word, 0);
        return true;
    }

    private Node delete(Node node, String word, int index) {
        if (node == null) {
            return null;
        }
        
        char c = word.charAt(index);
        
        if (c < node.data) {
            node.left = delete(node.left, word, index);
        } else if (c > node.data) {
            node.right = delete(node.right, word, index);
        } else {
            if (index == word.length() - 1) {
                node.isEnd = false;
            } else {
                node.middle = delete(node.middle, word, index + 1);
            }
        }
        
        // Remove node if it's not needed
        if (!node.isEnd && node.left == null && node.middle == null && node.right == null) {
            return null;
        }
        
        return node;
    }

    /**
     * Checks if the tree is empty.
     * 
     * @return true if the tree is empty, false otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }
}