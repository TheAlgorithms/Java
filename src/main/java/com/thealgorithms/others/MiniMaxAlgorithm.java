package com.thealgorithms.others;

import java.util.Arrays;
import java.util.Random;

/**
 * MiniMax is an algorithm used in artificial intelligence and game theory for
 * minimizing the possible loss for the worst case scenario.
 *
 * See more (https://en.wikipedia.org/wiki/Minimax,
 * https://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-1-introduction/).
 *
 * @author aitofi (https://github.com/aitorfi)
 */
public class MiniMaxAlgorithm {

    /**
     * Game tree represented as an int array containing scores. Each array
     * element is a leaf node.
     */
    private int[] scores;
    private int height;

    /**
     * Initializes the scores with 8 random leaf nodes
     */
    public MiniMaxAlgorithm() {
        scores = getRandomScores(3, 99);
        height = log2(scores.length);
    }

    public static void main(String[] args) {
        MiniMaxAlgorithm miniMaxAlgorith = new MiniMaxAlgorithm();
        boolean isMaximizer = true; // Specifies the player that goes first.
        boolean verbose = true; // True to show each players choices.
        int bestScore;

        bestScore = miniMaxAlgorith.miniMax(0, isMaximizer, 0, verbose);

        if (verbose) {
            System.out.println();
        }

        System.out.println(Arrays.toString(miniMaxAlgorith.getScores()));
        System.out.println("The best score for " + (isMaximizer ? "Maximizer" : "Minimizer") + " is " + bestScore);
    }

    /**
     * Returns the optimal score assuming that both players play their best.
     *
     * @param depth Indicates how deep we are into the game tree.
     * @param isMaximizer True if it is maximizers turn; otherwise false.
     * @param index Index of the leaf node that is being evaluated.
     * @param verbose True to show each players choices.
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

        if (verbose) {
            System.out.printf("From %02d and %02d, %s chooses %02d%n",
                    score1, score2, (isMaximizer ? "Maximizer" : "Minimizer"), bestScore);
        }

        return bestScore;
    }

    /**
     * Returns an array of random numbers whose length is a power of 2.
     *
     * @param size The power of 2 that will determine the length of the array.
     * @param maxScore The maximum possible score.
     * @return An array of random numbers.
     */
    public static int[] getRandomScores(int size, int maxScore) {
        int[] randomScores = new int[(int) Math.pow(2, size)];
        Random rand = new Random();

        for (int i = 0; i < randomScores.length; i++) {
            randomScores[i] = rand.nextInt(maxScore) + 1;
        }

        return randomScores;
    }

    // A utility function to find log2(n)
    private int log2(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Input must be a positive integer.");
        }
        return (n == 1) ? 0 : 1 + log2(n / 2);
    }

    /**
     * Sets the scores array if its length is a power of 2, otherwise throws exception.
     *
     * @param scores input array of scores
     */
    public void setScores(int[] scores) {
        if (scores == null || scores.length == 0) {
            throw new IllegalArgumentException("Scores array cannot be null or empty.");
        }
        // Check if length is power of 2 using bitwise trick
        if ((scores.length & (scores.length - 1)) == 0) {
            this.scores = scores;
            height = log2(this.scores.length);
        } else {
            throw new IllegalArgumentException("The number of scores must be a power of 2.");
        }
    }

    public int[] getScores() {
        return scores;
    }

    public int getHeight() {
        return height;
    }
}
