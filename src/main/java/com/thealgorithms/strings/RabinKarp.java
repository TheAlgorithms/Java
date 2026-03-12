package com.thealgorithms.strings;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Prateek Kumar Oraon (https://github.com/prateekKrOraon)
 *
 *         An implementation of Rabin-Karp string matching algorithm
 *         Program will simply end if there is no match
 */
public final class RabinKarp {
    private RabinKarp() {
    }

    private static final int ALPHABET_SIZE = 256;

    public static List<Integer> search(String text, String pattern) {
        return search(text, pattern, 101); // 101 is a prime number used as modulo
    }

    public static List<Integer> search(String text, String pattern, int primeModulo) {
        List<Integer> occurrences = new ArrayList<>();
        if (text == null || pattern == null || pattern.isEmpty()) {
            return occurrences;
        }

        int patternLength = pattern.length();
        int textLength = text.length();
        int textHash = 0;
        int patternHash = 0;
        int highestPowerHash = 1;
        int matchIndex;
        int currentIndex;

        if (patternLength > textLength) {
            return new ArrayList<>();
        }

        // highestPowerHash = pow(ALPHABET_SIZE, patternLength-1) % primeModulo
        for (currentIndex = 0; currentIndex < patternLength - 1; currentIndex++) {
            highestPowerHash = highestPowerHash * ALPHABET_SIZE % primeModulo;
        }

        for (currentIndex = 0; currentIndex < patternLength; currentIndex++) {
            patternHash = (ALPHABET_SIZE * patternHash + pattern.charAt(currentIndex)) % primeModulo;
            textHash = (ALPHABET_SIZE * textHash + text.charAt(currentIndex)) % primeModulo;
        }

        for (currentIndex = 0; currentIndex <= textLength - patternLength; currentIndex++) {
            if (patternHash == textHash) {
                for (matchIndex = 0; matchIndex < patternLength; matchIndex++) {
                    if (text.charAt(currentIndex + matchIndex) != pattern.charAt(matchIndex)) {
                        break;
                    }
                }

                if (matchIndex == patternLength) {
                    occurrences.add(currentIndex);
                }
            }

            if (currentIndex < textLength - patternLength) {
                textHash = (textHash - text.charAt(currentIndex) * highestPowerHash % primeModulo);
                if (textHash < 0) {
                    textHash += primeModulo;
                }
                textHash = textHash * ALPHABET_SIZE % primeModulo;
                textHash = (textHash + text.charAt(currentIndex + patternLength)) % primeModulo;
            }
        }
        return occurrences;
    }
}
