package com.others;

/**
 * Implement the Word Wrap Problem
 * Brute-force solution: won't be optimal for all cases.
 */
public class WWP {

    /**
     * Render given text into same length lines.
     * Assume that the length of each word is smaller than the line width.
     * @param text is a long String
     * @param lineWidth is the max number of characters in one line
     * @return the number of lines where the text can render
     */
    public static int solveWWP(final String text, final int lineWidth){
        int[] wordsLength = getWordsLength(text);
        int countOfLines = 1;           // the text will occupy at least 1 line
        int currentPosition = 0;        // how many char places are already reserved

        for (int i = 0; i < wordsLength.length; i++){

            if (currentPosition + wordsLength[i] <= lineWidth){
                currentPosition += wordsLength[i] + 1;
            }
            else if (currentPosition + wordsLength[i] > lineWidth){
                countOfLines++;
                currentPosition = wordsLength[i] + 1;
            }

            // if currentPosition is above the lineWidth
            // last space can be left out
            if (currentPosition > lineWidth+1) {
                countOfLines++;
                currentPosition = 0;
            }
        }

        return countOfLines;
    }

    /**
     * Get the length of each word from a text.
     * @param text
     * @return an array that contains numbers of letters in each word
     */
    private static int[] getWordsLength(String text){
        String[] words = text.split(" ");

        int[] wordsLength = new int[words.length];

        for (int i = 0; i < words.length; i++){
            String[] lettersOfWord = words[i].split("");
            wordsLength[i] = lettersOfWord.length;
        }

        return wordsLength;
    }
}
