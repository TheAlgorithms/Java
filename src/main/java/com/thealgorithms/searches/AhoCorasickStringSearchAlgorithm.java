package com.thealgorithms.searches;

/*
Problem Statement:
   we have a string as input containing alphabets and an array of k words, arr[], find all occurrences of all words in our 
   input string.
   k is total numbers of input words.
   Here n is the length of string and m is number of characters in all words, 
   i.e. m = length(arr[0]) + length(arr[1]) + … + length(arr[k-1]).

Time Complexity:  O(n + l + z), where ‘n’ is the length of the text, ‘l’ is the length of keywords, and ‘z’ is the 
                  number of matches.

Auxiliary Space:  O(l * q), where ‘q’ is the length of the alphabet since that is the maximum number of children
                  a node can have.
 */
/**
 * @wiki: https://en.wikipedia.org/wiki/Aho%E2%80%93Corasick_algorithm
 */

import java.util.*;
public class AhoCorasickStringSearchAlgorithm {

   // Maximum number of characters in input alphabet
    private static final int MAXC = 26;
    private static final int MAXS = 500;

    private final StringBuilder resultBuilder = new StringBuilder();
// Bit i in this mask become 1 if the word with 
// index i appears when the it enters 
// this state.
    private int[] outputFunc = new int[MAXS];
    private int[] failureFunc = new int[MAXS];
    private int[][] gotoFunc = new int[MAXS][MAXC];

 // States are numbered from 0 up to the return value-1.
    private int matching(String[] array, int k) {
        // Reset arrays
        Arrays.fill(outputFunc, 0);
        for (int i = 0; i < MAXS; i++) {
            Arrays.fill(gotoFunc[i], -1);
        }

        int states = 1;

        // Build the trie
        for (int i = 0; i < k; ++i) {
            String word = array[i];
            int currentStateIndex = 0;

            for (char c : word.toCharArray()) {
                int charIndex = c - 'a';

                // if a node for ch doesn't exist.Create a new state
                if (gotoFunc[currentStateIndex][charIndex] == -1) {
                    gotoFunc[currentStateIndex][charIndex] = states++;
                }

                currentStateIndex = gotoFunc[currentStateIndex][charIndex];
            }

            outputFunc[currentStateIndex] |= (1 << i);
        }

        for (int charIndex = 0; charIndex < MAXC; ++charIndex) {
            if (gotoFunc[0][charIndex] == -1) {
                gotoFunc[0][charIndex] = 0;
            }
        }

        Arrays.fill(failureFunc, -1);
        Queue<Integer> queue = new LinkedList<>();

        // Initialize failure function
        for (int charIndex = 0; charIndex < MAXC; ++charIndex) {
            int nextState = gotoFunc[0][charIndex];
            if (nextState != 0) {
                failureFunc[nextState] = 0;
                queue.add(nextState);
            }
        }

        // Compute failure function
        while (!queue.isEmpty()) {
            int state = queue.poll();

            for (int charIndex = 0; charIndex < MAXC; ++charIndex) {
                if (gotoFunc[state][charIndex] != -1) {
                    int failure = failureFunc[state];
                    while (gotoFunc[failure][charIndex] == -1) {
                        failure = failureFunc[failure];
                    }

                    failure = gotoFunc[failure][charIndex];
                    failureFunc[gotoFunc[state][charIndex]] = failure;

                    outputFunc[gotoFunc[state][charIndex]] |= outputFunc[failure];

                    // Insert the next level node (of Trie) in our queue
                    queue.add(gotoFunc[state][charIndex]);
                }
            }
        }
     return states;
    }

    private int findNextState(int currentStateIndex, char nextInput) {
        int answer = currentStateIndex;
        int charIndex = nextInput - 'a';

        // If goto is not defined, use failure function
        while (gotoFunc[answer][charIndex] == -1) {
            answer = failureFunc[answer];
        }

        return gotoFunc[answer][charIndex];
    }

    public String searchWords(String[] array, int k, String text) {
        matching(array, k);
        int currentStateIndex = 0;

        for (int i = 0; i < text.length(); ++i) {
            currentStateIndex = findNextState(currentStateIndex, text.charAt(i));
            
            if (outputFunc[currentStateIndex] != 0) {
                for (int j = 0; j < k; ++j) {
                    if ((outputFunc[currentStateIndex] & (1 << j)) > 0) {
 resultBuilder.append("Word ").append(array[j]).append(" appears from ").append(i - array[j].length() + 1).append(" to ").append(i).append("\n");
                    }
                }
            }
        }

        return resultBuilder.toString();
    }
}


// Example: 

// Input: string = "acisweqs"    
//        arr[] = {"ac", "isw", "cisw", "qs"}

// Output:
//    Word ac appears from 1 to 3
//    Word isw appears from 4 to 5
//    Word csiw appears from 3 to 5
//    Word qs appears from 4 to 7