package com.thealgorithms.dynamicprogramming;

/**
 * The Smith-Waterman algorithm addresses the problem of local sequence
 * alignment.
 * In the context of biology and bioinformatics, it is commonly used for
 * comparing and
 * aligning two sequences to find the most significant local similarities,
 * particularly
 * in DNA, RNA, or protein sequences. The problem statement for Smith-Waterman
 * can be defined as follows:
 * Problem Statement:
 * Given two sequences, typically referred to as a "query" sequence and a
 * "subject" sequence,
 * the goal is to find the best local alignment between subsequences of these
 * two sequences.
 * Local alignment means finding the most significant matching region within the
 * sequences,
 * allowing for gaps, mismatches, and matches. The algorithm should return the
 * starting positions of
 * the aligned subsequences in both sequences, as well as the alignment score.
 * 
 * @author Akanksha Singh (https://github.com/singhakanksha03)
 */

public class SmithWatermanAlgorithm {
    public static void main(String[] args) {
        String query = "HEAGAWGHEE";
        String subject = "PAWHEAE";
        // Calculate the score matrix for Smith-Waterman algorithm
        int[][] scoreMatrix = smithWaterman(query, subject);
        // Find the optimal local alignment and print it
        String alignment = traceback(scoreMatrix, query, subject);
        System.out.println("Optimal Local Alignment:\n");
        System.out.println(alignment);
    }

    public static int[][] smithWaterman(String query, String subject) {
        int m = query.length();
        int n = subject.length();
        // Initialize the score matrix with zeros
        int[][] scoreMatrix = new int[m + 1][n + 1];
        // Fill in the score matrix using the Smith-Waterman algorithm
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int matchScore = scoreMatrix[i - 1][j - 1] + (query.charAt(i - 1) == subject.charAt(j - 1) ? 1 : -1);
                int deleteScore = scoreMatrix[i - 1][j] - 2;
                int insertScore = scoreMatrix[i][j - 1] - 2;
                // The score for the current cell is the maximum of these three values
                scoreMatrix[i][j] = Math.max(0, Math.max(matchScore, Math.max(deleteScore, insertScore)));
            }
        }
        return scoreMatrix;
    }

    public static String traceback(int[][] scoreMatrix, String query, String subject) {
        int m = query.length();
        int n = subject.length();
        int maxScore = 0;
        int maxI = 0;
        int maxJ = 0;
        // Find the cell with the maximum score in the score matrix
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (scoreMatrix[i][j] > maxScore) {
                    maxScore = scoreMatrix[i][j];
                    maxI = i;
                    maxJ = j;
                }
            }
        }
        StringBuilder alignedQuery = new StringBuilder();
        StringBuilder alignedSubject = new StringBuilder();
        // Trace back to find the optimal local alignment
        while (maxI > 0 && maxJ > 0 && scoreMatrix[maxI][maxJ] > 0) {
            if (scoreMatrix[maxI][maxJ] == scoreMatrix[maxI - 1][maxJ - 1] + (query.charAt(maxI - 1) == subject.charAt(maxJ - 1) ? 1 : -1)) {
                alignedQuery.insert(0, query.charAt(maxI - 1));
                alignedSubject.insert(0, subject.charAt(maxJ - 1));
                maxI--;
                maxJ--;
            } else if (scoreMatrix[maxI][maxJ] == scoreMatrix[maxI - 1][maxJ] - 2) {
                alignedQuery.insert(0, query.charAt(maxI - 1));
                alignedSubject.insert(0, '-');
                maxI--;
            } else {
                alignedQuery.insert(0, '-');
                alignedSubject.insert(0, subject.charAt(maxJ - 1));
                maxJ--;
            }
        }
        // Return the aligned sequences separated by a newline
        return alignedQuery.toString() + "\n" + alignedSubject.toString();
    }
}
