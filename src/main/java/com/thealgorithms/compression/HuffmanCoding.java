package com.thealgorithms.compression;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Huffman Coding Compression Algorithm Implementation.
 * <p>
 * Huffman Coding is a popular greedy algorithm used for lossless data compression.
 * It reduces the overall size of data by assigning variable-length, prefix-free
 * binary codes to input characters, ensuring that more frequent characters receive
 * the shortest possible codes.
 * </p>
 * <p>
 * <strong>Key Features:</strong>
 * <ul>
 * <li>Uses a PriorityQueue (min-heap) to efficiently construct the optimal prefix tree.</li>
 * <li>Fail-fast design throws exceptions for unsupported characters and malformed binary payloads.</li>
 * <li>Immutable internal dictionary state prevents external tampering with generated codes.</li>
 * <li>Robust handling of edge cases, including single-character strings and incomplete sequences.</li>
 * </ul>
 * </p>
 * @author Chahat Sandhu, <a href="https://github.com/singhc7">singhc7</a>
 * @see <a href="https://en.wikipedia.org/wiki/Huffman_coding">Huffman Coding (Wikipedia)</a>
 */
public class HuffmanCoding {

    private Node root;
    private final Map<Character, String> huffmanCodes;

    /**
     * Represents a node within the Huffman Tree.
     * Implements {@link Comparable} to allow sorting by frequency in a PriorityQueue.
     */
    private static class Node implements Comparable<Node> {
        final char ch;
        final int freq;
        final Node left;
        final Node right;

        /**
         * Constructs a leaf node containing a specific character and its frequency.
         *
         * @param ch   The character stored in this leaf.
         * @param freq The frequency of occurrence of the character.
         */
        Node(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
            this.left = null;
            this.right = null;
        }

        /**
         * Constructs an internal node that merges two child nodes.
         * The character is defaulted to the null character ('\0').
         *
         * @param freq  The combined frequency of the left and right child nodes.
         * @param left  The left child node.
         * @param right The right child node.
         */
        Node(int freq, Node left, Node right) {
            this.ch = '\0';
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        /**
         * Determines if the current node is a leaf (contains no children).
         *
         * @return {@code true} if both left and right children are null, {@code false} otherwise.
         */
        boolean isLeaf() {
            return left == null && right == null;
        }

        /**
         * Compares this node with another node based on their frequencies.
         * Used by the PriorityQueue to maintain the min-heap property.
         *
         * @param other The other Node to compare against.
         * @return A negative integer, zero, or a positive integer as this node's frequency
         * is less than, equal to, or greater than the specified node's frequency.
         */
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.freq, other.freq);
        }
    }

    /**
     * Initializes the Huffman Tree and generates immutable prefix-free codes
     * based on the character frequencies in the provided text.
     *
     * @param text The input string used to calculate frequencies and build the optimal tree.
     * If null or empty, an empty tree and dictionary are created.
     */
    public HuffmanCoding(String text) {
        if (text == null || text.isEmpty()) {
            this.huffmanCodes = Collections.emptyMap();
            return;
        }

        Map<Character, String> tempCodes = new HashMap<>();
        buildTree(text);
        generateCodes(root, "", tempCodes);

        if (tempCodes.size() == 1) {
            tempCodes.put(root.ch, "0");
        }

        this.huffmanCodes = Collections.unmodifiableMap(tempCodes);
    }

    /**
     * Computes character frequencies and constructs the Huffman Tree using a min-heap.
     * The optimal tree is built by repeatedly extracting the two lowest-frequency nodes
     * and merging them until a single root node remains.
     *
     * @param text The input text to analyze.
     */
    private void buildTree(String text) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }

        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            pq.add(new Node(left.freq + right.freq, left, right));
        }

        root = pq.poll();
    }

    /**
     * Recursively traverses the Huffman Tree to generate prefix-free binary codes.
     * Left traversals append a '0' to the code, while right traversals append a '1'.
     *
     * @param node The current node in the traversal.
     * @param code The accumulated binary string for the current path.
     * @param map  The temporary dictionary to populate with the final character-to-code mappings.
     */
    private void generateCodes(Node node, String code, Map<Character, String> map) {
        if (node == null) {
            return;
        }
        if (node.isLeaf()) {
            map.put(node.ch, code);
            return;
        }
        generateCodes(node.left, code + "0", map);
        generateCodes(node.right, code + "1", map);
    }

    /**
     * Encodes the given plaintext string into a binary string using the generated Huffman dictionary.
     *
     * @param text The plaintext string to compress.
     * @return A string of '0's and '1's representing the compressed data.
     * Returns an empty string if the input is null or empty.
     * @throws IllegalStateException    If attempting to encode when the Huffman tree is empty.
     * @throws IllegalArgumentException If the input text contains a character not present
     * in the original text used to build the tree.
     */
    public String encode(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }
        if (root == null) {
            throw new IllegalStateException("Huffman tree is empty.");
        }

        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (!huffmanCodes.containsKey(c)) {
                throw new IllegalArgumentException(String.format("Character '%c' (U+%04X) not found in Huffman dictionary.", c, (int) c));
            }
            sb.append(huffmanCodes.get(c));
        }
        return sb.toString();
    }

    /**
     * Decodes the given binary string back into the original plaintext using the Huffman Tree.
     * Validates the integrity of the binary payload during traversal.
     *
     * @param encodedText The binary string of '0's and '1's to decompress.
     * @return The reconstructed plaintext string. Returns an empty string if the input is null or empty.
     * @throws IllegalStateException    If attempting to decode when the Huffman tree is empty.
     * @throws IllegalArgumentException If the binary string contains characters other than '0' or '1',
     * or if the sequence ends abruptly without reaching a leaf node.
     */
    public String decode(String encodedText) {
        if (encodedText == null || encodedText.isEmpty()) {
            return "";
        }
        if (root == null) {
            throw new IllegalStateException("Huffman tree is empty.");
        }

        StringBuilder sb = new StringBuilder();

        if (root.isLeaf()) {
            for (char bit : encodedText.toCharArray()) {
                if (bit != '0') {
                    throw new IllegalArgumentException("Invalid binary sequence for single-character tree.");
                }
                sb.append(root.ch);
            }
            return sb.toString();
        }

        Node current = root;
        for (char bit : encodedText.toCharArray()) {
            if (bit != '0' && bit != '1') {
                throw new IllegalArgumentException("Encoded text contains invalid characters: " + bit);
            }

            current = (bit == '0') ? current.left : current.right;

            if (current.isLeaf()) {
                sb.append(current.ch);
                current = root;
            }
        }

        if (current != root) {
            throw new IllegalArgumentException("Malformed encoded string: incomplete sequence ending.");
        }

        return sb.toString();
    }

    /**
     * Retrieves the generated Huffman dictionary mapping characters to their binary codes.
     *
     * @return An unmodifiable map containing the character-to-binary-code mappings to prevent
     * external mutation of the algorithm's state.
     */
    public Map<Character, String> getHuffmanCodes() {
        return huffmanCodes;
    }
}
