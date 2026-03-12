package com.thealgorithms.strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Utility class to check if two strings are isomorphic.
 *
 * <p>
 * Two strings {@code s} and {@code t} are isomorphic if the characters in {@code s}
 * can be replaced to get {@code t}, while preserving the order of characters.
 * Each character must map to exactly one character, and no two characters can map to the same character.
 * </p>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Isomorphism_(computer_science)">Isomorphic Strings</a>
 */
public final class Isomorphic {

    private Isomorphic() {
    }

    /**
     * Checks if two strings are isomorphic.
     *
     * @param sourceString the first input string
     * @param targetString the second input string
     * @return {@code true} if {@code sourceString} and {@code targetString} are isomorphic; {@code false} otherwise
     */
    public static boolean areIsomorphic(String sourceString, String targetString) {
        if (sourceString.length() != targetString.length()) {
            return false;
        }

        Map<Character, Character> map = new HashMap<>();
        Set<Character> usedCharacters = new HashSet<>();

        for (int i = 0; i < sourceString.length(); i++) {
            char sourceChar = sourceString.charAt(i);
            char targetChar = targetString.charAt(i);

            if (map.containsKey(sourceChar)) {
                if (map.get(sourceChar) != targetChar) {
                    return false;
                }
            } else {
                if (usedCharacters.contains(targetChar)) {
                    return false;
                }
                map.put(sourceChar, targetChar);
                usedCharacters.add(targetChar);
            }
        }

        return true;
    }
}
