package com.thealgorithms.others;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Node class representing a node in the Huffman tree.
 * Each node contains a character, its frequency, and references to left and
 * right children.
 */
class HuffmanNode {
    int data;
    char c;
    HuffmanNode left;
    HuffmanNode right;

    /**
     * Constructor for HuffmanNode.
     *
     * @param c    the character stored in this node
     * @param data the frequency of the character
     */
    HuffmanNode(char c, int data) {
        this.c = c;
        this.data = data;
        this.left = null;
        this.right = null;
    }

    /**
     * Default constructor for HuffmanNode.
     */
    HuffmanNode() {
        this.left = null;
        this.right = null;
    }
}

/**
 * Comparator class for comparing HuffmanNode objects based on their frequency
 * data.
 * Used to maintain min-heap property in the priority queue.
 */
class HuffmanComparator implements Comparator<HuffmanNode> {
    @Override
    public int compare(HuffmanNode x, HuffmanNode y) {
        return Integer.compare(x.data, y.data);
    }
}

/**
 * Implementation of Huffman Coding algorithm for data compression.
 * Huffman Coding is a greedy algorithm that assigns variable-length codes to
 * characters
 * based on their frequency of occurrence. Characters with higher frequency get
 * shorter codes.
 *
 * <p>
 * Time Complexity: O(n log n) where n is the number of unique characters
 * Space Complexity: O(n)
 *
 * @see <a href="https://en.wikipedia.org/wiki/Huffman_coding">Huffman
 *      Coding</a>
 */
public final class Huffman {
    private Huffman() {
    }

    /**
     * Builds a Huffman tree from the given character array and their frequencies.
     *
     * @param charArray array of characters
     * @param charFreq  array of frequencies corresponding to the characters
     * @return root node of the Huffman tree
     * @throws IllegalArgumentException if arrays are null, empty, or have different
     *                                  lengths
     */
    public static HuffmanNode buildHuffmanTree(char[] charArray, int[] charFreq) {
        if (charArray == null || charFreq == null) {
            throw new IllegalArgumentException("Character array and frequency array cannot be null");
        }
        if (charArray.length == 0 || charFreq.length == 0) {
            throw new IllegalArgumentException("Character array and frequency array cannot be empty");
        }
        if (charArray.length != charFreq.length) {
            throw new IllegalArgumentException("Character array and frequency array must have the same length");
        }

        int n = charArray.length;
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>(n, new HuffmanComparator());

        // Create leaf nodes and add to priority queue
        for (int i = 0; i < n; i++) {
            if (charFreq[i] < 0) {
                throw new IllegalArgumentException("Frequencies must be non-negative");
            }
            HuffmanNode node = new HuffmanNode(charArray[i], charFreq[i]);
            priorityQueue.add(node);
        }

        // Build the Huffman tree
        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();

            HuffmanNode parent = new HuffmanNode();
            parent.data = left.data + right.data;
            parent.c = '-';
            parent.left = left;
            parent.right = right;

            priorityQueue.add(parent);
        }

        return priorityQueue.poll();
    }

    /**
     * Generates Huffman codes for all characters in the tree.
     *
     * @param root root node of the Huffman tree
     * @return map of characters to their Huffman codes
     */
    public static Map<Character, String> generateCodes(HuffmanNode root) {
        Map<Character, String> huffmanCodes = new HashMap<>();
        if (root != null) {
            generateCodesHelper(root, "", huffmanCodes);
        }
        return huffmanCodes;
    }

    /**
     * Helper method to recursively generate Huffman codes by traversing the tree.
     *
     * @param node         current node in the tree
     * @param code         current code being built
     * @param huffmanCodes map to store character-to-code mappings
     */
    private static void generateCodesHelper(HuffmanNode node, String code, Map<Character, String> huffmanCodes) {
        if (node == null) {
            return;
        }

        // If it's a leaf node, store the code
        if (node.left == null && node.right == null && Character.isLetter(node.c)) {
            huffmanCodes.put(node.c, code.isEmpty() ? "0" : code);
            return;
        }

        // Traverse left with '0' and right with '1'
        if (node.left != null) {
            generateCodesHelper(node.left, code + "0", huffmanCodes);
        }
        if (node.right != null) {
            generateCodesHelper(node.right, code + "1", huffmanCodes);
        }
    }

    /**
     * Prints Huffman codes for all characters in the tree.
     * This method is kept for backward compatibility and demonstration purposes.
     *
     * @param root root node of the Huffman tree
     * @param code current code being built (initially empty string)
     */
    public static void printCode(HuffmanNode root, String code) {
        if (root == null) {
            return;
        }

        // If it's a leaf node, print the code
        if (root.left == null && root.right == null && Character.isLetter(root.c)) {
            System.out.println(root.c + ":" + code);
            return;
        }

        // Traverse left with '0' and right with '1'
        if (root.left != null) {
            printCode(root.left, code + "0");
        }
        if (root.right != null) {
            printCode(root.right, code + "1");
        }
    }

    /**
     * Demonstrates the Huffman coding algorithm with sample data.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        // Sample characters and their frequencies
        char[] charArray = {'a', 'b', 'c', 'd', 'e', 'f'};
        int[] charFreq = {5, 9, 12, 13, 16, 45};

        System.out.println("Characters: a, b, c, d, e, f");
        System.out.println("Frequencies: 5, 9, 12, 13, 16, 45");
        System.out.println("\nHuffman Codes:");

        // Build Huffman tree
        HuffmanNode root = buildHuffmanTree(charArray, charFreq);

        // Generate and print Huffman codes
        Map<Character, String> codes = generateCodes(root);
        for (Map.Entry<Character, String> entry : codes.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
