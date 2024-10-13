package com.thealgorithms.stacks;

/**
 * Solves the celebrity problem using a stack-based algorithm.
 *
 * <p>Celebrity is someone known by everyone but doesn't know anyone else.
 * <p>Applications: Graph theory and social network analysis.
 *
 * @author Hardvan
 */
public final class CelebrityFinder {
    private CelebrityFinder() {
    }

    /**
     * Finds the celebrity in the given party matrix.
     *
     * @param party A 2D matrix where party[i][j] is 1 if i knows j, otherwise 0.
     * @return The index of the celebrity, or -1 if there is no celebrity.
     */
    public static int findCelebrity(int[][] party) {
        int n = party.length;
        int candidate = 0;

        // Find a potential celebrity
        for (int i = 1; i < n; i++) {
            if (party[candidate][i] == 1) {
                candidate = i;
            }
        }

        // Verify the candidate
        for (int i = 0; i < n; i++) {
            if (i != candidate && (party[candidate][i] == 1 || party[i][candidate] == 0)) {
                return -1;
            }
        }
        return candidate;
    }
}
