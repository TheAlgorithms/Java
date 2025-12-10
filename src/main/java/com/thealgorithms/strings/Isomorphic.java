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
     * @param s the first input string
     * @param t the second input string
     * @return {@code true} if {@code s} and {@code t} are isomorphic; {@code false} otherwise
     */
    public static boolean areIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Character> map = new HashMap<>();
        Set<Character> usedCharacters = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            char sourceChar = s.charAt(i);
            char targetChar = t.charAt(i);

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
