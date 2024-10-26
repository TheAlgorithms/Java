package com.thealgorithms.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides a solution to the "All Construct" problem.
 *
 * The problem is to determine all the ways a target string can be constructed
 * from a given list of substrings. Each substring in the word bank can be used
 * multiple times, and the order of substrings matters.
 *
 * @author Hardvan
 */
public final class AllConstruct {
    private AllConstruct() {
    }

    /**
     * Finds all possible ways to construct the target string using substrings
     * from the given word bank.
     * Time Complexity: O(n * m * k), where n = length of the target,
     * m = number of words in wordBank, and k = average length of a word.
     *
     * Space Complexity: O(n * m) due to the size of the table storing combinations.
     *
     * @param target   The target string to construct.
     * @param wordBank An iterable collection of substrings that can be used to construct the target.
     * @return A list of lists, where each inner list represents one possible
     *         way of constructing the target string using the given word bank.
     */
    public static List<List<String>> allConstruct(String target, Iterable<String> wordBank) {
        List<List<List<String>>> table = new ArrayList<>(target.length() + 1);

        for (int i = 0; i <= target.length(); i++) {
            table.add(new ArrayList<>());
        }

        table.get(0).add(new ArrayList<>());

        for (int i = 0; i <= target.length(); i++) {
            if (!table.get(i).isEmpty()) {
                for (String word : wordBank) {
                    if (i + word.length() <= target.length() && target.substring(i, i + word.length()).equals(word)) {

                        List<List<String>> newCombinations = new ArrayList<>();
                        for (List<String> combination : table.get(i)) {
                            List<String> newCombination = new ArrayList<>(combination);
                            newCombination.add(word);
                            newCombinations.add(newCombination);
                        }

                        table.get(i + word.length()).addAll(newCombinations);
                    }
                }
            }
        }

        return table.get(target.length());
    }
}
