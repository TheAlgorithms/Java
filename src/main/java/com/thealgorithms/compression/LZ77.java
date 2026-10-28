package com.thealgorithms.compression;

import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of the Lempel-Ziv 77 (LZ77) compression algorithm.
 * <p>
 * LZ77 is a lossless data compression algorithm that works by finding repeated
 * occurrences of data in a sliding window. It replaces subsequent occurrences
 * with references (offset, length) to the first occurrence within the window.
 * </p>
 * <p>
 * This implementation uses a simple sliding window and lookahead buffer approach.
 * Output format is a sequence of tuples (offset, length, next_character).
 * </p>
 * <p>
 * Time Complexity: O(n*W) in this naive implementation, where n is the input length
 * and W is the window size, due to the search for the longest match. More advanced
 * data structures (like suffix trees) can improve this.
 * </p>
 * <p>
 * References:
 * <ul>
 * <li><a href="https://en.wikipedia.org/wiki/LZ77_and_LZ78#LZ77">Wikipedia: LZ77</a></li>
 * </ul>
 * </p>
 */
public final class LZ77 {

    private static final int DEFAULT_WINDOW_SIZE = 4096;
    private static final int DEFAULT_LOOKAHEAD_BUFFER_SIZE = 16;
    private static final char END_OF_STREAM = '\u0000';
    private LZ77() {
    }

    /**
     * Represents a token in the LZ77 compressed output.
     * Stores the offset back into the window, the length of the match,
     * and the next character after the match (or END_OF_STREAM if at end).
     */
    public record Token(int offset, int length, char nextChar) {
    }

    /**
     * Compresses the input text using the LZ77 algorithm.
     *
     * @param text The input string to compress. Must not be null.
     * @param windowSize The size of the sliding window (search buffer). Must be positive.
     * @param lookaheadBufferSize The size of the lookahead buffer. Must be positive.
     * @return A list of {@link Token} objects representing the compressed data.
     * @throws IllegalArgumentException if windowSize or lookaheadBufferSize are not positive.
     */
    public static List<Token> compress(String text, int windowSize, int lookaheadBufferSize) {
        if (text == null) {
            return new ArrayList<>();
        }
        if (windowSize <= 0 || lookaheadBufferSize <= 0) {
            throw new IllegalArgumentException("Window size and lookahead buffer size must be positive.");
        }

        List<Token> compressedOutput = new ArrayList<>();
        int currentPosition = 0;

        while (currentPosition < text.length()) {
            int bestMatchDistance = 0;
            int bestMatchLength = 0;

            // Define the start of the search window
            int searchBufferStart = Math.max(0, currentPosition - windowSize);
            // Define the end of the lookahead buffer (don't go past text length)
            int lookaheadEnd = Math.min(currentPosition + lookaheadBufferSize, text.length());

            // Search for the longest match in the window
            for (int i = searchBufferStart; i < currentPosition; i++) {
                int currentMatchLength = 0;

                // Check how far the match extends into the lookahead buffer
                // This allows for overlapping matches (e.g., "aaa" can match with offset 1)
                while (currentPosition + currentMatchLength < lookaheadEnd) {
                    int sourceIndex = i + currentMatchLength;

                    // Handle overlapping matches (run-length encoding within LZ77)
                    // When we've matched beyond our starting position, wrap around using modulo
                    if (sourceIndex >= currentPosition) {
                        int offset = currentPosition - i;
                        sourceIndex = i + (currentMatchLength % offset);
                    }

                    if (text.charAt(sourceIndex) == text.charAt(currentPosition + currentMatchLength)) {
                        currentMatchLength++;
                    } else {
                        break;
                    }
                }

                // If this match is longer than the best found so far
                if (currentMatchLength > bestMatchLength) {
                    bestMatchLength = currentMatchLength;
                    bestMatchDistance = currentPosition - i; // Calculate offset from current position
                }
            }

            char nextChar;
            if (currentPosition + bestMatchLength < text.length()) {
                nextChar = text.charAt(currentPosition + bestMatchLength);
            } else {
                nextChar = END_OF_STREAM;
            }

            // Add the token to the output
            compressedOutput.add(new Token(bestMatchDistance, bestMatchLength, nextChar));

            // Move the current position forward
            // If we're at the end and had a match, just move by the match length
            if (nextChar == END_OF_STREAM) {
                currentPosition += bestMatchLength;
            } else {
                currentPosition += bestMatchLength + 1;
            }
        }

        return compressedOutput;
    }

    /**
     * Compresses the input text using the LZ77 algorithm with default buffer sizes.
     *
     * @param text The input string to compress. Must not be null.
     * @return A list of {@link Token} objects representing the compressed data.
     */
    public static List<Token> compress(String text) {
        return compress(text, DEFAULT_WINDOW_SIZE, DEFAULT_LOOKAHEAD_BUFFER_SIZE);
    }

    /**
     * Decompresses a list of LZ77 tokens back into the original string.
     *
     * @param compressedData The list of {@link Token} objects. Must not be null.
     * @return The original, uncompressed string.
     */
    public static String decompress(List<Token> compressedData) {
        if (compressedData == null) {
            return "";
        }

        StringBuilder decompressedText = new StringBuilder();

        for (Token token : compressedData) {
            // Copy matched characters from the sliding window
            if (token.length > 0) {
                int startIndex = decompressedText.length() - token.offset;

                // Handle overlapping matches (e.g., when length > offset)
                for (int i = 0; i < token.length; i++) {
                    decompressedText.append(decompressedText.charAt(startIndex + i));
                }
            }

            // Append the next character (if not END_OF_STREAM)
            if (token.nextChar != END_OF_STREAM) {
                decompressedText.append(token.nextChar);
            }
        }

        return decompressedText.toString();
    }
}
