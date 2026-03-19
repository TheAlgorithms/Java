package com.thealgorithms.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Class generates all subsequences for a given list of elements using backtracking
 */
public final class SubsequenceFinder {
    private SubsequenceFinder() {
    }

    /**
     * Find all subsequences of given list using backtracking
     *
     * @param sequence a list of items on the basis of which we need to generate all subsequences
     * @param <T> the type of elements in the array
     * @return a list of all subsequences
     */
    public static <T> List<List<T>> generateAll(List<T> sequence) {
        List<List<T>> allSubSequences = new ArrayList<>();
        if (sequence.isEmpty()) {
            allSubSequences.add(new ArrayList<>());
            return allSubSequences;
        }
        List<T> currentSubsequence = new ArrayList<>();
        backtrack(sequence, currentSubsequence, 0, allSubSequences);
        return allSubSequences;
    }

    /**
     * Iterate through each branch of states
     * We know that each state has exactly two branching
     * It terminates when it reaches the end of the given sequence
     *
     * @param sequence all elements
     * @param currentSubsequence current subsequence
     * @param index current index
     * @param allSubSequences contains all sequences
     * @param <T> the type of elements which we generate
     */

    /**
     * Complexity Analysis
     * 
     * Time Complexity:
     * O(2^N * N), where N is the size of the input list.
     * There are 2^N subsequences and each takes O(N) time to copy.
     *
     * Space Complexity:
     * O(2^N * N) for storing all subsequences +
     * O(N) recursion stack.
     */

    private static <T> void backtrack(List<T> sequence, List<T> currentSubsequence, final int index, List<List<T>> allSubSequences) {
        assert index <= sequence.size();
        if (index == sequence.size()) {
            allSubSequences.add(new ArrayList<>(currentSubsequence));
            return;
        }

        backtrack(sequence, currentSubsequence, index + 1, allSubSequences);
        currentSubsequence.add(sequence.get(index));
        backtrack(sequence, currentSubsequence, index + 1, allSubSequences);
        currentSubsequence.removeLast();
    }
}
