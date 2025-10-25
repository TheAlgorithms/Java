package com.thealgorithms.compression;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the Move-to-Front (MTF) transform and its inverse.
 * <p>
 * MTF is a data transformation algorithm that encodes each symbol in the input
 * as its current position in a dynamically-maintained list, then moves that symbol
 * to the front of the list. This transformation is particularly effective when used
 * after the Burrows-Wheeler Transform (BWT), as BWT groups similar characters together.
 * </p>
 *
 * <p>The transform converts runs of repeated characters into sequences of small integers
 * (often zeros), which are highly compressible by subsequent entropy encoding algorithms
 * like Run-Length Encoding (RLE) or Huffman coding. This technique is used in the
 * bzip2 compression algorithm.
 * </p>
 *
 * <p><b>How it works:</b>
 * <ol>
 *   <li>Maintain a list of symbols (the alphabet), initially in a fixed order</li>
 *   <li>For each input symbol:
 *     <ul>
 *       <li>Output its current index in the list</li>
 *       <li>Move that symbol to the front of the list</li>
 *     </ul>
 *   </li>
 * </ol>
 * This means frequently occurring symbols quickly move to the front and are encoded
 * with small indices (often 0), while rare symbols remain near the back.
 * </p>
 *
 * <p><b>Time Complexity:</b>
 * <ul>
 *   <li>Forward transform: O(n × m) where n is input length and m is alphabet size</li>
 *   <li>Inverse transform: O(n × m)</li>
 * </ul>
 * Note: Using {@link LinkedList} for O(1) insertions and O(m) search operations.
 * </p>
 *
 * <p><b>Example:</b></p>
 * <pre>
 * Input:    "annb$aa"
 * Alphabet: "$abn" (initial order)
 * Output:   [1, 3, 0, 3, 3, 3, 0]
 *
 * Step-by-step:
 * - 'a': index 1 in [$,a,b,n] → output 1, list becomes [a,$,b,n]
 * - 'n': index 3 in [a,$,b,n] → output 3, list becomes [n,a,$,b]
 * - 'n': index 0 in [n,a,$,b] → output 0, list stays [n,a,$,b]
 * - 'b': index 3 in [n,a,$,b] → output 3, list becomes [b,n,a,$]
 * - etc.
 *
 * Notice how repeated 'n' characters produce zeros after the first occurrence!
 * </pre>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Move-to-front_transform">Move-to-front transform (Wikipedia)</a>
 */
public final class MoveToFront {

    private MoveToFront() {
    }

    /**
     * Performs the forward Move-to-Front transform.
     * <p>
     * Converts the input string into a list of integers, where each integer represents
     * the position of the corresponding character in a dynamically-maintained alphabet list.
     * </p>
     *
     * <p><b>Note:</b> All characters in the input text must exist in the provided alphabet,
     * otherwise an {@link IllegalArgumentException} is thrown. The alphabet should contain
     * all unique characters that may appear in the input.</p>
     *
     * @param text the input string to transform; if empty, returns an empty list
     * @param initialAlphabet a string containing the initial ordered set of symbols
     *                        (e.g., "$abn" or the full ASCII set); must not be empty
     *                        when {@code text} is non-empty
     * @return a list of integers representing the transformed data, where each integer
     *         is the index of the corresponding input character in the current alphabet state
     * @throws IllegalArgumentException if {@code text} is non-empty and {@code initialAlphabet}
     *                                  is {@code null} or empty
     * @throws IllegalArgumentException if any character in {@code text} is not found in
     *                                  {@code initialAlphabet}
     */
    public static List<Integer> transform(String text, String initialAlphabet) {
        if (text == null || text.isEmpty()) {
            return new ArrayList<>();
        }
        if (initialAlphabet == null || initialAlphabet.isEmpty()) {
            throw new IllegalArgumentException("Alphabet cannot be null or empty when text is not empty.");
        }

        List<Integer> output = new ArrayList<>(text.length());

        // Use LinkedList for O(1) add-to-front and O(n) remove operations
        // This is more efficient than ArrayList for the move-to-front pattern
        List<Character> alphabet = initialAlphabet.chars().mapToObj(c -> (char) c).collect(Collectors.toCollection(LinkedList::new));

        for (char c : text.toCharArray()) {
            int index = alphabet.indexOf(c);
            if (index == -1) {
                throw new IllegalArgumentException("Symbol '" + c + "' not found in the initial alphabet.");
            }

            output.add(index);

            // Move the character to the front
            Character symbol = alphabet.remove(index);
            alphabet.addFirst(symbol);
        }
        return output;
    }

    /**
     * Performs the inverse Move-to-Front transform.
     * <p>
     * Reconstructs the original string from the list of indices produced by the
     * forward transform. This requires the exact same initial alphabet that was
     * used in the forward transform.
     * </p>
     *
     * <p><b>Important:</b> The {@code initialAlphabet} parameter must be identical
     * to the one used in the forward transform, including character order, or the
     * output will be incorrect.</p>
     *
     * @param indices The list of integers from the forward transform.
     * @param initialAlphabet the exact same initial alphabet string used for the forward transform;
     *                        if {@code null} or empty, returns an empty string
     * @return the original, untransformed string
     * @throws IllegalArgumentException if any index in {@code indices} is negative or
     *                                  exceeds the current alphabet size
     */
    public static String inverseTransform(Collection<Integer> indices, String initialAlphabet) {
        if (indices == null || indices.isEmpty() || initialAlphabet == null || initialAlphabet.isEmpty()) {
            return "";
        }

        StringBuilder output = new StringBuilder(indices.size());

        // Use LinkedList for O(1) add-to-front and O(n) remove operations
        List<Character> alphabet = initialAlphabet.chars().mapToObj(c -> (char) c).collect(Collectors.toCollection(LinkedList::new));

        for (int index : indices) {
            if (index < 0 || index >= alphabet.size()) {
                throw new IllegalArgumentException("Index " + index + " is out of bounds for the current alphabet of size " + alphabet.size() + ".");
            }

            // Get the symbol at the index
            char symbol = alphabet.get(index);
            output.append(symbol);

            // Move the symbol to the front (mirroring the forward transform)
            alphabet.remove(index);
            alphabet.addFirst(symbol);
        }
        return output.toString();
    }
}
