package com.thealgorithms.stacks;

import java.util.Stack;

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
     * Finds the celebrity in the given party matrix using a stack-based algorithm.
     *
     * @param party A 2D matrix where party[i][j] is 1 if i knows j, otherwise 0.
     * @return The index of the celebrity, or -1 if there is no celebrity.
     */
    public static int findCelebrity(int[][] party) {

        // Push all people onto the stack
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < party.length; i++) {
            stack.push(i);
        }

        // Find the potential celebrity by comparing pairs
        while (stack.size() > 1) {
            int person1 = stack.pop();
            int person2 = stack.pop();

            if (party[person1][person2] == 1) {
                stack.push(person2); // person1 knows person2, so person2 might be the celebrity
            } else {
                stack.push(person1); // person1 doesn't know person2, so person1 might be the celebrity
            }
        }

        // Verify the candidate
        int candidate = stack.pop();
        for (int i = 0; i < party.length; i++) {
            if (i != candidate && (party[candidate][i] == 1 || party[i][candidate] == 0)) {
                return -1;
            }
        }
        return candidate;
    }
}
