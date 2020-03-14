package com.others;
import java.util.ArrayList;

/**
 * Implementation of the Knuth–Morris–Pratt algorithm
 */
public class KMP {

    /**
     * KMPmatcher method finds if any string "needle" is in the string "haystack" and returns the index of its first letter for all occurrencies.
     * @param haystack string in which the method is searching
     * @param needle string which the method is searching for
     * @return an ArrayList of starting indexes of the haystack that matches the searched needle
     */
    public static ArrayList<Integer> KMPmatcher(final String haystack, final String needle) {
        final int haystackLength = haystack.length();
        final int needleLength = needle.length();
        final int[] pi = computePrefixFunction(needle);
        int matchingLength = 0;
        ArrayList<Integer> startingIndexes = new ArrayList<>();
        for (int i = 0; i < haystackLength; i++) {
            while (matchingLength > 0 && haystack.charAt(i) != needle.charAt(matchingLength)) {
                matchingLength = pi[matchingLength - 1];
            }

            if (haystack.charAt(i) == needle.charAt(matchingLength)) {
                matchingLength++;
            }

            if (matchingLength == needleLength) {
                startingIndexes.add(i + 1 - needleLength);
                matchingLength = pi[matchingLength - 1];
            }
        }
        return startingIndexes;
    }

    /**
     * The computePrefixFunction method gets the prefix function of the given string.
     * @param P string (this should be the needle in the KNP)
     * @return an array of indexes of the given sting where it matches itself
     */
    private static int[] computePrefixFunction(final String P) {
        final int stringLength = P.length();
        final int[] pi = new int[stringLength];
        pi[0] = 0;
        int matchingLength = 0;
        for (int i = 1; i < stringLength; i++) {
            while (matchingLength > 0 && P.charAt(i) != P.charAt(matchingLength)) {
                matchingLength = pi[matchingLength - 1];
            }

            if (P.charAt(i) == P.charAt(matchingLength)) {
                matchingLength++;
            }

            pi[i] = matchingLength;

        }
        return pi;
    }
}