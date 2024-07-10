package com.thealgorithms.searches;

// Implementation of Rabin Karp Algorithm

public final class RabinKarpAlgorithm {
    private RabinKarpAlgorithm() {
    }

    private static final int ALPHABET_SIZE = 256;

    public static int search(String pattern, String text, int primeNumber) {

        int index = -1; // -1 here represents not found
        int patternLength = pattern.length();
        int textLength = text.length();
        int hashForPattern = 0;
        int hashForText = 0;
        int h = 1;

        // The value of h would be "pow(d, patternLength-1)%primeNumber"
        for (int i = 0; i < patternLength - 1; i++) {
            h = (h * ALPHABET_SIZE) % primeNumber;
        }

        // Calculate the hash value of pattern and first
        // window of text
        for (int i = 0; i < patternLength; i++) {
            hashForPattern = (ALPHABET_SIZE * hashForPattern + pattern.charAt(i)) % primeNumber;
            hashForText = (ALPHABET_SIZE * hashForText + text.charAt(i)) % primeNumber;
        }

        // Slide the pattern over text one by one
        for (int i = 0; i <= textLength - patternLength; i++) {
            /* Check the hash values of current window of text
               and pattern. If the hash values match then only
               check for characters one by one*/

            int j = 0;
            if (hashForPattern == hashForText) {
                /* Check for characters one by one */
                for (j = 0; j < patternLength; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        break;
                    }
                }

                // if hashForPattern == hashForText and pattern[0...patternLength-1] = text[i, i+1, ...i+patternLength-1]
                if (j == patternLength) {
                    index = i;
                    return index;
                }
            }

            // Calculate hash value for next window of text: Remove
            // leading digit, add trailing digit
            if (i < textLength - patternLength) {
                hashForText = (ALPHABET_SIZE * (hashForText - text.charAt(i) * h) + text.charAt(i + patternLength)) % primeNumber;

                // handling negative hashForText
                if (hashForText < 0) {
                    hashForText = (hashForText + primeNumber);
                }
            }
        }
        return index; // return -1 if pattern does not found
    }
}
// This code is contributed by nuclode
