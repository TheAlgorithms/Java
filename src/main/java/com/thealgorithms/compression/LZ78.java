package com.thealgorithms.compression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An implementation of the Lempel-Ziv 78 (LZ78) compression algorithm.
 * <p>
 * LZ78 is a dictionary-based lossless data compression algorithm. It processes
 * input data sequentially, building a dictionary of phrases encountered so far.
 * It outputs pairs (dictionary_index, next_character), representing
 * the longest match found in the dictionary plus the character that follows it.
 * </p>
 * <p>
 * This implementation builds the dictionary dynamically during compression.
 * The dictionary index 0 represents the empty string (no prefix).
 * </p>
 * <p>
 * Time Complexity: O(n) on average for compression and decompression, assuming
 * efficient dictionary lookups (using a HashMap), where n is the
 * length of the input string.
 * </p>
 * <p>
 * References:
 * <ul>
 * <li><a href="https://en.wikipedia.org/wiki/LZ77_and_LZ78#LZ78">Wikipedia: LZ78</a></li>
 * </ul>
 * </p>
 */
public final class LZ78 {

    /**
     * Special character used to mark end of stream when needed.
     */
    private static final char END_OF_STREAM = '\u0000';

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private LZ78() {
    }

    /**
     * Represents a token in the LZ78 compressed output.
     * Stores the index of the matching prefix in the dictionary and the next character.
     * Index 0 represents the empty string (no prefix).
     */
    public record Token(int index, char nextChar) {
    }

    /**
     * A node in the dictionary trie structure.
     * Each node represents a phrase and can have child nodes for extended phrases.
     */
    private static final class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        int index = -1; // -1 means not assigned yet
    }

    /**
     * Compresses the input text using the LZ78 algorithm.
     *
     * @param text The input string to compress. Must not be null.
     * @return A list of {@link Token} objects representing the compressed data.
     */
    public static List<Token> compress(String text) {
        if (text == null || text.isEmpty()) {
            return new ArrayList<>();
        }

        List<Token> compressedOutput = new ArrayList<>();
        TrieNode root = new TrieNode();
        int nextDictionaryIndex = 1;

        TrieNode currentNode = root;
        int lastMatchedIndex = 0;

        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);

            if (currentNode.children.containsKey(currentChar)) {
                currentNode = currentNode.children.get(currentChar);
                lastMatchedIndex = currentNode.index;
            } else {
                // Output: (index of longest matching prefix, current character)
                compressedOutput.add(new Token(lastMatchedIndex, currentChar));

                TrieNode newNode = new TrieNode();
                newNode.index = nextDictionaryIndex++;
                currentNode.children.put(currentChar, newNode);

                currentNode = root;
                lastMatchedIndex = 0;
            }
        }

        // Handle remaining phrase at end of input
        if (currentNode != root) {
            compressedOutput.add(new Token(lastMatchedIndex, END_OF_STREAM));
        }

        return compressedOutput;
    }

    /**
     * Decompresses a list of LZ78 tokens back into the original string.
     *
     * @param compressedData The list of {@link Token} objects. Must not be null.
     * @return The original, uncompressed string.
     */
    public static String decompress(List<Token> compressedData) {
        if (compressedData == null || compressedData.isEmpty()) {
            return "";
        }

        StringBuilder decompressedText = new StringBuilder();
        Map<Integer, String> dictionary = new HashMap<>();
        int nextDictionaryIndex = 1;

        for (Token token : compressedData) {
            String prefix = (token.index == 0) ? "" : dictionary.get(token.index);

            if (token.nextChar == END_OF_STREAM) {
                decompressedText.append(prefix);
            } else {
                String currentPhrase = prefix + token.nextChar;
                decompressedText.append(currentPhrase);
                dictionary.put(nextDictionaryIndex++, currentPhrase);
            }
        }

        return decompressedText.toString();
    }
}
