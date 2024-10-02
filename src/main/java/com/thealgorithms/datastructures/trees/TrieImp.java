package com.thealgorithms.datastructures.trees;

import java.util.Scanner;

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

        // Array to store references to child nodes, one for each letter of the alphabet
        // (a-z)
        TrieNode[] child;

        boolean end; // Flag to indicate if this node marks the end of a valid word

        /**
         * Constructor to initialize a TrieNode with an empty child array and set end to
         * false.
         */
        public TrieNode() {
            child = new TrieNode[26]; // Initialize child array with 26 slots (for each letter a-z)
            end = false; // By default, this node doesn't mark the end of any word
        }
    }

    private final TrieNode root;

    /**
     * Constructor to initialize the Trie.
     * The root node is created but doesn't represent any character.
     */
    public TrieImp() {
        root = new TrieNode(); // Initialize the root node of the Trie
    }

    /**
     * Inserts a word into the Trie.
     * <p>
     * The method traverses the Trie from the root, character by character, and adds
     * nodes
     * if necessary. It marks the last node of the word as an end node.
     *
     * @param word The word to be inserted into the Trie.
     */
    public void insert(String word) {
        TrieNode currentNode = root;
        for (int i = 0; i < word.length(); i++) {
            // Calculate index of character ('a' -> 0, 'b' -> 1, ..., 'z' -> 25)
            TrieNode node = currentNode.child[word.charAt(i) - 'a'];
            if (node == null) {
                // If the node doesn't exist, create a new one and assign it to the child array
                node = new TrieNode();
                currentNode.child[word.charAt(i) - 'a'] = node;
            }
            currentNode = node; // Move to the next node
        }
        currentNode.end = true; // Mark the last node as the end of a word
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
                return false; // Word not found
            }
            currentNode = node;
        }
        return currentNode.end; // Return true if it's an end node, false otherwise
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
                return false; // Word not found
            }
            currentNode = node;
        }
        if (currentNode.end) {
            // If the word exists, mark the end flag as false (word is deleted)
            currentNode.end = false;
            return true;
        }
        return false; // Word doesn't exist
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
        return word.matches("^[a-z]+$"); // Regex for lowercase letters only
    }

    /**
     * Main method to demonstrate Trie operations using user input.
     * <p>
     * The user can choose between inserting a word, searching for a word,
     * deleting a word, or quitting the program. It uses a loop to continuously
     * ask for user input until the program is quit.
     *
     * @param args Command-line arguments (not used in this program).
     */
    public static void main(String[] args) {
        TrieImp obj = new TrieImp();
        String word;
        @SuppressWarnings("resource") Scanner scan = new Scanner(System.in);

        sop("string should contain only a-z character for all operation");

        // Loop indefinitely until the user decides to quit
        while (true) {
            sop("1. Insert\n2. Search\n3. Delete\n4. Quit");
            try {
                int t = scan.nextInt();
                switch (t) {
                case 1:
                    // Insert a word into the Trie
                    word = scan.next();
                    if (isValid(word)) {
                        obj.insert(word);
                        sop("Word inserted successfully");
                    } else {
                        sop("Invalid string: allowed only a-z");
                    }
                    break;
                case 2:
                    // Search for a word in the Trie
                    word = scan.next();
                    boolean resS = false;
                    if (isValid(word)) {
                        resS = obj.search(word);
                    } else {
                        sop("Invalid string: allowed only a-z");
                    }
                    sop(resS ? "Word found" : "Word not found");
                    break;
                case 3:
                    // Delete a word from the Trie
                    word = scan.next();
                    boolean resD = false;
                    if (isValid(word)) {
                        resD = obj.delete(word);
                    } else {
                        sop("Invalid string: allowed only a-z");
                    }
                    sop(resD ? "Word deleted successfully" : "Word not found");
                    break;
                case 4:
                    // Quit the program
                    sop("Quit successfully");
                    System.exit(1);
                    break;
                default:
                    sop("Input int from 1-4");
                    break;
                }
            } catch (Exception e) {
                // Handle bad input
                String badInput = scan.next();
                sop("This is bad input: " + badInput);
            }
        }
    }
}
