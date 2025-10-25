package com.thealgorithms.others;

import java.util.Arrays;
import java.util.Random;

/**
 * MiniMax is an algorithm used in artificial intelligence and game theory for
 * minimizing the possible loss for the worst case scenario. It is commonly used
 * in two-player turn-based games such as Tic-Tac-Toe, Chess, and Checkers.
 *
 * <p>
 * The algorithm simulates all possible moves in a game tree and chooses the
 * move that minimizes the maximum possible loss. The algorithm assumes both
 * players play optimally.
 *
 * <p>
 * Time Complexity: O(b^d) where b is the branching factor and d is the depth
 * <p>
 * Space Complexity: O(d) for the recursive call stack
 *
 * <p>
 * See more:
 * <ul>
 * <li><a href="https://en.wikipedia.org/wiki/Minimax">Wikipedia - Minimax</a>
 * <li><a href=
 * "https://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-1-introduction/">
 * GeeksforGeeks - Minimax Algorithm</a>
 * </ul>
 *
 * @author aitofi (https://github.com/aitorfi)
 */
public final class MiniMaxAlgorithm {

    private static final Random RANDOM = new Random();

    /**
     * Game tree represented as an int array containing scores. Each array
     * element is a leaf node. The array length must be a power of 2.
     */
    private int[] scores;

    /**
     * The height of the game tree, calculated as log2(scores.length).
     */
    private int height;

    /**
     * Initializes the MiniMaxAlgorithm with 8 random leaf nodes (2^3 = 8).
     * Each score is a random integer between 1 and 99 inclusive.
     */
    public MiniMaxAlgorithm() {
        this(getRandomScores(3, 99));
    }

    /**
     * Initializes the MiniMaxAlgorithm with the provided scores.
     *
     * @param scores An array of scores representing leaf nodes. The length must be
     *               a power of 2.
     * @throws IllegalArgumentException if the scores array length is not a power of
     *                                  2
     */
    public MiniMaxAlgorithm(int[] scores) {
        if (!isPowerOfTwo(scores.length)) {
            throw new IllegalArgumentException("The number of scores must be a power of 2.");
        }
        this.scores = Arrays.copyOf(scores, scores.length);
        this.height = log2(scores.length);
    }

    /**
     * Demonstrates the MiniMax algorithm with a random game tree.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        MiniMaxAlgorithm miniMaxAlgorithm = new MiniMaxAlgorithm();
        boolean isMaximizer = true; // Specifies the player that goes first.
        int bestScore;

        bestScore = miniMaxAlgorithm.miniMax(0, isMaximizer, 0, true);

        System.out.println();
        System.out.println(Arrays.toString(miniMaxAlgorithm.getScores()));
        System.out.println("The best score for " + (isMaximizer ? "Maximizer" : "Minimizer") + " is " + bestScore);
    }

    /**
     * Returns the optimal score assuming that both players play their best.
     *
     * <p>
     * This method recursively evaluates the game tree using the minimax algorithm.
     * At each level, the maximizer tries to maximize the score while the minimizer
     * tries to minimize it.
     *
     * @param depth       The current depth in the game tree (0 at root).
     * @param isMaximizer True if it is the maximizer's turn; false for minimizer.
     * @param index       Index of the current node in the game tree.
     * @param verbose     True to print each player's choice during evaluation.
     * @return The optimal score for the player that made the first move.
     */
    public int miniMax(int depth, boolean isMaximizer, int index, boolean verbose) {
        int bestScore;
        int score1;
        int score2;

        if (depth == height) { // Leaf node reached.
            return scores[index];
        }

        score1 = miniMax(depth + 1, !isMaximizer, index * 2, verbose);
        score2 = miniMax(depth + 1, !isMaximizer, (index * 2) + 1, verbose);

        if (isMaximizer) {
            // Maximizer player wants to get the maximum possible score.
            bestScore = Math.max(score1, score2);
        } else {
            // Minimizer player wants to get the minimum possible score.
            bestScore = Math.min(score1, score2);
        }

        // Leaf nodes can be sequentially inspected by
        // recursively multiplying (0 * 2) and ((0 * 2) + 1):
        // (0 x 2) = 0; ((0 x 2) + 1) = 1
        // (1 x 2) = 2; ((1 x 2) + 1) = 3
        // (2 x 2) = 4; ((2 x 2) + 1) = 5 ...
        if (verbose) {
            System.out.printf("From %02d and %02d, %s chooses %02d%n", score1, score2, (isMaximizer ? "Maximizer" : "Minimizer"), bestScore);
        }

        return bestScore;
    }

    /**
     * Returns an array of random numbers whose length is a power of 2.
     *
     * @param size     The power of 2 that will determine the length of the array
     *                 (array length = 2^size).
     * @param maxScore The maximum possible score (scores will be between 1 and
     *                 maxScore inclusive).
     * @return An array of random numbers with length 2^size.
     */
    public static int[] getRandomScores(int size, int maxScore) {
        int[] randomScores = new int[(int) Math.pow(2, size)];

        for (int i = 0; i < randomScores.length; i++) {
            randomScores[i] = RANDOM.nextInt(maxScore) + 1;
        }

        return randomScores;
    }

    /**
     * Calculates the logarithm base 2 of a number.
     *
     * @param n The number to calculate log2 for (must be a power of 2).
     * @return The log2 of n.
     */
    private int log2(int n) {
        return (n == 1) ? 0 : log2(n / 2) + 1;
    }

    /**
     * Checks if a number is a power of 2.
     *
     * @param n The number to check.
     * @return True if n is a power of 2, false otherwise.
     */
    private boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    /**
     * Sets the scores array for the game tree.
     *
     * @param scores The array of scores. Length must be a power of 2.
     * @throws IllegalArgumentException if the scores array length is not a power of
     *                                  2
     */
    public void setScores(int[] scores) {
        if (!isPowerOfTwo(scores.length)) {
            throw new IllegalArgumentException("The number of scores must be a power of 2.");
        }
        this.scores = Arrays.copyOf(scores, scores.length);
        height = log2(this.scores.length);
    }

    /**
     * Returns a copy of the scores array.
     *
     * @return A copy of the scores array.
     */
    public int[] getScores() {
        return Arrays.copyOf(scores, scores.length);
    }

    /**
     * Returns the height of the game tree.
     *
     * @return The height of the game tree (log2 of the number of leaf nodes).
     */
    public int getHeight() {
        return height;
    }
}
